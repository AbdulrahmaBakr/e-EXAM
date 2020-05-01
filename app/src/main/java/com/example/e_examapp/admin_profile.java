package com.example.e_examapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class admin_profile extends Fragment {
         Button levels,professros;

    public admin_profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_admin_profile, container, false);

        levels = v.findViewById(R.id.levels_depart);
        professros= v.findViewById(R.id.professors);

        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_admin_profile_to_levels_deprat);
            }
        });



        return  v;
    }

}
