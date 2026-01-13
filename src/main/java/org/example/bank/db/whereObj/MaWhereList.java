// src/main/java/org/example/bank/db/whereObj/MaWhereList.java
package org.example.bank.db.whereObj;

import org.example.JsonBuilder.json.ma.MAJson;
import org.example.bank.db.contextObj.Rules;
import org.example.bank.db.contextObj.match.JsonMaContextMatch;

import java.util.*;
import java.util.stream.Collectors;

public class MaWhereList implements WhereObjInterface {

    private Map<MAJson, Rules> maJsonList;

    public MaWhereList(List<JsonMaContextMatch> maFilter) {
        this.maJsonList = new HashMap<>();
        if (maFilter == null) return;

        for (JsonMaContextMatch maContextMatch : maFilter) {
            if (maContextMatch == null) continue;
            MAJson maJson = maContextMatch.getJson();
            if (maJson == null) continue;

            String maName = maJson.getName();
            if (maName == null) continue;

            Rules contextRules = maContextMatch.getContext() == null ? null : maContextMatch.getContext().getRules();

            if (maJsonList.containsKey(maJson)) {
                Rules existing = maJsonList.get(maJson);
                if (existing == null) {
                    existing = new Rules();
                    maJsonList.put(maJson, existing);
                }
                if (contextRules != null) existing.addAll(contextRules);
            } else {
                Rules toPut = new Rules();
                if (contextRules != null) toPut.addAll(contextRules);
                maJsonList.put(maJson, toPut);
            }
        }
    }

    public MaWhereList(Collection<MAJson> gmaMas) {
        System.out.println("initializing MaWhereList with null Rules for provided MAs");
        this.maJsonList = new HashMap<>();
        if (gmaMas == null) return;

        for (MAJson ma : gmaMas) {
            if (ma == null) continue;
            String name = ma.getName();
            if (name == null) continue;
            maJsonList.putIfAbsent(ma, null);
        }
    }

    public Map<MAJson, Rules> getMaJsonList() {
        return maJsonList;
    }

    public void setMaJsonList(Map<MAJson, Rules> maJsonList) {
        this.maJsonList = maJsonList;
    }

    public Rules addRule(MAJson ma, Rules incoming) {
        if (ma == null) throw new IllegalArgumentException("ma cannot be null");
        String maName = ma.getName();
        if (maName == null) throw new IllegalArgumentException("ma name cannot be null");

        if (maJsonList == null) maJsonList = new HashMap<>();

        if (!maJsonList.containsKey(ma)) {
            Rules toPut = new Rules();
            if (incoming != null) toPut.addAll(incoming);
            maJsonList.put(ma, toPut);
            return toPut;
        } else {
            Rules existing = maJsonList.get(ma);
            if (existing == null) {
                existing = new Rules();
                maJsonList.put(ma, existing);
            }
            if (incoming != null) existing.addAll(incoming);
            return existing;
        }
    }

    @Override
    public Rules addRule(Rules incoming) {
        throw new UnsupportedOperationException("Use addRule(MAJson, Rules) with an MA name");
    }

    public List<MAJson> getList() {
        if (maJsonList == null) return Collections.emptyList();
        return maJsonList.keySet().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "MaWhereList{" +
                "maJsonList=" + maJsonList +
                '}';
    }
}
