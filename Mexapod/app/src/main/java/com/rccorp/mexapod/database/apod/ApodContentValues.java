package com.rccorp.mexapod.database.apod;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rccorp.mexapod.database.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code apod} table.
 */
public class ApodContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ApodColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ApodSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable ApodSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ApodContentValues putTitle(@Nullable String value) {
        mContentValues.put(ApodColumns.TITLE, value);
        return this;
    }

    public ApodContentValues putTitleNull() {
        mContentValues.putNull(ApodColumns.TITLE);
        return this;
    }

    public ApodContentValues putExplanation(@Nullable String value) {
        mContentValues.put(ApodColumns.EXPLANATION, value);
        return this;
    }

    public ApodContentValues putExplanationNull() {
        mContentValues.putNull(ApodColumns.EXPLANATION);
        return this;
    }

    public ApodContentValues putCopyright(@Nullable String value) {
        mContentValues.put(ApodColumns.COPYRIGHT, value);
        return this;
    }

    public ApodContentValues putCopyrightNull() {
        mContentValues.putNull(ApodColumns.COPYRIGHT);
        return this;
    }

    public ApodContentValues putDate(@Nullable String value) {
        mContentValues.put(ApodColumns.DATE, value);
        return this;
    }

    public ApodContentValues putDateNull() {
        mContentValues.putNull(ApodColumns.DATE);
        return this;
    }

    public ApodContentValues putHdurl(@Nullable String value) {
        mContentValues.put(ApodColumns.HDURL, value);
        return this;
    }

    public ApodContentValues putHdurlNull() {
        mContentValues.putNull(ApodColumns.HDURL);
        return this;
    }

    public ApodContentValues putUrl(@Nullable String value) {
        mContentValues.put(ApodColumns.URL, value);
        return this;
    }

    public ApodContentValues putUrlNull() {
        mContentValues.putNull(ApodColumns.URL);
        return this;
    }

    public ApodContentValues putServiceVersion(@Nullable String value) {
        mContentValues.put(ApodColumns.SERVICE_VERSION, value);
        return this;
    }

    public ApodContentValues putServiceVersionNull() {
        mContentValues.putNull(ApodColumns.SERVICE_VERSION);
        return this;
    }

    public ApodContentValues putMediaType(@Nullable String value) {
        mContentValues.put(ApodColumns.MEDIA_TYPE, value);
        return this;
    }

    public ApodContentValues putMediaTypeNull() {
        mContentValues.putNull(ApodColumns.MEDIA_TYPE);
        return this;
    }
}
