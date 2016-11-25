package facebookintegrationexample.com.androidfacebookintegrationexample.json;

import org.json.JSONException;
import org.json.JSONObject;

import facebookintegrationexample.com.androidfacebookintegrationexample.user.User;

/**
 * Created by Himanshu on 11/22/2016.
 */

public class UserFaseBookDataParser {
    public User parse(JSONObject fbData){
        User user=null;
        if(fbData!=null){
            try{
                 user=User.getInstance();
                user.setFacebookId(fbData.getString("id"));
                user.setName(fbData.getString("name"));
                user.setFaceBookLink(fbData.getString("link"));
                user.setProfilePictureUrl("http://graph.facebook.com/"+fbData.getString("id")+"/picture?type=large");
                user.setGender(fbData.getString("gender"));
                user.setDateOfBirth(fbData.getString("birthday"));
               JSONObject friends= fbData.getJSONObject("friends");
                JSONObject friendsCount=friends.getJSONObject("summary");
                user.setNumberOfFriends(friendsCount.getString("total_count"));

            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        }
        return user;
    }
}
