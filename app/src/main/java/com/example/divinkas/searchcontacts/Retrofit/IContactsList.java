package com.example.divinkas.searchcontacts.Retrofit;

import com.example.divinkas.searchcontacts.Data.ContactsObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IContactsList {
    @GET("declaration/")
    Observable<ContactsObject> getContactsList(@Query("q") String q);
}
