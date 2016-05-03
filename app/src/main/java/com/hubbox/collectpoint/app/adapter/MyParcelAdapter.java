package com.hubbox.collectpoint.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hubbox.collectpoint.app.MainActivity;
import com.hubbox.collectpoint.app.R;
import com.hubbox.collectpoint.app.dto.Parcel;
import com.hubbox.collectpoint.app.fragments.IdDocumentChoiseFragment;
import com.hubbox.collectpoint.app.fragments.MyParcelsFragment;
import com.hubbox.collectpoint.app.fragments.RefNumberEnterFragment;
import com.hubbox.collectpoint.app.util.FragmentUtils;

/**
 * Created by VMakarenko on 01.05.2016.
 */
public class MyParcelAdapter extends ArrayAdapter<Parcel> {
    private Context context;
    private MyParcelsFragment fragment;


//    private List<Parcel> parcelList = new ArrayList<>();

    public MyParcelAdapter(Context context, int resource, MyParcelsFragment fragment) {
        super(context, resource);
        this.context = context;
        this.fragment = fragment;
        Log.d(this.getClass().getName(), "My parcel adapter loaded");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.my_parcel_item, parent, false);
        ((TextView)row.findViewById(R.id.user_name_tv)).setText(getItem(position).getName());
        ((TextView)row.findViewById(R.id.hubbox_code_tv)).setText(getItem(position).getHubboxCode().toString());

        row.findViewById(R.id.ref_number_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.setFragment((MainActivity) fragment.getActivity(), new RefNumberEnterFragment());
            }
        });
        row.findViewById(R.id.id_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.setFragment((MainActivity) fragment.getActivity(), new IdDocumentChoiseFragment());
            }
        });
        return row;
    }




}
