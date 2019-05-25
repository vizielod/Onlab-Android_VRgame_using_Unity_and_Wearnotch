package com.unity3d.plugintest;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {


    public BlankFragment2() {
        // Required empty public constructo
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = View.inflate(getActivity(), R.layout.fragment_blank_fragment2, null);
        TextView textView = (TextView) root.findViewById(R.id.placeholder_text);
        textView.setText(getArguments().getString("message"));
        textView.setTextColor(Color.RED);
        return root;
        //return inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
    }


}
