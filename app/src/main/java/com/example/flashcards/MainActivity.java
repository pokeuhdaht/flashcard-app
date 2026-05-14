package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.widget.Button;


import com.example.flashcards.Flashcard;
import com.example.flashcards.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainViewModel viewModel;
    private FlashcardListAdapter adapter;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            viewModel = new ViewModelProvider(this).get(MainViewModel.class);

            listenerSetup();
            observerSetup();
            recyclerSetup();

            button = findViewById(R.id.studyButton);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, StudyActivity.class);
                    startActivity(intent);
                }
            });
    }

    private void clearFields() {
        binding.editFront.setText("");
        binding.editBack.setText("");
    }

    private void listenerSetup() {
        binding.saveButton.setOnClickListener(view -> {

            String front = binding.editFront.getText().toString();
            String back = binding.editBack.getText().toString();

            if (!front.isEmpty() && !back.isEmpty()) {
                Flashcard flashcard = new Flashcard(front, back);
                viewModel.insertFlashcard(flashcard);
                clearFields();
            } else {
                binding.editFront.setText("Incomplete Information");
            }
        });

        binding.findButton.setOnClickListener(view ->
                viewModel.findFlashcard(binding.editFront.getText().toString()
        )
    );

        binding.deleteButton.setOnClickListener(view -> {
            viewModel.deleteFlashcard(binding.editFront.getText().toString());
            clearFields();
        });
    }

    private void observerSetup() {
        viewModel.getAllFlashcards().observe(this,
                flashcards -> adapter.setFlashcardList(flashcards));

        viewModel.getSearchResults().observe(this,
                flashcards -> {
            if(!flashcards.isEmpty()) {
                binding.editFront.setText(flashcards.get(0).getFront());
                binding.editBack.setText(flashcards.get(0).getBack());
            } else {
                binding.editFront.setText("No Match");
            }
        });
    }

    private void recyclerSetup() {
        adapter = new FlashcardListAdapter(R.layout.flashcard_list_item);
        binding.flashcardRecycler.setLayoutManager(
                new LinearLayoutManager(this));
        binding.flashcardRecycler.setAdapter(adapter);
    }



}