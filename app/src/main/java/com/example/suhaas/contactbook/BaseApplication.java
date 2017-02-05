package com.example.suhaas.contactbook;


import android.app.Application;
import android.content.Context;

import com.example.suhaas.contactbook.injection.component.ApplicationComponent;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public class BaseApplication extends Application {

    ApplicationComponent mApplicationComponent;

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
        }
    }

    public ApplicationComponent getComponent() {
//        if (mApplicationComponent == null) {
//            mApplicationComponent = DaggerApplicationComponent.builder()
//                    .applicationModule(new ApplicationModule(this))
//                    .build();
//        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
