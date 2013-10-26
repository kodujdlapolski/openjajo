package com.kodujdlapolski.openjajo.adapters;

public class AdapterItem {

	private String name;
	private String value;

	public AdapterItem() {
	}

	public AdapterItem(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

}
