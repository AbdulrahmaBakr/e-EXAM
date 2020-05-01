package com.example.e_examapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Objects;


public class ComputerProgramming extends Fragment {

    private String chapter="chapter Name";
    private String content1 = "content one";
    private String content2 = "content two";
    private String content3= "content three";
    private EditText chapterName,one,two,three;
    private Button add,load,update,delete,ShowAll;
    private TextView dataview,alldataview;

    //to sava data
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    //to retrive data
    private DocumentReference documentReference ;
    CollectionReference collectionReference=firebaseFirestore.collection("chapter Name");

    public ComputerProgramming() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_computer_programming, container, false);

        chapterName = view.findViewById(R.id.chapterName);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        add = view.findViewById(R.id.add);
        load = view.findViewById(R.id.load);
        update=view.findViewById(R.id.update);
        delete=view.findViewById(R.id.delete);
        ShowAll=view.findViewById(R.id.ShowAll);
        dataview=view.findViewById(R.id.dataview);
        alldataview=view.findViewById(R.id.alldataview);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String chap =chapterName.getText().toString();
                 String contOne =one.getText().toString();
                 String contTwo =two.getText().toString();
                 String contThree =three.getText().toString();
                if (chap.isEmpty()){
                    chapterName.setError("enter field please");

                }else if (contOne.isEmpty()){
                    one.setError("enter here");
                }else if(contTwo.isEmpty()){
                    two.setError("enter here");
                }else if(contThree.isEmpty()){
                    three.setError("enter here");
                }else {

                    HashMap<String, String> data =new HashMap<>();
                    data.put(chapter,chap);
                    data.put(content1,contOne);
                    data.put(content2,contTwo);
                    data.put(content3,contThree);
                    firebaseFirestore.collection("chapter Name")
                                     .document(chap)
                                      .set(data)
                                       .addOnSuccessListener(new OnSuccessListener<Void>() {
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
              if(!chapterName.getText().toString().isEmpty()){
                documentReference = firebaseFirestore.collection("chapter Name")
                        .document(chapterName.getText().toString());
                documentReference.get()
                                 .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                     @SuppressLint("SetTextI18n")
                                     @Override
                                     public void onSuccess(DocumentSnapshot documentSnapshot) {
                                         String chapName = documentSnapshot.getId();
                                         String cont1 = documentSnapshot.getString(content1);
                                         String cont2 = documentSnapshot.getString(content2);
                                         String cont3 = documentSnapshot.getString(content3);
                                         dataview.setText("Chapter One : "+chapName+
                                                 "\nContent two :"+cont1+
                                                 "\n Content two :"+cont2+
                                                 "\n Content three :"+cont3);

                                     }
                                 }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "eRRor", Toast.LENGTH_LONG).show();
                    }
                });
              }else{
                  Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "provide us the chapter number to added", Toast.LENGTH_LONG).show();
              }

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String contOne =one.getText().toString();
                 String contTwo =two.getText().toString();
                 String contThree =three.getText().toString();
                if(!chapterName.getText().toString().isEmpty()) {
                    documentReference = firebaseFirestore.collection("chapter Name")
                            .document(chapterName.getText().toString());
                    HashMap<String, Object> map =new HashMap<>();
                    map.put(content1,contOne);
                    map.put(content2,contTwo);
                    map.put(content3,contThree);
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
                    Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "provide us the chapter number to update", Toast.LENGTH_LONG).show();
                }
            }
        });

    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!chapterName.getText().toString().isEmpty()) {
                documentReference = firebaseFirestore.collection("chapter Name")
                        .document(chapterName.getText().toString());
                HashMap<String, Object> map = new HashMap<>();
                map.put(content1, FieldValue.delete());
                map.put(content2, FieldValue.delete());
                map.put(content3, FieldValue.delete());
                documentReference.update(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "fields deleted", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "field deleted", Toast.LENGTH_LONG).show();
                    }
                });
            }else{
                Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "provide us the chapter number to deleted the content", Toast.LENGTH_LONG).show();
            }
        }
    });

        ShowAll.setOnClickListener(new View.OnClickListener() {
            String data = "";
            @Override
            public void onClick(View v) {
                collectionReference.get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            for(DocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                                String name = documentSnapshot.getId();
                                                String con1 = documentSnapshot.getString(content1);
                                                String cont2 = documentSnapshot.getString(content2);
                                                String con3 = documentSnapshot.getString(content3);

                                                data +="Chapter : "+name +"\nContent One : "+con1+"\nContent two : "+cont2+
                                                        "\nContent three : "+con3+"\n"+"\n";
                                            }
                                            alldataview.setText(data);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });

    return view;
    }

}
