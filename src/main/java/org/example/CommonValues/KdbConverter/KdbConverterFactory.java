package org.example.CommonValues.KdbConverter;

import java.util.HashMap;
import java.util.Map;

public class KdbConverterFactory {

    private static final Map<String, KdbConverter<?, ?>> CONVERTER_MAP = new HashMap<>();

    static {
        CONVERTER_MAP.put("VARCHAR(255)", Converters.STRING);
        CONVERTER_MAP.put("TEXT", Converters.TEXT);
        CONVERTER_MAP.put("INT", Converters.INTEGER);
        CONVERTER_MAP.put("BIGINT", Converters.LONG);
        CONVERTER_MAP.put("FLOAT", Converters.FLOAT);
        CONVERTER_MAP.put("DOUBLE", Converters.DOUBLE);
        CONVERTER_MAP.put("TINYINT", Converters.BOOLEAN);

        CONVERTER_MAP.put("DATE", Converters.DATE);
        CONVERTER_MAP.put("DATETIME", Converters.DATETIME);
        CONVERTER_MAP.put("DATETIME(6)", Converters.DATETIME6);
        CONVERTER_MAP.put("BLOB", Converters.BLOB);

    }

    @SuppressWarnings("unchecked")
    public static <T> KdbConverter<Object, T> getConverter(String mysqlType) {
        return (KdbConverter<Object, T>) CONVERTER_MAP.get(mysqlType);
    }


}
