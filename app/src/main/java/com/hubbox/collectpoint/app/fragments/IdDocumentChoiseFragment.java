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
import com.hubbox.collectpoint.app.util.FragmentUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnIdDocChoiceFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class IdDocumentChoiseFragment extends Fragment implements ITagFragment{

    private OnIdDocChoiceFragmentInteractionListener mListener;

    @Override
    public String getFragmentTag() {
        return "id_document_choise";
    }

    public class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FragmentUtils.addFragment((MainActivity)(IdDocumentChoiseFragment.this.getActivity()), new ConfirmReleaseParcelsFragment());
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_id_document_choise, container, false);
        ButtonListener buttonListener = new ButtonListener();
        view.findViewById(R.id.passport_id).setOnClickListener(buttonListener);
        view.findViewById(R.id.bank_card_id).setOnClickListener(buttonListener);
        view.findViewById(R.id.drive_license_id).setOnClickListener(buttonListener);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnIdDocChoiceFragmentInteractionListener) {
            mListener = (OnIdDocChoiceFragmentInteractionListener) context;
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

    public interface OnIdDocChoiceFragmentInteractionListener {
        void onIdDocChoiseFragmentInteraction();
    }
}
