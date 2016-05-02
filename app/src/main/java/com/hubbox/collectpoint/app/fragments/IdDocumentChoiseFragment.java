package com.hubbox.collectpoint.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hubbox.collectpoint.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IdDocumentChoiseFragment.OnIdDocChoiseFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class IdDocumentChoiseFragment extends Fragment {

    private OnIdDocChoiseFragmentInteractionListener mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_id_document_choise, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnIdDocChoiseFragmentInteractionListener) {
            mListener = (OnIdDocChoiseFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnIdDocChoiseFragmentInteractionListener");
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
    public interface OnIdDocChoiseFragmentInteractionListener {
        // TODO: Update argument type and name
        void onIdDocChoiseFragmentInteraction(Uri uri);
    }
}
