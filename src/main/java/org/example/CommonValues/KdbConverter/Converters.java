package org.example.CommonValues.KdbConverter;

import org.example.CommonValues.EntityValue;


import javax.swing.text.html.parser.Entity;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converters {

    public static final KdbConverter<Object, String> STRING = input ->
            new EntityValue<>(input == null ? null : input.toString(), String.class);

    public static final KdbConverter<Object, Integer> INTEGER = input -> {
        if (input == null) return new EntityValue<>(null, Integer.class);
        if (input instanceof Integer) return new EntityValue<>((Integer) input, Integer.class);
        return new EntityValue<>(Integer.parseInt(input.toString()), Integer.class);
    };

    public static final KdbConverter<Object, Long> LONG = input -> {
        if (input == null) return new EntityValue<>(null, Long.class);
        if (input instanceof Long) return new EntityValue<>((Long) input, Long.class);
        return new EntityValue<>(Long.parseLong(input.toString()), Long.class);
    };

    public static final KdbConverter<Object, Float> FLOAT = input -> {
        if (input == null) return new EntityValue<>(null, Float.class);
        if (input instanceof Float) return new EntityValue<>((Float) input, Float.class);
        return new EntityValue<>(Float.parseFloat(input.toString()), Float.class);
    };

    public static final KdbConverter<Object, Double> DOUBLE = input -> {
        if (input == null) return new EntityValue<>(null, Double.class);
        if (input instanceof Double) return new EntityValue<>((Double) input, Double.class);
        return new EntityValue<>(Double.parseDouble(input.toString()), Double.class);
    };

    public static final KdbConverter<Object, Boolean> BOOLEAN = input -> {
        if (input == null) return new EntityValue<>(null, Boolean.class);
        if (input instanceof Boolean) return new EntityValue<>((Boolean) input, Boolean.class);
        return new EntityValue<>(input.toString().equals("1") || input.toString().equalsIgnoreCase("true"), Boolean.class);
    };

    public static final KdbConverter<Object, Byte> BYTE = input -> {
        if (input == null) return new EntityValue<>(null, Byte.class);
        if (input instanceof Byte) return new EntityValue<>((Byte) input, Byte.class);
        return new EntityValue<>(Byte.parseByte(input.toString()), Byte.class);
    };

    public static final KdbConverter<Object, LocalDate> DATE = input -> {
        if (input == null) return new EntityValue<>(null, LocalDate.class);
        if (input instanceof LocalDate) return new EntityValue<>((LocalDate) input, LocalDate.class);
        return new EntityValue<>(LocalDate.parse(input.toString()), LocalDate.class);
    };

    public static final KdbConverter<Object, Timestamp> TIMESTAMP = input->{
        if(input==null) return new EntityValue<>(null, Timestamp.class);
        if(input instanceof Timestamp) return new EntityValue<>((Timestamp) input, Timestamp.class);
        return new EntityValue<>(Timestamp.valueOf(input.toString()), Timestamp.class);
    };

    public static final KdbConverter<Object, LocalDateTime> DATETIME = input -> {
        if (input == null) return new EntityValue<>(null, LocalDateTime.class);
        if (input instanceof LocalDateTime) return new EntityValue<>((LocalDateTime) input, LocalDateTime.class);
        return new EntityValue<>(LocalDateTime.parse(input.toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), LocalDateTime.class);
    };

    public static final KdbConverter<Object, LocalDateTime> DATETIME6 = input -> {
        if (input == null) return new EntityValue<>(null, LocalDateTime.class);
        if (input instanceof LocalDateTime) return new EntityValue<>((LocalDateTime) input, LocalDateTime.class);
        return new EntityValue<>(LocalDateTime.parse(input.toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")), LocalDateTime.class);
    };

    public static final KdbConverter<Object, String> TEXT = input ->
            new EntityValue<>(input == null ? null : input.toString(), String.class);

    public static final KdbConverter<Object, byte[]> BLOB = input -> {
        if (input == null) return new EntityValue<>(null, byte[].class);
        if (input instanceof byte[]) return new EntityValue<>((byte[]) input, byte[].class);
        return new EntityValue<>(input.toString().getBytes(), byte[].class);
    };

}
