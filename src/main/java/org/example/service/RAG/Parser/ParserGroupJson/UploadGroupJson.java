package org.example.service.RAG.Parser.ParserGroupJson;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.collections.ArrayStack;
import org.example.bank.OutputClassBank.EntityInterface;
import org.example.output.vyta.ai.MA_ai;
import org.example.output.vyta.ai.parse_groups.TAB_parse_groups;
import org.example.output.vyta.ai.parse_input_groups.TAB_parse_input_groups;
import org.example.output.vyta.ai.parse_input_names.TAB_parse_input_names;
import org.example.output.vyta.ai.parse_input_types.TAB_parse_input_types;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.example.bank.commonValues.ColumnConverter.toPersonaJson;

/**
 * Root model for the DEXA upload JSON
 */

public class UploadGroupJson {





    @SerializedName("upload_name")
    private String uploadName;
    private String description;

    private List<Group> groups;

    // ---------- Getters / Setters ----------


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



    public void dbInsert(EntityManager entityManager) throws Exception {
        if (entityManager == null) {
            throw new IllegalArgumentException("entityManager must not be null");
        }

        List<EntityInterface> parseGroupEntities = new ArrayList<>();
        List<EntityInterface> parseInputGroupEntities = new ArrayList<>();
        List<EntityInterface> parseInputTypeEntities = new ArrayList<>();
        List<EntityInterface> parseInputNameEntities = new ArrayList<>();

        List<String> parseGroupUpsertStrings = new ArrayList<>();
        List<String> parseInputGroupUpsertStrings = new ArrayList<>();
        List<String> parseInputTypeUpsertStrings = new ArrayList<>();
        List<String> parseInputNameUpsertStrings = new ArrayList<>();



        MA_ai ma_ai = new MA_ai();

        // top-level upload group (one entry)
        TAB_parse_groups parseGroupsTemplate = new TAB_parse_groups();
        String uuid = java.util.UUID.randomUUID().toString();
        parseGroupsTemplate.getCOL_upload_group().setEntityValue(this.uploadName);
        parseGroupsTemplate.getCOL_description().setEntityValue(this.description);
        parseGroupsTemplate.getCOL_db_id().setEntityValue(uuid);
        parseGroupsTemplate.getCOL_is_active().setEntityValue("1");
        parseGroupEntities.add(parseGroupsTemplate);

        if (this.groups == null) {
            // nothing more to insert
            ma_ai.saveAll(parseGroupsTemplate, parseGroupEntities, entityManager, parseGroupUpsertStrings);
            return;
        }

        for (Group group : this.groups) {
            if (group == null) continue;
            String groupUuid = java.util.UUID.randomUUID().toString();

            // create a new input-group instance per loop
            TAB_parse_input_groups inputGroup = new TAB_parse_input_groups();
            inputGroup.getCOL_input_group().setEntityValue(group.getGroupName());
            inputGroup.getCOL_description().setEntityValue(group.getDescription());
            inputGroup.getCOL_db_id().setEntityValue(groupUuid);
            inputGroup.getCOL_upload_group_id().setEntityValue(uuid);
            inputGroup.getCOL_is_active().setEntityValue("1");
            parseInputGroupEntities.add(inputGroup);

            if (group.getTypes() == null) continue;
            for (TypeEntry typeEntry : group.getTypes()) {
                if (typeEntry == null) continue;
                String typeUuid = java.util.UUID.randomUUID().toString();

                // new input-type instance per loop
                TAB_parse_input_types inputType = new TAB_parse_input_types();
                inputType.getCOL_input_type().setEntityValue(typeEntry.getTypeName());
                inputType.getCOL_description().setEntityValue(typeEntry.getDescription());
                inputType.getCOL_input_group_id().setEntityValue(groupUuid);
                inputType.getCOL_is_active().setEntityValue("1");
                inputType.getCOL_db_id().setEntityValue(typeUuid);
                parseInputTypeEntities.add(inputType);

                if (typeEntry.getNames() == null) continue;
                for (NameEntry nameEntry : typeEntry.getNames()) {
                    if (nameEntry == null) continue;
                    String nameUuid = java.util.UUID.randomUUID().toString();

                    // new input-name instance per loop
                    TAB_parse_input_names inputName = new TAB_parse_input_names();
                    inputName.getCOL_input_name().setEntityValue(nameEntry.getInputName());
                    inputName.getCOL_db_id().setEntityValue(nameUuid);
                    inputName.getCOL_input_type_id().setEntityValue(typeUuid);
                    inputName.getCOL_is_active().setEntityValue("1");
                    inputName.getCOL_description().setEntityValue(nameEntry.getDescription());
                    parseInputNameEntities.add(inputName);
                }
            }
        }

        // Use fresh template instances for saveAll where appropriate
        String insertParseGroup = parseGroupsTemplate.getUploadInsert();
        String updateParseGroup = parseGroupsTemplate.getUploadUpdate(List.of(parseGroupsTemplate.getCOL_upload_group()),false,toPersonaJson(parseGroupsTemplate.getColumns()));
        parseGroupUpsertStrings.add(updateParseGroup);
        parseGroupUpsertStrings.add(insertParseGroup);

        String insertInputGroup = new TAB_parse_input_groups().getUploadInsert();
        String updateInputGroup = new TAB_parse_input_groups().getUploadUpdate(List.of(new TAB_parse_input_groups().getCOL_input_group()),false,toPersonaJson(new TAB_parse_input_groups().getColumns()));
        parseInputGroupUpsertStrings.add(updateInputGroup);
        parseInputGroupUpsertStrings.add(insertInputGroup);

        String insertInputType = new TAB_parse_input_types().getUploadInsert();
        String updateInputType = new TAB_parse_input_types().getUploadUpdate(List.of(new TAB_parse_input_types().getCOL_input_type()),false,toPersonaJson(new TAB_parse_input_types().getColumns()));
        parseInputTypeUpsertStrings.add(updateInputType);
        parseInputTypeUpsertStrings.add(insertInputType);

        String insertInputName = new TAB_parse_input_names().getUploadInsert();
        String updateInputName = new TAB_parse_input_names().getUploadUpdate(List.of(new TAB_parse_input_names().getCOL_input_name()),false,toPersonaJson(new TAB_parse_input_names().getColumns()));
        parseInputNameUpsertStrings.add(updateInputName);
        parseInputNameUpsertStrings.add(insertInputName);




        ma_ai.saveAll(parseGroupsTemplate, parseGroupEntities, entityManager, parseGroupUpsertStrings);
        ma_ai.saveAll(new TAB_parse_input_groups(), parseInputGroupEntities, entityManager, parseInputGroupUpsertStrings);
        ma_ai.saveAll(new TAB_parse_input_types(), parseInputTypeEntities, entityManager, parseInputTypeUpsertStrings);
        ma_ai.saveAll(new TAB_parse_input_names(), parseInputNameEntities, entityManager, parseInputNameUpsertStrings);
    }



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

//        private List<ValueWrapper> values;


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

    public static class ValueWrapper {

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
