package com.example.suhaas.contactbook.data.remote;


import com.example.suhaas.contactbook.data.model.ContactListResponse;
import com.example.suhaas.contactbook.data.model.ContactResponse;
import com.example.suhaas.contactbook.data.model.Contacts;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface ContactService {

    @GET("contacts.json")
    Single<List<Contacts>> getContactList(@Query("limit") int limit);

    @GET("contacts/{name}")
    Single<ContactResponse> getContact(@Path("name") String name);
}
