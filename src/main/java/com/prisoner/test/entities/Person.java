package com.prisoner.test.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "PersonsDna")
public class Person {

	public Person() {
	}

	public Person(String dna, boolean isMutant) {
		super();
		this.dna = dna;
		this.isMutant = isMutant;
	}

	private String dna;
	private boolean isMutant;

	@DynamoDBHashKey(attributeName = "Dna")
	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	@DynamoDBAttribute
	public boolean getIsMutant() {
		return isMutant;
	}

	public void setIsMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
}
