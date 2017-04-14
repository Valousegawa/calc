package com.a2017.dev.insta.calculatrice;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by Telest on 10/04/2017.
 */

public class CalculatriceWidgetProvider extends AppWidgetProvider{
    private static RemoteViews mRemoteView;
    private static AppWidgetManager mWidgetManager;
    private static Intent mIntent;
    private static Context mContext;
    private static int[] mWidgetIds;

    private static CalculateModel model;

    private void setupIntent(String action, int id){
        mIntent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, mIntent, 0);
        mRemoteView.setOnClickPendingIntent(id, pendingIntent);
    }

    private void initVariables(Context context){
        mContext = context;
        updateWidgetIds();
        mRemoteView = new RemoteViews(mContext.getPackageName(), R.layout.widget_layout);
        mWidgetManager = AppWidgetManager.getInstance(mContext);
        model = new CalculateModel();
    }

    private void updateWidget(){
        for(int widgetId: mWidgetIds){
            mWidgetManager.updateAppWidget(widgetId, mRemoteView);
        }
    }

    private void updateWidgetIds(){
        final ComponentName componentName = new ComponentName(mContext, CalculatriceWidgetProvider.class);
        mWidgetManager = AppWidgetManager.getInstance(mContext);
        mWidgetIds = mWidgetManager.getAppWidgetIds(componentName);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        initVariables(context);
        mIntent = new Intent(context, CalculatriceWidgetProvider.class);

        setupIntent(Constant.OPERATOR_PLUS, R.id.digitP);
        setupIntent(Constant.OPERATOR_MOINS, R.id.digitM);
        setupIntent(Constant.OPERATOR_DIV, R.id.digitD);
        setupIntent(Constant.OPERATOR_MULT, R.id.digitMu);
        setupIntent(Constant.OPERATOR_C, R.id.digitC);
        setupIntent(Constant.OPERATOR_EGAL, R.id.egal);
        setupIntent(Constant.OPERATOR_POINT, R.id.digitPo);

        setupIntent(Constant.DIGIT_0, R.id.digit0);
        setupIntent(Constant.DIGIT_1, R.id.digit1);
        setupIntent(Constant.DIGIT_2, R.id.digit2);
        setupIntent(Constant.DIGIT_3, R.id.digit3);
        setupIntent(Constant.DIGIT_4, R.id.digit4);
        setupIntent(Constant.DIGIT_5, R.id.digit5);
        setupIntent(Constant.DIGIT_6, R.id.digit6);
        setupIntent(Constant.DIGIT_7, R.id.digit7);
        setupIntent(Constant.DIGIT_8, R.id.digit8);
        setupIntent(Constant.DIGIT_9, R.id.digit9);

        updateWidget();
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        switch(action){
            case Constant.OPERATOR_PLUS :
            case Constant.OPERATOR_MOINS :
            case Constant.OPERATOR_DIV :
            case Constant.OPERATOR_MULT :
            case Constant.OPERATOR_C :
            case Constant.OPERATOR_EGAL :
            case Constant.OPERATOR_POINT :
            case Constant.DIGIT_0 :
            case Constant.DIGIT_1 :
            case Constant.DIGIT_2 :
            case Constant.DIGIT_3 :
            case Constant.DIGIT_4 :
            case Constant.DIGIT_5 :
            case Constant.DIGIT_6 :
            case Constant.DIGIT_7 :
            case Constant.DIGIT_8 :
            case Constant.DIGIT_9 :
                doAction(action, context);
                break;
            default:
                super.onReceive(context, intent);
                break;
        }
    }

    private void doAction(String action, Context context){
        if(model == null || mRemoteView == null || mWidgetManager == null || mContext == null){
            initVariables(context);
        }
        switch (action){
            case Constant.OPERATOR_PLUS:
            case Constant.OPERATOR_MOINS:
            case Constant.OPERATOR_MULT:
            case Constant.OPERATOR_DIV:
                model.setOperator(action);
                break;
            case Constant.OPERATOR_EGAL:
                try {
                    double result = model.calcul();
                    mRemoteView.setTextViewText(R.id.result, String.valueOf(result));
                    model.clear();
                    model.setFirstValue(String.valueOf(result));
                    break;
                } catch(NumberFormatException e){
                        model.clear();
                        mRemoteView.setTextViewText(R.id.result, "0");
        }
            case Constant.OPERATOR_C:
                model.clear();
                mRemoteView.setTextViewText(R.id.result, "0");
                break;
            default:
                if(model.getOperator() == ""){
                    model.addFirstValueNumber(action);
                    mRemoteView.setTextViewText(R.id.result, String.valueOf(model.getFirstValue()));
                } else {
                    model.addSecondValueNumber(action);
                    mRemoteView.setTextViewText(R.id.result, String.valueOf(model.getSecondValue()));
                }
                break;
        }
        updateWidget();
    }
}
