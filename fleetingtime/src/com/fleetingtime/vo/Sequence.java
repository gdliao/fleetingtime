package com.fleetingtime.vo;

public class Sequence {
	private String name;
	private Integer currentValue;
	private Integer increment;
	private String nextval;

	public String getNextval() {
		return nextval;
	}

	public void setNextval(String nextval) {
		this.nextval = nextval;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}

	public Integer getIncrement() {
		return increment;
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
	}

}
