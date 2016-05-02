package com.hubbox.collectpoint.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.hubbox.collectpoint.app.R;
import com.hubbox.collectpoint.app.dto.Parcel;
import com.hubbox.collectpoint.app.util.PrototypeUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMakarenko on 01.05.2016.
 */
public class MyParcelAdapter extends ArrayAdapter<Parcel> {
    private Context context;


//    private List<Parcel> parcelList = new ArrayList<>();

    public MyParcelAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        Log.d(this.getClass().getName(), "My parcel adapter loaded");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.my_parcel_item, parent, false);
        ((TextView)row.findViewById(R.id.user_name_tv)).setText(getItem(position).getName());
        ((TextView)row.findViewById(R.id.hubbox_code_tv)).setText(getItem(position).getHubboxCode().toString());
        return row;
    }




}
