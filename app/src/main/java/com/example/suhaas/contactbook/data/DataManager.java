package com.example.suhaas.contactbook.data;

import com.example.suhaas.contactbook.data.remote.ContactService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;
import rx.functions.Func1;

@Singleton
public class DataManager {

    private final ContactService mContactService;

    @Inject
    DataManager(ContactService mvpStarterService) {
        mContactService = mvpStarterService;
    }
}
