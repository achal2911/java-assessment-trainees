package com.data.processor.data.ingestion.module;

import com.data.processor.error.handling.FileNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ReadingOfDataFromCSV {
    // Extracting sensorData from csv file
    public static List<SensorData> extractSensorData(String filePath) {
        List<SensorData> sensorDataList = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip first header line
            while ((line = br.readLine()) != null) {
                try {
                    String[] values = line.split(",");
                    if (values.length < 6) {
                        System.err.println("Skipping line due to insufficient fields: " + line);
                        continue;
                    }

                    String dateTimeString = values[0].isEmpty() ? null : values[0].length() == 10 ? values[0] + " 00:00:00" : values[0];
                    LocalDateTime date = dateTimeString == null ? null : LocalDateTime.parse(dateTimeString, dateTimeFormatter);
                    String sensorType = values[1];
                    double value = values[2].isEmpty() ? Double.NaN : Double.parseDouble(values[2]);
                    String unit = values[3];
                    double locationId = values[4].isEmpty() ? Double.NaN : Double.parseDouble(values[4]);
                    YearMonth month = YearMonth.parse(values[5]);

                    if (date != null && !Double.isNaN(value) && !Double.isNaN(locationId)) {
                        SensorData sensorData = new SensorData(date, sensorType, value, unit, locationId, month);
                        sensorDataList.add(sensorData);
                    } else {
                        System.err.println("Skipping line due to missing or invalid data: " + line);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sensorDataList;
    }

    public static List<Thresholds> extractThresholdsData(String filePath) {
        List<Thresholds> thresholdsDataList = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip first header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");


                String sensorType = values[0];
                int min_threshold = Integer.parseInt(values[1]);
                int max_threshold = Integer.parseInt(values[2]);

                Thresholds thresholdsData = new Thresholds(sensorType, min_threshold, max_threshold);
                thresholdsDataList.add(thresholdsData);
            }
        } catch (IOException e) {
            throw new FileNotFoundException("File not found");
        }
        return thresholdsDataList;


    }


}


