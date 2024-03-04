package com.example.notes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.Notes;
import com.example.notes.R;
import com.example.notes.database.DBManager;


public class ChangeNoteFragment extends Fragment {

    Notes notes;
    public ChangeNoteFragment(Notes notes){
        this.notes = notes;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBManager dbManager = new DBManager(getContext());
        dbManager.openDb();
        EditText title = view.findViewById(R.id.editTextNoteChangeTitle);
        EditText text = view.findViewById(R.id.editTextNoteChangeText);
        Button change = view.findViewById(R.id.buttonNoteChange);
        title.setText(notes.getTitle());
        text.setText(notes.getText());
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notes.getTitle().equals("") | notes.getText().equals("")){
                    Toast.makeText(getContext(),"Заполните поля",Toast.LENGTH_SHORT).show();
                    return;
                }
                notes.setTitle(title.getText().toString());
                notes.setText(text.getText().toString());
                dbManager.updateNotes(notes);
                dbManager.closeDb();
                getParentFragmentManager().popBackStack();
            }
        });
    }
}