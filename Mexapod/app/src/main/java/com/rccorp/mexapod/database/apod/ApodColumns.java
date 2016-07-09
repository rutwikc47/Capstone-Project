package com.rccorp.mexapod.database.apod;

import android.net.Uri;
import android.provider.BaseColumns;

import com.rccorp.mexapod.database.DataProvider;
import com.rccorp.mexapod.database.apod.ApodColumns;

/**
 * Columns for the {@code apod} table.
 */
public class ApodColumns implements BaseColumns {
    public static final String TABLE_NAME = "apod";
    public static final Uri CONTENT_URI = Uri.parse(DataProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);
    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TITLE = "title";

    public static final String EXPLANATION = "explanation";

    public static final String COPYRIGHT = "copyright";

    public static final String DATE = "date";

    public static final String HDURL = "hdurl";

    public static final String URL = "url";

    public static final String SERVICE_VERSION = "service_version";

    public static final String MEDIA_TYPE = "media_type";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TITLE,
            EXPLANATION,
            COPYRIGHT,
            DATE,
            HDURL,
            URL,
            SERVICE_VERSION,
            MEDIA_TYPE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(EXPLANATION) || c.contains("." + EXPLANATION)) return true;
            if (c.equals(COPYRIGHT) || c.contains("." + COPYRIGHT)) return true;
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(HDURL) || c.contains("." + HDURL)) return true;
            if (c.equals(URL) || c.contains("." + URL)) return true;
            if (c.equals(SERVICE_VERSION) || c.contains("." + SERVICE_VERSION)) return true;
            if (c.equals(MEDIA_TYPE) || c.contains("." + MEDIA_TYPE)) return true;
        }
        return false;
    }

}
