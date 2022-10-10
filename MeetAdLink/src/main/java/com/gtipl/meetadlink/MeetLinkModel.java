package com.gtipl.meetadlink;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MeetLinkModel {

    Context context_float;
    final String DEEP_LINK_PREFIX = "com.visx.vbuywith";

    private WindowManager mWindowManager;
    WindowManager.LayoutParams layoutParams;
    RelativeLayout layout_main;
    WebView webView;

   public MeetLinkModel(Context context, WindowManager mWManager, String url_link) {
        this.context_float = context;
         mWindowManager = mWManager;
         layout_main = new RelativeLayout(this.context_float);
         layout_main.setBackgroundColor(Color.parseColor("#FFFFFF"));


        int height = (int) this.context_float.getResources()
                .getDimensionPixelSize(R.dimen.height_ad_link);
        int width = (int) this.context_float.getResources()
                .getDimensionPixelSize(R.dimen.width_ad_link);
        int layout_parms;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layout_parms = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        } else {
            layout_parms = WindowManager.LayoutParams.TYPE_PHONE;
        }

        layoutParams = new WindowManager.LayoutParams(
                width,
                height,
                layout_parms,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSPARENT);

        layoutParams.gravity = Gravity.BOTTOM|Gravity.RIGHT;
        layoutParams.y = 20;
        layoutParams.x = 5;

        webView = new WebView(context_float);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUserAgentString("Chrome/102.0.5005.63 Mobile");

        RelativeLayout.LayoutParams lay =  new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        lay.setMargins(5,5,5,5);
        webView.setLayoutParams(lay);
        webView.loadUrl(url_link);
        layout_main.removeView(webView);
        layout_main.addView(webView);

        RelativeLayout.LayoutParams lay1 =  new RelativeLayout.LayoutParams(
                50,
                50);
        ImageView img = new ImageView(context_float);
        img.setLayoutParams(lay1);
        img.setBackgroundColor(Color.parseColor("#FFFFFF"));
        img.setImageResource(R.drawable.arrows);
        img.bringToFront();
        layout_main.removeView(img);
        layout_main.addView(img);

        img.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //remember the initial position.
                        initialX = layoutParams.x;
                        initialY = layoutParams.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        layoutParams.x = initialX - (int) (event.getRawX() - initialTouchX);
                        layoutParams.y = initialY - (int) (event.getRawY() - initialTouchY);

                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(layout_main, layoutParams);
                        return true;
                }
                return false;
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //return true load with system-default-browser

                if (url.contains(DEEP_LINK_PREFIX)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context_float.startActivity(intent);

                    try{
                        mWindowManager.removeView(layout_main);
                    }catch (Exception e){
                    }
                    return true;
                }
                return  false;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // or other browsers, false with your webView
                if (url.contains("Registration")){

                    layoutParams = new WindowManager.LayoutParams(
                            WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.MATCH_PARENT,
                            layout_parms,
                            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD,
                            PixelFormat.TRANSLUCENT);

                    //Specify the view position
                    layoutParams.gravity = Gravity.TOP|Gravity.LEFT;
                    layoutParams.y = 0;
                    layoutParams.x = 0;

                    //Add the view to the window
                    mWindowManager.removeView(layout_main);
                    mWindowManager.addView(layout_main, layoutParams);

                }
            }
        });
    }

   public void showPopView() {
        mWindowManager.addView(layout_main, layoutParams);
    }

   public void hidePopView() {
        mWindowManager.removeView(layout_main);
    }
}
