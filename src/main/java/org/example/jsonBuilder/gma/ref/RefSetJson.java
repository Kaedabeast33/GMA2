package org.example.jsonBuilder.gma.ref;

public class RefSetJson {
    String name;
    String setId;

    public RefSetJson(String s) {
        this.name = s;
//        this.setId = "set" + s;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }
}
