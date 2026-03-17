package org.example.bank.OutputClassBank;

import java.util.List;

public class AirBodyJson {
    public static class Request {
        public List<Record> records;

        public Request(List<Record> records) {
            this.records = records;
        }
    }

    public static class Record {
        public Fields fields;

        public Record(Fields fields) {
            this.fields = fields;
        }
    }

    public static class Fields {

        public String roleId;
        public String universalRoleName;
        public String legacyTitle;
        public String businessUnit;

        public List<String> sellerHistoryLinkedRecords;

        public String sellerTableLinkedRecords;

        public Fields(
                String roleId,
                String universalRoleName,
                String legacyTitle,
                String businessUnit,
                List<String> sellerHistoryLinkedRecords,
                String sellerTableLinkedRecords
        ) {
            this.roleId = roleId;
            this.universalRoleName = universalRoleName;
            this.legacyTitle = legacyTitle;
            this.businessUnit = businessUnit;
            this.sellerHistoryLinkedRecords = sellerHistoryLinkedRecords;
            this.sellerTableLinkedRecords = sellerTableLinkedRecords;
        }
    }
}
