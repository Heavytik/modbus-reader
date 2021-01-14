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

    public String convert(String bn) { return convertUSBtoSB(bn); }

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
