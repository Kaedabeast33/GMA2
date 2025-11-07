package org.example.bank.KdbConverter;

import org.example.bank.JsonBuilderRef.EntityValue;

public interface KdbConverter<S, T> {
    EntityValue<T> convert(S input) throws Exception;
}
