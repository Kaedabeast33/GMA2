package org.example.ClassOutputCreator.templates;

import org.example.bank.OutputClassBank.AirtableInterface;

import java.util.List;


public abstract class AirTableTemplate implements AirtableInterface {
    protected final String name;
    protected final String description;
    protected final String[] tag;
    protected final String tableId;
    protected final String appId;

    protected final String gmaName;
    protected final String maName;

//    protected final String upsert;


    public AirTableTemplate(String name, String description, String[] tag, String tableId, String appId, String gmaName, String maName) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.tableId = tableId;
        this.appId = appId;
        this.gmaName = gmaName;
        this.maName = maName;


    }


//    private static final Map<String, Function<String, Object>> TYPE_PARSERS = new HashMap<>();
//
//
//    static {
//        TYPE_PARSERS.put(ValueTypes.STRING, v -> v);
//        TYPE_PARSERS.put(ValueTypes.INTEGER, Integer::parseInt);
//        TYPE_PARSERS.put(ValueTypes.BOOLEAN, Boolean::parseBoolean);
//        TYPE_PARSERS.put(ValueTypes.FLOAT, Float::parseFloat);
//        TYPE_PARSERS.put(ValueTypes.DOUBLE, Double::parseDouble);
//        TYPE_PARSERS.put(ValueTypes.BYTE, Byte::parseByte);
//        TYPE_PARSERS.put(ValueTypes.LONG, Long::parseLong);
//        TYPE_PARSERS.put(ValueTypes.DATE, LocalDate::parse);
//        TYPE_PARSERS.put(ValueTypes.DATETIME, LocalDateTime::parse);
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T> T getColumnValue(ColumnTemplate col) {
//        if (col.getEntityValue() == null || col.getType() == null) return null;
//
//        Function<String, Object> parser = TYPE_PARSERS.get(type.toUpperCase());
//        Object parsed = parser != null ? parser.apply(value) : value;
//
//        return (T) parsed; // Caller should use correct expected type
//    }


//}


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String[] getTags() {
        return tag;
    }


    public List<AirColumnTemplate> getAllCollumns() {
        return List.of();
    }


    @Override
    public String getTableId() {
        return tableId;
    }

    @Override
    public String getAppId() {
        return appId;
    }





    public String getGmaName() {
        return gmaName;
    }

    public String getMaName() {
        return maName;
    }

    public abstract List<AirColumnTemplate> getAllColumns();
}
