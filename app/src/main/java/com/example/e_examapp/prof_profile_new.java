package com.example.e_examapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class prof_profile_new extends Fragment {
    ListView listViewProf;
    ArrayAdapter<String> arrayAdapterProf;
    String[] data = {"Computer Programming","Program design","Networking","Computer Architecture"};


    public prof_profile_new() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_prof_profile_new, container, false);
        listViewProf = v.findViewById(R.id.list_prof_profile);
        arrayAdapterProf = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,data);
        listViewProf.setAdapter(arrayAdapterProf);

        listViewProf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final NavController navController = Navigation.findNavController(v);
                if(position==0){
                    navController.navigate(R.id.action_professor_profile_to_computerProgramming);
                }

                if(position==3){
                    navController.navigate(R.id.action_professor_profile_to_computerArchitecture);
                }

            }
        });
        return v;
    }

}
