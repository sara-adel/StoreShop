package com.sara.project.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sara.project.Database.DBController;
import com.sara.project.R;

public class Login extends AppCompatActivity {

    DBController mydb;
    String id;
    private EditText email , pass;
    private ImageView showPass;
    private Button login, forgetPass, signup, skip ;
    private LoginButton facebookLogin;
    private CheckBox rememberMe;
    private ProgressBar progressBarLogin;
    private String emailText , passText;
    private CallbackManager mCallbackManager;
    private boolean isCilcked = false;
    private FirebaseAuth mAuth;
    private SharedPreferences loginPrefs;
    private SharedPreferences.Editor loginPrefEditor;
    private boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Init();
    }

    private void Init(){
        mAuth = FirebaseAuth.getInstance();
        mydb = new DBController(this);

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(Login.this , Home.class));
            finish();
        }

        facebookLogin = findViewById(R.id.faceLogin);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        showPass = findViewById(R.id.show_pass);
        login = findViewById(R.id.login);
        forgetPass = findViewById(R.id.forgetPassword);
        rememberMe = findViewById(R.id.rememberMe);
        signup = findViewById(R.id.signup_login);
        skip = findViewById(R.id.skip);
        progressBarLogin = findViewById(R.id.progressBarLogin);

        loginPrefs = getSharedPreferences("loginPrefrence" , MODE_PRIVATE);
        loginPrefEditor = loginPrefs.edit();

        //remember me
        saveLogin = loginPrefs.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            email.setText(loginPrefs.getString("userPref", ""));
            pass.setText(loginPrefs.getString("passPref", ""));
            rememberMe.setChecked(true);
        }

        LoginFacebook();
        ShowPasswordAction();
        LoginAction();
        ForgetPasswordAction();
        SignupAction();
        SkipAction();
    }

    private void LoginFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        facebookLogin.setReadPermissions("email", "public_profile");
        facebookLogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("msg", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("msg", "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("msg", "facebook:onError", error);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI();
        }
    }

    private void updateUI(){
        Toast.makeText(Login.this ,"You are logged in." , Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        finish();
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("msg", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("msg", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //facebookLogin.setEnabled(true);

                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("msg", "signInWithCredential:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //facebookLogin.setEnabled(true);
                            updateUI();
                        }

                        // ...
                    }
                });
    }

    private void ShowPasswordAction(){

        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCilcked){
                    //pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pass.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    //pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    private void LoginAction(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailText = email.getText().toString().trim();
                passText = pass.getText().toString().trim();

                if(TextUtils.isEmpty(emailText)){
                    email.setError("Field cannot be left blank.");
                    return;
                }
                if( TextUtils.isEmpty(passText)){
                    pass.setError("Field cannot be left blank.");
                    return;
                }

                //remember me
                if (rememberMe.isChecked()) {
                    loginPrefEditor.putBoolean("saveLogin", true);
                    loginPrefEditor.putString("userPref", emailText);
                    loginPrefEditor.putString("passPref", passText);
                    loginPrefEditor.commit();

                } else {
                    loginPrefEditor.clear();
                    loginPrefEditor.commit();
                }

                progressBarLogin.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(emailText , passText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBarLogin.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {
                                    // there was an error
                                        Toast.makeText(Login.this, "check your email and password or sign up",
                                                Toast.LENGTH_LONG).show();
                                } else {

                                    //if user already exist in database
                                    String email_value = mydb.getEmailOfUser(emailText);
                                    if (emailText.equals(email_value)) {

                                        id = mydb.getIdOfUser(emailText);

                                        Intent intent = new Intent(Login.this, Home.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("idOfUser", id);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        //insert data into table of users
                                        long insert = mydb.insert_intoUsers(emailText);
                                        if (insert == -1) {
                                            Toast.makeText(Login.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(Login.this, "Data Inserted", Toast.LENGTH_LONG).show();

                                            //get id
                                            id = mydb.getIdOfUser(emailText);
                                        }
                                    }

                                    Intent intent = new Intent(Login.this, Home.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("idOfUser", id);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }


    private void ForgetPasswordAction(){
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forget = new Intent(Login.this , ForgetPassword.class);
                startActivity(forget);
            }
        });
    }

    private void SignupAction(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(Login.this , Signup.class);
                startActivity(signup);
            }
        });

    }

    private void SkipAction(){
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skip = new Intent(Login.this , Home.class);
                startActivity(skip);
            }
        });
    }

}