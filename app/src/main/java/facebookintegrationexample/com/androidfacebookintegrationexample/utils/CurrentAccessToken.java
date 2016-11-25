package facebookintegrationexample.com.androidfacebookintegrationexample.utils;

import com.facebook.AccessToken;

/**
 * Created by Himanshu on 11/22/2016.
 */

public class CurrentAccessToken {
    private AccessToken accessToken;
    private static CurrentAccessToken currentAccessToken;
    private CurrentAccessToken(){

    }
    public static CurrentAccessToken getInstance(){
        if(currentAccessToken==null){
            currentAccessToken=new CurrentAccessToken();
        }
        return currentAccessToken;
    }
    public void setAccessToken(AccessToken accessToken){
        this.accessToken=accessToken;

    }
    public AccessToken getAccessToken(){
        return  accessToken;

    }

}
