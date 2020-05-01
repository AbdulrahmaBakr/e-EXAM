package com.example.e_examapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class main extends Fragment {
    Button admin_btn,professor_btn,student_btn,exam;
    ImageView student_Img,prof_Img,admin_Img;
    Animation a1,a2,a3;


    public main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_main, container, false);

        admin_btn = v.findViewById(R.id.admin_btn);
        professor_btn =v.findViewById(R.id.professor_btn);
        student_btn =v.findViewById(R.id.student_btn);
        admin_Img = v.findViewById(R.id.admin_Img);
        prof_Img = v.findViewById(R.id.prof_Img);
        student_Img=v.findViewById(R.id.student_Img);
        exam=v.findViewById(R.id.exam);

        a1 = AnimationUtils.loadAnimation(getActivity(),R.anim.a1);
        a2 =AnimationUtils.loadAnimation(getActivity(),R.anim.a2);
        a3 = AnimationUtils.loadAnimation(getActivity(),R.anim.a3);

        admin_btn.startAnimation(a1);
        professor_btn.startAnimation(a2);
        student_btn.startAnimation(a3);
        student_Img.startAnimation(a3);
        prof_Img.startAnimation(a2);
        admin_Img.startAnimation(a1);


    return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);
        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_main_to_exam);
            }
        });
        admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_main_to_admin_login);

            }
        });


        professor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_main_to_register_prof_student);
                Toast.makeText(getActivity().getBaseContext()," Welcome",Toast.LENGTH_LONG).show();
            }
        });


        student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_main_to_register_prof_student);
                Toast.makeText(getActivity().getBaseContext()," Welcome ",Toast.LENGTH_LONG).show();
            }
        });

    }
}
