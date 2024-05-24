package com.example.bdp139;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bdp139.model.ResetPasswordActivity;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText etEmail, etPassword;
    private Button btnLogin, btnLoginGoogle, btnLoginFacebook;
    private TextView tvRegister, tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginGoogle = findViewById(R.id.btnLoginGoogle);
        btnLoginFacebook = findViewById(R.id.btnLoginFacebook);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        btnLogin.setOnClickListener(v -> loginUser());
        btnLoginGoogle.setOnClickListener(v -> loginGoogle());
        btnLoginFacebook.setOnClickListener(v -> loginFacebook());
        tvRegister.setOnClickListener(v -> register());
        tvForgotPassword.setOnClickListener(v -> resetPassword());
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email or password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginGoogle() {
    }


    private void loginFacebook() {
    }


    private void register() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }

    private void resetPassword() {
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }
}
