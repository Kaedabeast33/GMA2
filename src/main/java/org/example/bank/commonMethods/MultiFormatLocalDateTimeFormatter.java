package org.example.bank.commonMethods;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

public final class MultiFormatLocalDateTimeFormatter {

    private static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US),
            DateTimeFormatter.ofPattern("M/d/yyyy", Locale.US),
            DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US)
    );

    private static final List<DateTimeFormatter> LOCAL_DATETIME_FORMATTERS = List.of(
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.US),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    );

    private MultiFormatLocalDateTimeFormatter() {}

    /**
     * Parse an input string into LocalDateTime. Date-only inputs become start-of-day.
     */
    public static LocalDateTime parseDateAndTime(String input) {
        if (input == null) return null;
        String s = input.trim();
        if (s.isEmpty()) return null;

        // Try date-only formatters first -> return start of day
        for (DateTimeFormatter df : DATE_FORMATTERS) {
            try {
                LocalDate ld = LocalDate.parse(s, df);
                return ld.atStartOfDay();
            } catch (DateTimeParseException ignored) {}
        }

        return parseToLocalDateTime(s);
    }

    public static LocalDateTime parseToLocalDateTime(String input) {
        if (input == null) return null;
        String s = input.trim();
        if (s.isEmpty()) return null;

        ZoneId zone = ZoneId.of("UTC");

        // numeric epoch (seconds or milliseconds)
        if (s.matches("^-?\\d+$")) {
            try {
                long n = Long.parseLong(s);
                Instant inst;
                if (Math.abs(n) < 100_000_000_000L) { // seconds heuristic
                    inst = Instant.ofEpochSecond(n);
                } else {
                    inst = Instant.ofEpochMilli(n);
                }
                return LocalDateTime.ofInstant(inst, zone);
            } catch (NumberFormatException ignored) {}
        }

        // Try ISO instant
        try {
            Instant inst = Instant.parse(s);
            return LocalDateTime.ofInstant(inst, zone);
        } catch (DateTimeParseException ignored) {}

        // Try OffsetDateTime / ZonedDateTime
        try {
            OffsetDateTime odt = OffsetDateTime.parse(s);
            Instant inst = odt.toInstant();
            return LocalDateTime.ofInstant(inst, zone);
        } catch (DateTimeParseException ignored) {}
        try {
            ZonedDateTime zdt = ZonedDateTime.parse(s);
            Instant inst = zdt.toInstant();
            return LocalDateTime.ofInstant(inst, zone);
        } catch (DateTimeParseException ignored) {}

        // Try local date-time formats
        for (DateTimeFormatter fmt : LOCAL_DATETIME_FORMATTERS) {
            try {
                LocalDateTime ldt = LocalDateTime.parse(s, fmt);
                return ldt;
            } catch (DateTimeParseException ignored) {}
        }

        // Try local date-only -> start of day
        for (DateTimeFormatter df : DATE_FORMATTERS) {
            try {
                LocalDate ld = LocalDate.parse(s, df);
                return ld.atStartOfDay();
            } catch (DateTimeParseException ignored) {}
        }

        // Try common patterns with space between date/time
        try {
            DateTimeFormatter alt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
            LocalDateTime ldt = LocalDateTime.parse(s, alt);
            return ldt;
        } catch (DateTimeParseException ignored) {}
        try {
            DateTimeFormatter alt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
            LocalDateTime ldt = LocalDateTime.parse(s, alt2);
            return ldt;
        } catch (DateTimeParseException ignored) {}

        // Replace space with 'T' and try ISO_LOCAL_DATE_TIME
        try {
            String t = s.replace(' ', 'T');
            LocalDateTime ldt = LocalDateTime.parse(t, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return ldt;
        } catch (DateTimeParseException ignored) {}

        // RFC_1123 attempt (produces a ZonedDateTime)
        try {
            ZonedDateTime zdt = ZonedDateTime.parse(s, DateTimeFormatter.RFC_1123_DATE_TIME);
            return LocalDateTime.ofInstant(zdt.toInstant(), zone);
        } catch (DateTimeParseException ignored) {}

        // As last resort, try LocalDateTime.parse using default expected format
        try {
            return LocalDateTime.parse(s);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unrecognized date/time format: " + input, e);
        }
    }
}