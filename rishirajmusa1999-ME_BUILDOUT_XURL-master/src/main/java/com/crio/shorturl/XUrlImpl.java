package com.crio.shorturl;

import java.util.HashMap;

public class XUrlImpl implements XUrl{
HashMap<String, String> url=new HashMap<String,String>() ;
HashMap<String, String> reverseurl=new HashMap<String,String>() ;
HashMap<String, Integer> counturl=new HashMap<String,Integer>() ;
static int count=1;
    @Override
    public String registerNewUrl(String longUrl) {
        if(url.containsKey(longUrl)){
            counturl.put(longUrl, counturl.get(longUrl)+1);
            return url.get(longUrl);
        }else{
            counturl.put(longUrl, 1);
        }
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb=new StringBuilder();
        String s="http://short.url/";
        for(int i=0;i<9;i++){
            int index=(int)(AlphaNumericString.length()* Math.random());
            sb.append(AlphaNumericString.charAt(index)); 
        }

        String shorturl=s+sb;
        url.put(longUrl, shorturl);
        reverseurl.put(shorturl, longUrl);
        return shorturl;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        if(url.containsKey(longUrl)){
            counturl.put(longUrl, counturl.get(longUrl)+1);
        }else{
            counturl.put(longUrl, 1);
        }
        if(url.containsKey(reverseurl.get(shortUrl))){
            return null;
        }
        url.put(longUrl, shortUrl);
        reverseurl.put(shortUrl, longUrl);
        return url.get(longUrl);
    }

    @Override
    public String getUrl(String shortUrl) {
        return reverseurl.get(shortUrl);
    }

    @Override
    public Integer getHitCount(String longUrl) {
        if(counturl.containsKey(longUrl)){
        return counturl.get(longUrl);
        }
        return 0;
    }

    @Override
    public String delete(String longUrl) {
        reverseurl.remove(url.get(longUrl));
        url.remove(longUrl);
        return null;
    }

}