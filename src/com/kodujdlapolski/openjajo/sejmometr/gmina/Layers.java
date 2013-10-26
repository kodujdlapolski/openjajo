package com.kodujdlapolski.openjajo.sejmometr.gmina;

import com.google.gson.annotations.SerializedName;

public class Layers {
    private @SerializedName("dane") DetailedData detailedData;

    public DetailedData getDetailedData() {
        return detailedData;
    }

    public void setDetailedData(DetailedData detailedData) {
        this.detailedData = detailedData;
    }
}
