package com.example.hifooddeliveries;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderListAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<OrderModel> list;
    private Context context;

    public OrderListAdapter(ArrayList<OrderModel> list, Context context) {
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
            view = inflater.inflate(R.layout.order_list_item, null);
        }
        DBHelper dbHelper = new DBHelper(context);

        TextView id = view.findViewById(R.id.note);
        id.setText("Order : " + list.get(position).getOrderId());
        TextView item = view.findViewById(R.id.subNote);
        item.setText(dbHelper.getFoodItemById(list.get(position).itemId).getName());

        EditText rider = view.findViewById(R.id.deliverer);


        ImageView confirm = view.findViewById(R.id.confirmButton);
        if (list.get(position).getConfirmed()=="true"){
            confirm.setImageResource(R.drawable.done);
        }
        confirm.setClickable(true);
        confirm.setOnClickListener(v -> {
            String riderId = dbHelper.getRiderByName(rider.getText().toString());
            if (riderId==null)
                Toast.makeText(context, "The rider does not exesit", Toast.LENGTH_SHORT).show();
            else if (dbHelper.confirmOrder(list.get(position).getOrderId())){
                    dbHelper.setRider(list.get(position).getOrderId(),riderId);
                    confirm.setImageResource(R.drawable.done);
                    Toast.makeText(context, "Order confirmed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
