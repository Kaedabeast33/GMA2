package org.example.classbuilder;

import java.text.ParseException;
import java.util.List;

public interface EntityInterface  {

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
//    String getDependencies();

}
