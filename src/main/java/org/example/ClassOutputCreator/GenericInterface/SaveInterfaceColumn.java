package org.example.ClassOutputCreator.GenericInterface;

import org.example.bank.JsonBuilderRef.EntityValue;

public interface SaveInterfaceColumn {
    String getName();
    boolean isPrimaryKey();


    EntityValue<?> getEntityValue();

    boolean isEmbedding();

    Object getDefaultValue();
}
