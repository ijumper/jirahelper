package uk.co.ijump.jirahelper.client.utils;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.OAuthServiceProvider;
import net.oauth.client.OAuthClient;
import net.oauth.client.httpclient4.HttpClient4;

public class oauthwrapper {


    protected net.oauth.client.OAuthClient oAuthClient;



    private String getRequestToken() {


        return "the_request_token";
    }


    private  String getAccessToken(String requestToken) throws java.io.IOException, net.oauth.OAuthException, java.net.URISyntaxException{



        OAuthServiceProvider provider = getProvider();

        OAuthConsumer consumer = getConsumer(provider);

        consumer.setProperty(OAuth.OAUTH_SIGNATURE_METHOD, OAuth.HMAC_SHA1);

        OAuthClient client = getClient();

        OAuthAccessor accessor = getAccessor(consumer);

        accessor.requestToken = requestToken;

        OAuthMessage result = client.getAccessToken(accessor,"GET",null);

        return result.getToken();
    }


    private OAuthServiceProvider getProvider() {

        String requestUrl = "https://www.google.com/accounts/OAuthGetRequestToken";
        String authorizeUrl = "https://www.google.com/accounts/OAuthAuthorizeToken";
        String accessUrl = "https://www.google.com/accounts/OAuthGetAccessToken";

        OAuthServiceProvider provider = new OAuthServiceProvider(requestUrl, authorizeUrl, accessUrl);

        return provider;
    }

    private OAuthConsumer getConsumer(OAuthServiceProvider provider) {


        String consumerKey = ""; //TODO get the consumer key from a secure location
        String consumerSecret = "";
        String callbackUrl = "XXXXX";

        OAuthConsumer consumer = new OAuthConsumer(callbackUrl, consumerKey, consumerSecret, provider);

        return consumer;

    }

    private OAuthClient getClient() {

        OAuthClient client = new OAuthClient(new HttpClient4());

        return client;
    }

    private OAuthAccessor getAccessor(OAuthConsumer consumer) {

        OAuthAccessor accessor = new OAuthAccessor(consumer);

        return accessor;

    }
}
