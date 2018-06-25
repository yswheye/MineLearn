package com.example.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);

        byte b1 = new Byte("02");
        byte b2 = new Byte("36");
        System.out.println(bytesToHexString(b1));
        System.out.println(bytesToHexString(b2));
        String w = bytesToHexString(b1) + bytesToHexString(b2);

        System.out.println(hexStringToFloat(w));
    }

    private String bytesToHexString(byte b) {
        StringBuilder stringBuilder = new StringBuilder();
        String toHexString = Integer.toHexString(b & 255);
        if (toHexString.length() == 1) {
            stringBuilder.append('0');
        }
        stringBuilder.append(toHexString);
        return stringBuilder.toString();
    }

    public static float hexStringToFloat(String str) {
        float f = 0.0f;
        if (str.equals(new String("FFFFFF").substring(0, str.length()))) {
            return f;
        }
        try {
            return (float) hexStringToInt(str);
        } catch (Exception e) {
            return f;
        }
    }

    public static int hexStringToInt(String str) {
        int i = 0;
        try {
            i = Integer.parseInt(str, 16);
        } catch (Exception e) {
        }
        return i;
    }
}