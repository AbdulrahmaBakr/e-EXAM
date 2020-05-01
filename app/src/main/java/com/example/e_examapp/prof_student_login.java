package com.example.e_examapp;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class prof_student_login extends Fragment {
    private String userID;
    EditText mail,password;
    Button login;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    DocumentReference documentReference;





    public prof_student_login() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View v =  inflater.inflate(R.layout.fragment_prof_student_login, container, false);
        mail=v.findViewById(R.id.mail);
        password=v.findViewById(R.id.password);
        login=v.findViewById(R.id.login);
        firebaseAuth =FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String _password = password.getText().toString().trim();
                final String _mail = mail.getText().toString().trim();
                if (_mail.isEmpty()) {
                    mail.setError("enter email . ");
                } else if (_password.isEmpty()) {
                    password.setError("Required . ");
                } else if (_password.length() < 6) {
                    password.setError("must more than  6 characters .");
                }

                else{
                firebaseAuth.signInWithEmailAndPassword(_mail, _password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            check(v);
                        }

                    }
                });
                }
            }
        });
        return v;
    }

    private void check(final View v) {
        firebaseFirestore.collection("user")
                          .document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid())
                          .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    final NavController navController = Navigation.findNavController(v);
                    DocumentSnapshot documentSnapshot = task.getResult();

                    String re = documentSnapshot.getString("type");

                    assert re != null;
                    if (re.equals("Professor")) {
                        navController.navigate(R.id.action_prof_student_login_to_professor_profile);
                        Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), " hello professor ", Toast.LENGTH_LONG).show();
                    }
                    else {
                        navController.navigate(R.id.action_prof_student_login_to_student_profile);
                        Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), " hello student : Are you ready to exam :) ", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        TextView torgister =view.findViewById(R.id.torgister);
        torgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_prof_student_login_to_register_prof_student);
            }
        });
        final NavController navController2 = Navigation.findNavController(view);
        ImageView home = view.findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController2.navigate(R.id.action_prof_student_login_to_main);

            }
        });
    }



}
/*

        firebaseFirestore.collection("user").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                final NavController navController = Navigation.findNavController(v);
                String re = documentSnapshot.getString("type");
                if (re.equals("Professor")  ) {
                    navController.navigate(R.id.action_prof_student_login_to_professor_profile);
                    Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), " hello professor ", Toast.LENGTH_LONG).show();
                }
                else if(re.equals("Student")  ) {

                    navController.navigate(R.id.action_prof_student_login_to_student_profile);
                    Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), " hello student : Are you ready to exam :) ", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), " error ", Toast.LENGTH_LONG).show();
                }



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), " error ", Toast.LENGTH_LONG).show();
            }
        });

 */