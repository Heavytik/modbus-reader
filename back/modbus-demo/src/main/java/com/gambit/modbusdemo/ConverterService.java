package com.gambit.modbusdemo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Scanner;

public class ConverterService {

    private String date;
    private ArrayList<String> textLines = new ArrayList<>();

    public ConverterService(FileText inputFile) {
        Scanner scanner = new Scanner(inputFile.getText());
        if(scanner.hasNextLine())
            this.date = scanner.nextLine();
        while(scanner.hasNextLine())
            textLines.add(scanner.nextLine());
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getEntities() {
        ArrayList<String> registerEntities = new ArrayList<>();

        registerEntities.add(createRegisterEntity(0, 2, "Flow Rate", MyNumbers.FLOAT));
        registerEntities.add(createRegisterEntity(2, 2, "Energy Flow Rate", MyNumbers.FLOAT));
        registerEntities.add(createRegisterEntity(4, 2, "Velocity", MyNumbers.FLOAT));
        registerEntities.add(createRegisterEntity(32, 2, "Temperature #1/inlet", MyNumbers.FLOAT));

        //registerEntities.add(createRegisterEntity(20, 2, "Negative Energy", NumberType.LONG));
        //registerEntities.add(createRegisterEntity(8, 2, "Positive accumulator", NumberType.LONG));
        //registerEntities.add(createRegisterEntity(12, 2, "Negative accumulator", NumberType.LONG));

        return registerEntities;
    }

    private String createRegisterEntity(int start, int number, String variableName, MyNumbers format) {
        String entityString = variableName + ":";
        ArrayList<Integer> numbers = new ArrayList<>();

        for(int i=0; i < number; i++) {
            String n = this.textLines.get(start + i).split(":")[1];
            numbers.add(Integer.parseInt(n));
        }

        // convert numbers to different format
        Numerical num = new Numerical(numbers, format);

        return entityString + num.parseNumberToString();
    }


}
