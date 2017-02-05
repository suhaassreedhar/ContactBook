package com.example.suhaas.contactbook.injection.component;

import com.example.suhaas.contactbook.injection.PerFragment;
import com.example.suhaas.contactbook.injection.module.FragmentModule;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

}
