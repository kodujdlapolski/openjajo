package com.kodujdlapolski.openjajo.sejmometr.gmina;

import com.google.gson.annotations.SerializedName;

public class DetailedData {
    private @SerializedName("dane") DetailedGminaData[] data;

    public DetailedGminaData[] getData() {
        return data;
    }

    public void setData(DetailedGminaData[] data) {
        this.data = data;
    }
}
