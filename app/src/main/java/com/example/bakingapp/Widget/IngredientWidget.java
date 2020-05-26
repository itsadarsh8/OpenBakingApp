package com.example.bakingapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.bakingapp.IngredientsActivity;
import com.example.bakingapp.R;

import static com.example.bakingapp.IngredientsActivity.widgetText;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientWidget extends AppWidgetProvider {




    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingerdient_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingerdient_widget);
        views.setTextViewText(R.id.appwidget_text, "Your List Here");
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingerdient_widget);
        views.setTextViewText(R.id.appwidget_text, "Your List Here");
        // Enter relevant functionality for when the last widget is disabled
    }
}

