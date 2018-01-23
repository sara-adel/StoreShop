package com.sara.project.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sara.project.R;

public class Signup extends AppCompatActivity {

    private EditText emailSignup , passSignup;
    private ImageView showPassSignup;
    private Button signup , login;
    private ProgressBar progressBar;

    private int isCilcked = 0;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Init();
    }

    private void Init(){
        mAuth = FirebaseAuth.getInstance();

        emailSignup = findViewById(R.id.email_signup);
        passSignup = findViewById(R.id.password_signup);
        showPassSignup = findViewById(R.id.showpass_signup);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.haveAccount_login);
        progressBar = findViewById(R.id.progressBarSignUp);

        ShowPasswordAction();
        Signup();
        Login();
    }

    private void ShowPasswordAction(){
        showPassSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (isCilcked){
                    case 0:
                        passSignup.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        break;
                    case 1:
                        passSignup.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void Signup(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = emailSignup.getText().toString().trim();
                String passText = passSignup.getText().toString().trim();

                if(TextUtils.isEmpty(emailText)){
                    emailSignup.setError("Field cannot be left blank.");
                    return;
                }

                if(TextUtils.isEmpty(passText) ){
                    passSignup.setError("Field cannot be left blank.");
                    return;
                }
                if (passSignup.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(emailText , passText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Signup.this, "User Created.", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);

                                //empty email and pass
                                emailSignup.setText("");
                                passSignup.setText("");

                                //if whitebutton failed
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Signup.this, "User Created failed.", Toast.LENGTH_LONG).show();
                                } else {
                                    startActivity(new Intent(Signup.this, Home.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    private void Login(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Signup.this , Login.class);
                startActivity(login);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
