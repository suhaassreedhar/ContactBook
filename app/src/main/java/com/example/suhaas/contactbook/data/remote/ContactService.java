package com.example.suhaas.contactbook.data.remote;


import com.example.suhaas.contactbook.data.model.ContactListResponse;
import com.example.suhaas.contactbook.data.model.ContactResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface ContactService {

    @GET("contacts.json")
    Single<ContactListResponse> getContactList(@Query("limit") int limit);

    @GET("contacts/{name}")
    Single<ContactResponse> getContact(@Path("name") String name);
}
