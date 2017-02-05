package com.example.suhaas.contactbook.injection.component;

import android.app.Application;
import android.content.Context;

import com.example.suhaas.contactbook.data.remote.ContactService;
import com.example.suhaas.contactbook.data.DataManager;
import com.example.suhaas.contactbook.injection.ApplicationContext;
import com.example.suhaas.contactbook.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();

    ContactService contactService();
}
