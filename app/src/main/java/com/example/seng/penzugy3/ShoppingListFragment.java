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

public class ShoppingListFragment extends Fragment{
    View shoppingListView;
    Button btnAddItem, btnRemoveItem, btnTickItem;
    EditText editTextItemName, editTextItemValue, editTextItemRealValue, editTextItemQuantity;
    ListView listView;
    Spinner spinnerUsers, spinnerCategories;
    private final String TAG = "EditCategoryFrament";

    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        shoppingListView = inflater.inflate(R.layout.shopping_list_layout, container, false);

        databaseHelper = new DatabaseHelper(getActivity());

        spinnerUsers = shoppingListView.findViewById(R.id.spinnerShppingUsers);
        spinnerCategories = shoppingListView.findViewById(R.id.spinnerShoppingCategory);
        listView = shoppingListView.findViewById(R.id.listViewShoppingList);
        editTextItemName = shoppingListView.findViewById(R.id.editTextItemName);
        editTextItemQuantity = shoppingListView.findViewById(R.id.editTextItemQuantity);
        editTextItemValue = shoppingListView.findViewById(R.id.editTextItemValue);
        editTextItemRealValue = shoppingListView.findViewById(R.id.editTextItemRealValue);

        btnAddItem = shoppingListView.findViewById(R.id.buttonAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String itemName = editTextItemName.getText().toString();
                Cursor dataUsers = databaseHelper.getShoppingList(itemName);
                int shoppingID = -1;

                while(dataUsers.moveToNext()){
                    shoppingID = dataUsers.getInt(databaseHelper.ID_POSITION);
                }

                Log.d(TAG, "User ID = " + shoppingID);
                if (shoppingID == -1){
                    Log.d(TAG, "-- Adding new shopping item --");
                    String itemValue, itemRealValue, itemQuantity;
                    if(editTextItemValue.getText().toString().equals(""))
                        itemValue = "0";
                    else
                        itemValue = editTextItemValue.getText().toString();
                    if(editTextItemRealValue.getText().toString().equals(""))
                        itemRealValue = "0";
                    else
                        itemRealValue = editTextItemValue.getText().toString();
                    if(editTextItemQuantity.getText().toString().equals(""))
                        itemQuantity = "0";
                    else
                        itemQuantity = editTextItemQuantity.getText().toString();
                    databaseHelper.addShoppingListItem(itemName, Integer.parseInt(itemValue)
                            , Integer.parseInt(itemRealValue)
                            , Integer.parseInt(itemQuantity));
                    Log.d(TAG, "name = " + itemName);
                    clearTextFields();
                    populateListView();
                }
                else{
                    Log.d(TAG, "-- FAILED Adding Shopping item: The item name must be unique. --");
                    toastMessage("Az elem már létezik. Ha többet szeretne venni, módosítsa a mennyiséget!");
                }

                // To drop the database and rebuild it, remove the comments below.
                //databaseHelper.rebuild();
                //Log.d(TAG, "-------- REBUILD -----------");
            }
        });

        btnRemoveItem = shoppingListView.findViewById(R.id.buttonDeleteItem);
        btnRemoveItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String itemName = editTextItemName.getText().toString();
                Cursor dataShoppingList = databaseHelper.getShoppingList(itemName);
                int itemID = -1;

                while(dataShoppingList.moveToNext()){
                    itemID = dataShoppingList.getInt(databaseHelper.ID_POSITION);
                }

                Log.d(TAG, "SHOPPING LIST ID = " + itemID);
                if (itemID != -1){
                    Log.d(TAG, "-- Removing shopping list item --");
                    databaseHelper.removeShoppingListItem(itemName);
                    Log.d(TAG, "Removing shopping list item = " + itemName);
                    clearTextFields();
                    populateListView();
                }
                else   {
                    Log.d(TAG, "-- FAILED Removing shopping list item: The item is not exists. --");
                }
            }
        });

        btnTickItem = shoppingListView.findViewById(R.id.buttonItemReady);
        btnTickItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String itemName = editTextItemName.getText().toString();
                Cursor dataShoppingList = databaseHelper.getShoppingList(itemName);
                int itemID = -1;

                while(dataShoppingList.moveToNext()){
                    itemID = dataShoppingList.getInt(databaseHelper.ID_POSITION);
                }

                Log.d(TAG, "SHOPPING LIST ID = " + itemID);
                if (itemID != -1){
                    Log.d(TAG, "-- Calculating shopping list --");

                    int userID = -1;
                    int categoryID = -1;
                    Cursor data = databaseHelper.getUsers(spinnerUsers.getSelectedItem().toString());
                    while (data.moveToNext()){
                        userID = data.getInt(databaseHelper.ID_POSITION);
                    }
                    Log.d(TAG, "user id while adding finance: " + userID);

                    data = databaseHelper.getCategoryID(spinnerCategories.getSelectedItem().toString());
                    while (data.moveToNext()){
                        categoryID = data.getInt(databaseHelper.ID_POSITION);
                    }
                    Log.d(TAG, "category id while adding finance: " + categoryID);

                    int value = Integer.parseInt(editTextItemRealValue.getText().toString());

                    databaseHelper.addFinance(userID, categoryID, value);
                    toastMessage("Költség sikeresen hozzáadva a költések táblához " + editTextItemName.getText().toString());

                    databaseHelper.removeShoppingListItem(itemName);
                    Log.d(TAG, "Removing shopping list item = " + itemName);
                    clearTextFields();
                    populateListView();
                }
                else   {
                    Log.d(TAG, "-- FAILED Removing shopping list item: The item is not exists. --");
                }
            }
        });


        populateListView();
        fillSpinner();

        return shoppingListView;
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void clearTextFields(){
        editTextItemRealValue.setText("");
        editTextItemValue.setText("");
        editTextItemQuantity.setText("");
        editTextItemName.setText("");
    }

    private void populateListView(){
        Log.d(TAG, "displaying user list");

        Cursor data = databaseHelper.getShoppingList();
        ArrayList<String> arrayList = new ArrayList<>();

        while(data.moveToNext()){
            arrayList.add(data.getString(databaseHelper.NAME_POSITION));
        }

        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemName = parent.getItemAtPosition(position).toString();
                editTextItemName.setText(itemName);
                Log.d(TAG, "onItemClick: You clicked on " + itemName);
                int shoppingID = -1;
                Cursor data = databaseHelper.getShoppingList(itemName);
                while(data.moveToNext()){
                    shoppingID = data.getInt(databaseHelper.ID_POSITION);
                    editTextItemName.setText(data.getString(databaseHelper.NAME_POSITION));
                    editTextItemValue.setText(data.getString(databaseHelper.SHOPPING_LIST_VALUE_POSITION));
                    editTextItemRealValue.setText(data.getString(databaseHelper.SHOPPING_LIST_REAL_VALUE_POSITION));
                editTextItemQuantity.setText(data.getString(databaseHelper.SHOPPING_LIST_QUANTITY));
                }
                Log.d(TAG, "onItemClick: the userOD after click = " + shoppingID);
                if (-1 == shoppingID){
                    toastMessage("Hiba a lista elem kiválasztása során!");
                    clearTextFields();
                }
            }
        });
    }

    private void fillSpinner(){
        Cursor data = databaseHelper.getCategories();
        ArrayList<String> arrayListCategories = new ArrayList<>();

        while (data.moveToNext()){
            arrayListCategories.add(data.getString(databaseHelper.NAME_POSITION) + "");
        }

        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayListCategories);
        spinnerCategories.setAdapter(adapter);


        data = databaseHelper.getUsers();
        ArrayList<String> arrayListUsers = new ArrayList<>();

        while (data.moveToNext()){
            arrayListUsers.add(data.getString(databaseHelper.NAME_POSITION) + "");
        }

        ArrayAdapter adapterUsers = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayListUsers);
        spinnerUsers.setAdapter(adapterUsers);
    }

}
