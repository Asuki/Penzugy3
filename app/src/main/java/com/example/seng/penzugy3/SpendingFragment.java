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
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SpendingFragment extends Fragment{
    View spendingView;
    Spinner spinnerCategories, spinnerUsers;
    Button btnV1, btnV2, btnV3, btnV4, btnV5, btnV6, btnV7, btnV8, btnV9, btnV0, btnV00, btnV000;
    Button btnBckSpc, btnEnter;
    ImageButton imageButtonDaily, imageButtonHouse,imageButtonEntertainment, imageButtonCelebrations;
    ImageButton imageButtonTravel, imageButtonCredit, imageButtonIncome;
    EditText editTextSpending;
    int selectedId;

    private static final String TAG = "SpendingFragment";
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        selectedId = 2;

        spendingView = inflater.inflate(R.layout.spending_layout, container, false);

        databaseHelper = new DatabaseHelper(getActivity());
        //spinnerCategories = spendingView.findViewById(R.id.spinnerSpendingCategories);
        spinnerUsers = spendingView.findViewById(R.id.spinnerSpendingUsers);
        editTextSpending = spendingView.findViewById(R.id.editTextSpendingAmount);

        imageButtonDaily = spendingView.findViewById(R.id.imageButtonDaily);
        imageButtonHouse = spendingView.findViewById(R.id.imageButtonHouse);
        imageButtonCelebrations = spendingView.findViewById(R.id.imageButtonCelebrations);
        imageButtonEntertainment = spendingView.findViewById(R.id.imageButtonEntertainment);
        imageButtonTravel = spendingView.findViewById(R.id.imageButtonTravel);
        imageButtonCredit = spendingView.findViewById(R.id.imageButtonCredit);
        imageButtonIncome = spendingView.findViewById(R.id.imageButtonIncome);

        btnV1 = spendingView.findViewById(R.id.buttonV1);
        btnV2 = spendingView.findViewById(R.id.buttonV2);
        btnV3 = spendingView.findViewById(R.id.buttonV3);
        btnV4 = spendingView.findViewById(R.id.buttonV4);
        btnV5 = spendingView.findViewById(R.id.buttonV5);
        btnV6 = spendingView.findViewById(R.id.buttonV6);
        btnV7 = spendingView.findViewById(R.id.buttonV7);
        btnV8 = spendingView.findViewById(R.id.buttonV8);
        btnV9 = spendingView.findViewById(R.id.buttonV9);
        btnV000 = spendingView.findViewById(R.id.buttonV000);
        btnV00 = spendingView.findViewById(R.id.buttonV00);
        btnV0 = spendingView.findViewById(R.id.buttonV0);
        btnBckSpc = spendingView.findViewById(R.id.buttonBckSpc);
        btnEnter = spendingView.findViewById(R.id.buttonEnter);

        imageButtonIncome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonIncome.setImageResource(R.drawable.ic_huf_inv60);
                selectedId = 1;
                showCategoryName(selectedId);
            }
        });

        imageButtonDaily.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonDaily.setImageResource(R.drawable.ic_shopping_cart_inv60);
                selectedId = 2;
                showCategoryName(selectedId);
            }
        });

        imageButtonEntertainment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonEntertainment.setImageResource(R.drawable.ic_entertainment_inv60);
                selectedId = 3;
                showCategoryName(selectedId);
            }
        });

        imageButtonCelebrations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonCelebrations.setImageResource(R.drawable.ic_celebrations_inv60);
                selectedId = 4;
                showCategoryName(selectedId);
            }
        });

        imageButtonTravel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonTravel.setImageResource(R.drawable.ic_travel_inv60);
                selectedId = 5;
                showCategoryName(selectedId);
            }
        });

        imageButtonHouse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonHouse.setImageResource(R.drawable.ic_house_inv60);
                selectedId = 6;
                showCategoryName(selectedId);
            }
        });

        imageButtonCredit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonCredit.setImageResource(R.drawable.ic_credit_inv60);
                selectedId = 6;
                showCategoryName(selectedId);
            }
        });

        btnV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "1");
            }
        });
        btnV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "2");
            }
        });
        btnV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "3");
            }
        });
        btnV4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "4");
            }
        });
        btnV5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "5");
            }
        });
        btnV6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "6");
            }
        });
        btnV7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "7");
            }
        });
        btnV8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "8");
            }
        });
        btnV9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "9");
            }
        });
        btnV000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "000");
            }
        });
        btnV00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "00");
            }
        });
        btnV0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "0");
            }
        });
        btnBckSpc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSpending.getText().length() > 0)
                    editTextSpending.setText(editTextSpending.getText().subSequence(0, editTextSpending.getText().length() - 1));
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Log.d(TAG, "-- Calculating shopping list --");

                    int userID = -1;
                    int categoryID = selectedId;
                    Log.d(TAG, "selectedID = " + selectedId);
                    Cursor data = databaseHelper.getUsers(spinnerUsers.getSelectedItem().toString());
                    while (data.moveToNext()){
                        userID = data.getInt(databaseHelper.ID_POSITION);
                    }
                    Log.d(TAG, "user id while adding finance: " + userID);

                    //data = databaseHelper.getCategoryID(spinnerCategories.getSelectedItem().toString());
                    /*while (data.moveToNext()){
                        categoryID = data.getInt(databaseHelper.ID_POSITION);
                    }*/
                    Log.d(TAG, "category id while adding finance: " + categoryID);

                    int value = Integer.parseInt(editTextSpending.getText().toString());

                    databaseHelper.addFinance(userID, categoryID, value);
                    toastMessage("Költség sikeresen hozzáadva a költések táblához " + editTextSpending.getText().toString());
                    editTextSpending.setText("");
                }
        });

        //try {
            fillSpinner();
        /*}
        catch (Exception e) {
        }*/
        return spendingView;
    }

    private void setDefaultButtonImages(){
        imageButtonDaily.setImageResource(R.drawable.ic_shopping_cart60);
        imageButtonHouse.setImageResource(R.drawable.ic_house60);
        imageButtonEntertainment.setImageResource(R.drawable.ic_entertainment60);
        imageButtonCelebrations.setImageResource(R.drawable.ic_celebrations60);
        imageButtonTravel.setImageResource(R.drawable.ic_travel60);
        imageButtonCredit.setImageResource(R.drawable.ic_credit60);
        imageButtonIncome.setImageResource(R.drawable.ic_huf60);
    }

    private void showCategoryName(int selectedId){
        String catName = "";
        Cursor data = databaseHelper.getCategoryName(selectedId);
        try{
            while(data.moveToNext()){
                catName = data.getString(databaseHelper.NAME_POSITION);
            }
            toastMessage(catName);
        }
        catch (Exception e){

        }
    }

    private void fillSpinner(){
        Cursor data = databaseHelper.getCategories();

        /*
        ArrayList<String> arrayListCategories = new ArrayList<>();

        while (data.moveToNext()){
            arrayListCategories.add(data.getString(databaseHelper.NAME_POSITION) + "");
        }

        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayListCategories);
        spinnerCategories.setAdapter(adapter);*/


        data = databaseHelper.getUsers();
        ArrayList<String> arrayListUsers = new ArrayList<>();

        while (data.moveToNext()){
            arrayListUsers.add(data.getString(databaseHelper.NAME_POSITION) + "");
        }

        ArrayAdapter adapterUsers = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayListUsers);
        spinnerUsers.setAdapter(adapterUsers);
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
