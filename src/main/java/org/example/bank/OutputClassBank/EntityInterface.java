package org.example.bank.OutputClassBank;

import jakarta.persistence.EntityManager;
import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;



public interface EntityInterface {

    String getMaName();

    String getUpsertName();

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
    String getUploadDeleteGma(List<KdbColumnPersona> toDeleteBy, Boolean includeNullValues );
    String getUploadUpdateGma(List<KdbColumnPersona> toUpdateBy,Boolean includeNullValues,List<KdbColumnPersona> updateColumns );
    String getUploadInsertGma(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues,List<KdbColumnPersona> insertColumns,Boolean includePrimaryKey );
    String getUploadInsertGma(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues);
    String getUploadInsertGma();
    String getUploadInsertGma(Boolean includePrimaryKey );

    String getUploadDelete(List<KdbColumnPersona> toDeleteBy, Boolean includeNullValues );
    String getUploadUpdate(List<KdbColumnPersona> toUpdateBy,Boolean includeNullValues,List<KdbColumnPersona> updateColumns );
    String getUploadInsert(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues,List<KdbColumnPersona> insertColumns,Boolean includePrimaryKey );
    String getUploadInsert(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues);
    String getUploadInsert();
    String getUploadInsert(Boolean includePrimaryKey );

    QueryResult getQueryByCols(List<ColumnTemplate> byColumns, EntityManager entityManager) throws SQLException;
    QueryResult getQueryByCols(List<ColumnTemplate> byColumns,List<KdbColumnPersona> getColumns, EntityManager entityManager) throws SQLException;
    QueryResult getQuery(List<KdbColumnPersona> getColumns, EntityManager entityManager) throws SQLException;

    QueryResult getQueryByCols(List<ColumnTemplate> byColumns) throws SQLException;
    QueryResult getQueryByCols(List<ColumnTemplate> byColumns,List<KdbColumnPersona> getColumns) throws SQLException;
    QueryResult getQuery(List<KdbColumnPersona> getColumns) throws SQLException;

//    String getDependencies();

}


