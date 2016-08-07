package uk.co.ijump.jirahelper.client.utils;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.OAuthServiceProvider;
import net.oauth.client.OAuthClient;
import net.oauth.client.httpclient4.HttpClient4;

import java.util.List;

public class oauthwrapper {


    private OAuthClient oAuthClient;

    private OAuthServiceProvider oAuthServiceProvider;

    private OAuthAccessor oAuthAccessor;

    private OAuthConsumer oAuthConsumer;

    private void getRequestToken() throws java.io.IOException, net.oauth.OAuthException, java.net.URISyntaxException {

        getClient();
        getProvider();
        getConsumer();
        getAccessor();

        oAuthClient.getRequestToken(oAuthAccessor,"GET",null);


    }



    /*
        To be able to use OAuth authentication the client application has to do the "OAuth dance" with JIRA. This dance consists of three parts.
        Obtain a request token
        Ask the user to authorize this request token
        Swap the request token for an access token

     */
    private  void getAccessToken(String requestToken) throws java.io.IOException, net.oauth.OAuthException, java.net.URISyntaxException {



        getClient();
        getProvider();
        getConsumer();
        getAccessor();

        OAuthMessage result;

        if (oAuthAccessor.requestToken != null){
            getRequestToken();

        }

        result = oAuthClient.getAccessToken(oAuthAccessor,"GET",null);

        result.getToken();
    }


    private void getProvider() {

        String requestUrl = "https://www.google.com/accounts/OAuthGetRequestToken";
        String authorizeUrl = "https://www.google.com/accounts/OAuthAuthorizeToken";
        String accessUrl = "https://www.google.com/accounts/OAuthGetAccessToken";

        oAuthServiceProvider = new OAuthServiceProvider(requestUrl, authorizeUrl, accessUrl);

    }

    private void getConsumer() {


        String consumerKey = ""; //TODO get the consumer key from a secure location
        String consumerSecret = "";
        String callbackUrl = "XXXXX";

        oAuthConsumer = new OAuthConsumer(callbackUrl, consumerKey, consumerSecret, oAuthServiceProvider);

        oAuthConsumer.setProperty(OAuth.OAUTH_SIGNATURE_METHOD, OAuth.HMAC_SHA1);

    }

    private  void getClient() {

         oAuthClient = new OAuthClient(new HttpClient4());


    }

    private void getAccessor() {

        oAuthAccessor = new OAuthAccessor(oAuthConsumer);

    }
}
