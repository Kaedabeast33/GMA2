package org.example.bank.OutputClassBank;

import org.example.ClassOutputCreator.templates.ai.AiColumnTemplate;
import org.example.ClassOutputCreator.templates.airtable.AirColumnTemplate;

import java.util.List;

public interface AiMaInterface {
    String getName();
    String getDescription();
    String[] getTags();

    List<AiColumnTemplate> getAllKeys();
        List<AiColumnTemplate> getAllPrimaryKeys();




}
