package com.example.flashcards;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import android.app.Application;
import androidx.lifecycle.LiveData;

public class FlashcardRepository {
    private final MutableLiveData<List<Flashcard>> searchResults =
            new MutableLiveData<>();
    private List<Flashcard> results;
    private final LiveData<List<Flashcard>> allFlashcards;
    private final FlashcardDao flashcardDao;

    public FlashcardRepository(Application application){
        FlashcardRoomDatabase db;
        db = FlashcardRoomDatabase.getDatabase(application);
        flashcardDao = db.flashcardDao();
        allFlashcards = flashcardDao.getAllFlashcards();
    }

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override public void handleMessage(Message msg) {
            searchResults.setValue(results);
        }
    };

    public void insertFlashcard(Flashcard newflashcard) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> flashcardDao.insertFlashcard(newflashcard));
        executor.shutdown();
    }
    public void deleteFlashcard(String front) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> flashcardDao.deleteFlashcard(front));
        executor.shutdown();
    }
    public void findFlashcard(String front) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            results = flashcardDao.findFlashcard(front);
            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }

    public LiveData<List<Flashcard>> getAllFlashcards(){
        return allFlashcards;
    }
    public MutableLiveData<List<Flashcard>> getSearchResults(){
        return searchResults;
    }

}
