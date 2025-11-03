package org.example.JsonBuilder.IDE.json.ref;

public class RefQueryGroupJson {
    String queryGroupName;
    String queryGroupId;
    int order;

    public RefQueryGroupJson(String s, int order) {
        this.order = order;
        this.queryGroupName = s;
    }
}
