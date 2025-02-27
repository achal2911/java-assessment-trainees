package com.data.processor.data.ingestion.module;

import java.util.List;

public class CSVExtractor {
    public static void ingestionModuleMain() {

        //reading sensor data from CSV file
        List<SensorData> sensorDataList = ReadingOfDataFromCSV.extractSensorData("C:\\Users\\Achal Tikale\\IdeaProjects\\java-assessment-trainees\\src\\com\\data\\processor\\data\\ingestion\\module\\SensorData.java");
        sensorDataList.forEach(System.out::println);

        //reading threshold data from CSV file
        List<Thresholds> thresholdsData1 = ReadingOfDataFromCSV.extractThresholdsData("C:\\Users\\Achal Tikale\\IdeaProjects\\java-assessment-trainees\\src\\com\\data\\processor\\data\\ingestion\\module\\threshold.csv");
        for (Thresholds thresholdsData : thresholdsData1) {
            System.out.println(thresholdsData);
        }
    }
}
