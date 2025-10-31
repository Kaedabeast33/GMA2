package org.example.jsonBuilder.gma.ref;

public class RefMaJson {
    String maName;
    String maId;

    public RefMaJson(String maNamew, String maId) {
        this.maName = maNamew;
        this.maId = maId;
    }

    public String getMaNamew() {
        return maName;
    }

    public void setMaNamew(String maNamew) {
        this.maName = maNamew;
    }

    public String getMaId() {
        return maId;
    }

    public void setMaId(String maId) {
        this.maId = maId;
    }
}
