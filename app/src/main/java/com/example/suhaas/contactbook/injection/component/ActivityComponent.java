package com.example.suhaas.contactbook.injection.component;

import com.example.suhaas.contactbook.ui.MainActivity;
import com.example.suhaas.contactbook.injection.PerActivity;
import com.example.suhaas.contactbook.injection.module.ActivityModule;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}

