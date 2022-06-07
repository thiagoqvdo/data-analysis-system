package com.thiagoqvdo.data.analyze;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import com.thiagoqvdo.data.analyze.services.GenerateReport;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GenerateReportTest {
    private Path input1, input2;
    private File file1, file2;
    private Mock mock;

    @TempDir
    private Path inputDir;

    @TempDir
    private Path outputDir;

    @BeforeEach
    public void setUp() {
        input1 = inputDir.resolve("test1.dat");
        input2 = inputDir.resolve("test2.dat");
        mock = new Mock();

        try {
            Files.write(input1, mock.lines1, StandardCharsets.UTF_8);
            Files.write(input2, mock.lines2, StandardCharsets.UTF_8);

            file1 = input1.toFile();
            file2 = input2.toFile();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldGenerateReport() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(new GenerateReport(file1, outputDir));
        executor.execute(new GenerateReport(file2, outputDir));
        executor.shutdown();

        String doneFileName1 = file1.getName()
                .replace(".dat", ".done.dat");
        String doneFileName2 = file2.getName()
                .replace(".dat", ".done.dat");

        Assertions.assertTrue(executor.awaitTermination(10, TimeUnit.SECONDS) && executor.isTerminated());
        Assertions.assertAll(
                () -> Assertions.assertTrue(Files.exists(outputDir.resolve(doneFileName1))),
                () -> Assertions.assertLinesMatch(mock.expectedLines1, Files.readAllLines(outputDir
                        .resolve(doneFileName1)))
        );
        Assertions.assertAll(
                () -> Assertions.assertTrue(Files.exists(outputDir.resolve(doneFileName2))),
                () -> Assertions.assertLinesMatch(mock.expectedLines2, Files.readAllLines(outputDir
                        .resolve(doneFileName2)))
        );
    }
}
