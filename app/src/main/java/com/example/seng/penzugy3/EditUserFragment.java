package com.example.seng.penzugy3;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditUserFragment extends Fragment{
    View userView;
    Button btnAddUser, btnRemoveUser;
    EditText editTextUserName;
    ListView listView;

    String userName;
    int userID;

    private static final String TAG = "EditUserFragment";
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        userView = inflater.inflate(R.layout.user_editor, container, false);

        databaseHelper = new DatabaseHelper(getActivity());
        editTextUserName = userView.findViewById(R.id.editTextUserName);
        listView = userView.findViewById(R.id.userListView);


        btnAddUser = userView.findViewById(R.id.buttonAddUser);
        btnAddUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                userName = editTextUserName.getText().toString();
                Cursor dataUsers = databaseHelper.getUsers(userName);
                userID = -1;

                while(dataUsers.moveToNext()){
                    userID = dataUsers.getInt(databaseHelper.ID_POSITION);
                }

                Log.d(TAG, "User ID = " + userID);
                if (userID == -1){
                    Log.d(TAG, "-- Adding new user --");
                    databaseHelper.addUser(userName);
                    Log.d(TAG, "name = " + userName);
                    editTextUserName.setText("");
                    populateListView();
                }
                else{
                    Log.d(TAG, "-- FAILED Adding User: The user name is already exists. --");
                    toastMessage("A felhasználónév már létezik!");
                }
            }
        });


        btnRemoveUser = userView.findViewById(R.id.buttonRemoveUser);
        btnRemoveUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userName = editTextUserName.getText().toString();
                Cursor dataUsers = databaseHelper.getUsers(userName);
                userID = -1;

                while(dataUsers.moveToNext()){
                    userID = dataUsers.getInt(databaseHelper.ID_POSITION);
                    Log.d(TAG, "User ID in loop = " + userID);
                }

                Log.d(TAG, "User ID = " + userID);
                if (userID != -1){
                    Log.d(TAG, "-- Removing user --");
                    databaseHelper.removeUser(userName);
                    Log.d(TAG, "Removing user = " + userName);
                    editTextUserName.setText("");
                    populateListView();
                }
                else   {
                    Log.d(TAG, "-- FAILED Removing User: The User name is not exists. --");
                }
            }
        });

        userName = "";
        userID = -1;

        populateListView();
        return userView;
    }

    private void populateListView(){
        Log.d(TAG, "displaying user list");

        Cursor data = databaseHelper.getUsers();
        ArrayList<String> arrayList = new ArrayList<>();

        while(data.moveToNext()){
            arrayList.add(data.getString(databaseHelper.NAME_POSITION));
        }

        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userName = parent.getItemAtPosition(position).toString();
                editTextUserName.setText(userName);
                Log.d(TAG, "onItemClick: You clicked on " + userName);

                Cursor data = databaseHelper.getUsers(userName);
                while(data.moveToNext()){
                    userID = data.getInt(databaseHelper.ID_POSITION);
                    editTextUserName.setText(data.getString(databaseHelper.NAME_POSITION));
                }
                Log.d(TAG, "onItemClick: the userOD after click = " + userID);
                if (-1 == userID){
                    toastMessage("Hiba a felhasználónév kiválasztása során!");
                    userName = "";
                }
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
