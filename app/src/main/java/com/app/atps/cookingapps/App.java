package com.app.atps.cookingapps;

import android.app.Application;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import com.app.atps.cookingapps.data.component.DaggerNetComponent;
import com.app.atps.cookingapps.data.component.NetComponent;
import com.app.atps.cookingapps.data.module.AppModule;
import com.app.atps.cookingapps.data.module.NetModule;
import com.app.atps.cookingapps.util.MyPreferenceManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by emerio on 5/24/17.
 */

public class App extends Application {
    private NetComponent mNetComponent;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions gso;
    public AppCompatActivity activity;
    private MyPreferenceManager pref;
    private static App mInstance;
    @Override
    public void onCreate(){
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder().appModule(new AppModule(this)).netModule(new NetModule("https://cookingapps-f8622.firebaseio.com"))
                .build();
        mInstance = this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static synchronized App getInstance() {
        return mInstance;
    }
    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public GoogleSignInOptions getGoogleSignInOptions(){
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        return gso;
    }
    public GoogleApiClient getGoogleApiClient(AppCompatActivity activity, GoogleApiClient.OnConnectionFailedListener listener){
        this.activity = activity;
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this.activity, listener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, getGoogleSignInOptions())
                .build();
        return mGoogleApiClient;
    }
}
