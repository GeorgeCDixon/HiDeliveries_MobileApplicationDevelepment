package com.example.hifooddeliveries;

import android.content.Context;
import android.content.Intent;
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

public class CartListItemAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<FoodModel> list;
    private Context context;

    public CartListItemAdapter(ArrayList<FoodModel> list, Context context) {
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
            view = inflater.inflate(R.layout.cart_list_item, null);
        }


        TextView name= view.findViewById(R.id.food_txt);
        name.setText(list.get(position).getName());

        ImageView img = view.findViewById(R.id.imageView3);
        int imageResource = context.getResources().getIdentifier(list.get(position).getImgURL(), null, context.getPackageName());
        System.out.println(list.get(position).getImgURL()+" here");
        if (imageResource>0) {
            img.setImageDrawable(context.getDrawable(imageResource));
        }

        //use the below commented code to load image form the internet using an URL
//        Picasso.get().load(list.get(position).getImgURL()).into(img);

        Button edit= view.findViewById(R.id.btn_view2);

        edit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddToCartActivity.class);
            intent.putExtra("ITEM_ID", list.get(position).getId());
            context.startActivity(intent);

        });

        return view;
    }
}
