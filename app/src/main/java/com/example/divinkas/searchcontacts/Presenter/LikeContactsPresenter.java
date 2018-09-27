package com.example.divinkas.searchcontacts.Presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.divinkas.searchcontacts.Adapter.LikeContactAdapter;
import com.example.divinkas.searchcontacts.Model.SavedModel;
import com.example.divinkas.searchcontacts.View.IlikeContactsView;

@InjectViewState
public class LikeContactsPresenter extends MvpPresenter<IlikeContactsView> {
    private Context context;
    private SavedModel savedModel;
    private LikeContactAdapter likeContactAdapter;

    public LikeContactsPresenter() {
    }

    public void setDefaultContext(Context context){
        if(this.context == null){
            this.context = context;
            savedModel = new SavedModel(this.context);
            likeContactAdapter = new LikeContactAdapter(savedModel.getListSavedContacts(), this.context, savedModel);
        }
    }

    public LikeContactAdapter getLikeContactAdapter(){
        return likeContactAdapter;
    }

    public void closeRealm(){ savedModel.close();}

}
