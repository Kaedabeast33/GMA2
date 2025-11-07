package org.example.bank.OutputClassBank;

import org.example.ClassOutputCreator.templates.UpsertTemplate;

import java.text.ParseException;
import java.util.List;
import java.util.function.Supplier;

public interface EntityInterface {

    String getValues(String arg) throws ParseException;

    String getValues() throws ParseException;

    String replaceCharacters(String value);

    String getTableName();

    List<String> getColumns();

    List<String> getColumnsByGroupName(String groupName);

    List<String> getUniqueIdentifierColumns();

    List<String> getUniqueIdentifierColumnsByGroupName(String groupName);

    //    List<String> getIndexes();
//    String getIdName();
    String getDescription();
    UpsertTemplate getUpsert();
//    String getDependencies();

}
