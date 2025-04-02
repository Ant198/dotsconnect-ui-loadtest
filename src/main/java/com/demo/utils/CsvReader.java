package com.demo.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static List<String[]> readCSV(String path) throws CsvException {
        List<String[]> data = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new FileReader(path))) {
            data = csvReader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}