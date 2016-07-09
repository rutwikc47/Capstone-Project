package com.rccorp.mexapod.database.mex;

import com.rccorp.mexapod.database.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code mex} table.
 */
public interface MexModel extends BaseModel {

    /**
     * Get the {@code minsol} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getMinsol();

    /**
     * Get the {@code imgsrc} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImgsrc();

    /**
     * Get the {@code earthdate} value.
     * Can be {@code null}.
     */
    @Nullable
    String getEarthdate();

    /**
     * Get the {@code camname} value.
     * Can be {@code null}.
     */
    @Nullable
    String getCamname();

    /**
     * Get the {@code maxsol} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getMaxsol();

    /**
     * Get the {@code maxearthdate} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMaxearthdate();
}
