package com.rccorp.mexapod.database.apod;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.rccorp.mexapod.database.base.AbstractSelection;

/**
 * Selection for the {@code apod} table.
 */
public class ApodSelection extends AbstractSelection<ApodSelection> {
    @Override
    protected Uri baseUri() {
        return ApodColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ApodCursor} object, which is positioned before the first entry, or null.
     */
    public ApodCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ApodCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ApodCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ApodCursor} object, which is positioned before the first entry, or null.
     */
    public ApodCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ApodCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ApodCursor query(Context context) {
        return query(context, null);
    }


    public ApodSelection id(long... value) {
        addEquals("apod." + ApodColumns._ID, toObjectArray(value));
        return this;
    }

    public ApodSelection idNot(long... value) {
        addNotEquals("apod." + ApodColumns._ID, toObjectArray(value));
        return this;
    }

    public ApodSelection orderById(boolean desc) {
        orderBy("apod." + ApodColumns._ID, desc);
        return this;
    }

    public ApodSelection orderById() {
        return orderById(false);
    }

    public ApodSelection title(String... value) {
        addEquals(ApodColumns.TITLE, value);
        return this;
    }

    public ApodSelection titleNot(String... value) {
        addNotEquals(ApodColumns.TITLE, value);
        return this;
    }

    public ApodSelection titleLike(String... value) {
        addLike(ApodColumns.TITLE, value);
        return this;
    }

    public ApodSelection titleContains(String... value) {
        addContains(ApodColumns.TITLE, value);
        return this;
    }

    public ApodSelection titleStartsWith(String... value) {
        addStartsWith(ApodColumns.TITLE, value);
        return this;
    }

    public ApodSelection titleEndsWith(String... value) {
        addEndsWith(ApodColumns.TITLE, value);
        return this;
    }

    public ApodSelection orderByTitle(boolean desc) {
        orderBy(ApodColumns.TITLE, desc);
        return this;
    }

    public ApodSelection orderByTitle() {
        orderBy(ApodColumns.TITLE, false);
        return this;
    }

    public ApodSelection explanation(String... value) {
        addEquals(ApodColumns.EXPLANATION, value);
        return this;
    }

    public ApodSelection explanationNot(String... value) {
        addNotEquals(ApodColumns.EXPLANATION, value);
        return this;
    }

    public ApodSelection explanationLike(String... value) {
        addLike(ApodColumns.EXPLANATION, value);
        return this;
    }

    public ApodSelection explanationContains(String... value) {
        addContains(ApodColumns.EXPLANATION, value);
        return this;
    }

    public ApodSelection explanationStartsWith(String... value) {
        addStartsWith(ApodColumns.EXPLANATION, value);
        return this;
    }

    public ApodSelection explanationEndsWith(String... value) {
        addEndsWith(ApodColumns.EXPLANATION, value);
        return this;
    }

    public ApodSelection orderByExplanation(boolean desc) {
        orderBy(ApodColumns.EXPLANATION, desc);
        return this;
    }

    public ApodSelection orderByExplanation() {
        orderBy(ApodColumns.EXPLANATION, false);
        return this;
    }

    public ApodSelection copyright(String... value) {
        addEquals(ApodColumns.COPYRIGHT, value);
        return this;
    }

    public ApodSelection copyrightNot(String... value) {
        addNotEquals(ApodColumns.COPYRIGHT, value);
        return this;
    }

    public ApodSelection copyrightLike(String... value) {
        addLike(ApodColumns.COPYRIGHT, value);
        return this;
    }

    public ApodSelection copyrightContains(String... value) {
        addContains(ApodColumns.COPYRIGHT, value);
        return this;
    }

    public ApodSelection copyrightStartsWith(String... value) {
        addStartsWith(ApodColumns.COPYRIGHT, value);
        return this;
    }

    public ApodSelection copyrightEndsWith(String... value) {
        addEndsWith(ApodColumns.COPYRIGHT, value);
        return this;
    }

    public ApodSelection orderByCopyright(boolean desc) {
        orderBy(ApodColumns.COPYRIGHT, desc);
        return this;
    }

    public ApodSelection orderByCopyright() {
        orderBy(ApodColumns.COPYRIGHT, false);
        return this;
    }

    public ApodSelection date(String... value) {
        addEquals(ApodColumns.DATE, value);
        return this;
    }

    public ApodSelection dateNot(String... value) {
        addNotEquals(ApodColumns.DATE, value);
        return this;
    }

    public ApodSelection dateLike(String... value) {
        addLike(ApodColumns.DATE, value);
        return this;
    }

    public ApodSelection dateContains(String... value) {
        addContains(ApodColumns.DATE, value);
        return this;
    }

    public ApodSelection dateStartsWith(String... value) {
        addStartsWith(ApodColumns.DATE, value);
        return this;
    }

    public ApodSelection dateEndsWith(String... value) {
        addEndsWith(ApodColumns.DATE, value);
        return this;
    }

    public ApodSelection orderByDate(boolean desc) {
        orderBy(ApodColumns.DATE, desc);
        return this;
    }

    public ApodSelection orderByDate() {
        orderBy(ApodColumns.DATE, false);
        return this;
    }

    public ApodSelection hdurl(String... value) {
        addEquals(ApodColumns.HDURL, value);
        return this;
    }

    public ApodSelection hdurlNot(String... value) {
        addNotEquals(ApodColumns.HDURL, value);
        return this;
    }

    public ApodSelection hdurlLike(String... value) {
        addLike(ApodColumns.HDURL, value);
        return this;
    }

    public ApodSelection hdurlContains(String... value) {
        addContains(ApodColumns.HDURL, value);
        return this;
    }

    public ApodSelection hdurlStartsWith(String... value) {
        addStartsWith(ApodColumns.HDURL, value);
        return this;
    }

    public ApodSelection hdurlEndsWith(String... value) {
        addEndsWith(ApodColumns.HDURL, value);
        return this;
    }

    public ApodSelection orderByHdurl(boolean desc) {
        orderBy(ApodColumns.HDURL, desc);
        return this;
    }

    public ApodSelection orderByHdurl() {
        orderBy(ApodColumns.HDURL, false);
        return this;
    }

    public ApodSelection url(String... value) {
        addEquals(ApodColumns.URL, value);
        return this;
    }

    public ApodSelection urlNot(String... value) {
        addNotEquals(ApodColumns.URL, value);
        return this;
    }

    public ApodSelection urlLike(String... value) {
        addLike(ApodColumns.URL, value);
        return this;
    }

    public ApodSelection urlContains(String... value) {
        addContains(ApodColumns.URL, value);
        return this;
    }

    public ApodSelection urlStartsWith(String... value) {
        addStartsWith(ApodColumns.URL, value);
        return this;
    }

    public ApodSelection urlEndsWith(String... value) {
        addEndsWith(ApodColumns.URL, value);
        return this;
    }

    public ApodSelection orderByUrl(boolean desc) {
        orderBy(ApodColumns.URL, desc);
        return this;
    }

    public ApodSelection orderByUrl() {
        orderBy(ApodColumns.URL, false);
        return this;
    }

    public ApodSelection serviceVersion(String... value) {
        addEquals(ApodColumns.SERVICE_VERSION, value);
        return this;
    }

    public ApodSelection serviceVersionNot(String... value) {
        addNotEquals(ApodColumns.SERVICE_VERSION, value);
        return this;
    }

    public ApodSelection serviceVersionLike(String... value) {
        addLike(ApodColumns.SERVICE_VERSION, value);
        return this;
    }

    public ApodSelection serviceVersionContains(String... value) {
        addContains(ApodColumns.SERVICE_VERSION, value);
        return this;
    }

    public ApodSelection serviceVersionStartsWith(String... value) {
        addStartsWith(ApodColumns.SERVICE_VERSION, value);
        return this;
    }

    public ApodSelection serviceVersionEndsWith(String... value) {
        addEndsWith(ApodColumns.SERVICE_VERSION, value);
        return this;
    }

    public ApodSelection orderByServiceVersion(boolean desc) {
        orderBy(ApodColumns.SERVICE_VERSION, desc);
        return this;
    }

    public ApodSelection orderByServiceVersion() {
        orderBy(ApodColumns.SERVICE_VERSION, false);
        return this;
    }

    public ApodSelection mediaType(String... value) {
        addEquals(ApodColumns.MEDIA_TYPE, value);
        return this;
    }

    public ApodSelection mediaTypeNot(String... value) {
        addNotEquals(ApodColumns.MEDIA_TYPE, value);
        return this;
    }

    public ApodSelection mediaTypeLike(String... value) {
        addLike(ApodColumns.MEDIA_TYPE, value);
        return this;
    }

    public ApodSelection mediaTypeContains(String... value) {
        addContains(ApodColumns.MEDIA_TYPE, value);
        return this;
    }

    public ApodSelection mediaTypeStartsWith(String... value) {
        addStartsWith(ApodColumns.MEDIA_TYPE, value);
        return this;
    }

    public ApodSelection mediaTypeEndsWith(String... value) {
        addEndsWith(ApodColumns.MEDIA_TYPE, value);
        return this;
    }

    public ApodSelection orderByMediaType(boolean desc) {
        orderBy(ApodColumns.MEDIA_TYPE, desc);
        return this;
    }

    public ApodSelection orderByMediaType() {
        orderBy(ApodColumns.MEDIA_TYPE, false);
        return this;
    }
}
