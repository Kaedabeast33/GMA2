package org.example.ClassOutputCreator.templates;

import jakarta.persistence.EntityManager;
import org.example.bank.OutputClassBank.EntityInterface;

import java.text.ParseException;
import java.util.List;
import java.util.function.Supplier;

public abstract class MATemplate {

    protected final String name;
    protected final String description;
    protected final String[] tags;

    protected final String maId;
    protected final String gmaName;


    public MATemplate(String name, String description, String[] tags, String maId, String gmaName) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.maId = maId;
        this.gmaName = gmaName;

    }



    public String getGmaName() {
        return gmaName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getTags() {
        return tags;
    }

    public String getMaId() {
        return maId;
    }


}
