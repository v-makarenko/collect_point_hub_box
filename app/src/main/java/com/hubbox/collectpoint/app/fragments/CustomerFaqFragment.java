package com.hubbox.collectpoint.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hubbox.collectpoint.app.R;
import com.hubbox.collectpoint.app.interfaces.ITagFragment;
import com.hubbox.collectpoint.app.util.ResourceUtils;


public class CustomerFaqFragment extends Fragment implements ITagFragment{
    public static final String TAG = "customer_faq_fragment";

    public CustomerFaqFragment() {
        // Required empty public constructor
    }

    public static CustomerFaqFragment newInstance() {
        CustomerFaqFragment fragment = new CustomerFaqFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =  inflater.inflate(R.layout.fragment_customer_faq, container, false);
        ((TextView)view.findViewById(R.id.text_view_id)).setText(Html.fromHtml(ResourceUtils.getStringResourceByName(this.getActivity(), "faq_text")));
        return view;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
