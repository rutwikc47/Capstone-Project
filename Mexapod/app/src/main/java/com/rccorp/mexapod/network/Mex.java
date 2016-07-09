package com.rccorp.mexapod.network;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mex implements Parcelable {


    @SerializedName("sol")
    @Expose
    private int minsol;
    @SerializedName("img_src")
    @Expose
    private String imgSrc;
    @SerializedName("earth_date")
    @Expose
    private String earthDate;
    @SerializedName("name")
    @Expose
    private String camName;
    @SerializedName("max_sol")
    @Expose
    private int maxSol;
    @SerializedName("max_date")
    @Expose
    private String maxEarthdate;
    @SerializedName("photos")
    @Expose

    private List mphoto;

    public Mex(){

    }

    public List getMphoto() {
        return mphoto;
    }

    public void setMphoto(List mphoto) {
        this.mphoto = mphoto;
    }

    /**
     *
     * @return
     * The sol
     */
    public int getMinsol() {
        return minsol;
    }

    /**
     *
     * @param sol
     * The sol
     */
    public void setMinsol(int sol) {
        this.minsol = sol;
    }

    /**
     *
     * @return
     * The imgSrc
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     *
     * @param imgSrc
     * The img_src
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    /**
     *
     * @return
     * The earthDate
     */
    public String getEarthDate() {
        return earthDate;
    }

    /**
     *
     * @param earthDate
     * The earth_date
     */
    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }

    /**
     *
     * @return
     * The name
     */
    public String getCamName() {
        return camName;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setCamName(String name) {
        this.camName = name;
    }

    /**
     *
     * @return
     * The maxSol
     */
    public int getMaxSol() {
        return maxSol;
    }

    /**
     *
     * @param maxSol
     * The max_sol
     */
    public void setMaxSol(int maxSol) {
        this.maxSol = maxSol;
    }

    /**
     *
     * @return
     * The maxDate
     */
    public String getMaxEarthdate() {
        return maxEarthdate;
    }

    /**
     *
     * @param maxDate
     * The max_date
     */
    public void setMaxEarthdate(String maxDate) {
        this.maxEarthdate = maxDate;
    }

    private Mex(Parcel in){
        imgSrc=in.readString();
        maxEarthdate=in.readString();
        camName=in.readString();
        earthDate=in.readString();
        minsol=in.readInt();
        maxSol=in.readInt();
    }

    @Override
    public String toString() {
        return imgSrc + ": " + maxEarthdate + ": " + camName + ": " + earthDate + ": " + minsol + ": " + maxSol;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(imgSrc);
        out.writeString(maxEarthdate);
        out.writeString(camName);
        out.writeString(earthDate);
        out.writeInt(minsol);
        out.writeInt(maxSol);
    }

    public static final Parcelable.Creator<Mex> CREATOR = new Parcelable.Creator<Mex>() {
        public Mex createFromParcel(Parcel in) {
            return new Mex(in);
        }

        public Mex[] newArray(int size) {
            return new Mex[size];
        }
    };

}