package org.example.service.RAG.Parser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.bank.OutputClassBank.EntityInterface;
import org.example.bank.OutputClassBank.KdbColumnPersona;
import org.example.bank.OutputClassBank.QueryResult;
import org.example.output.vyta.ai.parse_groups.TAB_parse_groups;
import org.example.service.RAG.Parser.ParserGroupJson.UploadGroupJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class AiUploadGroupInferenceService {
    @PersistenceContext(unitName = "entityManagerFactoryAi")
    EntityManager entityManager;

    public String getGroupInference(){

        return "";
    }

    public String getUploadGroups() throws SQLException {
        TAB_parse_groups parseGroups =  new TAB_parse_groups();
        List<KdbColumnPersona> getCols = List.of(parseGroups.getCOL_upload_group(),
                                                 parseGroups.getCOL_description()
                                                 );
        List<ColumnTemplate> byCols = List.of(parseGroups.getCOL_is_active().setQueryMatchStrings(List.of("1")));

        QueryResult q = parseGroups.getQueryByCols(byCols,getCols);

        System.out.println(q.getData());
        return q.getData().toString();
    }

    public String getPromptSchema(Boolean onlyActive) {
        if(onlyActive !=true){
            return "";
        }else{
         return "";
        }
    }


    @Transactional(transactionManager = "transactionManagerAi")
    public void postUploadGroup(UploadGroupJson uploadGroupJson) throws Exception {

        uploadGroupJson.dbInsert(entityManager);
    }
}
