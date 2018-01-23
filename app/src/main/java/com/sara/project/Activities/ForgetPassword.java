package com.sara.project.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.sara.project.R;

public class ForgetPassword extends AppCompatActivity {

    private EditText emailReset ;
    private Button resetPass , back;
    private ProgressBar progressBarReset;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        Init();
    }

    private void Init(){
        mAuth =FirebaseAuth.getInstance();

        emailReset = findViewById(R.id.email_reset);
        resetPass = findViewById(R.id.reset_pass);
        back = findViewById(R.id.back);
        progressBarReset = findViewById(R.id.progressBar_reset);

        ResetPassword();
        Back();
    }

    private void ResetPassword(){
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailReset.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailReset.setError("Field cannot be left blank.");
                    return;
                }

                progressBarReset.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgetPassword.this, "We have sent you instructions to reset" +
                                            " your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgetPassword.this, "Failed to send reset email!",
                                            Toast.LENGTH_SHORT).show();
                                }

                                progressBarReset.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }

    private void Back(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
