package com.example.e_examapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Objects;


public class editChapterAdmin extends Fragment {
    private String profe="prof Name";
    private String subj = "subj";
    EditText professor , subject;
    Button add,load,update,viewall ;
    TextView showdata,alldataview;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private DocumentReference documentReference ;
    CollectionReference collectionReference=firebaseFirestore.collection("Level Subject And Professors");

    public editChapterAdmin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_edit_chapter_admin, container, false);

        professor=v.findViewById(R.id.professor);
        subject=v.findViewById(R.id.subject);
        add=v.findViewById(R.id.add);
        load=v.findViewById(R.id.load);
        update=v.findViewById(R.id.updatee);
        viewall=v.findViewById(R.id.viewall);
        alldataview=v.findViewById(R.id.alldataview);

        showdata=v.findViewById(R.id.showdata);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prof= professor.getText().toString();
                String sub= subject.getText().toString();
                if(prof.isEmpty()||sub.isEmpty()){
                    Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "field empty ", Toast.LENGTH_LONG).show();
                }else{

                    HashMap<String,Object> info =new HashMap<>();
                    info.put(profe,prof);
                    info.put(subj,sub);
                    firebaseFirestore.collection("Level Subject And Professors")
                                     .document(sub)
                                      .set(info).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "added", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "eRRor", Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }
        });



        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!subject.getText().toString().isEmpty()){
                    documentReference = firebaseFirestore
                            .collection("Level Subject And Professors")
                            .document(subject.getText().toString());
                    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String sub = documentSnapshot.getId();
                            String pro = documentSnapshot.getString(profe);
                            showdata.setText("Subject : "+sub +
                                        "\n Professor :" +pro);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "eRRor", Toast.LENGTH_LONG).show();
                        }
                    });
                }


            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String P =professor.getText().toString();
                String S =subject.getText().toString();
                if(!subject.getText().toString().isEmpty()) {
                    documentReference = firebaseFirestore.collection("Level Subject And Professors")
                            .document(subject.getText().toString());
                    HashMap<String, Object> map = new HashMap<>();
                    map.put(profe, P);
                    map.put(subj, S);
                    documentReference.update(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "updated", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "field", Toast.LENGTH_LONG).show();
                        }
                    });

                }else{
                    Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "provide us the subject name to update", Toast.LENGTH_LONG).show();

                }
            }
        });




        viewall.setOnClickListener(new View.OnClickListener() {
            String allData = "";
            @Override
            public void onClick(View v) {
                collectionReference.get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for(DocumentSnapshot documentSnapshot:queryDocumentSnapshots){

                                    String prof=documentSnapshot.getString(profe);
                                    String subje = documentSnapshot.getId();
                                    allData += "professor : " + prof + "\n subject : " + subje + "\n"+"\n";
                                }

                                alldataview.setText(allData);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });





        return v;
    }


}
