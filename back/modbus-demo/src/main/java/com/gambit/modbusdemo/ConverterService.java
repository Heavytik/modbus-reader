package com.gambit.modbusdemo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Scanner;

public class ConverterService {

    private String date;
    private ArrayList<String> textLines = new ArrayList<>();

    enum NumberType {
        REAL,
        LONG,
        FLOAT
    }

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

        registerEntities.add(createRegisterEntity(0, 2, "Flow Rate", NumberType.FLOAT));
        registerEntities.add(createRegisterEntity(2, 2, "Energy Flow Rate", NumberType.FLOAT));
        registerEntities.add(createRegisterEntity(4, 2, "Velocity", NumberType.FLOAT));
        registerEntities.add(createRegisterEntity(32, 2, "Temperature #1/inlet", NumberType.FLOAT));

        //registerEntities.add(createRegisterEntity(20, 2, "Negative Energy", NumberType.LONG));
        //registerEntities.add(createRegisterEntity(8, 2, "Positive accumulator", NumberType.LONG));
        //registerEntities.add(createRegisterEntity(12, 2, "Negative accumulator", NumberType.LONG));

        return registerEntities;
    }

    private String createRegisterEntity(int start, int number, String variableName, NumberType format) {
        String entityString = variableName + ":";
        switch (number) {
            case 1:
                String s11 = this.textLines.get(start).split(":")[1];
                int i11 = Integer.parseInt(s11);
                entityString = entityString + s11;
                break;
            case 2:
                String s21 = this.textLines.get(start).split(":")[1];
                int i21 = Integer.parseInt(s21, 10);
                String s22 = this.textLines.get(start + 1).split(":")[1];
                int i22 = Integer.parseInt(s22, 10);
                //String value = Long.toString(convertLong(i21, i22));
                String value = Float.toString(convertFloat(i21, i22));
                entityString = entityString + value;
                break;
            case 3:
                String s31 = this.textLines.get(start).split(":")[1];
                String s32 = this.textLines.get(start + 1).split(":")[1];
                String s33 = this.textLines.get(start + 2).split(":")[1];
                entityString = entityString + s31 + s32 + s33;
                break;
            default:
        }
        return entityString;
    }

    private Long convertLong(int x, int y) {
        return Long.parseLong(convertToBinString(x, y),2);
    }

    private Float convertFloat(int x, int y) {
        return Float.intBitsToFloat(convertLong(x, y).intValue());
    }

    private String convertToBinString(int lowBit, int highBit) {
        String lb = addLeaderZeros(Integer.toBinaryString(lowBit), 16);
        String hb = addLeaderZeros(Integer.toBinaryString(highBit), 16);

        return convertUSBtoSB(hb + lb);
    }

    private String addLeaderZeros(String x, int bitCount) {
        String str = x;
        int i = (bitCount - x.length());
        for (int k = 0; k < i; k++) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * Converts binary number format from Integer.toBinaryString(...) output
     * added with leading zeros (i.e. binary number 1 = 0000000000000000000000000001)
     * to match required format for Integer.parseInt(...) parameter.
     *
     * Examples:
     *        convertUSBtoSB("0101") returns 0101
     *        convertUSBtoSB("01") returns 01
     *        concertUSBtoSB("1") returns -1
     *
     * @param b two's complement binary number as string (unsigned integer in base 2)
     * @return signed binary number as string, minus sign indicates negative value
     */

    private String convertUSBtoSB(String b) {
        if ( b.charAt(0) == '0' ) {
            // If positive binary (start by 0), no need to convert
            return b;
        } else {
            // Convert negative binary
            StringBuffer sb = new StringBuffer();
            sb.append(b);

            int n = sb.length();
            int i;

            for (i = n-1 ; i >= 0 ; i--)
                if (sb.charAt(i) == '1')
                    break;
            for (int k = i-1; k >= 0; k--) {
                if (sb.charAt(k) == '1')
                    sb.replace(k, k+1, "0");
                else
                    sb.replace(k, k+1, "1");
            }

            // in this case, negative number needs leading - sign
            return "-" + sb.toString();
        }
    }
}
