package com.itgate.restaurant.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itgate.restaurant.R;
import com.itgate.restaurant.model.LigneCommande;
import com.itgate.restaurant.model.Plat;
import com.itgate.restaurant.util.ConvertImageFromStringToBitmap;

import java.util.ArrayList;
import java.util.List;


public class PlatAdapter extends ArrayAdapter<Plat> {

    Context context;
    List<Plat> drawerItemList;
    int layoutResID;
    DrawerItemHolder drawerHolder;
    public PlatAdapter(Context context, int layoutResourceID,
                       ArrayList<Plat> dataList) {
        super(context, layoutResourceID, dataList);
        this.context = context;
        this.drawerItemList = dataList;
        this.layoutResID = layoutResourceID;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.thumbnail);
            drawerHolder.ItemPlat = (TextView) view.findViewById(R.id.textView8);
            drawerHolder.ItemQuantite = (TextView) view.findViewById(R.id.textView9);
            view.setTag(drawerHolder);
        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();

        }
        Plat dItem = (Plat) this.drawerItemList.get(position);
        drawerHolder.icon.setImageBitmap(ConvertImageFromStringToBitmap.convert(dItem.getImagePlat()));
        drawerHolder.ItemPlat.setText(dItem.getLibellePlat());
        drawerHolder.ItemQuantite.setText(String.valueOf(dItem.getPrixPlat()));



        return view;
    }

    private static class DrawerItemHolder {

        TextView ItemPlat;
        TextView ItemQuantite;


        ImageView icon;
    }
}

