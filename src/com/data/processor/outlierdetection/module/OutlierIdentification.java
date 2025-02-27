package com.data.processor.outlierdetection.module;

import com.data.processor.data.ingestion.module.ReadingOfDataFromCSV;
import com.data.processor.data.ingestion.module.SensorData;
import com.data.processor.data.ingestion.module.Thresholds;
import com.data.processor.error.handling.FileNotFoundException;
import com.data.processor.error.handling.InvalidDataFormatException;
import com.data.processor.error.handling.ProcessingException;
import com.data.processor.error.handling.ThresholdsNotDefinedException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutlierIdentification {

    public static void outlierModuleMain() {

        try {
            String senssorDataFilePath = "C:\\Users\\Achal Tikale\\IdeaProjects\\java-assessment-trainees\\src\\com\\data\\processor\\data\\ingestion\\module\\sensor_data.csv";
            String thresholdFilePath = "C:\\Users\\Achal Tikale\\IdeaProjects\\java-assessment-trainees\\src\\com\\data\\processor\\data\\ingestion\\module\\threshold.csv";
            String outlierOutputFilePath = "C:\\Users\\Achal Tikale\\IdeaProjects\\java-assessment-trainees\\src\\com\\data\\processor\\reporting\\module\\outliers.csv";

            List<SensorData> sensorDataList = ReadingOfDataFromCSV.extractSensorData(senssorDataFilePath);
            List<Thresholds> thresholdsList = ReadingOfDataFromCSV.extractThresholdsData(thresholdFilePath);
            Map<String, Thresholds> thresholdsMap = thresholdsList.stream()
                    .collect(Collectors.toMap(t -> t.getSensor_type(), t -> t));

            List<SensorData> outliers = OutlierDetection.identifyOutliers(sensorDataList, thresholdsMap);
            OutlierDetection.writeOutliers(outlierOutputFilePath, outliers, thresholdsMap);
        } catch (FileNotFoundException e) {
            System.err.println("ERR001: " + e.getMessage());
        } catch (InvalidDataFormatException e) {
            System.err.println("ERR002: " + e.getMessage());
        } catch (ProcessingException e) {
            System.err.println("ERR003: " + e.getMessage());
        } catch (ThresholdsNotDefinedException e) {
            System.err.println("ERR004: " + e.getMessage());
        }
    }
}

