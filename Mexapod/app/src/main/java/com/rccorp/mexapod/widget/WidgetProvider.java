package com.rccorp.mexapod.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.rccorp.mexapod.R;
import com.rccorp.mexapod.network.MexSync;

/**
 * Created by Rutwik on 20/05/16.
 */

/**
 * Implementation of App Widget functionality.
 */
public class WidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.stackwidget);
        remoteViews.setRemoteAdapter(appWidgetId, R.id.stackWidgetView, intent);
        remoteViews.setEmptyView(R.id.stackWidgetView, R.id.stackWidgetEmptyView);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }
}


