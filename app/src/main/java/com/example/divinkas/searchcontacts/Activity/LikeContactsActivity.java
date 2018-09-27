package com.example.divinkas.searchcontacts.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.searchcontacts.Presenter.LikeContactsPresenter;
import com.example.divinkas.searchcontacts.R;
import com.example.divinkas.searchcontacts.View.IlikeContactsView;

public class LikeContactsActivity extends MvpAppCompatActivity implements IlikeContactsView {

    @InjectPresenter
    public LikeContactsPresenter likeContactsPresenter;

    private RecyclerView rvSavedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_contacts);

        likeContactsPresenter.setDefaultContext(LikeContactsActivity.this);

        rvSavedList = findViewById(R.id.rvSavedList);
        rvSavedList.setLayoutManager(new LinearLayoutManager(this));
        rvSavedList.setAdapter(likeContactsPresenter.getLikeContactAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, ErrorActivity.class);
        switch (item.getItemId()){
            case R.id.openSearchActivity:
                intent = new Intent(this, SearchActivity.class);
                break;
            case R.id.openLikeActivity:
                intent = new Intent(this, LikeContactsActivity.class);
                break;
        }
        startActivity(intent);
        return true;
    }

}
