package org.example.bank.OutputClassBank;

import java.util.List;

public interface KdbColumnPersona {


        String getName();

        String getRealName();

        boolean isNullable();

        boolean isEditable();

        boolean isUnique();

        boolean isRequired();

        String getType();

        Object getDefaultValue();

        boolean isIndex();

        boolean isPrimaryKey();

        List<String> getQueryMatchStrings();





}
