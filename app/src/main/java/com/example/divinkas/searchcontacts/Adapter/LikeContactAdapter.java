package com.example.divinkas.searchcontacts.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.divinkas.searchcontacts.Activity.OpenPdfActivity;
import com.example.divinkas.searchcontacts.Data.Item;
import com.example.divinkas.searchcontacts.Model.SavedModel;
import com.example.divinkas.searchcontacts.R;

import java.util.List;

public class LikeContactAdapter extends RecyclerView.Adapter<LikeContactAdapter.LikeContactViewHolder> {

    private List<Item> itemList;
    private Context context;
    private SavedModel savedModel;

    public LikeContactAdapter(List<Item> list, Context context, SavedModel savedModel) {
        this.context = context;
        itemList = list;
        this.savedModel = savedModel;
    }

    @NonNull
    @Override
    public LikeContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LikeContactViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_like_contact, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LikeContactViewHolder likeContactViewHolder, int position) {
        likeContactViewHolder.setIsRecyclable(false);

        likeContactViewHolder.tvLikeContactComment.setText(itemList.get(position).getComments());
        likeContactViewHolder.tvLikeContactDescription.setText(itemList.get(position).getPlaceOfWork());
        likeContactViewHolder.tvLikeContactName.setText(
                String.format("%s %s", itemList.get(position).getLastname(), itemList.get(position).getFirstname())
        );

        likeContactViewHolder.imgDellFromLiked.setOnClickListener(view -> {
            View view1 = LayoutInflater.from(context).inflate(R.layout.message_dell_dilalog, null);
            AlertDialog.Builder dialog =  new AlertDialog.Builder(context)
                    .setTitle("Видалити?")
                    .setView(view1)
                    .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                        savedModel.dellItemFromDb(itemList.get(position));
                        //itemList.remove(itemList.get(position));
                        notifyDataSetChanged();
                    })
                    .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {

                    });
            dialog.show();

        });
        likeContactViewHolder.imgChangeComment.setOnClickListener(view -> {
            View view1 = LayoutInflater.from(context).inflate(R.layout.view_change_comment, null);
            EditText newComment = view1.findViewById(R.id.etNewComment);
            newComment.setText(itemList.get(position).getComments());
            AlertDialog.Builder dialog =  new AlertDialog.Builder(context)
                    .setTitle("Зберегти редагований коментар?")
                    .setView(view1)
                    .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                        savedModel.changeNewComment(itemList.get(position).getId(), newComment.getText().toString());
                        notifyDataSetChanged();
                    })
                    .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {

                    });
            dialog.show();
        });
        likeContactViewHolder.imgOpenPdfLike.setOnClickListener(view -> {
            Intent intent = new Intent(context, OpenPdfActivity.class);
            intent.putExtra("link", itemList.get(position).getLinkPDF());
            context.startActivity(intent);
        });
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

    public class LikeContactViewHolder extends RecyclerView.ViewHolder{

        private TextView tvLikeContactName, tvLikeContactDescription, tvLikeContactComment;
        private ImageView imgOpenPdfLike, imgDellFromLiked, imgChangeComment;

        public LikeContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLikeContactName = itemView.findViewById(R.id.tvLikeContactName);
            tvLikeContactDescription = itemView.findViewById(R.id.tvLikeContactDescription);
            tvLikeContactComment = itemView.findViewById(R.id.tvLikeContactComment);

            imgOpenPdfLike = itemView.findViewById(R.id.imvOpenPdfLike);
            imgChangeComment = itemView.findViewById(R.id.imvChangeComment);
            imgDellFromLiked = itemView.findViewById(R.id.imvDellFromLiked);
        }
    }
}
