/**
 * Login page for Java Android application to access the
 * bookings app which interacts with a RESTful web service.
 */
package dms.android_booking_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
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
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            Toast.makeText(MainActivity.this, request.getUrl().toString(), Toast.LENGTH_LONG).show();
            view.setVisibility(View.INVISIBLE);

            Intent homeIntent = new Intent(MainActivity.this, LookupBookingsActivity.class);
            startActivity(homeIntent);
            return true;
        }
    }
}