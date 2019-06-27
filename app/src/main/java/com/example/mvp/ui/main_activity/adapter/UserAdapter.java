package com.example.mvp.ui.main_activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvp.R;
import com.example.mvp.data.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ArrayList<User> mList;
    RecyclerViewCallBack mCallBack;

    public UserAdapter(ArrayList<User> list, RecyclerViewCallBack callBack) {
        this.mList = list;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        User user = mList.get(i);
        Picasso.get()
                .load(mList.get(i).getAvatar())
                .fit()
                .centerCrop()
                .into(viewHolder.imgView);
        viewHolder.txtTen.setText(user.getFirstName() + user.getLastName());
        viewHolder.txtMail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView txtTen, txtMail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img_view);
            txtMail = itemView.findViewById(R.id.txt_mail);
            txtTen = itemView.findViewById(R.id.txt_ten);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.onClickItem(getAdapterPosition());
                }
            });

        }
    }

    public interface RecyclerViewCallBack {
        void onClickItem(int position);
    }
}
