package com.rccorp.mexapod.widget;

/**
 * Created by Rutwik on 23/05/16.
 */

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.rccorp.mexapod.R;
import com.rccorp.mexapod.database.mex.MexColumns;
import com.rccorp.mexapod.database.mex.MexCursor;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class WidgetService extends RemoteViewsService {



    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {

            private Cursor mCursor = null;

            @Override
            public void onCreate() {

            }

            @Override
            public void onDataSetChanged() {

                if (mCursor != null) {
                    mCursor.close();
                }

                final long identityToken = Binder.clearCallingIdentity();
                Binder.restoreCallingIdentity(identityToken);
                mCursor=getBaseContext().getApplicationContext().getContentResolver().query(
                        MexColumns.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );

            }

            @Override
            public void onDestroy() {
                if (mCursor != null) {
                    mCursor.close();
                    mCursor = null;
                }

            }

            @Override
            public int getCount() {
                return mCursor == null ? 0 : mCursor.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        mCursor == null || !mCursor.moveToPosition(position)) {
                    return null;
                }
                final RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_item);

                try {
                    Bitmap b = Picasso.with(WidgetService.this).load(mCursor.getString(mCursor.getColumnIndex(MexColumns.IMGSRC))).get();
                    Bitmap mutableBitmap=b.copy(Bitmap.Config.ARGB_8888, true);
                    mutableBitmap.setHeight(250);
                    views.setImageViewBitmap(R.id.widgetimage, mutableBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                views.setTextViewText(R.id.camera_tv,mCursor.getString(mCursor.getColumnIndex(MexColumns.CAMNAME)));
                return views;

            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_item);

            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {

                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }

}
















