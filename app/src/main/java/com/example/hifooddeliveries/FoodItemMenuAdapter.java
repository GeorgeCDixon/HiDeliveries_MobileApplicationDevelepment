package com.example.hifooddeliveries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodItemMenuAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<FoodModel> list;
    private Context context;

    public FoodItemMenuAdapter(ArrayList<FoodModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.menu_list_item, null);
        }


        TextView name= view.findViewById(R.id.food1);
        name.setText(list.get(position).getName());

        ImageView img = view.findViewById(R.id.img1);
        int imageResource = context.getResources().getIdentifier(list.get(position).getImgURL(), null, context.getPackageName());
        System.out.println(list.get(position).getImgURL()+" here");
        if (imageResource>0) {
            img.setImageDrawable(context.getDrawable(imageResource));
        }

        //use the below commented code to load image form the internet using an URL
//        Picasso.get().load(list.get(position).getImgURL()).into(img);


        Button edit= view.findViewById(R.id.btn_edit1);
        Button delete= view.findViewById(R.id.btn_delete1);

        edit.setOnClickListener(v -> {

        });
        delete.setOnClickListener(v -> {

        });

        return view;
    }
}