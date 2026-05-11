package org.example.ai;

import com.google.gson.annotations.SerializedName;
import org.example.bank.commonMethods.MultiFormatLocalDateTimeFormatter;

import java.time.LocalDateTime;
import java.util.List;

public class AiRagSchemaParse {
    @SerializedName("upload_name")
    private String uploadName;




    @SerializedName("timestamp")
    private String timestamp;

    private List<AiRagSchemaParse.Group> groups;

    // ---------- Getters / Setters ----------

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }





    public String getTimestamp() {
        return timestamp;
    }

    public LocalDateTime getTimestampAsLocalDateTime() {
        LocalDateTime l = MultiFormatLocalDateTimeFormatter.parseDateAndTime(timestamp);
        return l == null ? LocalDateTime.now() : l;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<AiRagSchemaParse.Group> getGroups() {
        return groups;
    }

    public void setGroups(List<AiRagSchemaParse.Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "AiRagSchemaParse{" +
                "uploadName='" + uploadName + '\'' +

                ", timestamp='" + timestamp + '\'' +
                ", groups=" + groups +
                '}';
    }

    // ============================================================
    // Nested Classes
    // ============================================================

    public static class Group {
        @SerializedName("group_name")
        private String groupName;



        private List<AiRagSchemaParse.TypeEntry> types;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;




        }

        public List<AiRagSchemaParse.TypeEntry> getTypes() {
            return types;
        }

        public void setTypes(List<AiRagSchemaParse.TypeEntry> types) {
            this.types = types;
        }

        @Override
        public String toString() {
            return "Group{" +
                    "groupName='" + groupName + '\'' +

                    ", types=" + types +
                    '}';
        }
    }

    public static class TypeEntry {
        @SerializedName("type_name")
        private String typeName;



        private List<AiRagSchemaParse.NameEntry> names;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;




        }

        public List<AiRagSchemaParse.NameEntry> getNames() {
            return names;
        }

        public void setNames(List<AiRagSchemaParse.NameEntry> names) {
            this.names = names;
        }

        @Override
        public String toString() {
            return "TypeEntry{" +
                    "typeName='" + typeName + '\'' +

                    ", names=" + names +
                    '}';
        }
    }

    public static class NameEntry {
        @SerializedName("input_name")
        private String inputName;



        private List<AiRagSchemaParse.ValueWrapper> values;

        public String getInputName() {
            return inputName;
        }

        public void setInputName(String inputName) {
            this.inputName = inputName;




        }

        public List<AiRagSchemaParse.ValueWrapper> getValues() {
            return values;
        }

        public void setValues(List<AiRagSchemaParse.ValueWrapper> values) {
            this.values = values;
        }

        @Override
        public String toString() {
            return "NameEntry{" +
                    "inputName='" + inputName + '\'' +

                    ", values=" + values +
                    '}';
        }
    }

    public static class ValueWrapper {
        @SerializedName("value")
        private AiRagSchemaParse.MeasurementValue value;

        private String notes;

        public AiRagSchemaParse.MeasurementValue getValue() {
            return value;
        }

        public void setValue(AiRagSchemaParse.MeasurementValue value) {
            this.value = value;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        @Override
        public String toString() {
            return "ValueWrapper{" +
                    "value=" + value +
                    ", notes='" + notes + '\'' +
                    '}';
        }
    }

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

        private AiRagSchemaParse.Source source;

        private Double confidence;
        private String notes;

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

        public AiRagSchemaParse.Source getSource() {
            return source;
        }

        public void setSource(AiRagSchemaParse.Source source) {
            this.source = source;
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

        @Override
        public String toString() {
            return "MeasurementValue{" +
                    "name='" + name + '\'' +

                    ", value=" + value +
                    ", unit='" + unit + '\'' +
                    ", normalizedValue=" + normalizedValue +
                    ", normalizedUnit='" + normalizedUnit + '\'' +
                    ", measurementType='" + measurementType + '\'' +
                    ", date='" + date + '\'' +
                    ", time='" + time + '\'' +
                    ", frequency='" + frequency + '\'' +
                    ", source=" + source +
                    ", confidence=" + confidence +
                    ", notes='" + notes + '\'' +
                    '}';
        }
    }

    public static class Source {
        private String type;
        private String name;
        private Object page;

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

        public Object getPage() {
            return page;
        }

        public void setPage(Object page) {
            this.page = page;
        }

        @Override
        public String toString() {
            return "Source{" +
                    "type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", page=" + page +
                    '}';
        }
    }
}
