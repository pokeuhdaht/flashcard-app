package com.example.flashcards;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Flashcard.class}, version = 1,
exportSchema = false)
public abstract class FlashcardRoomDatabase extends RoomDatabase{

    public abstract FlashcardDao flashcardDao();
    private static FlashcardRoomDatabase INSTANCE;

    static FlashcardRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FlashcardRoomDatabase.class) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(),
                                FlashcardRoomDatabase.class,
                                "flashcard_database").build();
            }
        }
        return INSTANCE;
    }

}
