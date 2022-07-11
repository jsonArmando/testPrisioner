package com.prisoner.test.services;

import com.prisoner.test.entities.Stats;
import com.prisoner.test.exceptions.DnaExcepcion;

public interface PrisionerService {
    boolean isPrisioner(String[] dna) throws DnaExcepcion;
    Stats getStats();
    boolean getProsionerStatus(String[] dna) throws DnaExcepcion;
    void saveDna(String dnaParser, boolean prisionerCondition);
    void incrementCounter(String counter);
    boolean validateMatrix(String[] dna) throws DnaExcepcion;
    boolean areEqualDna(char a, char b, char c, char d);
}
