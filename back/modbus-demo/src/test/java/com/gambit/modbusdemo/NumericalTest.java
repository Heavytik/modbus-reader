package com.gambit.modbusdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NumericalTest {

    @Test
    void testLong() {
        Numerical num = new Numerical(new ArrayList<Integer>(Arrays.asList(65480, 65535)), MyNumbers.LONG );
        assertEquals("-56", num.parseNumberToString());
    }

    @Test
    void testFloat() {
        Numerical num = new Numerical(new ArrayList<Integer>(Arrays.asList(15568, 16611)), MyNumbers.FLOAT );
        assertEquals("7.1011734", num.parseNumberToString());
    }
}
