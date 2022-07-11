package com.prisoner.test.entities;


public class Stats {
    public Stats(double count_successful_escape, double count_unsuccessful_escape) {
		super();
		this.count_successful_escape = count_successful_escape;
		this.count_unsuccessful_escape = count_unsuccessful_escape;

		if (count_successful_escape == 0 && count_unsuccessful_escape == 0) {
			this.ratio = 0.0;
		} else {
			if (count_unsuccessful_escape == 0) {
				this.ratio = 1;
			} else {
				this.ratio = Math.round((count_successful_escape / count_unsuccessful_escape) * 10) / 10.0;
			}
		}

	}

	private double count_successful_escape;
	private double count_unsuccessful_escape;
	private double ratio;

	public double getCount_successful_escape() {
		return count_successful_escape;
	}

	public void setCount_successful_escape(double count_successful_escape) {
		this.count_successful_escape = count_successful_escape;
	}

	public double getCount_unsuccessful_escape() {
		return count_unsuccessful_escape;
	}

	public void setCount_unsuccessful_escape(double count_unsuccessful_escape) {
		this.count_unsuccessful_escape = count_unsuccessful_escape;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
