package org.example.bank;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

public final class MultiFormatTimestampFormatter {

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

    private MultiFormatTimestampFormatter() {}

    /**
     * Parse a single input string that may be:
     * - a date-only (e.g. "2024-05-16")
     * - a combined date/time with space (e.g. "2024-05-16 10:31:00")
     * - an ISO/Timestamp with offset (e.g. "2024-05-16T10:31:00-07:00" or "2024-05-16T17:31:00Z")
     * - epoch seconds/ms
     *
     * Date-only inputs return start-of-day.
     */
    public static Timestamp parseDateAndTime(String input) {
//        System.out.println(input+" is being parsed by MultiFormatTimestampFormatter");
        if (input == null) return null;
        String s = input.trim();
        if (s.isEmpty()) return null;

        // Try date-only formatters first -> return start of day
        for (DateTimeFormatter df : DATE_FORMATTERS) {
            try {
                LocalDate ld = LocalDate.parse(s, df);
                return Timestamp.valueOf(ld.atStartOfDay());
            } catch (DateTimeParseException ignored) {}
        }

        // Otherwise delegate to the tolerant single-string parser
        return parseToTimestamp(s);
    }

    public static Timestamp parseToTimestamp(String input) {
        if (input == null|| input.equals("null")) return null;
        String s = input.trim();
        if (s.isEmpty()) return null;

        // numeric epoch (seconds or milliseconds)
        if (s.matches("^-?\\d+$")) {
            try {
                long n = Long.parseLong(s);
                if (Math.abs(n) < 100_000_000_000L) { // seconds heuristic
                    return Timestamp.from(Instant.ofEpochSecond(n));
                } else {
                    return Timestamp.from(Instant.ofEpochMilli(n));
                }
            } catch (NumberFormatException ignored) {}
        }

        // Try ISO instant
        try {
            Instant inst = Instant.parse(s);
            return Timestamp.from(inst);
        } catch (DateTimeParseException ignored) {}

        // Try OffsetDateTime / ZonedDateTime
        try {
            OffsetDateTime odt = OffsetDateTime.parse(s);
            return Timestamp.from(odt.toInstant());
        } catch (DateTimeParseException ignored) {}
        try {
            ZonedDateTime zdt = ZonedDateTime.parse(s);
            return Timestamp.from(zdt.toInstant());
        } catch (DateTimeParseException ignored) {}

        // Try local date-time formats
        for (DateTimeFormatter fmt : LOCAL_DATETIME_FORMATTERS) {
            try {
                LocalDateTime ldt = LocalDateTime.parse(s, fmt);
                return Timestamp.valueOf(ldt);
            } catch (DateTimeParseException ignored) {}
        }

        // Try local date-only -> start of day
        for (DateTimeFormatter df : DATE_FORMATTERS) {
            try {
                LocalDate ld = LocalDate.parse(s, df);
                return Timestamp.valueOf(ld.atStartOfDay());
            } catch (DateTimeParseException ignored) {}
        }

        // Try parsing common patterns with space between date/time
        try {
            DateTimeFormatter alt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm[:ss][.SSS]", Locale.US);
            LocalDateTime ldt = LocalDateTime.parse(s, alt);
            return Timestamp.valueOf(ldt);
        } catch (DateTimeParseException ignored) {}

        // Replace space with 'T' and try ISO_LOCAL_DATE_TIME
        try {
            String t = s.replace(' ', 'T');
            LocalDateTime ldt = LocalDateTime.parse(t, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return Timestamp.valueOf(ldt);
        } catch (DateTimeParseException ignored) {}

        // RFC_1123 attempt
        try {
            ZonedDateTime zdt = ZonedDateTime.parse(s, DateTimeFormatter.RFC_1123_DATE_TIME);
            return Timestamp.from(zdt.toInstant());
        } catch (DateTimeParseException ignored) {}

        // As last resort, try Timestamp.valueOf which expects "yyyy-[m]m-[d]d hh:mm:ss[.f...]"
        try {
            return Timestamp.valueOf(s);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unrecognized date/time format: " + input, e);
        }
    }
}
