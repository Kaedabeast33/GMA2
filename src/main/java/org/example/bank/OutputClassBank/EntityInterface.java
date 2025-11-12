package org.example.bank.OutputClassBank;

import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.ClassOutputCreator.templates.UpsertTemplate;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;

import java.text.ParseException;
import java.util.List;
import java.util.function.Supplier;

public interface EntityInterface {

    String getValues(String arg) throws ParseException;

    String getValues() throws ParseException;

    String replaceCharacters(String value);

    String getTableName();

    List<String> getColumnsString();

    List<String> getColumnsByGroupNameString(String groupName);



    List<String> getUniqueIdentifierColumnsByGroupNameString(String groupName);


    List<ColumnJson> getColumns();

    List<ColumnJson> getColumnsByGroupName(String groupName);



    List<ColumnJson> getUniqueIdentifierColumnsByGroupName(String groupName);
    List<ColumnJson> getUniqueIdentifierColumns();
    List<String> getUniqueIdentifierColumnsString();

    //    List<String> getIndexes();
//    String getIdName();
    String getDescription();
    String getUploadDelete(List<ColumnTemplate> toDeleteBy, Boolean includeNullValues );
    String getUploadUpdate(List<ColumnTemplate> toUpdateBy,Boolean includeNullValues,List<ColumnJson> updateColumns );
    String getUploadInsert(List<ColumnTemplate> toInsertBy,Boolean includeNullValues,List<ColumnJson> insertColumns,Boolean includePrimaryKey );
    String getUploadInsert(List<ColumnTemplate> toInsertBy,Boolean includeNullValues);
    String getUploadInsert();

//    String getDependencies();

}
