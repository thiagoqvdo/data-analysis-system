package com.thiagoqvdo.data.analyze;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import com.thiagoqvdo.data.analyze.services.AppService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppServiceTest {

    private AppService service;
    private Mock mock;

    @TempDir
    private Path homeDir;

    private Path inputDir;
    private Path outputDir;

    @BeforeEach
    public void setUp() {
        System.setProperty("user.home", homeDir.toString());
        service = new AppService();
        mock = new Mock();
        inputDir = Path.of(homeDir + "/data/in");
        outputDir = Path.of(homeDir + "/data/out");

        inputDir.toFile().mkdirs();
        outputDir.toFile().mkdirs();
    }

    @Test
    public void shouldNotReadFileIfTypeIsInvalid() throws IOException, InterruptedException {
        File file = new File(inputDir.resolve("test1.txt").toString());
        Files.write(file.toPath(), mock.lines1);
        String doneFileName = file.getName()
                .replace(".dat", ".done.dat");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> service.watchInputDirectory());
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        Assertions.assertFalse(Files.exists(outputDir.resolve(doneFileName)));
    }
}
