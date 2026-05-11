//package org.example.ClassOutputCreator.templates.ai.blanks.inputs;
//
//
//import org.example.ClassOutputCreator.templates.TableTemplate;
//import org.example.ClassOutputCreator.templates.ColumnTemplate;
//
//import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;
//import org.example.bank.OutputClassBank.QueryResult;
//
//import static org.example.bank.OutputClassBank.KdbColumnWrapper.safeGetValue;
//
//
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_upload_group;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_input_group;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_input_type;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_input_name;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_input_value;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_db_embedding;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_db_update_date;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_db_insert_date;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_value;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_input_json;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_raw_id;
//import org.example.ClassOutputCreator.templates.ai.blanks.inputs.columns.COL_db_id;
//
//import java.util.List;
//import java.sql.SQLException;
//import jakarta.persistence.EntityManager;
//
//import org.example.bank.OutputClassBank.KDBContext;
//import org.example.bank.OutputClassBank.KdbColumnPersona;
//
//public class BlankInputTableTemplate extends TableTemplate {
//
//    public BlankInputTableTemplate() {
//        super(
//            "inputs"
//,
//            "This table contains the main input data for the AI model. Each record represents a single data point that the model will process. The fields in this table should capture all relevant features and attributes of the input data, such as numerical values, categorical labels, timestamps, and any other information necessary for the model to make accurate predictions or classifications."
//,
//            new String[]{""},
//            "tab7d903eb0-7bcb-4af8-9be0-85831e2c330f"
//,
//            "vyta"
//,
//            "client_med"
//
//        );
//    }
//
//
//
//
// KDBContext context = KDBContext.KDB_CONTEXT;
//    private final ColumnTemplate COL_upload_group = new COL_upload_group();
//
//    public ColumnTemplate getCOL_upload_group() {
//        return COL_upload_group;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_input_group = new COL_input_group();
//
//    public ColumnTemplate getCOL_input_group() {
//        return COL_input_group;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_input_type = new COL_input_type();
//
//    public ColumnTemplate getCOL_input_type() {
//        return COL_input_type;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_input_name = new COL_input_name();
//
//    public ColumnTemplate getCOL_input_name() {
//        return COL_input_name;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_input_value = new COL_input_value();
//
//    public ColumnTemplate getCOL_input_value() {
//        return COL_input_value;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_db_embedding = new COL_db_embedding();
//
//    public ColumnTemplate getCOL_db_embedding() {
//        return COL_db_embedding;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_db_update_date = new COL_db_update_date();
//
//    public ColumnTemplate getCOL_db_update_date() {
//        return COL_db_update_date;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_db_insert_date = new COL_db_insert_date();
//
//    public ColumnTemplate getCOL_db_insert_date() {
//        return COL_db_insert_date;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_value = new COL_value();
//
//    public ColumnTemplate getCOL_value() {
//        return COL_value;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_input_json = new COL_input_json();
//
//    public ColumnTemplate getCOL_input_json() {
//        return COL_input_json;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_raw_id = new COL_raw_id();
//
//    public ColumnTemplate getCOL_raw_id() {
//        return COL_raw_id;
//    }
//
//
//
//
//
//    private final ColumnTemplate COL_db_id = new COL_db_id();
//
//    public ColumnTemplate getCOL_db_id() {
//        return COL_db_id;
//    }
//
//
//
//
//
//        @Override
//        public List<ColumnJson> getColumns(){
//           List<ColumnJson> list = context.getColumns(this.getGmaName(),this.getMaName(),this.getName());
//           return list;
//        }
//
//        @Override
//        public List<ColumnJson> getColumnsByGroupName(String groupName){
//            List<ColumnJson> list = context.getColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
//            return list;
//        }
//
//        @Override
//        public List<ColumnJson> getUniqueIdentifierColumns(){
//            List<ColumnJson> list = context.getUniqueIdentifierColumns(this.getGmaName(),this.getMaName(),this.getName());
//            return list;
//        }
//
//        @Override
//        public List<ColumnJson> getUniqueIdentifierColumnsByGroupName(String groupName){
//            List<ColumnJson> list = context.getUniqueIdentifierColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
//            return list;
//            }
//
//        @Override
//        public List<String> getColumnsString(){
//           List<String> list = context.getColumnsString(this.getGmaName(),this.getMaName(),this.getName());
//           System.out.println(list);
//           return list;
//        }
//
//        @Override
//        public List<String> getColumnsByGroupNameString(String groupName){
//            List<String> list = context.getColumnsByGroupNameString(this.getGmaName(),this.getMaName(),this.getName(),groupName);
//            System.out.println(list);
//            return list;
//        }
//
//        @Override
//        public List<String> getUniqueIdentifierColumnsString(){
//            List<String> list = context.getUniqueIdentifierColumnsString(this.getGmaName(),this.getMaName(),this.getName());
//            System.out.println(list);
//            return list;
//        }
//
//        @Override
//        public List<String> getUniqueIdentifierColumnsByGroupNameString(String groupName){
//            List<String> list = context.getUniqueIdentifierColumnsByGroupNameString(this.getGmaName(),this.getMaName(),this.getName(),groupName);
//            System.out.println(list);
//            return list;
//            }
//
//
//        @Override
//        public String replaceCharacters(String value){
//           if(value==null){;
//               return null;
//           }
//             return value.replace("'","''");
//        }
//        @Override
//        public String getTableName(){
//            return this.getName();
//        }
//@Override
//public String getValues()  {
//    return String.format("(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)\n ,",
//                      safeGetValue(getCOL_upload_group()),
//          safeGetValue(getCOL_input_group()),
//          safeGetValue(getCOL_input_type()),
//          safeGetValue(getCOL_input_name()),
//          safeGetValue(getCOL_input_value()),
//          safeGetValue(getCOL_db_embedding()),
//          safeGetValue(getCOL_db_update_date()),
//          safeGetValue(getCOL_db_insert_date()),
//          safeGetValue(getCOL_value()),
//          safeGetValue(getCOL_input_json()),
//          safeGetValue(getCOL_raw_id()),
//          safeGetValue(getCOL_db_id())
//    );
//}
//            @Override
//            public String getValues(String arg) {
//                return String.format("(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)\n,",
//          safeGetValue(getCOL_upload_group()),
//          safeGetValue(getCOL_input_group()),
//          safeGetValue(getCOL_input_type()),
//          safeGetValue(getCOL_input_name()),
//          safeGetValue(getCOL_input_value()),
//          safeGetValue(getCOL_db_embedding()),
//          safeGetValue(getCOL_db_update_date()),
//          safeGetValue(getCOL_db_insert_date()),
//          safeGetValue(getCOL_value()),
//          safeGetValue(getCOL_input_json()),
//          safeGetValue(getCOL_raw_id()),
//          safeGetValue(getCOL_db_id()),
//           arg
//                );
//            }
//        @Override
//        public String getUploadDeleteGma(List<KdbColumnPersona> toDeleteBy,Boolean includeNullValues ) {
//            return context.getUploadDeleteGma(this.getGmaName(),this.getMaName(),this.getName(),toDeleteBy,includeNullValues);
//        }
//
//        @Override
//        public String getUploadUpdateGma(List<KdbColumnPersona> toUpdateBy,Boolean includeNullValues,List<KdbColumnPersona> updateColumns ) {
//            return context.getUploadUpdateGma(this.getGmaName(),this.getMaName(),this.getName(),toUpdateBy,includeNullValues,updateColumns);
//        }
//
//        @Override
//        public String getUploadInsertGma(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues,List<KdbColumnPersona> insertColumns,Boolean includePrimaryKey ) {
//            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues,insertColumns,includePrimaryKey);
//        }
//
//        @Override
//        public String getUploadInsertGma(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues) {
//            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues);
//        }
//
//        @Override
//        public String getUploadInsertGma() {
//            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName());
//        }
//
//        @Override
//        public String getUploadInsertGma(Boolean includePrimaryKey ) {
//            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName(),includePrimaryKey);
//        }
//
//
//        @Override
//        public String getUploadDelete(List<KdbColumnPersona> toDeleteBy,Boolean includeNullValues ) {
//            return context.getUploadDelete(this.getGmaName(),this.getMaName(),this.getName(),toDeleteBy,includeNullValues);
//        }
//
//        @Override
//        public String getUploadUpdate(List<KdbColumnPersona> toUpdateBy,Boolean includeNullValues,List<KdbColumnPersona> updateColumns ) {
//            return context.getUploadUpdate(this.getGmaName(),this.getMaName(),this.getName(),toUpdateBy,includeNullValues,updateColumns);
//        }
//
//        @Override
//        public String getUploadInsert(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues,List<KdbColumnPersona> insertColumns,Boolean includePrimaryKey ) {
//            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues,insertColumns,includePrimaryKey);
//        }
//
//        @Override
//        public String getUploadInsert(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues) {
//            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues);
//        }
//
//        @Override
//        public String getUploadInsert() {
//            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName());
//        }
//
//        @Override
//        public String getUploadInsert(Boolean includePrimaryKey ) {
//            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName(),includePrimaryKey);
//        }
//    @Override
//    public QueryResult getQueryByCols(List<ColumnTemplate> byColumns,
//                                      EntityManager entityManager) throws SQLException {
//        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns, entityManager);
//    }
//
//    @Override
//    public QueryResult getQueryByCols(List<ColumnTemplate> byColumns,
//                                      List<KdbColumnPersona> getColumns,
//                                      EntityManager entityManager) throws SQLException {
//        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns, getColumns, entityManager);
//    }
//
//    @Override
//    public QueryResult getQuery(List<KdbColumnPersona> getColumns,
//                                EntityManager entityManager) throws SQLException {
//        return context.getQuery(this.getGmaName(), this.getMaName(), this.getName(), getColumns, entityManager);
//    }
//
//
//
//    @Override
//    public QueryResult getQueryByCols(List<ColumnTemplate> byColumns) throws SQLException {
//        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns);
//    }
//
//    @Override
//    public QueryResult getQueryByCols(List<ColumnTemplate> byColumns,
//                                      List<KdbColumnPersona> getColumns) throws SQLException {
//        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns, getColumns);
//    }
//
//    @Override
//    public QueryResult getQuery(List<KdbColumnPersona> getColumns) throws SQLException {
//        return context.getQuery(this.getGmaName(), this.getMaName(), this.getName(), getColumns);
//    }
//
//}