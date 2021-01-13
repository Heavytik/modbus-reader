package com.gambit.modbusdemo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterService {
    private static List<String> testData = new ArrayList<>();

    static {
        testData.add("First");
        testData.add("Second");
        testData.add("Third");
    }

    public List<String> allStrings() {
        return testData;
    }
}
