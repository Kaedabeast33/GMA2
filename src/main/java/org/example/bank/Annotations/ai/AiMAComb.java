package org.example.bank.Annotations.ai;

import org.example.bank.Annotations.FieldsComb;
import org.example.bank.Annotations.KdbTable;
import org.example.bank.Annotations.MethodsComb;

import java.util.ArrayList;
import java.util.List;

public class AiMAComb {


    KdbAi kdbAi;
    private List<AiFieldsComb> fieldsComb = new ArrayList<>();
    String name;
    String description;
    String[] tags;

    public KdbAi getKdbAi() {
        return kdbAi;
    }

    public void setKdbAi(KdbAi kdbAi) {
        this.kdbAi = kdbAi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public List<AiFieldsComb> getFieldsComb() {
        return fieldsComb;
    }

    public void addAiFieldsComb(AiFieldsComb fieldsComb) {
        this.fieldsComb.add(fieldsComb);
    }

    public void setFieldsComb(List<AiFieldsComb> fieldsComb) {
        this.fieldsComb = fieldsComb;
    }


}
