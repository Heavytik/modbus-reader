package com.gambit.modbusdemo;

import java.util.ArrayList;

public class Numerical {
    private ArrayList<Integer> initialNumbers = new ArrayList<>();
    private MyNumbers numberType;

    public Numerical(ArrayList<Integer> numbers, MyNumbers type) {
        this.initialNumbers = numbers;
        this.numberType = type;
    }

    public String parseNumberToString() {

        String str = switch (this.numberType) {
            case LONG -> Long.toString(convertLong());
            case FLOAT -> Float.toString(convertFloat());
            case INTEGER_LOW -> "";
        };

        return str;
    }

    private Long convertLong() {
        int x = initialNumbers.get(0);
        int y = initialNumbers.get(1);
        return Long.parseLong(convertToBinString(x, y),2);
    }

    Float convertFloat() {
        return Float.intBitsToFloat(convertLong().intValue());
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
