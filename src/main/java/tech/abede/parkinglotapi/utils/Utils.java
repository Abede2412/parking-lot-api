package tech.abede.parkinglotapi.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static String format(LocalDateTime localDateTime){
        String result = dateTimeFormatter.format(localDateTime);
        return result;
    }
}
