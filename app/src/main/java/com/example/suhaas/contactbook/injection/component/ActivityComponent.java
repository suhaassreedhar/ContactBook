package com.example.suhaas.contactbook.injection.component;

import com.example.suhaas.contactbook.ui.main.MainActivity;
import com.example.suhaas.contactbook.injection.PerActivity;
import com.example.suhaas.contactbook.injection.module.ActivityModule;
import com.example.suhaas.contactbook.ui.base.BaseActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

}

