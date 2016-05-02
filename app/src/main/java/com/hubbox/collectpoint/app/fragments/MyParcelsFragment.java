package com.hubbox.collectpoint.app.fragments;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hubbox.collectpoint.app.R;
import com.hubbox.collectpoint.app.adapter.MyParcelAdapter;
import com.hubbox.collectpoint.app.dto.Parcel;
import com.hubbox.collectpoint.app.interfaces.ITagFragment;
import com.hubbox.collectpoint.app.util.PrototypeUtils;

import java.util.List;

public class MyParcelsFragment extends ListFragment implements ITagFragment{


    private OnMyParcelsFragmentInteractionListener mListener;
    private MyParcelAdapter myParcelAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myParcelAdapter =
                new MyParcelAdapter(getActivity(), R.layout.my_parcel_item);
        setListAdapter(myParcelAdapter);
        Log.d(this.getClass().getName(), "Loaded my parcel fragment");
    }

    @Override
    public void onResume(){
        super.onResume();
        myParcelAdapter.clear();
        myParcelAdapter.addAll(PrototypeUtils.getSampleParcelList());
        myParcelAdapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_my_parcels, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMyParcelsFragmentInteractionListener) {
            mListener = (OnMyParcelsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public String getFragmentTag() {
        return "my_parcels_fragment";
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMyParcelsFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMyParcelsFragmentInteraction();
    }
}
