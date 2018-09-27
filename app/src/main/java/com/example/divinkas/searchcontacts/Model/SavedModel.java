package com.example.divinkas.searchcontacts.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.divinkas.searchcontacts.Data.Item;
import com.example.divinkas.searchcontacts.R;

import java.util.List;
import java.util.Objects;

import io.realm.Realm;

public class SavedModel {
    private Context context;
    private Realm realm;

    public SavedModel(Context context) {
        this.context = context;
        realm = RealmClient.getInstance(this.context);
    }

    public List<Item> getListSavedContacts(){
        List<Item> list;
        realm.beginTransaction();
        list = realm.where(Item.class).findAll();
        realm.commitTransaction();
        return list;
    }

    public void addObject(Item item){
        View view1 = LayoutInflater.from(context).inflate(R.layout.message_dialog, null);
        AlertDialog.Builder dialog =  new AlertDialog.Builder(context)
                            .setTitle("Зберегти в обраних?")
                            .setView(view1)
                            .setPositiveButton(R.string.yes, (dialogInterface, i) -> save(item))
                            .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {

                            });
        dialog.show();


    }

    public void dellItemFromDb(Item item){
        realm.beginTransaction();
        realm.where(Item.class).equalTo("id", item.getId()).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    private void save(Item item){
        realm.beginTransaction();
        if(isNotInserted(item)) {
            realm.insert(item);
            Toast.makeText(context, "Додано!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Контакт вже є в списку!", Toast.LENGTH_LONG).show();
        }
        realm.commitTransaction();
    }

    private boolean isNotInserted(Item item){
        List<Item> controlList = realm.where(Item.class).equalTo("id", item.getId()).findAll();
        return controlList.size() == 0;
    }

    public void close(){
        realm.close();
    }

    public void changeNewComment(String id, String newComment) {
        realm.beginTransaction();
        realm.where(Item.class).equalTo("id", id).findFirst().setComments(newComment);
        realm.commitTransaction();
    }
}
