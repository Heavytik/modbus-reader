package com.gambit.modbusdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ConverterTest {

    @Autowired ConverterService converter;

    @Test
    public void convertPositive() {
        String b1 = this.converter.convert("01");
        assertEquals("01", b1 );
    }

    @Test
    public void convertNegative() {
        String b1 = this.converter.convert("11");
        assertEquals("-01", b1 );
        String b2 = this.converter.convert("1");
        assertEquals("-1", b2);
    }

}