package com.doubleia.currency.chp4;

public class MutablePoint {
	
	public MutablePoint(MutablePoint mutablePoint) {
		this.x = mutablePoint.x;
		this.y = mutablePoint.y;
	}

	public MutablePoint() {
		x = 0;
		y = 0;
	}
	
	public int x;
	
	public int y;
	
}
