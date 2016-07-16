package com.game3d.my.fragmentofmain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.game3d.my.game3duse.R;

/**
 * Created by my on 2016/7/6.
 */
public class LuntanFragment extends Fragment{
    View view;
    WebView webview;
    String url = "http://bbs.3dmgame.com/forum.php";
    WebSettings webSettings;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_luntan,null);
        webview = (WebView) view.findViewById(R.id.luntan_webview);
        webview.loadUrl(url);
        webSettings = webview.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("12345",url+"***************");
                view.loadUrl(url);
                return true;
            }
        });
        return view;
    }
}
