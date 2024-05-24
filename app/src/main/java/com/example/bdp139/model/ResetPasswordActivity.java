package com.example.bdp139.model;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bdp139.R;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText etResetEmail;
    private Button btnResetPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etResetEmail = findViewById(R.id.etResetEmail);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        auth = FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(v -> resetPassword());
    }

    private void resetPassword() {
        String email = etResetEmail.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ResetPasswordActivity.this, "Reset password instructions sent to your email", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(ResetPasswordActivity.this, "Failed to send reset password email", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
