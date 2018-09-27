package com.example.divinkas.searchcontacts.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.divinkas.searchcontacts.Activity.OpenPdfActivity;
import com.example.divinkas.searchcontacts.Data.Item;
import com.example.divinkas.searchcontacts.Model.SavedModel;
import com.example.divinkas.searchcontacts.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context context;
    private List<Item> itemList;
    private SavedModel savedModel;

    public ContactAdapter(Context context, List<Item> itemList, SavedModel savedModel) {
        this.context = context;
        this.itemList = itemList;
        this.savedModel = savedModel;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search_contact, viewGroup, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, @SuppressLint("RecyclerView") final int position) {
        contactViewHolder.setIsRecyclable(false);
        contactViewHolder.tvContactName.setText(itemList.get(position).getLastname() + " " + itemList.get(position).getFirstname());
        contactViewHolder.tvContactDescription.setText(itemList.get(position).getPlaceOfWork());
        contactViewHolder.imgSave.setOnClickListener(view -> savedModel.addObject(itemList.get(position)));
        contactViewHolder.imgOpenPdf.setOnClickListener(view -> openPdfByLink(itemList.get(position).getLinkPDF()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void listUpdate(List<Item> newList){
        itemList = newList;
        notifyDataSetChanged();
    }

    private void openPdfByLink(String link){
        Intent intent = new Intent(context, OpenPdfActivity.class);
        intent.putExtra("link", link);
        context.startActivity(intent);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        private TextView tvContactName, tvContactDescription;
        private ImageView imgOpenPdf, imgSave;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContactDescription = itemView.findViewById(R.id.tvContactDescription);
            tvContactName = itemView.findViewById(R.id.tvContactName);
            imgOpenPdf = itemView.findViewById(R.id.imvOpenPdf);
            imgSave = itemView.findViewById(R.id.imvAddToLike);
        }
    }
}
