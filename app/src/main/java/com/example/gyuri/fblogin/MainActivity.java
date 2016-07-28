package com.example.gyuri.fblogin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
//import com.facebook.login.LoginClient;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
//import java.security.Signature;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    private String TAG = "mainAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this.getApplication());

        setContentView(R.layout.activity_main);

        //generate keyhash
        /*
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("mainActKeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        */


        Log.d("mainActKeyHash2:", FacebookSdk.getApplicationSignature(FacebookSdk.getApplicationContext()));

        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d(TAG, "loginResult=? B");
            }

            @Override
            public void onCancel() {
                // App code
                Log.d(TAG, "login= cancel B");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d(TAG, "loginError= B" + exception.toString());
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        if (loginResult!=null){
                            Log.d(TAG, "loginResult=something");
                        } else {
                            Log.d(TAG, "loginResult=null");
                        }

                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.d(TAG, "login= cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.d(TAG, "loginError=" + exception.toString());
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void friends(){

        /*
        LoginClient.Request.newMyFriendsRequest(session, new GraphUserListCallback() {
            @Override
            public void onCompleted(List users, Response response) {
                makeFacebookFriendList(users);
            }
        }).executeAsync();
        */

    }
}
