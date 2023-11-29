package com.cgpm.api.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class ViewUtils {
	
    public String calendarFormat(Calendar calendar) {
        if (calendar != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(calendar.getTime());
        }
        return "";
    }
    
    public String calendarTableFormat(Calendar calendar) {
        if (calendar != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(calendar.getTime());
        }
        return "";
    }    
   
}
