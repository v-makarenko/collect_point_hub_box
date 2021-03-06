package com.hubbox.collectpoint.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.hubbox.collectpoint.app.R;
import com.hubbox.collectpoint.app.interfaces.ITagFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnEnterHubboxFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EnterHubBoxCodeFragment extends Fragment implements ITagFragment {

    private OnEnterHubboxFragmentInteractionListener mListener;

    EditText hubboxCodeET = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enter_hub_box_code, container, false);
        hubboxCodeET = ((EditText)view.findViewById(R.id.hubbox_code_et));
        view.findViewById(R.id.add_parcel_hubbox_code_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EnterHubBoxCodeFragment.this.getActivity(), String.format("Success! Added parcel Hubbox%s", hubboxCodeET.getText()), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEnterHubboxFragmentInteractionListener) {
            mListener = (OnEnterHubboxFragmentInteractionListener) context;
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
        return "enter_hubbox_fragment";
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
    public interface OnEnterHubboxFragmentInteractionListener {
        // TODO: Update argument type and name
        void onEnterHubboxFragmentInteraction(String hubboxNo);
    }
}
