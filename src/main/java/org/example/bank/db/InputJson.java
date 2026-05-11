package org.example.bank.db;


import org.example.ai.AiRagSchemaJson;
import org.example.ai.AiRagSchemaParse;


public class InputJson {

    private String clientId;
    public String reportedAt;
    private String uploadGroup;
    private String uploadGroupDescription;
    private String groupName;
    private String groupNameDescription;
    private String typeName;
    private String typeNameDescription;
    private String inputName;
    private String inputNameDescription;
    private String valueName;
    private String valueDescription;
    private AiRagSchemaJson.ValueWrapper value;
    private final String uuid = java.util.UUID.randomUUID().toString();

    public InputJson() {}


    public String getUuid() {
        return uuid;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getValueDescription() {
        return valueDescription;
    }

    public void setValueDescription(String valueDescription) {
        this.valueDescription = valueDescription;
    }

    public AiRagSchemaJson.ValueWrapper getValue() {
        return value;
    }

    public void setValue(AiRagSchemaJson.ValueWrapper value) {
        this.value = value;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


// Accepts multiple common datetime string formats and converts to LocalDateTime
//    public void setReportedAt(String reportedAt) {
//        if (reportedAt == null) {
//            this.reportedAt = null;
//            return;
//        }
//        String s = reportedAt.trim();
//        // Try ISO_LOCAL_DATE_TIME (e.g. 2025-10-15T08:51:00)
//        try {
//            this.reportedAt = LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//            return;
//        } catch (Exception ignored) {}
//
//        // Try common space-separated format (e.g. 2025-10-15 08:51:00)
//        try {
//            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
//            this.reportedAt = LocalDateTime.parse(s, f);
//            return;
//        } catch (Exception ignored) {}
//
//        // Try ISO_OFFSET_DATE_TIME (e.g. 2025-10-15T08:51:00-07:00) -> convert to LocalDateTime
//        try {
//            this.reportedAt = java.time.OffsetDateTime.parse(s, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDateTime();
//            return;
//        } catch (Exception ignored) {}
//
//        // Try ISO_ZONED_DATE_TIME (e.g. 2025-10-15T08:51:00-07:00[America/Los_Angeles])
//        try {
//            this.reportedAt = java.time.ZonedDateTime.parse(s, DateTimeFormatter.ISO_ZONED_DATE_TIME).toLocalDateTime();
//            return;
//        } catch (Exception ignored) {}
//
//        // Fallback: try default parse
//        try {
//            this.reportedAt = LocalDateTime.parse(s);
//        } catch (Exception e) {
//            // If parsing fails, leave as null
//            this.reportedAt = null;
//        }
//    }


    public String getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(String reportedAt) {
        this.reportedAt = reportedAt;
    }

    public String getUploadGroup() {
        return uploadGroup;
    }

    public void setUploadGroup(String uploadGroup) {
        this.uploadGroup = uploadGroup;
    }

    public String getUploadGroupDescription() {
        return uploadGroupDescription;
    }

    public void setUploadGroupDescription(String uploadGroupDescription) {
        this.uploadGroupDescription = uploadGroupDescription;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNameDescription() {
        return groupNameDescription;
    }

    public void setGroupNameDescription(String groupNameDescription) {
        this.groupNameDescription = groupNameDescription;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNameDescription() {
        return typeNameDescription;
    }

    public void setTypeNameDescription(String typeNameDescription) {
        this.typeNameDescription = typeNameDescription;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getInputNameDescription() {
        return inputNameDescription;
    }

    public void setInputNameDescription(String inputNameDescription) {
        this.inputNameDescription = inputNameDescription;
    }

    @Override
    public String toString() {
        return "InputJson{" +
                "clientId=" + clientId +
                ", reportedAt=" + reportedAt +
                ", uploadGroup='" + uploadGroup + '\'' +
                ", uploadGroupDescription='" + uploadGroupDescription + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupNameDescription='" + groupNameDescription + '\'' +
                ", typeName='" + typeName + '\'' +
                ", typeNameDescription='" + typeNameDescription + '\'' +
                ", inputName='" + inputName + '\'' +
                ", inputNameDescription='" + inputNameDescription + '\'' +
                '}';
    }

    public void setInputValueDescription(String desc) {
        this.inputNameDescription = desc;
    }
}

