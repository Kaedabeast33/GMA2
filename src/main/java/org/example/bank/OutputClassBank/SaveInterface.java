package org.example.bank.OutputClassBank;

import org.example.bank.JsonBuilderRef.EntityValue;

import java.text.ParseException;
import java.util.List;

public interface SaveInterface {
    String getValues(String arg) throws ParseException, ClassNotFoundException;

    String getValues() throws ParseException, ClassNotFoundException;
    String getMaName();
    List<String> getColumnsString();
    String getTableName();
    String getUpsertName();


}
