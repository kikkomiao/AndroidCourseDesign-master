package com.example.viewnews.tools;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        System.out.println("add activity + " + activity);
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        System.out.println("remove activity + " + activity);
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            System.out.println("finishAll activity + " + activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

//    public static void finishSome(){
//        for (Activity activity : activities) {
//            System.out.println("activity + " + activity);
//            System.out.println("???+++++" + activity.equals(MainActivity.class));
//            if (!activity.isFinishing() && activity.equals(MainActivity.class)) {
//                activity.finish();
//            }
//        }
//    }
}
