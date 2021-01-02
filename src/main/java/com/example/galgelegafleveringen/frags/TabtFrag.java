package com.example.galgelegafleveringen.frags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.galgelegafleveringen.MainActivity;
import com.example.galgelegafleveringen.R;
import com.example.galgelegafleveringen.frags.StartFrag;

public class TabtFrag extends Fragment implements View.OnClickListener {

    String rigtigtOrd;
    TextView TWRigtigtOrd;
    Button BTPrøvIgen;
    MainActivity mainActivity;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        View rod = i.inflate(R.layout.activity_tabt, container, false);
        mainActivity = (MainActivity) getActivity();

        rigtigtOrd = mainActivity.getContext().getPrefs().getString("orderet", "orderet kunne ikke findes");
        TWRigtigtOrd = rod.findViewById(R.id.TWDetRigtigOrd);
        BTPrøvIgen = rod.findViewById(R.id.BTPrøvIgen);
        TWRigtigtOrd.setText(rigtigtOrd);


        BTPrøvIgen.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {

        Fragment start = new StartFrag();
        getFragmentManager().popBackStack();

    }
}
