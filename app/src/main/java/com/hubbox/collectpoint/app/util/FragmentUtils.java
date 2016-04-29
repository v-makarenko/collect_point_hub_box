package com.hubbox.collectpoint.app.util;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.hubbox.collectpoint.app.fragments.AddGiveOutParcelFragment;
import com.hubbox.collectpoint.app.R;
import com.hubbox.collectpoint.app.interfaces.ITagFragment;

/**
 * Created by VMakarenko on 29.04.2016.
 */
public class FragmentUtils {
    public static void addFragment(AppCompatActivity activity, ITagFragment fragment){
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_id, (Fragment)fragment, fragment.getFragmentTag())
                .commit();

    }
}
