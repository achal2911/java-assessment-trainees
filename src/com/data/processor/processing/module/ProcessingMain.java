package com.data.processor.processing.module;

import com.data.processor.data.ingestion.module.ReadingOfDataFromCSV;
import com.data.processor.data.ingestion.module.SensorData;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public class ProcessingMain {
    public static void processingModuleMain() {
        String sensorDatafilePath = "C:\\Users\\Achal Tikale\\IdeaProjects\\java-assessment-trainees\\src\\com\\data\\processor\\data\\ingestion\\module\\sensor_data.csv";
        String newOutputFilePath = "C:\\Users\\Achal Tikale\\IdeaProjects\\java-assessment-trainees\\src\\com\\data\\processor\\reporting\\module\\monthly_statics_data.csv";


        List<SensorData> sensorDataList = ReadingOfDataFromCSV.extractSensorData(sensorDatafilePath);
        Map<String, Map<YearMonth, MonthlyStats>> monthlyStats = SensorStatistics.calculateMonthlyStats(sensorDataList);
        SensorStatistics.writeMonthlyStats(newOutputFilePath, monthlyStats);
    }
}

