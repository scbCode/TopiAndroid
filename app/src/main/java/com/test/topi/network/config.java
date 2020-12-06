package com.test.topi.network;
public class config  {

   static String url = "https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1";

    public static String getUrl(){
        return url;
    }
}
