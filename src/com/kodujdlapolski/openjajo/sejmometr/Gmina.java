package com.kodujdlapolski.openjajo.sejmometr;

import com.google.gson.annotations.SerializedName;

public class Gmina {

    public Integer getGminaId() {
        return gminaId;
    }

    public void setGminaId(Integer gminaId) {
        this.gminaId = gminaId;
    }

    public String getGminaNazwa() {
        return gminaNazwa;
    }

    public void setGminaNazwa(String gminaNazwa) {
        this.gminaNazwa = gminaNazwa;
    }

//    public String getGminaTyp() {
//        return gminaTyp;
//    }
//
//    public void setGminaTyp(String gminaTyp) {
//        this.gminaTyp = gminaTyp;
//    }
//
    public Integer getPowiatId() {
        return powiatId;
    }

    public void setPowiatId(Integer powiatId) {
        this.powiatId = powiatId;
    }

    public Integer getWojewodzwtwoId() {
        return wojewodzwtwoId;
    }

    public void setWojewodzwtwoId(Integer wojewodzwtwoId) {
        this.wojewodzwtwoId = wojewodzwtwoId;
    }

    private @SerializedName("gmina_id") Integer gminaId;
    private @SerializedName("gmina_nazwa") String gminaNazwa;
//    private @SerializedName("gmina_typ") String gminaTyp;
    private @SerializedName("powiat_id") Integer powiatId;
    private @SerializedName("wojewodztwo_id") Integer wojewodzwtwoId;
}
