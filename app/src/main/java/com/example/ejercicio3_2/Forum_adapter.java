package com.example.ejercicio3_2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Forum_adapter extends RecyclerView.Adapter<Forum_adapter.ForumViewHolder> {
    private List<Forum_post> forumPosts;

    public Forum_adapter(List<Forum_post> forumPosts) {
        this.forumPosts = forumPosts;
    }

    @NonNull
    @Override
    public ForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_forum_post, parent, false);
        return new ForumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumViewHolder holder, int position) {
        Forum_post post = forumPosts.get(position);
        holder.postTitle.setText(post.getTitle());
        holder.postContent.setText(post.getContent());
        holder.postImage.setImageResource(post.getImageResource());
    }


    @Override
    public int getItemCount() {
        return forumPosts.size();
    }

    public static class ForumViewHolder extends RecyclerView.ViewHolder {
        TextView postTitle;
        TextView postContent;
        ImageView postImage;

        public ForumViewHolder(@NonNull View itemView) {
            super(itemView);
            postTitle = itemView.findViewById(R.id.postTitle);
            postContent = itemView.findViewById(R.id.postContent);
            postImage = itemView.findViewById(R.id.postImage);
        }
    }
}
