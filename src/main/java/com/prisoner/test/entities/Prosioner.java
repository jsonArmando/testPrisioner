package com.prisoner.test.entities;

import java.util.List;

public class Prosioner {

    public Prosioner(List<String> dna) {
		super();
		this.dna = dna;
	}

	public Prosioner() {
	}

	private List<String> dna;

	public List<String> getDna() {
		return dna;
	}


}
