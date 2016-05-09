package com.itgate.restaurant.adapter;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itgate.restaurant.R;
import com.itgate.restaurant.model.Commande;
import com.itgate.restaurant.model.LigneCommande;
import com.itgate.restaurant.model.Plat;
import com.itgate.restaurant.service.RestServeur;
import com.itgate.restaurant.util.Config;
import com.itgate.restaurant.util.ConvertImageFromStringToBitmap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LigneCommandeAdapter extends ArrayAdapter<LigneCommande> {

    Context context;
    List<LigneCommande> drawerItemList;
    int layoutResID;
    DrawerItemHolder drawerHolder;
    public LigneCommandeAdapter(Context context, int layoutResourceID,
                                ArrayList<LigneCommande> dataList) {
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
        LigneCommande dItem = (LigneCommande) this.drawerItemList.get(position);
        drawerHolder.icon.setImageBitmap(ConvertImageFromStringToBitmap.convert(dItem.getPlat().getImagePlat()));
        drawerHolder.ItemPlat.setText(dItem.getPlat().getLibellePlat());
        drawerHolder.ItemQuantite.setText(String.valueOf(dItem.getQuantite()));



        return view;
    }

    private static class DrawerItemHolder {

        TextView ItemPlat;
        TextView ItemQuantite;


        ImageView icon;
    }
}

