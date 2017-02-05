package com.example.suhaas.contactbook.injection.module;

import android.app.Application;
import android.content.Context;

import com.example.suhaas.contactbook.data.remote.ContactService;
import com.example.suhaas.contactbook.data.remote.ContactServiceFactory;
import com.example.suhaas.contactbook.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    static ContactService provideContactService() {
        return ContactServiceFactory.makeContactService();
    }
}
