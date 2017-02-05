package com.example.suhaas.contactbook.injection.component;

import com.example.suhaas.contactbook.injection.ConfigPersistent;
import com.example.suhaas.contactbook.injection.module.ActivityModule;
import com.example.suhaas.contactbook.injection.module.FragmentModule;

import dagger.Component;

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    FragmentComponent fragmentComponent(FragmentModule fragmentModule);

}
