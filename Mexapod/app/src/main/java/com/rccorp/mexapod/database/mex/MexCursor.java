package com.rccorp.mexapod.database.mex;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rccorp.mexapod.database.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code mex} table.
 */
public class MexCursor extends AbstractCursor implements MexModel {
    public MexCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MexColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code minsol} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getMinsol() {
        Integer res = getIntegerOrNull(MexColumns.MINSOL);
        return res;
    }

    /**
     * Get the {@code imgsrc} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImgsrc() {
        String res = getStringOrNull(MexColumns.IMGSRC);
        return res;
    }

    /**
     * Get the {@code earthdate} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getEarthdate() {
        String res = getStringOrNull(MexColumns.EARTHDATE);
        return res;
    }

    /**
     * Get the {@code camname} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCamname() {
        String res = getStringOrNull(MexColumns.CAMNAME);
        return res;
    }

    /**
     * Get the {@code maxsol} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getMaxsol() {
        Integer res = getIntegerOrNull(MexColumns.MAXSOL);
        return res;
    }

    /**
     * Get the {@code maxearthdate} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMaxearthdate() {
        String res = getStringOrNull(MexColumns.MAXEARTHDATE);
        return res;
    }
}
