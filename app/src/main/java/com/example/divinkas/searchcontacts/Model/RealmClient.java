package com.example.divinkas.searchcontacts.Model;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmClient {

    private static Realm realm;

    public static Realm getInstance(Context context) {
        if(realm == null){
            Realm.init(context);
            realm = Realm.getDefaultInstance();
            RealmConfiguration config = new RealmConfiguration.Builder().name("contactList.realm").build();
            Realm.setDefaultConfiguration(config);
        }
        return realm;
    }

    private RealmClient() {
    }
}
