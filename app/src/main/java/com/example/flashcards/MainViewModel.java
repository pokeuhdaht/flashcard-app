package com.example.flashcards;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.flashcards.Flashcard;
import com.example.flashcards.FlashcardRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel{
    final private FlashcardRepository repository;
    final private LiveData<List<Flashcard>> allFlashcards;
    final private MutableLiveData<List<Flashcard>> searchResults;

    public MainViewModel(Application application) {
        super(application);
        repository = new FlashcardRepository(application);
        allFlashcards = repository.getAllFlashcards();
        searchResults = repository.getSearchResults();
    }

    MutableLiveData<List<Flashcard>> getSearchResults(){
        return searchResults;
    }

    LiveData<List<Flashcard>> getAllFlashcards(){
        return allFlashcards;
    }
    public void insertFlashcard(Flashcard flashcard){
        repository.insertFlashcard(flashcard);
    }
    public void findFlashcard(String front){
        repository.findFlashcard(front);
    }
    public void deleteFlashcard(String front){
        repository.deleteFlashcard(front);
    }

}
