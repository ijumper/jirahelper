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


        String consumerKey = "Key5b8b38ff8de3ba5887749bbbf3dc8bb054091f59"; //TODO get the consumer key from a secure location
        String consumerSecret = "MIIEowIBAAKCAQEAw4z282S17LQqQwQYB33VypYI3onoVJSrKbVHi8S7BMIKdDN/\n" + //TODO get the consumer from a secure location (keystore)
                "OPZ2A9f7aNshrAG9MG4ORjwpY3/7WT05clj2Rfbe6g0U+OiAeP3O917WlXLJN9QV\n" +
                "rerPUPn84jYwIoHfjOAABFPh87PJV/iZ3FdSMZIMB4cwwDc7tI5IgqUpUeWgHgj5\n" +
                "7/jzdgqm286FaWvMCv97Z3zHKi2tm19xD/5Ihb04GBEWW3U3CigMlnm9EdpybrlI\n" +
                "PaGR8Yuqg9uLEbNYXSfzI6X4CjXcbDER1Tj5dwaOB536X22wQjQMhaRwnzLZdk6A\n" +
                "TOiTs/smVpVIf8juJi43+edDlvajQS7bZcy4ywIDAQABAoIBAQC/GUnXz0qN1/8Y\n" +
                "4K5O2+UKhhaCy0hVJ7ghGTui1g1hd/LZXd3tH8VyuiXBcav1OPnWkya18mMMDmzl\n" +
                "55P1MATzmG59UW12VVf/ZSFV0sNm5vbG7SaGgFo7KyG7OvNNNZfuRpUdpY/BJagU\n" +
                "pShPELKNKMlCrhWxMgjRyN4JdjfsBAEYSli5l0Yz84iWw2Ol9NjTTEZOP+ERb0Wl\n" +
                "BBs2gEZSRRWRVKvPi9bVSwTHL+JSeBSoLXcH7Oi35QJOdYUl48YObGsgn7DPNvsW\n" +
                "UfLrk4Vu+lFKGDXEAKKMM/TD5p1p6KdWyk8lmGXKjhv7OwkNLk8pT9WSEPwSYITO\n" +
                "4f1ppvMBAoGBAPHDFyokJQLEXCJ8hrDaJKGujRG5pxFkCpIEdQ13ne/im0VatfQT\n" +
                "DeRLYyA3Znf2aB5We7efBrIhyP2nh+bBalSHw/ivWkysJ0It7k8yxLNyqhakZwud\n" +
                "HEKiDLRwMhYaETJxG/LRHadk6c3xKzkv6FLAO8mZ7wdoxMb5vzsReuBLAoGBAM8R\n" +
                "K7HnzNum5v/L1RTTOm1jsH6neXtMPtgNRfrPesrXvvl1ENyQH2FauWMcb3uq3TiI\n" +
                "dQRgaLDjq+oYgU6sFLthNNJAGNXdrBIR2xmnwbcLJ0ZSBlC9xpJYLJ3SxJ9biE2i\n" +
                "JP+ajIJpWH49fH5knxBRSh85Eh/Tsi98p8okMzmBAoGAUq4ipPHZinm2/MBTm/Xu\n" +
                "ozC74/z3CvaIuuk1Uta+t8Rqz6w3H/LIRHQXwXPQ4mPttbpj8yc6tf7h6wW5b4xk\n" +
                "HeaUed8WZbRWdMaagpFdPfWP/9BXwVwIxyz+ZVMdffuKOBMAuOkVWCMl5p1858Df\n" +
                "ljVR/R9c5M7KxTWyVbQjrsMCgYBvNNyPrlsiOG/lPxZQGeVOLHT0gINIybkSlYZY\n" +
                "3icnW1kwZkPJAQopYe0gonvpIemvW/HkiNaQ8aU5kzGXC+oSfHE+3be43FV2Wuqd\n" +
                "y6/I24+bP6Auqs1DeWF03WZF44W/k+5k7B68R1GEcwcyZl3bCJ1TSXZHa41N3ZfL\n" +
                "g0eTgQKBgBgAwUh3kEDL0T7Vo1DpMyZnhI8jTeV7anKKdgTvu1bBLYYgNLUijNwX\n" +
                "u9lOy+RYGaTbBFOK19BcDPmjkHYiOEr+/KqWU26IWj9J38wivthO86xxwnUlONXk\n" +
                "wkMnJNbep1F+vAOy2g9iTZX/km1UQ7sqXIqgsnxaZZiS4vU+Dz2F";
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
