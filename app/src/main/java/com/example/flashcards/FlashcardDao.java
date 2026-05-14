package com.example.flashcards;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface FlashcardDao {

    @Insert
    void insertFlashcard(Flashcard flashcard);

    @Query("SELECT * FROM flashcards WHERE flashcardFront = :front")
    List<Flashcard> findFlashcard(String front);

    @Query("DELETE FROM flashcards WHERE flashcardFront = :front")
    void deleteFlashcard(String front);

    @Query("SELECT * FROM flashcards")
    LiveData<List<Flashcard>> getAllFlashcards();

    @Query("SELECT * FROM flashcards")
    List<Flashcard> getAllCards();

}
