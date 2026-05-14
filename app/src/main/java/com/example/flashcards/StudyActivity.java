package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;




public class StudyActivity extends AppCompatActivity {

    private Button button;
    private Button buttonPrevious;
    private Button buttonFlip;
    private Button buttonNext;

    private TextView cardText;
    private TextView cardNumber;

    private boolean flipper = true;

    private int cardNum = 1;

    //using a set number for testing
    private int itemCount = 0;


    private MainViewModel viewModel;
    private FlashcardListAdapter adapter;

    private List<Flashcard> cardList;
    private ArrayList<String> front = new ArrayList<>();
    private ArrayList<String> back = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_study);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.editButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
/*
        viewModel.getAllFlashcards().observe(this, flashcards -> {
            for(Flashcard card : flashcards) {
                front.add(card.getFront());
                back.add(card.getBack());
                itemCount += 1;
            }
        });
*/

        cardText = findViewById(R.id.textView);
        cardNumber = findViewById(R.id.textView4);


        buttonPrevious = findViewById(R.id.previousButton);

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //what does the 'previous' button do?
                cardNum -= 1;
                if (cardNum == 0) {
                    cardNum = itemCount;
                }
                displayCard();


            }
        });

        buttonFlip = findViewById(R.id.flipButton);
        buttonFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //what does the 'flip' button do?

                if (flipper == true) {
                    flipper = false;
                } else {
                    flipper = true;
                }
                displayCard();

            }
        });

        buttonNext = findViewById(R.id.nextButton);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //what does the 'next' button do?
                cardNum += 1;
                if (cardNum > itemCount) {
                    cardNum = 1;
                }
                displayCard();
            }
        });

        //creates a list based on observing the MainViewModel
        createList();

        //displayCard();

    }

    private void createList(){
        front.clear();
        back.clear();
        viewModel.getAllFlashcards().observe(this, flashcards -> {
            for(Flashcard card : flashcards) {
                front.add(card.getFront());
                back.add(card.getBack());
                itemCount += 1;
                Log.d("cards", front.toString());
                Log.d("cards", back.toString());
            }
            if(!front.isEmpty()){
                cardText.setText(front.get(cardNum));
                cardNumber.setText(Integer.toString(cardNum)+"/"+Integer.toString(itemCount));
            }
        });
        //displayCard();
    }

    private void displayCard() {
        //Flashcard card = cardList.get(cardNum);

        if (flipper == true) {
            //cardText.setText("Front");
            cardText.setText(front.get(cardNum-1));
        } else {
            //cardText.setText("Back");
            cardText.setText(back.get(cardNum-1));
        }
        cardNumber.setText(Integer.toString(cardNum) + "/" + Integer.toString(itemCount));
    }









}