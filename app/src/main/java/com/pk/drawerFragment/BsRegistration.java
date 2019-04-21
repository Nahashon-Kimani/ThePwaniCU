package com.pk.drawerFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pk.R;
import com.pk.model.BsRegistrationModel;

import java.text.DateFormat;
import java.util.Date;

public class BsRegistration extends Fragment {
    View view;
    WebView webView;
    ProgressBar bsProgressBar;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    String bsLink;

    public BsRegistration() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bs_registration, container, false);

        webView = view.findViewById(R.id.bs_registration_web_view);
        bsProgressBar = view.findViewById(R.id.bs_registration_progress);

        //Database init
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("New").child("BS Registration Link");

        String currentDate = DateFormat.getDateTimeInstance().format(new Date());

        BsRegistrationModel registrationModel = new BsRegistrationModel("Semester 2", "2019",
                "https://goo.gl/forms/ZUwIHCMEFqGed2ys1", currentDate);
        mRef.setValue(registrationModel);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BsRegistrationModel model = snapshot.getValue(BsRegistrationModel.class);
                    //bsLink = model.getLink();
                    Toast.makeText(getContext(), model.getLink(), Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        bsProgressBar.setVisibility(View.VISIBLE);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (bsProgressBar.isShown()) {
                    bsProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Sorry An Error Occurred");
                builder.setIcon(R.drawable.error);
                builder.setCancelable(false);
                builder.setMessage(error.toString() + "\n Please Check your internet Connectivity.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
//"https://goo.gl/forms/ZUwIHCMEFqGed2ys1"

        Toast.makeText(getContext(), "\"https://goo.gl/forms/ZUwIHCMEFqGed2ys1\"", Toast.LENGTH_SHORT).show();
        webView.loadUrl("https://goo.gl/forms/ZUwIHCMEFqGed2ys1");
        return view;
    }
}