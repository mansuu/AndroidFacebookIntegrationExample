package facebookintegrationexample.com.androidfacebookintegrationexample.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import facebookintegrationexample.com.androidfacebookintegrationexample.R;
import facebookintegrationexample.com.androidfacebookintegrationexample.json.UserFaseBookDataParser;
import facebookintegrationexample.com.androidfacebookintegrationexample.user.User;
import facebookintegrationexample.com.androidfacebookintegrationexample.utils.CurrentAccessToken;
import facebookintegrationexample.com.androidfacebookintegrationexample.utils.MyBitmap;

public class HomePage extends AppCompatActivity {

     @BindView(R.id.txt_name)
     TextView txt_name;
    @BindView(R.id.txt_gender)
    TextView txt_gender;
    @BindView(R.id.txt_dob)
    TextView txt_dob;
    @BindView(R.id.txt_friends)
    TextView txt_friends;

     @BindView(R.id.img_profile_picture)
    ImageView img_profile_picture;
    private Context context;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        context=HomePage.this;
        ButterKnife.bind(this);
        AccessToken accessToken= CurrentAccessToken.getInstance().getAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {

                        Log.e("response",object+"");
                     user=new UserFaseBookDataParser().parse(object);
                        setProfile();


                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,gender,birthday,friends");
        request.setParameters(parameters);
        request.executeAsync();

    }


private void loadProfilePicture(final String url){
    new AsyncTask<Void, Bitmap, Bitmap>() {
        @Override
        protected Bitmap doInBackground(Void... params) {
            return new MyBitmap().getBitmapFromUrl(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null){
                img_profile_picture.setImageBitmap(bitmap);
        }
    }
    }.execute();
}

    private void setProfile(){
        txt_name.setText(user.getName());
        txt_gender.setText(user.getGender());
        txt_dob.setText(user.getDateOfBirth());
        txt_friends.setText(user.getNumberOfFriends());
    }

}
