package org.example.controller;

import org.example.bank.db.PythonContextBuilderJson;
import org.example.service.RAG.ContextGrabber.ContextService;
import org.example.bank.db.contextObj.ContextObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gma/v1/python")
public class PythonController {

    @Autowired
    ContextService contextService;

    @PostMapping("/context")
    public String helloPython(@RequestBody ContextObj json) {
        contextService.buildSelectString(json);

        return "Hello from Python Controller!";
    }

}
