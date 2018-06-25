package com.example.androiddetails;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        try {
            SecureRandom.getInstance("");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}