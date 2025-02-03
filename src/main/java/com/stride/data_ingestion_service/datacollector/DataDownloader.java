package com.stride.data_ingestion_service.datacollector;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class DataDownloader {

    private static final long FIXED_RATE = 12 * 60 * 60 * 1000; // Runs every 12 hours


    public static void runScript() {
        try {
            // Replace with the actual path to your Python script
            String[] command = {"python3", "/Users/pinaksawhney/Desktop/Project/data-ingestion-service/src/main/script/downloader.py"};
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            Process process = processBuilder.start();

            // Read output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
