package com.rccorp.mexapod.database.mex;

import android.net.Uri;
import android.provider.BaseColumns;

import com.rccorp.mexapod.database.MexProvider;
import com.rccorp.mexapod.database.mex.MexColumns;

/**
 * Columns for the {@code mex} table.
 */
public class MexColumns implements BaseColumns {
    public static final String TABLE_NAME = "mex";
    public static final Uri CONTENT_URI = Uri.parse(MexProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MINSOL = "minsol";

    public static final String IMGSRC = "imgsrc";

    public static final String EARTHDATE = "earthdate";

    public static final String CAMNAME = "camname";

    public static final String MAXSOL = "maxsol";

    public static final String MAXEARTHDATE = "maxearthdate";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MINSOL,
            IMGSRC,
            EARTHDATE,
            CAMNAME,
            MAXSOL,
            MAXEARTHDATE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MINSOL) || c.contains("." + MINSOL)) return true;
            if (c.equals(IMGSRC) || c.contains("." + IMGSRC)) return true;
            if (c.equals(EARTHDATE) || c.contains("." + EARTHDATE)) return true;
            if (c.equals(CAMNAME) || c.contains("." + CAMNAME)) return true;
            if (c.equals(MAXSOL) || c.contains("." + MAXSOL)) return true;
            if (c.equals(MAXEARTHDATE) || c.contains("." + MAXEARTHDATE)) return true;
        }
        return false;
    }

}
