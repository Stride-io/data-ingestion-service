package com.stride.data_ingestion_service.datacollector;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static common.Constants.PYTHON3;

@Component
public class DataDownloader implements Runnable {
    private static final String SCRIPT_PATH = "/app/script/downloader.py";

    @Override
    public void run() {
        try {
            // Replace with the actual path to your Python script
            String[] command = { PYTHON3, SCRIPT_PATH };
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);

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
