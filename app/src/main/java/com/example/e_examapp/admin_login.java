package com.example.e_examapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class admin_login extends Fragment {

    EditText name,password;
    Button login;


    public admin_login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.admin_login, container, false);

        name=v.findViewById(R.id.user_name);
        password=v.findViewById(R.id.password);
        login= v.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NavController navController2 = Navigation.findNavController(v);
                String _namelogin= name.getText().toString().trim();
                String _pass = password.getText().toString().trim();

                if(TextUtils.isEmpty(_namelogin)&&TextUtils.isEmpty(_pass)) {
                    name.setError("Required");
                    password.setError("Required");


                }else {
                    if(_namelogin.equals("admin")&&_pass.equals("1")){
                        navController2.navigate(R.id.action_admin_login_to_admin_profile);
                    }else{
                        Toast.makeText(getActivity().getBaseContext(),"you are not admin : حرامي ",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        ImageView home = view.findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_admin_login_to_main);

            }
        });
    }
}
