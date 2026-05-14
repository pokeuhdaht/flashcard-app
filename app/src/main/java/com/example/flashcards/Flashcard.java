package com.example.flashcards;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flashcards")
public class Flashcard {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "flashcardId")
    private int id;
    @ColumnInfo(name = "flashcardFront")
    private String front;


    private String back;

    public Flashcard(String front, String back){
        this.front = front;
        this.back = back;
    }

    public int getId(){
        return this.id;
    }
    public String getFront(){
        return this.front;
    }
    public String getBack(){
        return this.back;
    }
    public void setId(int id){
        this.id =id;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
