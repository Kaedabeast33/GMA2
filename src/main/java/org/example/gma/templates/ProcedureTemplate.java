package org.example.gma.templates;



abstract class ProcedureTemplate extends QueryTemplate {



    public ProcedureTemplate(String name, String description, String[] tags, String queId, String queryType, String[] queryGroups, String query) {
        super(name, description, tags, queId, queryType, queryGroups, query);
    }




    //package org.example.Annotations;
    //
    //public @interface KdbProcedure {
    //    String name();
    //
    //    String description();
    //
    //    String[] tags();
    //
    //    String procedureType();
    //
    //    String[] procedureGroups();
    //
    //    int[] procedureGroupsOrder();
    //
    //    String[] procedureSet();
    //}

}
