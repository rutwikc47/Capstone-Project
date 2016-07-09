package com.rccorp.mexapod.database.mex;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rccorp.mexapod.database.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code mex} table.
 */
public class MexContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MexColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MexSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MexSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MexContentValues putMinsol(@Nullable Integer value) {
        mContentValues.put(MexColumns.MINSOL, value);
        return this;
    }

    public MexContentValues putMinsolNull() {
        mContentValues.putNull(MexColumns.MINSOL);
        return this;
    }

    public MexContentValues putImgsrc(@Nullable String value) {
        mContentValues.put(MexColumns.IMGSRC, value);
        return this;
    }

    public MexContentValues putImgsrcNull() {
        mContentValues.putNull(MexColumns.IMGSRC);
        return this;
    }

    public MexContentValues putEarthdate(@Nullable String value) {
        mContentValues.put(MexColumns.EARTHDATE, value);
        return this;
    }

    public MexContentValues putEarthdateNull() {
        mContentValues.putNull(MexColumns.EARTHDATE);
        return this;
    }

    public MexContentValues putCamname(@Nullable String value) {
        mContentValues.put(MexColumns.CAMNAME, value);
        return this;
    }

    public MexContentValues putCamnameNull() {
        mContentValues.putNull(MexColumns.CAMNAME);
        return this;
    }

    public MexContentValues putMaxsol(@Nullable Integer value) {
        mContentValues.put(MexColumns.MAXSOL, value);
        return this;
    }

    public MexContentValues putMaxsolNull() {
        mContentValues.putNull(MexColumns.MAXSOL);
        return this;
    }

    public MexContentValues putMaxearthdate(@Nullable String value) {
        mContentValues.put(MexColumns.MAXEARTHDATE, value);
        return this;
    }

    public MexContentValues putMaxearthdateNull() {
        mContentValues.putNull(MexColumns.MAXEARTHDATE);
        return this;
    }
}
