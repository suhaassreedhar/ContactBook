package com.example.suhaas.contactbook.data;

import com.example.suhaas.contactbook.data.model.ContactListResponse;
import com.example.suhaas.contactbook.data.model.ContactResponse;
import com.example.suhaas.contactbook.data.model.Contacts;
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

    public Single<List<Contacts>> getContactList(int limit) {
        return mContactService.getContactList(limit)
                .flatMap(new Func1<List<Contacts>, Single<List<Contacts>>>() {
                    @Override
                    public Single<List<Contacts>> call(List<Contacts> contactListResponse) {
                        return Single.just(contactListResponse);
                    }
                });
    }

    public Single<ContactResponse> getContact(String name) {
        return mContactService.getContact(name);
    }
}
