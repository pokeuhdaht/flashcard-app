package com.example.flashcards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.flashcards.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.flashcards.Flashcard;
import java.util.List;
public class FlashcardListAdapter
        extends RecyclerView.Adapter<FlashcardListAdapter.ViewHolder>{

    private final int flashcardItemLayout;
    private List<Flashcard> flashcardList;

    public FlashcardListAdapter(int layoutId){
        flashcardItemLayout = layoutId;
    }

    public void setFlashcardList(List<Flashcard> flashcards) {
        flashcardList = flashcards;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return flashcardList == null ? 0 : flashcardList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(flashcardItemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        item.setText(flashcardList.get(listPosition).getFront());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        ViewHolder(View iteview) {
            super(iteview);
            item = iteview.findViewById(R.id.flashcard_row);
        }
    }

}
