package org.example.CommonValues.KdbConverter;


import org.example.CommonValues.EntityValue;

public interface KdbConverter<S, T> {
    EntityValue<T> convert(S input) throws Exception;
}
