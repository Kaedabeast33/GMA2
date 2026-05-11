package org.example.ai;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Root model for the DEXA upload JSON
 */

public class PromptForJsonSchema {





    @SerializedName("upload_name")
    private String uploadName;

    @SerializedName("reported_at")
    private String reportedAt;


    private List<Group> groups;

    // ---------- Getters / Setters ----------

    public String getReportedAt() {
        return reportedAt;
    }


    public void setReportedAt(String reportedAt) {
        this.reportedAt = reportedAt;
    }



//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    @Override
    public String toString() {
        return "UploadGroupJson{" +
                "uploadName=" + uploadName +
                ", groups=" + groups +
                '}';
    }









    // ============================================================
    // Nested Classes
    // ============================================================

    public static class Group {

        @SerializedName("group_name")
        private String groupName;

//        private String description;

        private List<TypeEntry> types;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

//        public String getDescription() {
//            return description;
//        }

//        public void setDescription(String description) {
//            this.description = description;
//        }

        public List<TypeEntry> getTypes() {
            return types;
        }

        public void setTypes(List<TypeEntry> types) {
            this.types = types;
        }
        @Override
        public String toString() {
            return "Group{" +
                    "groupName=" + groupName +
//                    ", description=" + description +
                    ", types=" + types +
                    '}';
        }

    }

    // ------------------------------------------------------------

    public static class TypeEntry {

        @SerializedName("type_name")
        private String typeName;

//        private String description;

        private List<NameEntry> names;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

//        public String getDescription() {
//            return description;
//        }

//        public void setDescription(String description) {
//            this.description = description;
//        }

        public List<NameEntry> getNames() {
            return names;
        }

        public void setNames(List<NameEntry> names) {
            this.names = names;
        }
        @Override
        public String toString() {
            return "TypeEntry{" +
                    "typeName=" + typeName +
//                    ", description=" + description +
                    ", names=" + names +
                    '}';
        }

    }

    // ------------------------------------------------------------

    public static class NameEntry {

        @SerializedName("input_name")
        private String inputName;
//        private String description;
        private List<Value> values;

//        private List<ValueWrapper> values;


        public List<Value> getValues() {
            return values;
        }

        public void setValues(List<Value> values) {
            this.values = values;
        }

//        public String getDescription() {
//            return description;
//        }

//        public void setDescription(String description) {
//            this.description = description;
//        }

        public String getInputName() {
            return inputName;
        }

        public void setInputName(String inputName) {
            this.inputName = inputName;
        }

        public NameEntry() {
            this.values = List.of(new Value());
        }

        @Override
        public String toString() {
            return "NameEntry{" +
                    "inputName=" + inputName +
                    '}';
        }

//
//        public List<ValueWrapper> getValues() {
//            return values;
//        }
//
//        public void setValues(List<ValueWrapper> values) {
//            this.values = values;
//        }
    }

    // ------------------------------------------------------------

    public static class Value {

        private MeasurementValue value;

        private Double confidence;

        private String notes;

        public MeasurementValue getValue() {
            return value;
        }

        public void setValue(MeasurementValue value) {
            this.value = value;
        }

        public Double getConfidence() {
            return confidence;
        }

        public void setConfidence(Double confidence) {
            this.confidence = confidence;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public Value() {
            this.value = new MeasurementValue();
        }

        @Override
        public String toString() {
            return "ValueWrapper{" +
                    "value=" + value +
                    ", confidence=" + confidence +
                    ", notes=" + notes +
                    '}';
        }

    }

    // ------------------------------------------------------------

    public static class MeasurementValue {

        private String name;

        /**
         * Can be String or Number → use Object for Gson flexibility
         */
        private Object value;

        private String unit;

        @SerializedName("normalized_value")
        private Object normalizedValue;

        @SerializedName("normalized_unit")
        private String normalizedUnit;

        @SerializedName("measurement_type")
        private String measurementType;

        private String date;
        private String time;
        private String frequency;

        private Source source;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Object getNormalizedValue() {
            return normalizedValue;
        }

        public void setNormalizedValue(Object normalizedValue) {
            this.normalizedValue = normalizedValue;
        }

        public String getNormalizedUnit() {
            return normalizedUnit;
        }

        public void setNormalizedUnit(String normalizedUnit) {
            this.normalizedUnit = normalizedUnit;
        }

        public String getMeasurementType() {
            return measurementType;
        }

        public void setMeasurementType(String measurementType) {
            this.measurementType = measurementType;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        @Override
        public String toString() {
            return "MeasurementValue{" +
                    "name=" + name +
                    ", value=" + value +
                    ", unit=" + unit +
                    ", normalizedValue=" + normalizedValue +
                    ", normalizedUnit=" + normalizedUnit +
                    ", measurementType=" + measurementType +
                    ", date=" + date +
                    ", time=" + time +
                    ", frequency=" + frequency +
                    ", source=" + source +
                    '}';
        }

    }

    // ------------------------------------------------------------

    public static class Source {

        private String type;
        private String name;
        private Integer page;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }
        @Override
        public String toString() {
            return "Source{" +
                    "type=" + type +
                    ", name=" + name +
                    ", page=" + page +
                    '}';
        }

    }
}
