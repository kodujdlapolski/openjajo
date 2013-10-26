package com.kodujdlapolski.openjajo.sejmometr.voivodeship;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class DetailedVoiovodeshipData {
    private @SerializedName("rocznik") Integer year;
    private @SerializedName("v") BigDecimal value;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
    @Override
    public String toString(){
    	return "{"+this.year+","+this.value+"}";
    }
}
