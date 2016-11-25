package facebookintegrationexample.com.androidfacebookintegrationexample.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import facebookintegrationexample.com.androidfacebookintegrationexample.R;
import facebookintegrationexample.com.androidfacebookintegrationexample.utils.CurrentAccessToken;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private CallbackManager callbackManager;
    @BindView(R.id.btn_login_with_facebook) com.facebook.login.widget.LoginButton btn_login;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            //displayMessage(profile);
            Intent homePageIntent=new Intent(context,HomePage.class);
            startActivity(homePageIntent);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        ButterKnife.bind(this);
        AccessToken accessToken=AccessToken.getCurrentAccessToken();
        if(accessToken!=null){
            CurrentAccessToken.getInstance().setAccessToken(accessToken);
            Intent homePageIntent=new Intent(context,HomePage.class);
            startActivity(homePageIntent);
        }
        btn_login.setReadPermissions(Arrays.asList("public_profile, email, user_birthday, user_friends"));
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
              CurrentAccessToken.getInstance().setAccessToken(newToken);
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn_login.registerCallback(callbackManager,callback);
    }

    @Override
    protected void onResume() {

        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }



    @Override
    protected void onStop() {
        super.onStop();
       // accessTokenTracker.stopTracking();;
       // accessTokenTracker.stopTracking();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
        accessTokenTracker.stopTracking();
    }
}
