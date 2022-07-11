package com.prisoner.test;

import com.prisoner.test.entities.Stats;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StatsTest {
    @Test
    public void testStatsWithNoData() {
        Stats stats = new Stats(0, 0);
        assertTrue(stats.getCount_successful_escape() == 0.0);
        assertTrue(stats.getCount_unsuccessful_escape() == 0.0);
        assertTrue(stats.getRatio() == 0.0);
    }

    @Test
    public void testStatsWithNoDataHuman() {
        Stats stats = new Stats(1, 0);
        assertTrue(stats.getCount_unsuccessful_escape() == 0.0);
        assertTrue(stats.getCount_successful_escape() == 1.0);
        assertTrue(stats.getRatio() == 1.0);
    }

    @Test
    public void testStatsWithData() {
        Stats stats = new Stats(40, 100);
        assertTrue(stats.getCount_unsuccessful_escape() == 100.0);
        assertTrue(stats.getCount_successful_escape() == 40.0);
        assertTrue(stats.getRatio() == 0.4);
    }
}
