package com.pk.drawerFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pk.R;

import java.util.ArrayList;

public class Help extends Fragment {
    View view;
    ListView helpList;
    ArrayList<String> helpArrayList = new ArrayList<>();
    ArrayAdapter<String> helpAdapter;

    public Help() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.help, container, false);

        helpList = view.findViewById(R.id.help_list);
        String[] helpMe = getResources().getStringArray(R.array.help_me);
        helpAdapter = new ArrayAdapter<String>(getContext(), R.layout.help_strip, R.id.help_text, helpMe);
        helpList.setAdapter(helpAdapter);

        return view;
    }
}
