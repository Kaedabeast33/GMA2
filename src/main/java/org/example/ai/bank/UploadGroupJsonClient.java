package org.example.ai.bank;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.EntityManager;
import okhttp3.*;
import org.example.bank.AppConfig;
import org.example.bank.OutputClassBank.EntityInterface;
import org.example.bank.OutputClassBank.KdbColumnPersona;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.example.bank.commonValues.ColumnConverter.toPersonaJson;
import static org.example.bank.commonValues.ColumnConverter.toPersonaTemplate;

public class UploadGroupJsonClient {






    @SerializedName("upload_name")
    private String uploadName;
    @SerializedName("upload_description")
    private String description;
    // Store reportedAt as a String for JSON binding to avoid reflection into java.time internals.
    // Public API still uses LocalDateTime via getter/setter.
    @SerializedName("reported_at")
    private String reportedAt;


    private List<Group> groups;

    // ---------- Getters / Setters ----------


    public LocalDateTime getReportedAt() {
        if (this.reportedAt == null) return null;
        try {
            return LocalDateTime.parse(this.reportedAt, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(this.reportedAt);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        if (reportedAt == null) this.reportedAt = null;
        else this.reportedAt = reportedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    // Allow raw string setter (used by Gson when it binds JSON directly)
    public void setReportedAtString(String reportedAt) {
        this.reportedAt = reportedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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







    public String rewriteDescription(List<String> descriptions,String name){
        String url = AppConfig.getPyServerUrl()+"/claude/simple_request";
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        String userPrompt = "Combine the two definitions of "+name+" into a single description: " + String.join("; ", descriptions);
        String systemPrompt = "You are a helpful assistant that combines multiple descriptions of a database column into one concise description. Focus on the most important details and avoid redundancy.";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, Object> payload = new HashMap<>();
        payload.put("system_prompt", systemPrompt);
        payload.put("user_prompt", userPrompt);
        String jsonPayload = gson.toJson(payload);

        RequestBody body = RequestBody.create(JSON, jsonPayload);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body() != null ? response.body().string().trim() : null;
        } catch (IOException e) {
            throw new RuntimeException("Failed to rewrite description", e);
        }

    }
    private String combineDescriptions(String dbDescription, String jsonDescription, String name) {
        if (dbDescription == null && jsonDescription == null) {
            return null;
        }
        if (dbDescription == null) return jsonDescription;
        if (jsonDescription == null) return dbDescription;
        if (dbDescription.equals(jsonDescription)) return dbDescription;

        // If they differ, call rewriteDescription
        return rewriteDescription(List.of(dbDescription, jsonDescription), name);
    }

//
//a
//    private ParseValue dbInsertChecker(String tableName, String client_parse_name) throws SQLException {
//        TAB_client_parse_groups parseGroups = new TAB_client_parse_groups();
//        TAB_client_parse_input_groups parseInputGroups = new TAB_client_parse_input_groups();
//        TAB_client_parse_input_types parseInputTypes = new TAB_client_parse_input_types();
//        TAB_client_parse_input_names parseInputNames = new TAB_client_parse_input_names();
//
//        switch (tableName){
//            case "client_parse_groups":
//                return new ParseValue(parseGroups.getQueryByCols(List.of(parseGroups.getCOL_upload_group().setQueryMatchStrings(List.of(client_parse_name))),toPersonaTemplate(List.of(parseGroups.getCOL_description(),parseGroups.getCOL_db_id(),parseGroups.getCOL_upload_group()))),parseGroups.getCOL_upload_group().getName());
//            case "client_parse_input_groups":
//                return new ParseValue(parseInputGroups.getQueryByCols(List.of(parseInputGroups.getCOL_input_group().setQueryMatchStrings(List.of(client_parse_name))),toPersonaTemplate(List.of(parseInputGroups.getCOL_description(),parseInputGroups.getCOL_db_id(),parseInputGroups.getCOL_input_group()))),parseInputGroups.getCOL_input_group().getName());
//            case "client_parse_input_types":
//                return new ParseValue(parseInputTypes.getQueryByCols(List.of(parseInputTypes.getCOL_input_type().setQueryMatchStrings(List.of(client_parse_name))),toPersonaTemplate(List.of(parseInputTypes.getCOL_description(),parseInputTypes.getCOL_db_id(),parseInputTypes.getCOL_input_type()))),parseInputTypes.getCOL_input_type().getName());
//            case "client_parse_input_names":
//                return new ParseValue(parseInputNames.getQueryByCols(List.of(parseInputNames.getCOL_input_name().setQueryMatchStrings(List.of(client_parse_name))),toPersonaTemplate(List.of(parseInputNames.getCOL_description(),parseInputNames.getCOL_db_id(),parseInputNames.getCOL_input_name()))),parseInputNames.getCOL_input_name().getName());
//            default:
//                throw new IllegalArgumentException("Unknown table name for dbInsertChecker: " + tableName);
//        }
//
//    }


    // ============================================================
    // Nested Classes
    // ============================================================

    public static class Group {

        @SerializedName("group_name")
        private String groupName;

        private String description;

        private List<TypeEntry> types;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

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
                    ", description=" + description +
                    ", types=" + types +
                    '}';
        }

    }

    // ------------------------------------------------------------

    public static class TypeEntry {

        @SerializedName("type_name")
        private String typeName;

        private String description;

        private List<NameEntry> names;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

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
                    ", description=" + description +
                    ", names=" + names +
                    '}';
        }

    }

    // ------------------------------------------------------------

    public static class NameEntry {

        @SerializedName("input_name")
        private String inputName;
        private String description;
        private List<Value> values;

//        private List<ValueWrapper> values;


        public List<Value> getValues() {
            return values;
        }

        public void setValues(List<Value> values) {
            this.values = values;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

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
        private String description;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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
