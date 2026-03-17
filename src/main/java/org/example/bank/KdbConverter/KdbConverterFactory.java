package org.example.bank.KdbConverter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class KdbConverterFactory {

    private static final Map<String, KdbConverter<?, ?>> CONVERTER_MAP = new HashMap<>();

    static {
        CONVERTER_MAP.put("VARCHAR", Converters.STRING);

        CONVERTER_MAP.put("TEXT", Converters.TEXT);
        CONVERTER_MAP.put("INT", Converters.INTEGER);
        CONVERTER_MAP.put("BIGINT", Converters.LONG);
        CONVERTER_MAP.put("FLOAT", Converters.FLOAT);
        CONVERTER_MAP.put("DOUBLE", Converters.DOUBLE);
        CONVERTER_MAP.put("TINYINT", Converters.BOOLEAN);
        CONVERTER_MAP.put("TIMESTAMP", Converters.TIMESTAMP);
        CONVERTER_MAP.put("VECTOR", Converters.VECTOR);

        CONVERTER_MAP.put("DATE", Converters.DATE);
        CONVERTER_MAP.put("DATETIME", Converters.DATETIME);
        CONVERTER_MAP.put("DATETIME(6)", Converters.DATETIME6);
        CONVERTER_MAP.put("BLOB", Converters.BLOB);
        CONVERTER_MAP.put("TIME(6)", Converters.TIME6);
        CONVERTER_MAP.put("JSON",Converters.TEXT);


    }


    private static final Map<String, KdbConverter<?, ?>> FIELD_TYPE_CONVERTER_MAP = new HashMap<>();

    static {
        // populate field type converter map (was erroneously putting into CONVERTER_MAP)
        FIELD_TYPE_CONVERTER_MAP.put(String.class.getSimpleName(), Converters.STRING);
        FIELD_TYPE_CONVERTER_MAP.put(LocalDateTime.class.getSimpleName(), Converters.DATETIME);
            FIELD_TYPE_CONVERTER_MAP.put(Integer.class.getSimpleName(), Converters.INTEGER);
            FIELD_TYPE_CONVERTER_MAP.put(Boolean.class.getSimpleName(), Converters.TRUEBOOLEAN);
    }

    @SuppressWarnings("unchecked")
    public static <T> KdbConverter<Object, T> getConverter(String mysqlType) {
        if(mysqlType.contains("VARCHAR")) {
            mysqlType = "VARCHAR";
        }

        if(mysqlType.contains("VECTOR")) {
            mysqlType = "VECTOR";
        }

        return (KdbConverter<Object, T>) CONVERTER_MAP.get(mysqlType.toUpperCase());
    }

    @SuppressWarnings("unchecked")
    public static <T> KdbConverter<Object, T> getConverter(Class<?> mysqlType) {
        // If caller passed null, return default String converter
        if (mysqlType == null) {
            return (KdbConverter<Object, T>) Converters.STRING;
        }

        KdbConverter<?, ?> conv = FIELD_TYPE_CONVERTER_MAP.get(mysqlType.getSimpleName());
        if (conv == null) {
            return (KdbConverter<Object, T>) Converters.STRING;
        }
        return (KdbConverter<Object, T>) conv;
    }



}
