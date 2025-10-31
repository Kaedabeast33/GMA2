package org.example.gma.templates;

import java.util.UUID;

public abstract class SetTemplate {
    String name;
    String description;
    String[] tags;
    String setId;

    QueryTemplate[] querySet;
    ProcedureTemplate[] procedureSet;
    TableTemplate[] tableSet;



}
