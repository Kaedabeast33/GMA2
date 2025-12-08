package org.example.bank.commonValues;



import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;
import org.example.bank.OutputClassBank.KdbColumnPersona;

import java.util.List;

public class ColumnConverter {
    public static List<KdbColumnPersona> toPersonaJson(List<ColumnJson> columnJsonList){
        // Implementation to convert ColumnJson list to Persona JSON format
        return columnJsonList.stream().map(c->(KdbColumnPersona) c).toList();

    }

    public static List<KdbColumnPersona> toPersonaTemplate(List<ColumnTemplate> columnTemplateList){
        // Implementation to convert ColumnTemplate list to Persona Template format
        return columnTemplateList.stream().map(c->(KdbColumnPersona) c).toList();

    }
}
