package org.example.jsonBuilder.gma.ma.tables;

import org.example.Annotations.KdbCustomContraint;
import org.example.jsonBuilder.gma.ref.RefColumnJson;
import org.example.jsonBuilder.gma.ref.RefSetJson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomContraintJson {
    String name;
    String description;
    String[] tags;
    String constraintId = "con" + java.util.UUID.randomUUID();
    String triggerString;
    RefSetJson[] triggerSets;
    RefColumnJson [] columns;

    public CustomContraintJson(KdbCustomContraint kdbCustomContraint, Method method) throws InvocationTargetException, IllegalAccessException {
        this.name = kdbCustomContraint.name();
        this.description = kdbCustomContraint.description();
        this.tags = kdbCustomContraint.tags();
        this.constraintId = "con" + java.util.UUID.randomUUID();
        this.triggerString = (String) method.invoke(null);
//        this.triggerSets = new RefSetJson[kdbCustomContraint.triggerSet().length];
//        for (int i = 0; i < kdbCustomContraint.triggerSet().length; i++) {
//            triggerSets[i] = new RefSetJson(kdbCustomContraint.triggerSet()[i]);
//        }
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

    public String getConstraintId() {
        return constraintId;
    }

    public void setConstraintId(String constraintId) {
        this.constraintId = constraintId;
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

    public RefColumnJson[] getColumns() {
        return columns;
    }

    public void setColumns(RefColumnJson[] columns) {
        this.columns = columns;
    }
}
