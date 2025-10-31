package org.example.jsonBuilder.gma.ref;


public class ReferenceColumnJson {
    private boolean isForeignKey;
    private RefColumnJson[] referenceColumns;
    private String cascadeRule;

    public ReferenceColumnJson(boolean isForeignKey, RefColumnJson[] referenceColumns, String cascadeRule) {
        this.isForeignKey = isForeignKey;
        this.referenceColumns = referenceColumns;
        this.cascadeRule = cascadeRule;

    }






    public RefColumnJson[] getReferenceColumns() {
        return referenceColumns;
    }

    public void setReferenceColumns(RefColumnJson[] referenceColumns) {
        this.referenceColumns = referenceColumns;
    }

    public boolean isForeignKey() {
        return isForeignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        isForeignKey = foreignKey;
    }

    public String getCascadeRule() {
        return cascadeRule;
    }

    public void setCascadeRule(String cascadeRule) {
        this.cascadeRule = cascadeRule;
    }


}
