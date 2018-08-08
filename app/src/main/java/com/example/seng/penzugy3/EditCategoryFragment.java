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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EditCategoryFragment extends Fragment{
    View categoryView;
    private Button btnAddCategory, btnRemoveCategory;
    private EditText editTextCategoryName, editTextCategoryMonthlyLimit, edittextBaseValue;
    private Spinner spinnerCategories;
    ListView listView;

    String categoryName;
    int categoryId;
    int monthlyLimit;
    int baseValue;

    private final String TAG = "EditCategoryFrament";

    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        categoryView = inflater.inflate(R.layout.category_editor, container, false);

        editTextCategoryName = categoryView.findViewById(R.id.editTextCategoryName);
        editTextCategoryMonthlyLimit = categoryView.findViewById(R.id.editTextMonthlyLimitValue);
        edittextBaseValue = categoryView.findViewById(R.id.editTextBaseValue);
        spinnerCategories = categoryView.findViewById(R.id.spinnerCategories);

        categoryId = -1;
        monthlyLimit = 0;
        baseValue = 0;

        listView = categoryView.findViewById(R.id.categoryList);

        categoryName = editTextCategoryName.getText().toString();

        databaseHelper = new DatabaseHelper(getActivity());

        btnAddCategory = categoryView.findViewById(R.id.buttonAddCategory);
        btnAddCategory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                categoryName = editTextCategoryName.getText().toString();
                Cursor dataCategoryIDs = databaseHelper.getCategoryID(categoryName);
                categoryId = -1;

                while(dataCategoryIDs.moveToNext()){
                    categoryId = dataCategoryIDs.getInt(databaseHelper.ID_POSITION);
                    Log.d(TAG, "Category ID in loop = " + categoryId);
                }

                Log.d(TAG, "Category ID = " + categoryId);
                if (categoryId == -1){
                        Log.d(TAG, "-- Adding new category --");
                        databaseHelper.addCategory(categoryName,
                                Integer.parseInt(editTextCategoryMonthlyLimit.getText().toString()),
                                spinnerCategories.getSelectedItem().toString(),
                                Integer.parseInt(edittextBaseValue.getText().toString()));
                        Log.d(TAG, "(name, monthlyLimit) = " + categoryName + ", " + editTextCategoryMonthlyLimit.getText().toString());
                    clearTextFields();
                    populateListView();
                    Log.d(TAG, "-- FAILED Adding Category: The Category name is already exists. --");
                }

                // To drop the database and rebuild it, remove the comments below.
                //databaseHelper.rebuild();
                //Log.d(TAG, "-------- REBUILD -----------");
            }
        });

        btnRemoveCategory = categoryView.findViewById(R.id.buttonRemoveCategory);
        btnRemoveCategory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                categoryName = editTextCategoryName.getText().toString();
                Cursor dataCategoryIDs = databaseHelper.getCategoryID(categoryName);
                categoryId = -1;

                while(dataCategoryIDs.moveToNext()){
                    categoryId = dataCategoryIDs.getInt(databaseHelper.ID_POSITION);
                    Log.d(TAG, "Category ID in loop = " + categoryId);
                }

                Log.d(TAG, "Category ID = " + categoryId);
                if (categoryId != -1){
                    Log.d(TAG, "-- Removing category --");
                    databaseHelper.removeCategory(categoryName);
                    Log.d(TAG, "Removing category = " + categoryName);
                    clearTextFields();
                    populateListView();
                }
                else   {
                    Log.d(TAG, "-- FAILED Removing Category: The Category name is not exists. --");
                }
            }
        });

        populateListView();
        fillSpinner();

        return categoryView;
    }

    private void clearTextFields(){
        editTextCategoryMonthlyLimit.setText("");
        editTextCategoryName.setText("");
        edittextBaseValue.setText("");
    }

    private void fillSpinner(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Egyenletes");
        arrayList.add("Növekvő");

        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        spinnerCategories.setAdapter(adapter);
    }

    private void populateListView(){
        Log.d(TAG, "displaying category list");

        Cursor data = databaseHelper.getCategories();
        ArrayList<String> arrayList = new ArrayList<>();

        while(data.moveToNext()){
            arrayList.add(data.getString(databaseHelper.NAME_POSITION));
        }

        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoryName = parent.getItemAtPosition(position).toString();
                editTextCategoryName.setText(categoryName);
                Log.d(TAG, "onItemClick: You clicked on " + categoryName);

                Cursor data = databaseHelper.getCategoryID(categoryName);
                while(data.moveToNext()){
                    categoryId = data.getInt(databaseHelper.ID_POSITION);
                    monthlyLimit = data.getInt(databaseHelper.CATEGORY_MONTHLY_LIMIT_POSITION);
                    editTextCategoryMonthlyLimit.setText(monthlyLimit + "");
                    edittextBaseValue.setText(data.getInt(databaseHelper.CATEGORY_BASE_VALUE_POSITION) + "");
                }
                Log.d(TAG, "onItemClick: the categoryID after click = " + categoryId);
                if (-1 == categoryId){
                    toastMessage("Hiba a kategória kiválasztása során!");
                    categoryName = "";
                }
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
