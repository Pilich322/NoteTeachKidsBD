package com.example.notes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notes.NoteAdapter;
import com.example.notes.Notes;
import com.example.notes.R;
import com.example.notes.database.DBManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotesFragment extends Fragment {
    DBManager dbManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton addNote = view.findViewById(R.id.actionButtonAddNotes);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView,new AddNotteFragment())
                        .addToBackStack("")
                        .commit();
            }
        });
        dbManager = new DBManager(getContext());
        dbManager.openDb();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewNotes);
        NoteAdapter.OnChangeClickListener onChangeClickListener = new NoteAdapter.OnChangeClickListener() {
            @Override
            public void onChangeClick(Notes note, int position) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView,new ChangeNoteFragment(note))
                        .addToBackStack("")
                        .commit();
            }
        };
        NoteAdapter.OnDeleteClickListener onDeleteClickListener = new NoteAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Notes note, int position) {
                dbManager.deleteNotes(note);
            }
        };
        NoteAdapter adapter = new NoteAdapter(getContext(),dbManager.getNotes(),onDeleteClickListener,onChangeClickListener);
        recyclerView.setAdapter(adapter);
    }
}