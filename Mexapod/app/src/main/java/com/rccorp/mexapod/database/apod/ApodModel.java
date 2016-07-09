package com.rccorp.mexapod.database.apod;

import com.rccorp.mexapod.database.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code apod} table.
 */
public interface ApodModel extends BaseModel {

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();

    /**
     * Get the {@code explanation} value.
     * Can be {@code null}.
     */
    @Nullable
    String getExplanation();

    /**
     * Get the {@code copyright} value.
     * Can be {@code null}.
     */
    @Nullable
    String getCopyright();

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDate();

    /**
     * Get the {@code hdurl} value.
     * Can be {@code null}.
     */
    @Nullable
    String getHdurl();

    /**
     * Get the {@code url} value.
     * Can be {@code null}.
     */
    @Nullable
    String getUrl();

    /**
     * Get the {@code service_version} value.
     * Can be {@code null}.
     */
    @Nullable
    String getServiceVersion();

    /**
     * Get the {@code media_type} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMediaType();
}
