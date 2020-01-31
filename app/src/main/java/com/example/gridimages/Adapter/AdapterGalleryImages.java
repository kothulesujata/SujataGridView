package com.example.gridimages.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.gridimages.Model.GalleryImage;
import com.example.gridimages.R;
import com.example.gridimages.Utils.Config;

import java.util.ArrayList;

/**
 * Created by neha on 03/03/18.
 */

public class AdapterGalleryImages extends BaseAdapter {

    private Context context;
    private ArrayList<GalleryImage> listOfImages;

    public AdapterGalleryImages(Context context , ArrayList<GalleryImage> list){
        this.context = context;
        this.listOfImages = list;
    }

    @Override
    public int getCount() {
        return listOfImages.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder;

        if (convertView == null){

            holder = new Holder();
            convertView = View.inflate(context , R.layout.item_imggrid  , null);
            holder.imgGallery = convertView.findViewById(R.id.img_grid);
            holder.name=convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }




        Glide.with(context).load(Config.URL_IMAGE+""+ listOfImages.get(position).getId()).into(holder.imgGallery);
        holder.name.setText(""+listOfImages.get(position).getAuthor());

        return convertView;
    }

    class Holder{
        ImageView imgGallery;
        TextView name;
    }
}
