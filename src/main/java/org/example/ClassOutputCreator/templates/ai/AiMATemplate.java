package org.example.ClassOutputCreator.templates.ai;

import org.example.ClassOutputCreator.templates.MATemplate;
//import org.example.ClassOutputCreator.templates.ai.blanks.BlankInputTableTemplate;
//import org.example.ClassOutputCreator.templates.ai.blanks.BlankRawInputTableTemplate;
//import org.example.ClassOutputCreator.templates.ai.blanks.BlankRawTableTemplate;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.ai.AiRagSchemaJson;
import org.example.ai.registry.embed.EmbedMethod;
import org.example.ai.registry.embed.MetaEmbed;
import org.example.ai.registry.skeleton.SkeletonUploadMethod;
import org.example.bank.JsonBuilderRef.EntityValue;
import org.example.bank.OutputClassBank.AiMaInterface;
import org.example.bank.OutputClassBank.KDBContext;
import org.example.bank.commonValues.Identifier;
import org.example.bank.commonValues.UploadTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.example.ai.registry.embed.MetaEmbed.MAIN_EMBED;
import static org.example.ai.registry.skeleton.MetaSkeletonUpload.MAIN_SKELETON_UPLOAD;
import static org.example.bank.AppConfig.getGmaName;
import static org.example.bank.OutputClassBank.KdbColumnWrapper.safeGetValue;

public class AiMATemplate implements AiMaInterface {

    KDBContext kdbContext = KDBContext.KDB_CONTEXT;

    protected final String name;
    protected final String custom;


    protected final String description;
    protected final String[] tag;
    protected final String maId;
//    protected final BlankInputTableTemplate blankInputTableTemplate = new BlankInputTableTemplate();
//    protected final BlankRawTableTemplate blankRawTableTemplate = new BlankRawTableTemplate();
//    protected  final BlankRawInputTableTemplate blankRawInputTableTemplate = new BlankRawInputTableTemplate();
    protected final List <AiColumnTemplate> keys;
    protected final List <AiColumnTemplate> primaryKeys;
    protected final List<AiColumnTemplate> cols;
    protected  final String[] types;
    protected final Identifier identifier;

    protected final String schema;
    EmbedMethod embedMethod = MAIN_EMBED.getEmbedMethod();
    SkeletonUploadMethod skeletonUploadMethod = MAIN_SKELETON_UPLOAD.getSkeletonUploadMethod();

    public AiMATemplate(String name, String custom, String description, String[] tag, String maId, List<AiColumnTemplate> keys, List<AiColumnTemplate> primaryKeys, List<AiColumnTemplate> cols, String[] types, String schema) {
        this.name = name;
        this.custom = custom;
        this.description = description;
        this.tag = tag;
        this.cols = cols;
        this.types = types;
        this.maId = maId;
        this.keys = keys;
        this.primaryKeys = primaryKeys;
        this.identifier = new Identifier(getGmaName(),name,null);
        this.schema = schema;
    }



    public void setCol(AiColumnTemplate template) throws Exception {
        System.out.println("set COL"+template.getEntityValue().getType());
        List<List<AiColumnTemplate>> sources = new ArrayList<>();
        if (cols != null) sources.add(cols);
        if (keys != null) sources.add(keys);
        if (primaryKeys != null) sources.add(primaryKeys);

        for (List<AiColumnTemplate> list : sources) {
            for (AiColumnTemplate col : list) {
                if (Objects.equals(template.getName(), col.getName())) {
                    col.setEntityValueDirect(template.getEntityValue());
                    return;
                }
            }
        }
        throw new Exception("Column not found: " + template.getName());
    }


    @Override
    public String getPrefix(){
        return "c_"+getCustom();
    }

    @Override
    public String getName() {
        if(getCustom() != null && !getCustom().isEmpty()){
            return getPrefix() + "_" + this.name;
        }
        return this.name;
    }

