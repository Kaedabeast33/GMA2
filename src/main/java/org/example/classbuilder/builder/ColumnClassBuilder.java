package org.example.classbuilder.builder;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbReference;

import java.lang.reflect.Field;

import static ch.qos.logback.core.util.StringUtil.capitalizeFirstLetter;


public class ColumnClassBuilder {

    public static void  buildFromClass(Class<?> clazz,String outputDir) {
        for (Field field : clazz.getDeclaredFields()){
            KdbColumn columnJson = field.getAnnotation(KdbColumn.class);
            KdbPrimaryKey kdbPrimaryKey = field.getAnnotation(KdbPrimaryKey.class);
            KdbIndex kdbIndex = field.getAnnotation(KdbIndex.class);
            KdbReference kdbReference = field.getAnnotation(KdbReference.class);
            if(columnJson!=null ){
                String className = capitalizeFirstLetter(clazz.getSimpleName());

                
            }
        }
        
    }

    private static String generateColGetSet(){
        return """
                                    
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
                
                        public String getColId() {
                            return colId;
                        }
                
                        public void setColId(String colId) {
                            this.colId = colId;
                        }
                
                        public boolean isNullable() {
                            return isNullable;
                        }
                
                        public void setNullable(boolean nullable) {
                            isNullable = nullable;
                        }
                
                        public boolean isEditable() {
                            return isEditable;
                        }
                
                        public void setEditable(boolean editable) {
                            isEditable = editable;
                        }
                
                        public String[] getColumnGroupNames() {
                            return columnGroupNames;
                        }
                
                        public void setColumnGroupNames(String[] columnGroupNames) {
                            this.columnGroupNames = columnGroupNames;
                        }
                
                        public boolean isUnique() {
                            return unique;
                        }
                
                        public void setUnique(boolean unique) {
                            this.unique = unique;
                        }
                
                        public boolean isUniqueIdentifier() {
                            return uniqueIdentifier;
                        }
                
                        public void setUniqueIdentifier(boolean uniqueIdentifier) {
                            this.uniqueIdentifier = uniqueIdentifier;
                        }
                
                        public String[] getUniqueIdentifierGroupNames() {
                            return uniqueIdentifierGroupNames;
                        }
                
                        public void setUniqueIdentifierGroupNames(String[] uniqueIdentifierGroupNames) {
                            this.uniqueIdentifierGroupNames = uniqueIdentifierGroupNames;
                        }
                
                        public boolean isRequired() {
                            return isRequired;
                        }
                
                        public void setRequired(boolean required) {
                            isRequired = required;
                        }
                
                        public String getType() {
                            return type;
                        }
                
                        public void setType(String type) {
                            this.type = type;
                        }
                
                        public String getDefaultValue() {
                            return defaultValue;
                        }
                
                        public void setDefaultValue(String defaultValue) {
                            this.defaultValue = defaultValue;
                        }
                """;
    }




//    String name, String colId,
//    String description, String[] tag, boolean isNullable, boolean isEditable,
//    boolean unique, boolean isRequired,String type, String defaultValue,
//    List<String> columnGroups, boolean uniqueIdentifier,
//   List<String> uniqueIdentifierGroupNames, boolean isIndex
//   , List<String> indexGroups, List<ColumnTemplate> referenceColumns




}


























