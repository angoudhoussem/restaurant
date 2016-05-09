package com.itgate.restaurant.adapter;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
import com.itgate.restaurant.model.Plat;
import com.itgate.restaurant.service.RestServeur;
import com.itgate.restaurant.util.Config;
import com.itgate.restaurant.util.ConvertImageFromStringToBitmap;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ServeurAdapter extends ArrayAdapter<Commande> {

    Context context;
    List<Commande> drawerItemList;
    int layoutResID;
    DrawerItemHolder drawerHolder;
    public ServeurAdapter(Context context, int layoutResourceID,
                          ArrayList<Commande> dataList) {
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
            drawerHolder.ItemName = (TextView) view.findViewById(R.id.title);
            drawerHolder.ItemDate = (TextView) view.findViewById(R.id.textView3);
            drawerHolder.ItemDaprtemt = (TextView) view.findViewById(R.id.textView5);
            view.setTag(drawerHolder);
        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();

        }
        Commande dItem = (Commande) this.drawerItemList.get(position);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
                Config.base_url + "/plats").build();
        final RestServeur rest = restAdapter.create(RestServeur.class);
        rest.getPlatByIDPlat(dItem.getIdFirstPlat(), new Callback<Plat>() {
            @Override
            public void success(Plat plat, Response response) {

                Log.v("success", plat.getLibellePlat());
                drawerHolder.icon.setImageBitmap(ConvertImageFromStringToBitmap.convert(plat.getImagePlat()));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("erreur", error.getMessage());

            }
        });

        drawerHolder.ItemName.setText(String.valueOf(dItem.getTableCommande().getNumTable()));
       drawerHolder.ItemDaprtemt.setText(String.valueOf(dItem.getTableCommande().getDepartement().getNomDepartement()));
        Date date = new Date(Long.parseLong(dItem.getDateCommande()));
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
       drawerHolder.ItemDate.setText(String.valueOf(format.format(date)));
        Log.d("Getview", "Passed5");

        return view;
    }

    private static class DrawerItemHolder {

        TextView ItemName;
        TextView ItemDaprtemt;
        TextView ItemDate;

        ImageView icon;
    }
}

