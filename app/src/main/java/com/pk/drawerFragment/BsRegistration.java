package com.pk.drawerFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.pk.R;

public class BsRegistration extends Fragment {
    View view;
    WebView webView;
    ProgressBar bsProgressBar;

    public BsRegistration() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bs_registration, container, false);

        webView = view.findViewById(R.id.bs_reg_web_view);
        bsProgressBar = view.findViewById(R.id.bs_reg_progress_bar);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.setWebViewClient(new WebViewClient());
        bsProgressBar.setVisibility(View.VISIBLE);


        return view;
    }
}



