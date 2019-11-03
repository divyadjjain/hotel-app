package com.main.util;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.main.domain.Extra;

@Component
public class TemplateUtil {
    public String formatMonth(Month month) {
        return month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

    public boolean hasSelectedFoodExtra(Long id, List<Extra> foodExtras) {
        System.out.println("Is id=" + id  + " in food extras? " + foodExtras);
        return foodExtras.stream().map(Extra::getId).anyMatch(it -> it.equals(id));
    }
}
