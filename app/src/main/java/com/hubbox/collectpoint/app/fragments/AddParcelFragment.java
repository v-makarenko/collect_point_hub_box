package com.hubbox.collectpoint.app.fragments;

import android.content.Context;
import android.net.Uri;
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
 * {@link OnAddParcelFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AddParcelFragment extends Fragment implements ITagFragment{

    public enum Result{
        HUBBOX_CODE, JUST_NAME
    }

    private OnAddParcelFragmentInteractionListener mListener;

    public AddParcelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_parcel, container, false);

        view.findViewById(R.id.hubbox_code_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).onAddParcelFragmentInteraction(Result.HUBBOX_CODE);
            }
        });
        view.findViewById(R.id.just_a_name_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).onAddParcelFragmentInteraction(Result.JUST_NAME);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddParcelFragmentInteractionListener) {
            mListener = (OnAddParcelFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddParcelFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public String getFragmentTag() {
        return "add_parcel_fragment";
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
    public interface OnAddParcelFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAddParcelFragmentInteraction(Result result);
    }
}
