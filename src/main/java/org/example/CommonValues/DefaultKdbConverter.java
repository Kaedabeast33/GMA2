package org.example.CommonValues;

import org.example.CommonValues.KdbConverter.KdbConverter;

public class DefaultKdbConverter implements KdbConverter<Object, Object> {
    @Override
    public EntityValue<Object> convert(Object input) {
        return null;
    }
}
