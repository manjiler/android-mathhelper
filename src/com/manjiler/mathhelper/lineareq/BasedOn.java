package com.manjiler.mathhelper.lineareq;

public enum BasedOn {
	X("X"),
	Y("Y");
	
	private String value;
	
	private BasedOn(String value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value;
	}
}
