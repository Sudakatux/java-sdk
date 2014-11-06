package com.xapo.model;

public enum BitCoinUnit {
	BTC("BTC"),
	SAT("SAT");
	
	private final String name;       

    private BitCoinUnit(String s) {
    
    	name = s;
    
    }
    
    @Override
    public String toString() {

    	return name;
    
    }
	

}
