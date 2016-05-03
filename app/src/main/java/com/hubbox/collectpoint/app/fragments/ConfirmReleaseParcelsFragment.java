package com.hubbox.collectpoint.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hubbox.collectpoint.app.MainActivity;
import com.hubbox.collectpoint.app.R;
import com.hubbox.collectpoint.app.interfaces.ITagFragment;
import com.hubbox.collectpoint.app.util.FragmentUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConfirmReleaseParcelsFragment.OnConfirmReleaseParcelsFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ConfirmReleaseParcelsFragment extends Fragment implements ITagFragment{
    private OnConfirmReleaseParcelsFragmentInteractionListener mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_confirm_release_parcels, container, false);
        view.findViewById(R.id.confirm_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfirmReleaseParcelsFragment.this.getActivity(),
                        R.string.success_give_out_parcel_tst,Toast.LENGTH_SHORT).show();
                FragmentUtils.setFragment((MainActivity) getActivity(), new AddGiveOutParcelFragment());
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnConfirmReleaseParcelsFragmentInteractionListener) {
            mListener = (OnConfirmReleaseParcelsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnConfirmReleaseParcelsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public String getFragmentTag() {
        return "confirm_release_parcels";
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
    public interface OnConfirmReleaseParcelsFragmentInteractionListener {
        // TODO: Update argument type and name
        void onConfirmReleaseParcelsFragmentInteraction();
    }
}
