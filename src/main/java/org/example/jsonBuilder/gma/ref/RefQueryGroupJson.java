package org.example.jsonBuilder.gma.ref;

public class RefQueryGroupJson {
    String queryGroupName;
    String queryGroupId;
    int order;

    public RefQueryGroupJson(String s,int order) {
        this.order = order;
        this.queryGroupName = s;
    }
}
