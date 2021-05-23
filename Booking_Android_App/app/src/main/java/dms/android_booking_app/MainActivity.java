/**
 * A Java Android application accessing the bookings
 * RESTful web service.
 */
package dms.android_booking_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        WebView simpleWebView = findViewById(R.id.loginWebView);
        simpleWebView.setWebViewClient(new MyWebViewClient());

        String url = "https://blackboard.aut.ac.nz/webapps/login/?action=relogin";
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url); // load the url on the web view
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();
            // load the url
            view.setVisibility(View.INVISIBLE);

            //once user logs in with url successfully, redirect them to the home page of this app
            Intent homeIntent = new Intent(MainActivity.this, LookUpBookingsActivity.class);
            startActivity(homeIntent);
            return true;
        }
    }
}