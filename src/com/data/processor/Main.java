package com.data.processor;

import com.data.processor.data.ingestion.module.CSVExtractor;
import com.data.processor.outlierdetection.module.OutlierIdentification;
import com.data.processor.processing.module.ProcessingMain;

public class Main {
    public static void main(String[] args) {
        // CSVExtractor.ingestionModuleMain();
        ProcessingMain.processingModuleMain();
        OutlierIdentification.outlierModuleMain();
    }
}
