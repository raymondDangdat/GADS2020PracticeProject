package com.example.gadspracticeproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gadspracticeproject.R;
import com.example.gadspracticeproject.interfaces.SendPostInterface;
import com.example.gadspracticeproject.models.RetrofitPost;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextGitHUbLink;
    TextView textViewSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        bindViews();

        textViewSubmit.setOnClickListener(view -> processSubmission());

    }

    private void processSubmission() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String githubLink = editTextGitHUbLink.getText().toString().trim();

        if (isEmpty(firstName)){
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_LONG).show();
        }else if (isEmpty(lastName)){
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_LONG).show();
        }else if (isEmpty(email)){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_LONG).show();
        }else if (isEmpty(githubLink)){
            Toast.makeText(this, "Enter Link to Project on GitHub", Toast.LENGTH_LONG).show();
        }else {
            submitProject(firstName, lastName, email, githubLink);
        }
    }

    private void submitProject(final String firstName, final String lastName, final String email,
                               final String githubLink) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.AlertDialog);



        View view = getLayoutInflater().inflate(R.layout.confirm_submission_layout, null);
        alertDialog.setView(view);
        final AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        TextView submitButton = view.getRootView().findViewById(R.id.submit_yes);
        ImageView imageView_cancel = view.getRootView().findViewById(R.id.submit_cancel);
        imageView_cancel.setOnClickListener(
                view_e->alert.cancel()
        );
        submitButton.setOnClickListener(
                viiew_ee ->{
                    alert.cancel();
                    new RetrofitPostRequest(firstName, lastName, email, githubLink);
                }
        );
        alert.show();
        

    }

    private void bindViews() {
        editTextEmail = findViewById(R.id.edit_email);
        editTextFirstName = findViewById(R.id.edit_firstname);
        editTextLastName = findViewById(R.id.edit_lastname);
        editTextGitHUbLink = findViewById(R.id.edit_githublink);
        textViewSubmit = findViewById(R.id.submit);
    }

    private boolean isEmpty(String value){
        return value==null||value.equals("");
    }

    private class RetrofitPostRequest {
        public RetrofitPostRequest(String firstName, String lastName, String email, String githubLink) {
            SendPostInterface postInterface = RetrofitPost.getRetrofit().create(SendPostInterface.class);
            Call<Void>call = postInterface.sendPost(firstName, lastName, email, githubLink);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                    if (response.isSuccessful()){
                        Log.d("Posst", "Success message");
                        alertSentSuccessful();
                    }else {
                        alertFailedToSend();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                    alertFailedToSend();
                }
            });
        }
    }

    private void alertSentSuccessful() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this, R.style.AlertDialog);
        View view = getLayoutInflater().inflate(R.layout.submission_successful_layout, null);
        builder.setView(view);
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        alert.show();

    }

    private void alertFailedToSend(){
        androidx.appcompat.app.AlertDialog.Builder alertDialog=new androidx.appcompat.app.AlertDialog.Builder(this,R.style.AlertDialog);
        View view=getLayoutInflater().inflate(R.layout.submission_failed_layout,null);
        alertDialog.setView(view);
        androidx.appcompat.app.AlertDialog alert=alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        alert.show();
    }

//    private void askToEnterValues() {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this,R.style.AlertDialog);
//        alertDialog.setTitle("Empty Fields!");
//        alertDialog.setMessage("Please fill out all input fields to continue");
//        alertDialog.setPositiveButton("OK",(dialog,which)->{
//            dialog.cancel();
//        });
//        AlertDialog alert = alertDialog.create();
//        alert.setCanceledOnTouchOutside(false);
//        alert.setCancelable(false);
//        alert.show();
//    }


}