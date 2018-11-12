package com.refunits.enumeration;

public enum Limit {

    TEN("10"), TWENTY("20"), FIFTY("50");

    private String number;

    Limit(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
