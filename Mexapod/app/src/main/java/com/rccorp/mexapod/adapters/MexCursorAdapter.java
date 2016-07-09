package com.rccorp.mexapod.adapters;

/**
 * Created by Rutwik on 25/06/16.
 */
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rccorp.mexapod.R;
import com.rccorp.mexapod.database.mex.MexCursor;


public class MexCursorAdapter extends CursorRecyclerViewAdapter<MexCursorAdapter.ViewHolder> {

    MexCursor mexCursor;

    Context mContext;

    public MexCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView meximg;
        TextView camera;

        public ViewHolder(View view) {
            super(view);
            meximg = (ImageView) view.findViewById(R.id.meximage);
            camera=(TextView)view.findViewById(R.id.camera_tv);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_mex, parent, false);
        mContext=itemView.getContext();
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {

        mexCursor=new MexCursor(cursor);
        String imgsrc=mexCursor.getImgsrc();
        Glide.with(mContext).load(imgsrc).into(viewHolder.meximg);

        String camname=mexCursor.getCamname();
        viewHolder.camera.setText(camname);

    }
}