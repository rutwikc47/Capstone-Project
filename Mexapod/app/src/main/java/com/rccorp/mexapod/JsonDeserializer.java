package com.rccorp.mexapod;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rccorp.mexapod.network.Apod;
import com.rccorp.mexapod.network.Mex;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rutwik on 28/05/16.
 */
public class JsonDeserializer implements com.google.gson.JsonDeserializer <Mex> {
    @Override
    public Mex deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject marsobject=json.getAsJsonObject();

        JsonArray photosarr=marsobject.getAsJsonArray("photos");

        List<Mex> mexList=new ArrayList<>();


        for (int i=0;i<photosarr.size();i++){
//            final JsonObject photo=photosarr.get(i).getAsJsonObject();
            JsonObject photo=photosarr.get(i).getAsJsonObject();

            String imgsrc=photo.get("img_src").getAsString();

            String earthdate=photo.get("earth_date").getAsString();

            int minsol=photo.get("sol").getAsInt();

            JsonObject rover=photo.get("rover").getAsJsonObject();

            int maxsol=rover.get("max_sol").getAsInt();

            String max_date=rover.get("max_date").getAsString();

            JsonObject camera=photo.get("camera").getAsJsonObject();

            String fullcameraname=camera.get("full_name").getAsString();

            Mex currmex=new Mex();
            currmex.setImgSrc(imgsrc);
            currmex.setEarthDate(earthdate);
            currmex.setMinsol(minsol);
            currmex.setMaxSol(maxsol);
            currmex.setMaxEarthdate(max_date);
            currmex.setCamName(fullcameraname);
            mexList.add(currmex);
        }

        Mex finalmex=new Mex();
        finalmex.setMphoto(mexList);
        return finalmex;
    }

}
