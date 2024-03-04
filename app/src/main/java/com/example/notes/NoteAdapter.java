package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<Notes> notes;
    private final OnDeleteClickListener onDeleteClickListener;
    private final OnChangeClickListener onChangeClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Notes note, int position);
    }

    public interface OnChangeClickListener {
        void onChangeClick(Notes note, int position);
    }


    public NoteAdapter(Context context,List<Notes> notes,  OnDeleteClickListener onDeleteClickListener, OnChangeClickListener onChangeClickListener){
        this.notes = notes;
        inflater = LayoutInflater.from(context);
        this.onDeleteClickListener = onDeleteClickListener;
        this.onChangeClickListener = onChangeClickListener;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_note,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.title.setText(note.getTitle());
        holder.text.setText(note.getText());
        holder.delete.setOnClickListener(v -> {
          onDeleteClickListener.onDeleteClick(note,holder.getAdapterPosition());
          notes.remove(note);
          notifyItemRemoved(holder.getAdapterPosition());
        });
        holder.change.setOnClickListener(
                v -> onChangeClickListener.onChangeClick(note, holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView delete, change;
        TextView text,title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textViewNoteText);
            title = itemView.findViewById(R.id.textViewNoteTitle);
            delete = itemView.findViewById(R.id.imageViewDelete);
            change = itemView.findViewById(R.id.imageViewChange);
        }
    }
}
