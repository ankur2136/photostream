package com.ankur.photostream;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ankur.photostream.utils.LogUtils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends FragmentActivity implements FacebookCallback<LoginResult> {

    private static final String LOG_TAG          = "HOME_ACTIVITY";

    CallbackManager             mCallbackManager;

    LoginButton                 mLoginButton;

    List<String>                mPermissionNeeds = Arrays.asList("user_photos", "email");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_home);
        mLoginButton = (LoginButton) findViewById(R.id.b_login);
        mLoginButton.setReadPermissions(mPermissionNeeds);
        mLoginButton.registerCallback(mCallbackManager, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        LogUtils.debugLog(LOG_TAG, "Login Successful " + loginResult.getAccessToken().toString());
    }

    @Override
    public void onCancel() {
        LogUtils.debugLog(LOG_TAG, "Login cancel ");
    }

    @Override
    public void onError(FacebookException e) {
        LogUtils.errorLog(LOG_TAG, "Login failed " + e.toString());
    }

}
