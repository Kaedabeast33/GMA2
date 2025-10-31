package org.example.classbuilder.builder;

import org.example.jsonBuilder.gma.GMAJson;

import java.util.Arrays;

import static org.example.classbuilder.builder.ClassCreator.wrapWithQuotes;

public class GmaClassBuilder {
    //                            super(name, description, tags, gmaId, dbType, dialect);
    static void generateGmaFields(GMAJson gma){
        String className = "GMA_" + gma.getName();
        String classFormat = String.format("""
                    public class %s extends GMATemplate {
                
                        public %s {
                            super(%s, %s, %s, %s, %s, %s);
                        }
                    }
                """
        ,
                className,
                className,
                wrapWithQuotes(gma.getName()),
                wrapWithQuotes(gma.getDescription()),
                Arrays.toString(wrapWithQuotes(gma.getTags())),
                wrapWithQuotes(gma.getGmaId()),
                wrapWithQuotes(gma.getGmaSettings().get("DbType")) ,
                wrapWithQuotes(gma.getGmaSettings().get("Dialect"))
        );
//        System.out.println(classFormat);
    }
}
