package org.zerock.springex.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //parse()
        // Obtains an instance of LocalDate from a text string using a specific formatter.
        //The text is parsed using the formatter, returning a date.
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
        //format();
        // Formats a date-time object using this formatter.
        //This formats the date-time to a String using the rules of the formatter
    }
}
