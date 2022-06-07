package com.thiagoqvdo.data.analyze.services;

import com.thiagoqvdo.data.analyze.entities.Customer;
import com.thiagoqvdo.data.analyze.entities.Sale;
import com.thiagoqvdo.data.analyze.entities.Salesman;
import com.thiagoqvdo.data.analyze.exceptions.FailedToReadFlatFileException;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class ReadLines implements Runnable {
    private final GenerateReport generateReport;

    public ReadLines(GenerateReport generateReport) {
        this.generateReport = generateReport;
    }

    @Override
    public void run() {
        String line;
        try {
            while ((line = generateReport.reader.readLine()) != null) {
                //take the line and scan the data
                Scanner scannerLine = new Scanner(line)
                        .useLocale(Locale.US)
                        .useDelimiter("[ç/|]");

                switch (scannerLine.next()) {
                    case "001":
                        generateReport.dataRepository.addToSalesmanList(new Salesman.Builder().build(scannerLine));
                        break;
                    case "002":
                        generateReport.dataRepository.addToCustomerList(new Customer.Builder().build(scannerLine));
                        break;
                    case "003":
                        generateReport.dataRepository.addToSaleList(new Sale.Builder().build(scannerLine));
                        break;
                    default:
                        //ignore
                }
            }
        } catch (IOException e) {
            throw new FailedToReadFlatFileException(e);
        }
    }
}
