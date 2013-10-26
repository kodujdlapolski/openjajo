package com.kodujdlapolski.openjajo.sejmometr.gmina;

import com.google.gson.annotations.SerializedName;

public class Document {
    private String type;
    private @SerializedName("content") Content content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
