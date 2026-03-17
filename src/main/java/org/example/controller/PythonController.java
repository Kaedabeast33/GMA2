//package org.example.controller;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import org.example.ClassOutputCreator.templates.ColumnTemplate;
//import org.example.bank.OutputClassBank.KdbColumnPersona;
//import org.example.bank.OutputClassBank.QueryResult;
//import org.example.bank.db.contextObj.ContextObj;
//import org.example.service.RAG.ContextGrabber.ContextService;
//
//import org.example.service.RAG.ContextGrabber.VectorQueryResult;
//import org.example.service.RAG.ContextGrabber.VectorService;
//import org.example.service.RAG.Python.PythonService;
////import org.example.service.RAG.upload.UploadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//import static org.example.bank.commonValues.ColumnConverter.toPersonaTemplate;
//
//@RestController
//@RequestMapping("/gma/v1/python")
//public class PythonController {
//
//    @Autowired
//
//
//    ContextService contextService;
//
////    @Autowired
////    UploadService service;
//
//    @Autowired
//    PythonService pythonService;
//
//    @Autowired
//    VectorService vectorService;
//
//
//
//    @PostMapping("/context")
//    public String helloPython(@RequestBody ContextObj json) {
//        List<String> qs = contextService.buildSelectString(json);
//
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
//                .create();
//
//        return gson.toJson(contextService.getQueryResultsRaw(qs));
//    }
//
//    @GetMapping("/matchTest")
//    public ResponseEntity<String> matchTest() throws SQLException {
//
//
//
//        vectorService.queryVectorSearch("\"DEXA T-Score values and diagnostic thresholds for osteoporosis and osteopenia\"\n", 1);
//
//
//
//        return ResponseEntity.ok("Match test successful");
//    }
//
//    @PostMapping("/contextFromQuery")
//    public String contextFromQuery(@RequestParam String query) {
////        List<String> qs = contextService.buildSelectString(json);
//
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
//                .create();
//
//        return gson.toJson(contextService.getQueryResults(List.of(query)));
//    }
//
//    @GetMapping("/checkConnection")
//    public ResponseEntity<String> uploadJson() throws Exception {
//        pythonService.checkConnection();
//        return ResponseEntity.ok("Connection to Python service is successful");
//
//    }
//
//
//}
