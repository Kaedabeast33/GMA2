package org.example.bank.OutputClassBank;

import org.example.ClassOutputCreator.templates.MATemplate;
import org.example.ClassOutputCreator.templates.ai.AiColumnTemplate;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.bank.commonValues.Identifier;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface AiMaInterface {
    String getPrefix();

    String getName();
    String getCustom();
    String getDescription();
    String[] getTags();
    String[] getTypes();
    Identifier getIdentifier();

    String getSchema();

    List<AiColumnTemplate> getAllCols();

    MAJson getMAJson();
    MATemplate getMATemplate();

    List<AiColumnTemplate> getAllKeysByUploadType(String uploadType);

    List<AiColumnTemplate> getAllKeys();
    List<AiColumnTemplate> getPrimaryKeys();

    List<AiColumnTemplate> getKeys();

    String getRawTableName();
    String getInputsTableName();
    String getRawInputsTableName();

    String getMtmPgIgTableName();
    String getMtmIgItTableName();
    String getMtmItInTableName();
    String getMtmInIvTableName();

    String getPgTableName();
    String getIgTableName();
    String getItTableName();
    String getInTableName();
    String getIvTableName();

    Future<Double[]> embedValue(String value) throws ExecutionException, InterruptedException;

    Future<Double[]> embedFile(File file, String fileType) throws ExecutionException, InterruptedException;

    Future<Double[]> embedValues(List<String> values);

    boolean checkKeys();






}
