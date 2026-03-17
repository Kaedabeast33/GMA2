package org.example.JsonBuilder.json.ref;

public class RefColumnGroupJson {
    String name;

    String columnGroupId = "cg" + java.util.UUID.randomUUID();

    public RefColumnGroupJson(String s) {
        this.name = s;
    }

    public String getName() {
        return null;
    }
}
