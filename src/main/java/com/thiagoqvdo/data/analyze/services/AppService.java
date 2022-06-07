package com.thiagoqvdo.data.analyze.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class AppService {

    private final Path HOME_DIRECTORY = Path.of(System.getProperty("user.home"));
    private final Path INPUT_DIRECTORY = Path.of(HOME_DIRECTORY + "/data/in");
    private final Path OUTPUT_DIRECTORY = Path.of(HOME_DIRECTORY + "/data/out");

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private void readFiles() {
        boolean inputDirectoryExist = INPUT_DIRECTORY.toFile().exists();
        if (!inputDirectoryExist) INPUT_DIRECTORY.toFile().mkdirs();

        //reads .dat files that already exist in the repository
        List<File> filesInInputDirectory = Arrays.stream(INPUT_DIRECTORY.toFile().listFiles())
                .filter(file -> file.getName().endsWith(".dat"))
                .collect(Collectors.toList());
        filesInInputDirectory.forEach(file -> executor.execute(new GenerateReport(file, OUTPUT_DIRECTORY)));
    }

    public void watchInputDirectory() {
        readFiles();

        WatchService watcher;
        WatchKey watchKey;

        while (true) {
            try {
                watcher = FileSystems.getDefault().newWatchService();
                INPUT_DIRECTORY.register(watcher, ENTRY_CREATE);
                //wait for a file to be created
                watchKey = watcher.take();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }

            watchKey.pollEvents().forEach(event -> {
                WatchEvent<Path> filename = (WatchEvent<Path>) event;
                Path path = filename.context();

                File file = INPUT_DIRECTORY.resolve(path).toFile();
                boolean fileFormatIsValid = file.getName().endsWith(".dat");

                //open a thread to read the file
                if (fileFormatIsValid) executor.execute(new GenerateReport(file, OUTPUT_DIRECTORY));
            });
        }
    }
}
