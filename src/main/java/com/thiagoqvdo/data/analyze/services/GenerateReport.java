package com.thiagoqvdo.data.analyze.services;

import com.thiagoqvdo.data.analyze.entities.Report;
import com.thiagoqvdo.data.analyze.entities.Sale;
import com.thiagoqvdo.data.analyze.entities.Salesman;
import com.thiagoqvdo.data.analyze.exceptions.FailedToCreateReportException;
import com.thiagoqvdo.data.analyze.exceptions.FailedToReadFlatFileException;
import com.thiagoqvdo.data.analyze.repositories.DataRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class GenerateReport implements Runnable {

    private static final Integer NUMBER_THREADS_TO_READ_LINES = 4;

    private final File file;
    private final Path outputDirectory;
    private Report report;

    protected BufferedReader reader;
    protected final DataRepository dataRepository;

    public GenerateReport(File file, Path outputDirectory) {
        this.file = file;
        this.outputDirectory = outputDirectory;

        this.report = new Report();
        this.dataRepository = new DataRepository();
    }

    @Override
    public void run() {
        readFile();
        generateReportFile();
    }

    private void readFile() {
        try {
            InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file));
            reader = new BufferedReader(inputStream);
            ExecutorService executor = Executors.newFixedThreadPool(NUMBER_THREADS_TO_READ_LINES);

            //opens the specified number of threads to read the lines from the file
            IntStream.rangeClosed(1, NUMBER_THREADS_TO_READ_LINES)
                    .forEach(i -> executor.execute(new ReadLines(this)));

            executor.shutdown();
            //waits until threads finish reading the file or until it times out
            if (executor.awaitTermination(10, TimeUnit.SECONDS) && executor.isTerminated()) buildReport();
            else throw new FailedToReadFlatFileException("Failed to read file. Thread tasks not completed.");

        } catch (IOException | InterruptedException e) {
            throw new FailedToReadFlatFileException("Failed to read file.", e);
        }
    }

    private void buildReport() {
        //load the sales data of the salesmen
        analyzeSalesmanData();

        int amountOfClients = dataRepository.getCustomerList().size();
        int amountOfSalesman = dataRepository.getSalesmanList().size();
        int idOfMostExpansiveSale = dataRepository.getSaleList().stream()
                .max(Comparator.comparing(Sale::getTotalPrice))
                .orElseThrow(FailedToReadFlatFileException::new)
                .getSaleId();
        Salesman worstSalesman = dataRepository.getSalesmanList().stream()
                .min(Comparator.comparing(Salesman::getSalesMean))
                .orElseThrow(FailedToReadFlatFileException::new);

        this.report = new Report.Builder()
                .setAmountOfClientsInInputFile(amountOfClients)
                .setAmountOfSalesmanInInputFile(amountOfSalesman)
                .setIdOfTheMostExpensiveSale(idOfMostExpansiveSale)
                .setWorstSalesman(worstSalesman)
                .build();
    }

    private void generateReportFile() {
        try {
            boolean outputDirectoryExist = Files.exists(outputDirectory);
            if (!outputDirectoryExist) outputDirectory.toFile().mkdirs();

            String doneFileName = file.getName().replace(".dat", ".done.dat");
            List<String> lines = List.of(report.toString().split("\n"));

            //create the report in the output directory
            Path reportFile = Path.of(outputDirectory + "/" + doneFileName);
            Files.write(reportFile, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FailedToCreateReportException("Failed to write the file.", e);
        }
    }

    private void analyzeSalesmanData() {
        //set the sales value for all salesmen
        dataRepository.getSalesmanList().forEach(salesman -> {
            salesman.setAmountOfSales(Math.toIntExact(dataRepository.getSaleList()
                            .stream()
                            .filter(sale -> sale.getSalesmanName().equalsIgnoreCase(salesman.getName()))
                            .peek(sale -> salesman.setTotalAmountSold(salesman.getTotalAmountSold() + sale.getTotalPrice()))
                            .count()));

            //calculate the sales mean after updating amount of sales and total amount sold
            salesman.calculateSalesMean();
        });
    }
}
