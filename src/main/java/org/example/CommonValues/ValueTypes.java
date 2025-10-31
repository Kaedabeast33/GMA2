package org.example.CommonValues;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ValueTypes {
    public static final String STRING = "VARCHAR(255)";       // generic string type
    public static final String TEXT = "TEXT";                 // longer text
    public static final String INTEGER = "INT";               // INT
    public static final String LONG = "BIGINT";               // BIGINT
    public static final String FLOAT = "FLOAT";               // FLOAT
    public static final String DOUBLE = "DOUBLE";             // DOUBLE
    public static final String BOOLEAN = "TINYINT";        // BOOLEAN / BIT
    public static final String BYTE = "TINYINT";              // BYTE
    public static final String DATE = "DATE";                 // DATE
    public static final String DATETIME = "DATETIME";         // DATETIME without fractional seconds
    public static final String DATETIME6 = "DATETIME(6)";     // DATETIME with microseconds
    public static final String BLOB = "BLOB";                 // Binary Large Object
    public static final  String VARCHAR50 = "VARCHAR(50)";     // VARCHAR with length 50
    public static final String VARCHAR100 = "VARCHAR(100)";   // VARCHAR with length 100
    public static final String VARCHAR150 = "VARCHAR(150)";   // VARCHAR with length 150
    public static final String MONEY = "DECIMAL(21,2)";   // VARCHAR with length 200
    public static final String DECIMAL_10_4 = "DECIMAL(10,4)";   // DECIMAL with precision 10 and scale 4
    public static final String DECIMAL_9_9 = "DECIMAL(9,9)";   // DECIMAL with precision 9 and scale 9
    public static final String DECIMAL_23_0 = "DECIMAL(23,0)";   // DECIMAL with precision 9 and scale 9
    public static final String MEDIUMTEXT = "MEDIUMTEXT";       // MEDIUMTEXT for very long text
    public static final String TIMESTAMP = "TIMESTAMP";       // MEDIUMTEXT for very long text
    public static  final String TIME = "TIME(6)";                 // TIME for time values


    public static final Map<Class<?>,String> TYPE_MAP = new HashMap<>();


    static {
        TYPE_MAP.put(String.class,ValueTypes.STRING);
        TYPE_MAP.put(Integer.class,ValueTypes.INTEGER);
        TYPE_MAP.put(Boolean.class,ValueTypes.BOOLEAN);
        TYPE_MAP.put(Float.class,ValueTypes.FLOAT);
        TYPE_MAP.put(Double.class,ValueTypes.DOUBLE);
        TYPE_MAP.put(Byte.class,ValueTypes.BYTE);
        TYPE_MAP.put(Long.class,ValueTypes.LONG);
        TYPE_MAP.put(LocalDate.class,ValueTypes.DATE);
        TYPE_MAP.put(LocalTime.class,ValueTypes.TIME);
        TYPE_MAP.put(LocalDateTime.class,ValueTypes.DATETIME);
        TYPE_MAP.put(Timestamp.class,ValueTypes.TIMESTAMP);
        TYPE_MAP.put(BigDecimal.class,ValueTypes.DECIMAL_23_0);


    }

    public static final Map<String, String> FIELD_TYPE_MAP = new HashMap<>();

    static {
        FIELD_TYPE_MAP.put("String", "java.lang.String.class");
        FIELD_TYPE_MAP.put("Integer", "java.lang.Integer.class");
        FIELD_TYPE_MAP.put("Boolean", "java.lang.Boolean.class");
        FIELD_TYPE_MAP.put("Float", "java.lang.Float.class");
        FIELD_TYPE_MAP.put("Double", "java.lang.Double.class");
        FIELD_TYPE_MAP.put("Byte[]", "java.lang.Byte[].class");
        FIELD_TYPE_MAP.put("Long", "java.lang.Long.class");
        FIELD_TYPE_MAP.put("LocalDate", "java.time.LocalDate.class");
        FIELD_TYPE_MAP.put("LocalDateTime", "java.time.LocalDateTime.class");
        FIELD_TYPE_MAP.put("Timestamp", "java.sql.Timestamp.class");
        FIELD_TYPE_MAP.put("BigDecimal", "java.math.BigDecimal.class");
    }



}
