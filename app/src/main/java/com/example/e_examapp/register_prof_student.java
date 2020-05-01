package com.example.e_examapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class register_prof_student extends Fragment {
    private EditText name,mail,password;
    private Button register_now;
    private Spinner choose_register;
    private FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
    private FirebaseFirestore fstore=FirebaseFirestore.getInstance();
    private String userID;

    public register_prof_student() {
        // Required empty public constructor

    }

    @Override
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.fragment_register_prof_student, container, false);

        name=v.findViewById(R.id.name);
        password=v.findViewById(R.id.password);
        mail=v.findViewById(R.id.mail);
        register_now=v.findViewById(R.id.register_now);
        choose_register=v.findViewById(R.id.choose_register);


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource( Objects.requireNonNull(getActivity()).getBaseContext(),R.array.usertype,R.layout.support_simple_spinner_dropdown_item);
        choose_register.setAdapter(adapter);

        register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final String _mail = mail.getText().toString().trim();
                final String _password = password.getText().toString().trim();
                final String _name = name.getText().toString().trim();
                final String _item = choose_register.getSelectedItem().toString();

                if (_name.isEmpty()&&_mail.isEmpty()&&_password.isEmpty()){
                    Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "ENTER FIELDS PLEASE ", Toast.LENGTH_LONG).show();
                }
                else if (_name.isEmpty()) {
                        name.setError("enter name . ");
                } else if (_mail.isEmpty()) {
                        mail.setError("enter mail . ");
                } else if (_password.length() < 6) {
                        password.setError("must more than  6 characters .");
                } else if (_password.isEmpty()) {
                        password.setError("Required . ");
                }

                else{
                firebaseAuth.createUserWithEmailAndPassword(_mail, _password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        final NavController navController = Navigation.findNavController(v);
                        if (task.isSuccessful()) {
                            userID = firebaseAuth.getCurrentUser().getUid();
                            // CREATE COLLECTION .......
                            String item = choose_register.getSelectedItem().toString();
                            if (item.equals("Professor")) {
                                DocumentReference documentReference = fstore.collection("user").document(userID);
                                //use hashe map to save data to firebase
                                Map<String, Object> user = new HashMap<>();
                                user.put("name", _name);
                                user.put("email", _mail);
                                user.put("password", _password);
                                user.put("type",_item);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        navController.navigate(R.id.action_register_prof_student_to_prof_student_login);
                                        Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "your data saved,please login ", Toast.LENGTH_LONG).show();
                                        Log.d(TAG, "onSuccess: CREATED USER");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: error" + e.toString());
                                    }
                                });


                            } else if(item.equals("Student"))  {
                                DocumentReference documentReference = fstore.collection("user").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("name", _name);
                                user.put("email", _mail);
                                user.put("password", _password);
                                user.put("type",_item);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: CREATED USER");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: error" + e.toString());
                                    }
                                });

                                navController.navigate(R.id.action_register_prof_student_to_prof_student_login);
                                Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "your data saved,please login ", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), " email used before or not valid", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            }
        });

        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
         TextView home =view.findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_register_prof_student_to_main);
            }
        });

        TextView tologin = view.findViewById(R.id.tologin);
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_register_prof_student_to_prof_student_login);
            }
        });
    }
}
