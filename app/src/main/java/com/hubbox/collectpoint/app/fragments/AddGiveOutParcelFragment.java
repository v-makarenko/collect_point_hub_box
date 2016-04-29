package com.hubbox.collectpoint.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hubbox.collectpoint.app.MainActivity;
import com.hubbox.collectpoint.app.R;
import com.hubbox.collectpoint.app.interfaces.ITagFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnAddGiveOutParcelInteractionListener} interface
 * to handle interaction events.
 */
public class AddGiveOutParcelFragment extends Fragment implements ITagFragment {

    public static final String TAG = "ADD_PARCEL_FRAGMENT";

    public enum Result {
        ADD_PARCEL, GIVE_OUT
    }

    private OnAddGiveOutParcelInteractionListener mListener;

    public AddGiveOutParcelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_give_out_parcel, container, false);
        view.findViewById(R.id.add_parcel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onAddGiveOutFragmentInteraction(Result.ADD_PARCEL);
            }
        });
        view.findViewById(R.id.give_out_parcel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onAddGiveOutFragmentInteraction(Result.GIVE_OUT);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddGiveOutParcelInteractionListener) {
            mListener = (OnAddGiveOutParcelInteractionListener) context;
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
    public interface OnAddGiveOutParcelInteractionListener {
        // TODO: Update argument type and name
        void onAddGiveOutFragmentInteraction(Result result);
    }


    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
