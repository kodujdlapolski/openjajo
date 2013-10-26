package com.kodujdlapolski.openjajo.sejmometr.voivodeship;

import com.google.gson.annotations.SerializedName;

public class Layers {
    private @SerializedName("dane_szczegolowe") DetailedData detailedData;

    public DetailedData getDetailedData() {
        return detailedData;
    }

    public void setDetailedData(DetailedData detailedData) {
        this.detailedData = detailedData;
    }
}
