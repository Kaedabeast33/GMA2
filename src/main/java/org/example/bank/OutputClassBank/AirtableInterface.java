package org.example.bank.OutputClassBank;



import org.example.ClassOutputCreator.templates.airtable.AirColumnTemplate;

import java.util.List;

public interface AirtableInterface  {

    String getName();
     String getDescription();
     String[] getTags();

 List<AirColumnTemplate> getAllColumns();

 String getTableId();
     String getAppId();

}
