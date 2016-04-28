package com.hubbox.collectpoint.app;

import android.app.Application;

import com.hubbox.collectpoint.app.util.AppConsts;

/**
 * Created by VMakarenko on 26.04.2016.
 */
public class HubBoxApplication extends Application {
    private boolean loggedIn = false;

    @Override
    public void onCreate() {
        super.onCreate();
        loggedIn = false;
    }

    public void logout(){
        loggedIn = false;
        //TODO add code
    }
    public void login(){
        loggedIn = true;
        // TODO add code
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
