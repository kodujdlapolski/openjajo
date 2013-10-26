package com.kodujdlapolski.openjajo.sejmometr.voivodeship;

import com.google.gson.annotations.SerializedName;

public class DetailedData {
    private @SerializedName("dane") DetailedVoiovodeshipData[] data;

    public DetailedVoiovodeshipData[] getData() {
        return data;
    }

    public void setData(DetailedVoiovodeshipData[] data) {
        this.data = data;
    }
}
