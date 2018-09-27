package com.example.divinkas.searchcontacts.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.searchcontacts.Presenter.SearchPresenter;
import com.example.divinkas.searchcontacts.R;
import com.example.divinkas.searchcontacts.View.IsearchView;

public class SearchActivity extends MvpAppCompatActivity implements IsearchView {

    @InjectPresenter
    public SearchPresenter searchPresenter;

    private RecyclerView rvResultSearch;
    private EditText etTextSearch;
    private Button btnStartSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchPresenter.setContextInstance(SearchActivity.this);
        if (!searchPresenter.getNewtworkState()){ gotoErrorActivity(); }

        rvResultSearch = findViewById(R.id.rvContainerResultSearch);
        etTextSearch = findViewById(R.id.etSearchText);
        btnStartSearch = findViewById(R.id.btnStartSearch);

        rvResultSearch.setLayoutManager(new LinearLayoutManager(this));
        rvResultSearch.setAdapter(searchPresenter.getContactAdapter());

        btnStartSearch.setOnClickListener(view -> searchContacts());
    }

    private void gotoErrorActivity(){
        Intent intent = new Intent(this, ErrorActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void searchContacts() {
        if (!etTextSearch.getText().toString().isEmpty()){
            searchPresenter.searchData(etTextSearch.getText().toString());
        }
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
