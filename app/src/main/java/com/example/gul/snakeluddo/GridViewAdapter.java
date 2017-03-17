package com.example.gul.snakeluddo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gul on 3/16/17.
 */

public class GridViewAdapter extends ArrayAdapter<Block> {
    Context context;
    int resource;
    List<Block> messages;

    public GridViewAdapter(Context context, int resource, List<Block> objects){

        super(context,resource,objects);

        this.context=context;
        this.resource=resource;
        this.messages=objects;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterViewHolder holder=null;
        LayoutInflater mInflater =  LayoutInflater.from(context);
        holder=new AdapterViewHolder();
        convertView=mInflater.inflate(resource, null);




        holder.blockDescription=(TextView)convertView.findViewById(R.id.block);
        holder.blockDescription.setText(messages.get(position).description);
        if(messages.get(position).light){

            //light background
            if(messages.get(position).user){
                //add user rectangle

                holder.blockDescription.setBackground(context.getResources().getDrawable(R.drawable.user_rectangle));
            }
            else if(messages.get(position).computer){
                //add computer rectangle
                holder.blockDescription.setBackground(context.getResources().getDrawable(R.drawable.computer_rectangle));

            }
        }

        holder.clearCache();


        return convertView;
    }

}
