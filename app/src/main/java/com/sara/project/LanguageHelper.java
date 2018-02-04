package com.sara.project;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by sara on 2/4/2018.
 */

public class LanguageHelper {

    public static void changeLocal(Resources resources, String local) {
        Configuration config = new Configuration(resources.getConfiguration());

        switch (local) {
            case "ar":
                config.locale = new Locale("ar");
                break;
            case "fr":
                config.locale = new Locale("fr");
                break;
            case "en":
                config.locale = new Locale("en");
                break;
            default:
                config.locale = Locale.ENGLISH;
                break;
        }

        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
