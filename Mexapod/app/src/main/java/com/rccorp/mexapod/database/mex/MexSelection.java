package com.rccorp.mexapod.database.mex;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.rccorp.mexapod.database.base.AbstractSelection;

/**
 * Selection for the {@code mex} table.
 */
public class MexSelection extends AbstractSelection<MexSelection> {
    @Override
    protected Uri baseUri() {
        return MexColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MexCursor} object, which is positioned before the first entry, or null.
     */
    public MexCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MexCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MexCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MexCursor} object, which is positioned before the first entry, or null.
     */
    public MexCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MexCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MexCursor query(Context context) {
        return query(context, null);
    }


    public MexSelection id(long... value) {
        addEquals("mex." + MexColumns._ID, toObjectArray(value));
        return this;
    }

    public MexSelection idNot(long... value) {
        addNotEquals("mex." + MexColumns._ID, toObjectArray(value));
        return this;
    }

    public MexSelection orderById(boolean desc) {
        orderBy("mex." + MexColumns._ID, desc);
        return this;
    }

    public MexSelection orderById() {
        return orderById(false);
    }

    public MexSelection minsol(Integer... value) {
        addEquals(MexColumns.MINSOL, value);
        return this;
    }

    public MexSelection minsolNot(Integer... value) {
        addNotEquals(MexColumns.MINSOL, value);
        return this;
    }

    public MexSelection minsolGt(int value) {
        addGreaterThan(MexColumns.MINSOL, value);
        return this;
    }

    public MexSelection minsolGtEq(int value) {
        addGreaterThanOrEquals(MexColumns.MINSOL, value);
        return this;
    }

    public MexSelection minsolLt(int value) {
        addLessThan(MexColumns.MINSOL, value);
        return this;
    }

    public MexSelection minsolLtEq(int value) {
        addLessThanOrEquals(MexColumns.MINSOL, value);
        return this;
    }

    public MexSelection orderByMinsol(boolean desc) {
        orderBy(MexColumns.MINSOL, desc);
        return this;
    }

    public MexSelection orderByMinsol() {
        orderBy(MexColumns.MINSOL, false);
        return this;
    }

    public MexSelection imgsrc(String... value) {
        addEquals(MexColumns.IMGSRC, value);
        return this;
    }

    public MexSelection imgsrcNot(String... value) {
        addNotEquals(MexColumns.IMGSRC, value);
        return this;
    }

    public MexSelection imgsrcLike(String... value) {
        addLike(MexColumns.IMGSRC, value);
        return this;
    }

    public MexSelection imgsrcContains(String... value) {
        addContains(MexColumns.IMGSRC, value);
        return this;
    }

    public MexSelection imgsrcStartsWith(String... value) {
        addStartsWith(MexColumns.IMGSRC, value);
        return this;
    }

    public MexSelection imgsrcEndsWith(String... value) {
        addEndsWith(MexColumns.IMGSRC, value);
        return this;
    }

    public MexSelection orderByImgsrc(boolean desc) {
        orderBy(MexColumns.IMGSRC, desc);
        return this;
    }

    public MexSelection orderByImgsrc() {
        orderBy(MexColumns.IMGSRC, false);
        return this;
    }

    public MexSelection earthdate(String... value) {
        addEquals(MexColumns.EARTHDATE, value);
        return this;
    }

    public MexSelection earthdateNot(String... value) {
        addNotEquals(MexColumns.EARTHDATE, value);
        return this;
    }

    public MexSelection earthdateLike(String... value) {
        addLike(MexColumns.EARTHDATE, value);
        return this;
    }

    public MexSelection earthdateContains(String... value) {
        addContains(MexColumns.EARTHDATE, value);
        return this;
    }

    public MexSelection earthdateStartsWith(String... value) {
        addStartsWith(MexColumns.EARTHDATE, value);
        return this;
    }

    public MexSelection earthdateEndsWith(String... value) {
        addEndsWith(MexColumns.EARTHDATE, value);
        return this;
    }

    public MexSelection orderByEarthdate(boolean desc) {
        orderBy(MexColumns.EARTHDATE, desc);
        return this;
    }

    public MexSelection orderByEarthdate() {
        orderBy(MexColumns.EARTHDATE, false);
        return this;
    }

    public MexSelection camname(String... value) {
        addEquals(MexColumns.CAMNAME, value);
        return this;
    }

    public MexSelection camnameNot(String... value) {
        addNotEquals(MexColumns.CAMNAME, value);
        return this;
    }

    public MexSelection camnameLike(String... value) {
        addLike(MexColumns.CAMNAME, value);
        return this;
    }

    public MexSelection camnameContains(String... value) {
        addContains(MexColumns.CAMNAME, value);
        return this;
    }

    public MexSelection camnameStartsWith(String... value) {
        addStartsWith(MexColumns.CAMNAME, value);
        return this;
    }

    public MexSelection camnameEndsWith(String... value) {
        addEndsWith(MexColumns.CAMNAME, value);
        return this;
    }

    public MexSelection orderByCamname(boolean desc) {
        orderBy(MexColumns.CAMNAME, desc);
        return this;
    }

    public MexSelection orderByCamname() {
        orderBy(MexColumns.CAMNAME, false);
        return this;
    }

    public MexSelection maxsol(Integer... value) {
        addEquals(MexColumns.MAXSOL, value);
        return this;
    }

    public MexSelection maxsolNot(Integer... value) {
        addNotEquals(MexColumns.MAXSOL, value);
        return this;
    }

    public MexSelection maxsolGt(int value) {
        addGreaterThan(MexColumns.MAXSOL, value);
        return this;
    }

    public MexSelection maxsolGtEq(int value) {
        addGreaterThanOrEquals(MexColumns.MAXSOL, value);
        return this;
    }

    public MexSelection maxsolLt(int value) {
        addLessThan(MexColumns.MAXSOL, value);
        return this;
    }

    public MexSelection maxsolLtEq(int value) {
        addLessThanOrEquals(MexColumns.MAXSOL, value);
        return this;
    }

    public MexSelection orderByMaxsol(boolean desc) {
        orderBy(MexColumns.MAXSOL, desc);
        return this;
    }

    public MexSelection orderByMaxsol() {
        orderBy(MexColumns.MAXSOL, false);
        return this;
    }

    public MexSelection maxearthdate(String... value) {
        addEquals(MexColumns.MAXEARTHDATE, value);
        return this;
    }

    public MexSelection maxearthdateNot(String... value) {
        addNotEquals(MexColumns.MAXEARTHDATE, value);
        return this;
    }

    public MexSelection maxearthdateLike(String... value) {
        addLike(MexColumns.MAXEARTHDATE, value);
        return this;
    }

    public MexSelection maxearthdateContains(String... value) {
        addContains(MexColumns.MAXEARTHDATE, value);
        return this;
    }

    public MexSelection maxearthdateStartsWith(String... value) {
        addStartsWith(MexColumns.MAXEARTHDATE, value);
        return this;
    }

    public MexSelection maxearthdateEndsWith(String... value) {
        addEndsWith(MexColumns.MAXEARTHDATE, value);
        return this;
    }

    public MexSelection orderByMaxearthdate(boolean desc) {
        orderBy(MexColumns.MAXEARTHDATE, desc);
        return this;
    }

    public MexSelection orderByMaxearthdate() {
        orderBy(MexColumns.MAXEARTHDATE, false);
        return this;
    }
}
