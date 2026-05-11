package org.example.bank.Annotations.ai;

import org.example.bank.Annotations.*;

public class AiFieldsComb {

    KdbAiColumn kdbColumn;
    KdbAiPrimaryKey kdbPrimaryKey;
    KdbAiKey kdbAiKey;

    Class<?> fieldType;

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }

    public KdbAiKey getKdbAiKey() {
        return kdbAiKey;
    }

    public void setKdbAiKey(KdbAiKey kdbAiKey) {
        this.kdbAiKey = kdbAiKey;
    }

    public KdbAiColumn getKdbColumn() {
        return kdbColumn;
    }

    public void setKdbColumn(KdbAiColumn kdbColumn) {
        this.kdbColumn = kdbColumn;
    }

    public KdbAiPrimaryKey getKdbPrimaryKey() {
        return kdbPrimaryKey;
    }

    public void setKdbPrimaryKey(KdbAiPrimaryKey kdbPrimaryKey) {
        this.kdbPrimaryKey = kdbPrimaryKey;
    }

    public void setKdbKey(KdbAiKey kdbKey) {
        this.kdbAiKey = kdbKey;
    }
}