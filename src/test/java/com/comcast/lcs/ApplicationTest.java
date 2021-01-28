package com.comcast.lcs;

import com.comcast.lcs.domain.LCValue;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApplicationTest {

    LCSService lcsService = new LCSService();

    List<LCValue> testList = new ArrayList<>();

    @After
    public void afterTests()
    {
        testList.clear();
    }

    /**
     * ensure that single substring is found
     */
    @Test
    public void testOne()
    {
        testList.add(new LCValue("stringone"));
        testList.add(new LCValue("stringtwo"));
        testList.add(new LCValue("strinthree"));

        List<LCValue> lcsList = lcsService.findLongest(testList);
        assertEquals(lcsList.size(), 1);
        assertTrue(lcsList.get(0).getValue().equals("strin"));
    }

    /**
     * ensure that common length of 1 finds all common chars
     */
    @Test
    public void testTwo()
    {
        testList.add(new LCValue("asdfg"));
        testList.add(new LCValue("gfdsa"));

        List<LCValue> lcsList = lcsService.findLongest(testList);
        assertEquals(lcsList.size(), 5);
        assertTrue(lcsList.get(0).getValue().equals("a"));
        assertTrue(lcsList.get(4).getValue().equals("s"));
    }

    /**
     * test to ensure that equal length max common substrings are found
     */
    @Test
    public void testThree()
    {
        testList.add(new LCValue("fotwelvurstrinfourt"));
        testList.add(new LCValue("onetwelvstringonefourt"));
        testList.add(new LCValue("twotwelvstringtwofourt"));
        testList.add(new LCValue("threestringtwelvthreefourt"));
        List<LCValue> lcsList = lcsService.findLongest(testList);
        assertEquals(lcsList.size(), 3);
        assertTrue(lcsList.get(0).getValue().equals("fourt"));
        assertTrue(lcsList.get(1).getValue().equals("strin"));
        assertTrue(lcsList.get(2).getValue().equals("twelv"));
    }


    /**
     * test to ensure that single chars not common substrings are not found
     */
    @Test
    public void testFour()
    {
        testList.add(new LCValue("asdfg"));
        testList.add(new LCValue("gfhdsa"));

        List<LCValue> lcsList = lcsService.findLongest(testList);
        assertEquals(lcsList.size(), 5);
        assertTrue(lcsList.get(0).getValue().equals("a"));
        assertTrue(lcsList.get(4).getValue().equals("s"));
    }
}
