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

    public Single<List<String>> getContactList(int limit) {
        return mContactService.getContactList(limit)
                .flatMap(new Func1<ContactListResponse, Single<List<String>>>() {
                    @Override
                    public Single<List<String>> call(ContactListResponse contactListResponse) {
                        List<String> contactNames = new ArrayList<>();
                        for (Contacts contacts : contactListResponse.results) {
                            contactNames.add(contacts.getFirstName());
                            contactNames.add(contacts.getLastName());
                        }
                        return Single.just(contactNames);
                    }
                });
    }

    public Single<ContactResponse> getPokemon(String name) {
        return mContactService.getContact(name);
    }
}
