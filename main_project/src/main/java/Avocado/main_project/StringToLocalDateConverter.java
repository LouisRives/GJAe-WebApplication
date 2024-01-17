package Avocado.main_project;

import org.springframework.core.convert.converter.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//The class is called during the creation of a task. The LocalDate format caused issues so creating a Converter "StringToLocalDateConverter" was necessary

public class StringToLocalDateConverter implements Converter<String, LocalDate> {
  @Override
    public LocalDate convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }

        try {
            // Adjust the pattern based on your date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(source, formatter);
        } catch (Exception e) {
            // Handle parsing exceptions if needed
            e.printStackTrace();
            return null;
        }
    }
}
