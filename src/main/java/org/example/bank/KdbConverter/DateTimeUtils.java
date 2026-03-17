package org.example.bank.KdbConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtils {
    private static final DateTimeFormatter ISO_FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final DateTimeFormatter SPACE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter WITH_T_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // Try common formats: ISO (with 'T'), explicit pattern with 'T', then space pattern.
    public static LocalDateTime parseFlexible(String s) {
        if (s == null || s.isBlank()) return null;
        try {
            return LocalDateTime.parse(s, ISO_FMT);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(s, WITH_T_FMT);
            } catch (DateTimeParseException e2) {
                try {
                    return LocalDateTime.parse(s, SPACE_FMT);
                } catch (DateTimeParseException e3) {
                    // If needed, normalize common separators and retry
                    String normalized = s.replace('T', ' ');
                    return LocalDateTime.parse(normalized, SPACE_FMT);
                }
            }
        }
    }
}