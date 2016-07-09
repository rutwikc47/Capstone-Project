package com.rccorp.mexapod.database.apod;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rccorp.mexapod.database.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code apod} table.
 */
public class ApodCursor extends AbstractCursor implements ApodModel {
    public ApodCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ApodColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(ApodColumns.TITLE);
        return res;
    }

    /**
     * Get the {@code explanation} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getExplanation() {
        String res = getStringOrNull(ApodColumns.EXPLANATION);
        return res;
    }

    /**
     * Get the {@code copyright} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCopyright() {
        String res = getStringOrNull(ApodColumns.COPYRIGHT);
        return res;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDate() {
        String res = getStringOrNull(ApodColumns.DATE);
        return res;
    }

    /**
     * Get the {@code hdurl} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getHdurl() {
        String res = getStringOrNull(ApodColumns.HDURL);
        return res;
    }

    /**
     * Get the {@code url} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getUrl() {
        String res = getStringOrNull(ApodColumns.URL);
        return res;
    }

    /**
     * Get the {@code service_version} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getServiceVersion() {
        String res = getStringOrNull(ApodColumns.SERVICE_VERSION);
        return res;
    }

    /**
     * Get the {@code media_type} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMediaType() {
        String res = getStringOrNull(ApodColumns.MEDIA_TYPE);
        return res;
    }
}
