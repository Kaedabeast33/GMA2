package org.example.CommonValues.KdbConverter;

import org.example.CommonValues.EntityValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class StringToDateTimeConverter implements KdbConverter<Object, LocalDateTime> {

    // List of supported input patterns
    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"), // DATETIME(6)
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),       // DATETIME
            DateTimeFormatter.ISO_LOCAL_DATE                          // yyyy-MM-dd
    );

    @Override
    public EntityValue<LocalDateTime> convert(Object input) {
        if (input == null || input.toString().trim().isEmpty()) {
            // return EntityValue with null value but valid type
            return new EntityValue<>(null, LocalDateTime.class);
        }

        String s = input.toString().trim();

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                LocalDateTime dt;
                if (formatter == DateTimeFormatter.ISO_LOCAL_DATE) {
                    dt = LocalDate.parse(s, formatter).atStartOfDay();
                } else {
                    dt = LocalDateTime.parse(s, formatter);
                }
                return new EntityValue<>(dt, LocalDateTime.class);
            } catch (Exception ignored) {}
        }

        throw new IllegalArgumentException("Cannot parse date/time: " + s);
    }

}
