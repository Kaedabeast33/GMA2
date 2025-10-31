package org.example.jsonBuilder.gma.ma.tables;

import org.example.Annotations.KdbTrigger;
import org.example.jsonBuilder.gma.ref.RefSetJson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TriggerJson {
    String name;
    String description;
    String[] tags;
    String triggerId = "trig" + java.util.UUID.randomUUID();
    String triggerType;
    String triggerString;
    RefSetJson[] triggerSets;

    public TriggerJson(KdbTrigger kdbTrigger, Method method) throws InvocationTargetException, IllegalAccessException {
        this.name = kdbTrigger.name();
        this.description = kdbTrigger.description();
        this.tags = kdbTrigger.tags();
        this.triggerId = "trig" + java.util.UUID.randomUUID();
        this.triggerType = kdbTrigger.triggerType();
        //set Method to String
        this.triggerString = (String) method.invoke(null);
//        this.triggerSets = new RefSetJson[kdbTrigger.triggerSet().length];
//        for (int i = 0; i < kdbTrigger.triggerSet().length; i++) {
//            triggerSets[i] = new RefSetJson(kdbTrigger.triggerSet()[i]);
//        }
    }

    public TriggerJson() {
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

    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public String getTriggerString() {
        return triggerString;
    }

    public void setTriggerString(String triggerString) {
        this.triggerString = triggerString;
    }

    public RefSetJson[] getTriggerSets() {
        return triggerSets;
    }

    public void setTriggerSets(RefSetJson[] triggerSets) {
        this.triggerSets = triggerSets;
    }
    // Constructor, getters, and setters can be added here if needed
}
