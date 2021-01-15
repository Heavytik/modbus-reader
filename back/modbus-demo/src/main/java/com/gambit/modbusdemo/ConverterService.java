package com.gambit.modbusdemo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Scanner;

public class ConverterService {

    private String date;
    private ArrayList<String> textLines = new ArrayList<>();

    /**
     * Cretes service to parse and convert data from
     * string. Data entities must be separated with linebreak.
     *
     * Parameter is FileText where is one
     *
     * @param inputFile
     */
    public ConverterService(FileText inputFile) {
        Scanner scanner = new Scanner(inputFile.getText());
        if(scanner.hasNextLine())
            this.date = scanner.nextLine();
        while(scanner.hasNextLine())
            textLines.add(scanner.nextLine());
    }

    /**
     * Measurement date and time.
     *
     * @return Measurement date, format YYYY-MM-DD HH:MM
     */
    public String getDate() {
        return date;
    }

    /**
     * Converts raw data to human readable format
     *
     * @return All measurement results in one string
     */
    public ArrayList<String> getEntities() {
        ArrayList<String> registerEntities = new ArrayList<>();

        registerEntities.add(createRegisterEntity(0, 2, "Flow Rate", MyNumbers.FLOAT, "m3/h"));
        registerEntities.add(createRegisterEntity(2, 2, "Energy Flow Rate", MyNumbers.FLOAT, "GJ/h"));
        registerEntities.add(createRegisterEntity(4, 2, "Velocity", MyNumbers.FLOAT, "m/s"));
        registerEntities.add(createRegisterEntity(6, 2, "Fluid sound speed", MyNumbers.FLOAT, "m/s"));
        registerEntities.add(createRegisterEntity(8, 2, "Positive accumulator", MyNumbers.LONG, "(?)"));
        registerEntities.add(createRegisterEntity(10, 2, "Positive decimal fraction", MyNumbers.FLOAT, ""));
        registerEntities.add(createRegisterEntity(12, 2, "Negative accumulator", MyNumbers.LONG, ""));

        registerEntities.add(createRegisterEntity(20, 2, "Negative Energy", MyNumbers.LONG, ""));

        registerEntities.add(createRegisterEntity(32, 2, "Temperature #1/inlet", MyNumbers.FLOAT, "C"));

        return registerEntities;
    }

    private String createRegisterEntity(int start, int number, String variableName, MyNumbers format, String unit) {

        ArrayList<Integer> numbers = new ArrayList<>();

        for(int i=0; i < number; i++) {
            String n = this.textLines.get(start + i).split(":")[1];
            numbers.add(Integer.parseInt(n));
        }

        // convert numbers to different format
        Numerical num = new Numerical(numbers, format);

        return variableName + ":" + num.parseNumberToString() + ":" + unit;
    }


}
