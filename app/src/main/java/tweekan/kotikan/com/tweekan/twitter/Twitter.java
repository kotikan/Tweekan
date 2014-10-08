/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Kotikan Ltd
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package tweekan.kotikan.com.tweekan.twitter;

import java.util.List;

/**
 * Created by roberthewitt on 07/10/2014.
 */
public class Twitter {

    // generate using https://apps.twitter.com/
    static final String consumerKey       = "fillIn";
    static final String consumerSecret    = "fillIn";
    static final String accessToken       = "fillIn";
    static final String accessTokenSecret = "fillIn";

    private static BasicTwitterClient basicTwitterClient = new BasicTwitterClient();
    public static Request request() {
        return basicTwitterClient;
    }

    public static interface Request {
        void tweetsForQuery(String query, Callback callback);
    }

    public static interface Callback {
        void onRequestComplete(Result result);
    }

    public static interface Result {
        ServerResponse response();
        List<String> tweets();
    }

    public static enum ServerResponse {
        SUCCESS, FAILURE
    }

}
