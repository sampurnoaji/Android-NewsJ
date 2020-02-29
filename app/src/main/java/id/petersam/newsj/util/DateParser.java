package id.petersam.newsj.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {
    public static String toLocalDateTimeFull(String stringDate){
        if (!stringDate.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            try {
                Date date = dateFormat.parse(stringDate);
                if (date != null) {
                    return DateFormat.getDateInstance(DateFormat.FULL, new Locale("id", "ID")).format(date);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
