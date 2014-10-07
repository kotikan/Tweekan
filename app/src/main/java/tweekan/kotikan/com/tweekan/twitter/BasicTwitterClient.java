package tweekan.kotikan.com.tweekan.twitter;

import android.net.http.AndroidHttpClient;
import android.util.Base64;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by roberthewitt on 07/10/2014.
 */
public class BasicTwitterClient implements Twitter.Request {

    private final ExecutorService executor = Executors.newFixedThreadPool(1);
    private final HttpClient client;

    public BasicTwitterClient() {
        final String consumerKey    = Twitter.consumerKey;
        final String consumerSecret = Twitter.consumerSecret;
        final String bearerToken = String.format("%s:%s", consumerKey, consumerSecret);
        String base64EncodedString = Base64.encodeToString(bearerToken.getBytes(), Base64.DEFAULT);
        client = AndroidHttpClient.newInstance("AndroidTweetAppTest");
        executor.submit(tokenRequest(base64EncodedString));
    }

    @Override
    public void tweetsForQuery(String query, Twitter.Callback callback) {

    }

    private Runnable tokenRequest(final String base64EncodedString) {
        return new Runnable() {
            @Override
            public void run() {
                int webRequestResult = 0;
                String content = "";

                try {
                    HttpPost httpRequest = new HttpPost("https://twitter.com/oauth/request_token");
                    httpRequest.setEntity(new StringEntity("grant_type=client_credentials"));
                    httpRequest.setHeader("Authorization", "Basic " + base64EncodedString);
                    httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                    httpRequest.setHeader("Accept-Encoding", "gzip");
                    HttpResponse response = client.execute(httpRequest);

                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine != null) {
                        webRequestResult = statusLine.getStatusCode();
                    }
                    content = EntityUtils.toString(response.getEntity());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.d("webRequest", "");
                Log.d("webRequest", "statusCode = " + webRequestResult);
                Log.d("webRequest", "content    = " + content);
                Log.d("webRequest", "");
            }
        };
    }
}
