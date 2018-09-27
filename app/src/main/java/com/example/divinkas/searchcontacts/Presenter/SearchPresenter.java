package com.example.divinkas.searchcontacts.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.divinkas.searchcontacts.Adapter.ContactAdapter;
import com.example.divinkas.searchcontacts.Data.Item;
import com.example.divinkas.searchcontacts.Model.SavedModel;
import com.example.divinkas.searchcontacts.Model.SearchModel;
import com.example.divinkas.searchcontacts.View.IsearchView;

import java.util.ArrayList;

@InjectViewState
public class SearchPresenter extends MvpPresenter<IsearchView> {

    private Context context;
    private SearchModel searchModel;
    private ContactAdapter contactAdapter;
    private SavedModel savedModel;

    public SearchPresenter() {
    }

    public void setContextInstance(Context context){
        if (this.context == null){
            this.context = context;
            searchModel = new SearchModel(this.context);
            savedModel = new SavedModel(this.context);
            contactAdapter = new ContactAdapter(this.context, new ArrayList<>(), savedModel);
        }
    }

    public boolean getNewtworkState(){
        return searchModel.isNetworkState();
    }

    public ContactAdapter getContactAdapter(){
        return contactAdapter;
    }

    public void searchData(String strQuery){
        searchModel.loadata(contactAdapter, strQuery);
    }

    public void closeRealm(){ savedModel.close(); }

}
