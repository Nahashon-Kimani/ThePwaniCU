package com.pk.drawerFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pk.MainActivity;
import com.pk.R;

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
        hasActiveInternetConnection(getContext());

        /*String currentDate = DateFormat.getDateTimeInstance().format(new Date());
        BsRegistrationModel registrationModel = new BsRegistrationModel("Semester 2", "2019",
                "https://goo.gl/forms/ZUwIHCMEFqGed2ys1", currentDate);
        mRef.setValue(registrationModel);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BsRegistrationModel model = snapshot.getValue(BsRegistrationModel.class);
                    //bsLink = model.getLink();
                    Toast.makeText(getContext(), model.getLink(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        webView.loadUrl("https://goo.gl/forms/ZUwIHCMEFqGed2ys1");
        return view;
    }

    public boolean hasActiveInternetConnection(Context context) {
        if (isNetworkAvailable(context)) {
            loadURL();
        } else {
            showDialog("Sorry An error Occurred. ");
            //Log.e("net", "Wee hakuna neti");
        }

        return false;
    }

    private void loadURL() {
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
                showDialog(error.toString());
            }
        });
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected();
    }

    public void showDialog(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Sorry An Error Occurred");
        builder.setIcon(R.drawable.error);
        builder.setCancelable(false);
        builder.setMessage(error.toString() + "\n Please Check your internet Connectivity.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
                /*Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}