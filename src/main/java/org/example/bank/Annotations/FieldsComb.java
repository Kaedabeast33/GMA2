package org.example.bank.Annotations;

public class FieldsComb {

    KdbColumn kdbColumn;
    KdbPrimaryKey kdbPrimaryKey;
    KdbIndex kdbIndex;
    KdbReference kdbReference;
    KdbKey kdbKey;
    KdbEmbedding kdbEmbedding;
    Class<?> fieldType;

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }

    public KdbKey getKdbKey() {
        return kdbKey;
    }

    public void setKdbKey(KdbKey kdbKey) {
        this.kdbKey = kdbKey;
    }

    public KdbColumn getKdbColumn() {
        return kdbColumn;
    }

    public void setKdbColumn(KdbColumn kdbColumn) {
        this.kdbColumn = kdbColumn;
    }

    public KdbPrimaryKey getKdbPrimaryKey() {
        return kdbPrimaryKey;
    }

    public void setKdbPrimaryKey(KdbPrimaryKey kdbPrimaryKey) {
        this.kdbPrimaryKey = kdbPrimaryKey;
    }

    public KdbIndex getKdbIndex() {
        return kdbIndex;
    }

    public void setKdbIndex(KdbIndex kdbIndex) {
        this.kdbIndex = kdbIndex;
    }

    public KdbReference getKdbReference() {
        return kdbReference;
    }

    public void setKdbReference(KdbReference kdbReference) {
        this.kdbReference = kdbReference;
    }

    public void setKdbEmbedding(KdbEmbedding kdbEmbedding) {
        this.kdbEmbedding = kdbEmbedding;
    }

    public KdbEmbedding getKdbEmbedding() {
        return kdbEmbedding;
    }
}
