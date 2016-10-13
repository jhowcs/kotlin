package br.com.jhowcs.devmedia.devmediasigninarticle

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener

class MainActivity : AppCompatActivity(), OnConnectionFailedListener {
    val TAG: String = "MainActivity"

    val RC_SIGN_IN: Int = 9001

    var mGoogleSignIn: GoogleSignInOptions? = null
    var mGoogleApiClient: GoogleApiClient? = null

    var btnGoogleSignIn: SignInButton? = null
    var btnGoogleSignOut: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGoogleSignIn = findViewById(R.id.sign_in_button) as SignInButton
        btnGoogleSignOut= findViewById(R.id.sign_out_button) as Button

        btnGoogleSignIn?.setSize(SignInButton.SIZE_STANDARD)
        btnGoogleSignIn?.setScopes(mGoogleSignIn?.scopeArray)

        btnGoogleSignIn?.setOnClickListener{
            googleSignIn()
        };

        btnGoogleSignOut?.setOnClickListener {
            googleSignOut()
        }

        buildGoogleSignIn()
        buildGoogleApiClient()
    }

    fun buildGoogleSignIn() {
        mGoogleSignIn = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.backend_id))
                .build()
    }

    fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
        .enableAutoManage(this, this)
        .addApi(Auth.GOOGLE_SIGN_IN_API, mGoogleSignIn!!)
        .build()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.e(TAG, "Connection Failed!")
    }

    fun googleSignIn() {
        val signInIntent: Intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun googleSignOut() {
        if(mGoogleApiClient!!.isConnected)
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback( {status -> Log.i(TAG, status.statusMessage)})
        else
            Log.i(TAG, "GoogleApiClient disconnected")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN) {
            val result: GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess())

        if(result.isSuccess) {
            var acct: GoogleSignInAccount? = result.signInAccount

            val idToken = acct?.idToken

            Log.d(TAG, "Token: " + idToken)
        } else {

        }
    }
}
