package com.customviews.tasks;

import static android.content.Context.ACTIVITY_SERVICE;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

public class GetTestInfo {
    public void getTaskInfo(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.AppTask appTask: activityManager.getAppTasks()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //System.out.println("Activity:topActivity "+appTask.getTaskInfo().topActivity);
                Log.d("Activity:topActivity ", appTask.getTaskInfo().topActivity.toString());
                //System.out.println("Activity:baseActivity "+appTask.getTaskInfo().baseActivity);
                Log.d("Activity:baseActivity ",appTask.getTaskInfo().baseActivity.toString());
                //System.out.println("Activity:numActivities "+appTask.getTaskInfo().numActivities);
                Log.d("Activity:numActivities ",appTask.getTaskInfo().numActivities+"");
            }
        }
    }
}
