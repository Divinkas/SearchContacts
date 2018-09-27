package com.example.divinkas.searchcontacts.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.divinkas.searchcontacts.Adapter.ContactAdapter;
import com.example.divinkas.searchcontacts.Data.ContactsObject;
import com.example.divinkas.searchcontacts.Retrofit.IContactsList;
import com.example.divinkas.searchcontacts.Retrofit.RetrofitClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SearchModel {
    private Context context;
    private Retrofit retrofit;
    private IContactsList iContactsList;
    private Observer<ContactsObject> observer;

    public SearchModel(Context context) {
        this.context = context;
        retrofit = RetrofitClient.getInstance();
        iContactsList = retrofit.create(IContactsList.class);
    }

    public boolean isNetworkState(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm != null ? cm.getActiveNetworkInfo() : null;

        if (netinfo != null) {
            if (netinfo.isConnectedOrConnecting()) {
                NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
            } else
                return false;
        }
        return false;
    }

    public void loadata(final ContactAdapter contactAdapter, String queryStr) {
        observer = new Observer<ContactsObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ContactsObject contactsObject) {
                contactAdapter.listUpdate(contactsObject.getItems());
            }
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        iContactsList.getContactsList(queryStr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .cache()
                .subscribe(observer);
    }
}
