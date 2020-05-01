package com.example.e_examapp;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class exam extends Fragment {
    TextView idecation , question;
    LinearLayout optionsContainer ;
    Button next;
    List<QuserstuinModel> list ;
    private int count = 0 ;
    private int position= 0;
    int degree = 0;

    public exam() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.fragment_exam, container, false);

        idecation = v.findViewById(R.id.idecation);
        question = v.findViewById(R.id.question);
        optionsContainer = v.findViewById(R.id.optionsContainer);
        next = v.findViewById(R.id.next);

        list =new ArrayList<>();
        list.add(new QuserstuinModel("Question1","A","B","C","D","C"));
        list.add(new QuserstuinModel("Question2","A","B","C","D","A"));
        list.add(new QuserstuinModel("Question3","A","B","C","D","B"));
        list.add(new QuserstuinModel("Question4","A","B","C","D","C"));
        list.add(new QuserstuinModel("Question5","A","B","C","D","D"));
        list.add(new QuserstuinModel("Question6","A","B","C","D","D"));
        list.add(new QuserstuinModel("Question7","A","B","C","D","A"));
        list.add(new QuserstuinModel("Question8","A","B","C","D","B"));
        list.add(new QuserstuinModel("Question9","A","B","C","D","C"));

        for(int i =0;i<4;i++){
            optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer((Button) v);

                }
            });

        }



        playAnima(question,0,list.get(position).getQuestion());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setEnabled(false);
                next.setAlpha((float) 0.7);
                enableOption(true);
                position++;
                if(position==list.size()){
                    Intent intent = new Intent(getActivity().getApplication(), results.class);

                    intent.putExtra("degree",degree);
                    intent.putExtra("total",list.size());
                    startActivity(intent);
                    getActivity().finish();
                    return;

                }
                count = 0;
                playAnima(question,0,list.get(position).getQuestion());
            }
        });

    return v ;
    }
    private void playAnima(final View v, final int value,final String data){
        v.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(value== 0 && count < 4){
                    String option="";
                     if (count==0){
                        option=list.get(position).getOptionA();
                    }else if(count==1){
                         option=list.get(position).getOptionB();

                     }else if (count==2){
                         option=list.get(position).getOptionC();
                     }else if (count==3){
                         option=list.get(position).getOptionD();
                     }
                    playAnima(optionsContainer.getChildAt(count),0,option);
                    count++;
                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            if(value == 0 ){
                try{
                    ((TextView)v).setText(data);
                    idecation.setText(position+1+"/"+list.size());
                }catch (ClassCastException e){
                    ((Button)v).setText(data);
                }
                v.setTag(data);
                playAnima(v, 1,data);
            }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void checkAnswer(Button selectOption) {
        enableOption(false);
        next.setEnabled(true);
        next.setAlpha(1);
        if(selectOption.getText().toString().equals(list.get(position).getCorectans())){
            degree++;
            selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF119C38")));

        }else {
            selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctOption = optionsContainer.findViewWithTag(list.get(position).getCorectans());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF119C38")));

        }
    }
    private void enableOption(boolean b){
        for(int i =0;i<4;i++){
            optionsContainer.getChildAt(i).setEnabled(b);
            if(b){
                optionsContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }

        }


    }
}
