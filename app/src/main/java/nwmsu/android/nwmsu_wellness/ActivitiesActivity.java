package nwmsu.android.nwmsu_wellness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class ActivitiesActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);


        webView =findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://calendar.nwmissouri.edu/");

        Button addCalanderBTN = findViewById(R.id.addCalanderBTN);
        addCalanderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent eventsIntent = new Intent( getApplicationContext(), EventActivity.class);
                    startActivity(eventsIntent);
                } catch (Exception e) {
                    // Error handling
                    Log.d("click", "Error on: click add button ");
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}