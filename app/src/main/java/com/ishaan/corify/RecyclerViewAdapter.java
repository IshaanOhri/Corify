package com.ishaan.corify;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();
    private ArrayList<String> source = new ArrayList<>();
    private ArrayList<String> url = new ArrayList<>();
    private ArrayList<String> imageURL = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> title, ArrayList<String> description, ArrayList<String> source, ArrayList<String> url, ArrayList<String> imageURL, Context context) {
        this.title = title;
        this.description = description;
        this.source = source;
        this.url = url;
        this.imageURL = imageURL;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_individual_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.title.setText(title.get(i));
        viewHolder.description.setText(description.get(i));
        viewHolder.source.setText(source.get(i));
        Glide.with(context)
                .load(imageURL.get(i))
                .placeholder(R.drawable.image_error)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imageView);

        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url.get(i));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title, description, source;
        private ImageView imageView;
        private ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description =  itemView.findViewById(R.id.description);
            source =  itemView.findViewById(R.id.source);
            imageView =  itemView.findViewById(R.id.imageView);
            constraintLayout =  itemView.findViewById(R.id.news);

        }
    }

}
