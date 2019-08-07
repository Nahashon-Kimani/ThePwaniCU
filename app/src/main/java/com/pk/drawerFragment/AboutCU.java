package com.pk.drawerFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pk.R;

public class AboutCU extends Fragment {
    View view;
    TextView aboutCUTextView;
    Button aboutCuButton;
    FloatingActionButton fabAbout;

    public AboutCU() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.about_cu, container, false);

        //Incase of change Control Peter should be able to edit the existing about CU and set and set a new About.
        aboutCuButton = view.findViewById(R.id.about_cu_button);
        aboutCUTextView = view.findViewById(R.id.about_cu_text_view);
        fabAbout = view.findViewById(R.id.share_about_cu);

        aboutCUTextView.setMovementMethod(new ScrollingMovementMethod());
        //aboutCUTextView.setText(Html.fromHtml(getString(R.string.about_cu_desc)));//Used to format text to html style.
        aboutCUTextView.setText(getString(R.string.about_cu_desc));

        fabAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareAboutCU();//This method shares about PwaniCU. Another item to be shared will be searched.
            }
        });

        aboutCuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCUWebsite();//This method is used to open the CU Website
            }
        });


        return view;
    }

    public void shareAboutCU() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.app_name));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share About Pwani Cu"));
    }

    public void openCUWebsite() {
        Uri uri = Uri.parse("https://pucu.co.ke");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
