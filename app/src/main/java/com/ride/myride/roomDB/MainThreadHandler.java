package com.example.myride.roomDB;

import android.os.Handler;
import android.os.HandlerThread;

public class MainThreadHandler extends HandlerThread {

    private Handler handler;

    public MainThreadHandler(String name) {
        super(name);
    }

    public void postTask(Runnable task){
        handler.post(task);
    }

    public void prepareHandler(){
        handler = new Handler(getLooper());
    }

}