    @Override
    public String getCustom() {
        return this.custom;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String[] getTags() {
        return this.tag;
    }

    @Override
    public String[] getTypes() {
        return this.types;
    }

    @Override
    public Identifier getIdentifier() {
        return this.identifier;
    }
    @Override
    public String getSchema() {
        return schema;
    }

    @Override
    public List<AiColumnTemplate> getAllCols(){
        List<AiColumnTemplate> list = new ArrayList<>();
        if(cols!=null){
            list.addAll(cols);
        }
        if(keys!=null){
            list.addAll(keys);
        }if(primaryKeys!=null) {
            list.addAll(primaryKeys);
        }
        return list;
    }

    @Override
    public MAJson getMAJson() {
        GMAJson gma =  kdbContext.getGmaByName(getGmaName());
        Identifier identifier1 = new Identifier(getGmaName(),schema,null);
        MAJson ma =  gma.getGmaObject(identifier1,MAJson.class);
        return   ma;

    }

    @Override
    public MATemplate getMATemplate() {
        return null;
    }



    @Override
    public List<AiColumnTemplate> getAllKeysByUploadType(String uploadType) {
        return getAllKeys().stream().filter(k-> k.getUploadTypes()!=null && List.of(k.getUploadTypes()).contains(uploadType)).toList();
    }


    @Override
    public List<AiColumnTemplate> getAllKeys() {

        List<AiColumnTemplate> list = new ArrayList<>();
        if(keys!=null){
            list.addAll(keys);
        }if(primaryKeys!=null) {
            list.addAll(primaryKeys);
        }
        return list;

    }

    @Override
    public List<AiColumnTemplate> getPrimaryKeys() {
            return this.primaryKeys;
    }

    @Override
    public List<AiColumnTemplate> getKeys() {
        return this.keys;
    }


private String buildName(String name) {
        return this.getName()+"_"+name;

}

    @Override
    public String getRawTableName() {

        return buildName(UploadTypes.RAW);
    }

    @Override
    public String getInputsTableName() {
        return buildName(UploadTypes.INPUTS);
    }

    @Override
    public String getRawInputsTableName() {
        return buildName(UploadTypes.RAW_INPUTS);
    }

    @Override
    public String getMtmPgIgTableName() {
        return buildName(UploadTypes.MTM_PGIG);
    }

    @Override
    public String getMtmIgItTableName() {
        return buildName(UploadTypes.MTM_IGIT);
    }

    @Override
    public String getMtmItInTableName() {
        return buildName(UploadTypes.MTM_ITIN);
    }

    @Override
    public String getMtmInIvTableName() {
        return buildName(UploadTypes.MTM_INIV);
    }

    @Override
    public String getPgTableName() {
        return buildName(UploadTypes.PG);
    }

    @Override
    public String getIgTableName() {
        String tableName = buildName(UploadTypes.IG);

        return tableName;
    }

    @Override
    public String getItTableName() {
        return buildName(UploadTypes.IT);
    }

    @Override
    public String getInTableName() {
        return buildName(UploadTypes.IN);
    }

    @Override
    public String getIvTableName() {
        return buildName(UploadTypes.IV);
    }

    public Future<AiRagSchemaJson> runSkeletonUploadMethod(List<File> files, String uploadGroup,String mimeType,String fullName) throws ExecutionException, InterruptedException {
        SkeletonUploadMethod.DbSkeletonDto dto = new SkeletonUploadMethod.DbSkeletonDto(files,uploadGroup,mimeType,fullName);

        return MAIN_SKELETON_UPLOAD.getSkeletonUploadMethod().runGetSkeleton(dto);
    }

    @Override
    public Future<Double[]> embedValue(String value) throws ExecutionException, InterruptedException {

        return MAIN_EMBED.getEmbedMethod().runEmbed(value);
    }

    @Override
    public Future<Double[]> embedFile(File file, String fileType) throws ExecutionException, InterruptedException {
        return MAIN_EMBED.getEmbedMethod().runEmbedFile(file,fileType);
    }

    @Override
    public Future<Double[]> embedValues(List<String> values) {
        Future<Double[]> embedding;
        try {
            StringBuilder sb = new StringBuilder();
            for (String value : values) {

                sb.append(value).append(" \n");
            }
            String embeddingStr = sb.toString();

            embedding = MetaEmbed.MAIN_EMBED.getEmbedMethod().runEmbed(embeddingStr);
            if (embedding == null) {
                throw new RuntimeException("Received null embedding from Python service for column: " + this.name);
            }
            return embedding;
        } catch (Exception e){
            throw new RuntimeException("Failed to get embedding for column: " + this.name, e);
        }

    }

    @Override
    public boolean checkKeys() {
        for(AiColumnTemplate key: getAllKeys()){
            if(key.getEntityValue().getValue()==null || key.getEntityValue().getValue()=="null" || key.getEntityValue().getValue().equals("")){
                return false;
            }
        }
        return true;
    }
}









































