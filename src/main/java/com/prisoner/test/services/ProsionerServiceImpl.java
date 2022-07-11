package com.prisoner.test.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.prisoner.test.entities.Person;
import com.prisoner.test.entities.Stats;
import com.prisoner.test.exceptions.DnaExcepcion;
import com.prisoner.test.exceptions.InvalidDnaSequence;
import com.prisoner.test.exceptions.InvalidMatrixSize;
import com.prisoner.test.repositories.PersonRepository;

@Service
public class ProsionerServiceImpl implements PrisionerService {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean isPrisioner(String[] dna) throws DnaExcepcion {
        int prisionerDna = 0;
        try {
            validateMatrix(dna);
            for (int i = 0; i < dna.length; i++) {
                for (int j = 0; j < dna[i].length(); j++) {
                    //Forma Horizontal
                    if (j < dna[i].length() - 3) {
                        if (areEqualDna(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2),
                                dna[i].charAt(j + 3))) {
                            ++prisionerDna;
                        }
                    }
                    //Forma Vertical
                    if (i < dna[i].length() - 3) {
                        if (areEqualDna(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j),
                                dna[i + 3].charAt(j))) {
                            ++prisionerDna;
                        }
                    }
                    //Forma Oblicua, Derecha a Izquierda & Arriba Abajo
                    if (j < dna[i].length() - 3 && i < dna[i].length() - 3) {
                        if (areEqualDna(dna[i].charAt(j), dna[i + 1].charAt(j + 1),
                                dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
                            ++prisionerDna;
                        }
                    }
                    //Forma Oblicua, Izquierda a Derecha & Abajo Arriba
                    if (dna[i].length() > 3 && j < dna[i].length() - 3 && i > 2) {
                        if (areEqualDna(dna[i].charAt(j), dna[i - 1].charAt(j + 1),
                                dna[i - 2].charAt(j + 2), dna[i - 3].charAt(j + 3))) {
                            ++prisionerDna;
                        }
                    }

                    if (prisionerDna >= 2) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    @Override
    public Stats getStats() {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("dna", new AttributeValue().withS("DNA"));
        GetItemResult result = amazonDynamoDB.getItem("DnaCounters", key);
        Stats stats;
        if (result.getItem() != null) {
            AttributeValue count_successful_escape = result.getItem().get("count_successful_escape");
            AttributeValue count_unsuccessful_escape = result.getItem().get("count_unsuccessful_escape");
            stats = new Stats(Integer.parseInt(count_successful_escape.getN()),
                    Integer.parseInt(count_successful_escape.getN()));
        } else {
            stats = new Stats(0, 0);
        }
        return stats;
    }

    @Override
    public boolean getProsionerStatus(String[] dna) throws DnaExcepcion {
        String dnaParser = Arrays.toString(dna);
        Optional<Person> person = personRepository.findById(dnaParser);
        Person per = person.get();

        boolean prisionerCondition = true;
        if (per != null) {
            prisionerCondition = per.getIsMutant();
        } else {
            prisionerCondition = this.isPrisioner(dna);
            this.saveDna(dnaParser, prisionerCondition);
        }
        return prisionerCondition;
    }

    @Override
    public void saveDna(String dnaParser, boolean prisionerCondition) {
        String counter;
        personRepository.save(new Person(dnaParser, prisionerCondition));
        if (prisionerCondition) {
            counter = "count_successful_escape";
        } else {
            counter = "count_unsuccessful_escape";
        }
        this.incrementCounter(counter);

    }

    @Override
    public void incrementCounter(String counter) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("dna", new AttributeValue().withS("DNA"));

        UpdateItemRequest updateItemRequest = new UpdateItemRequest().withTableName("DnaCounters").withKey(key)
                .addAttributeUpdatesEntry(counter, new AttributeValueUpdate().
                        withValue(new AttributeValue().withN("1"))
                        .withAction(AttributeAction.ADD));

        amazonDynamoDB.updateItem(updateItemRequest);

    }

    @Override
    public boolean validateMatrix(String[] dna) throws DnaExcepcion {
        int dnaSize = dna.length;
        String validString = "[|SP v<]+";
        for (String dnaSequence : dna) {
            if (dnaSequence.length() != dnaSize) {
                throw new InvalidMatrixSize();
            }

            if (!dnaSequence.matches(validString)) {
                throw new InvalidDnaSequence();
            }
        }
        return false;
    }
    @Override
    public boolean areEqualDna(char a, char b, char c, char d) {
        return a == b & b == c & c == d;
    }

}
