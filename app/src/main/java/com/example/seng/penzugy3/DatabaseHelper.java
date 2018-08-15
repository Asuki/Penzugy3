package com.example.seng.penzugy3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DatabaseHelper extends  SQLiteOpenHelper{
    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "db_finance";
    private static final int DATABASE_VERSION = 1;

    public final int ID_POSITION = 0;
    public final int NAME_POSITION = 1;
    public final int CATEGORY_MONTHLY_LIMIT_POSITION = 2;
    public final int CATEGORY_BASE_VALUE_POSITION = 3;
    public final int CATEGORY_VALUE_TYPE_POSITION = 4;
    public final int SHOPPING_LIST_VALUE_POSITION = 2;
    public final int SHOPPING_LIST_REAL_VALUE_POSITION = 3;
    public final int SHOPPING_LIST_QUANTITY = 4;

    private static final String CREATE_TABLE = " create table if not exists ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    private static final String ID_TYPE = " integer primary key autoincrement";
    private static final String STRING_TYPE_200 = " varchar(200)";
    private static final String INT_TYPE = " integer";
    private static final String UNIQUE_MODIFIER = " unique";
    private static final String TIME_STAMP_TYPE_CURR_DATE = " DATETIME";

    private static final String DEFAULT_INT = " DEFAULT 0";

    // region Tables
    public static final String FINANCE_TABLE = "finance";
    private static final String FINANCE_ID = "finance_id";
    private static final String FINANCE_USER_ID = "user_id";
    private static final String FINANCE_CATEGORY_ID = "category_id";
    private static final String FINANCE_VALUE = "value";
    private static final String FINANCE_USAGE_DATE = "usage_date";
    private static final String CREATE_FINANCE_ID = FINANCE_ID + ID_TYPE;
    private static final String CREATE_FINANCE_USER_ID = FINANCE_USER_ID + INT_TYPE;
    private static final String CREATE_FINANCE_CATEGORY_ID = FINANCE_CATEGORY_ID + INT_TYPE;
    private static final String CREATE_FINANCE_VALUE = FINANCE_VALUE + INT_TYPE;
    private static final String CREATE_FINANCE_USAGE_DATE = FINANCE_USAGE_DATE + TIME_STAMP_TYPE_CURR_DATE;
    private static final String CREATE_FINANCE_TABLE = CREATE_TABLE + FINANCE_TABLE + " (" +
            CREATE_FINANCE_ID + ", " +
            CREATE_FINANCE_USER_ID + ", " +
            CREATE_FINANCE_CATEGORY_ID + ", " +
            CREATE_FINANCE_VALUE + ", " +
            CREATE_FINANCE_USAGE_DATE + ");";

    public static final String CATEGORY_TABLE  = "category";
    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_NAME =  "category_name";
    private static final String CATEGORY_MONTHLY_LIMIT = "monthly_limit";
    private static final String CATEGORY_BASE_VALUE = "base_value";
    private static final String CATEGORY_VALUE_TYPE = "value_type";
    private static final String CREATE_CATEGORY_ID = CATEGORY_ID + ID_TYPE;
    private static final String CREATE_CATEGORY_NAME =  CATEGORY_NAME + STRING_TYPE_200 + UNIQUE_MODIFIER;
    private static final String CREATE_CATEGORY_MONTHLY_LIMIT = CATEGORY_MONTHLY_LIMIT + INT_TYPE + DEFAULT_INT;
    private static final String CREATE_CATEGORY_BASE_VALUE = CATEGORY_BASE_VALUE + INT_TYPE + DEFAULT_INT;
    private static final String CREATE_CATEGORY_VALUE_TYPE = CATEGORY_VALUE_TYPE + STRING_TYPE_200;
    private static final String CREATE_CATEGORY_TABLE = CREATE_TABLE + CATEGORY_TABLE + "( " +
            CREATE_CATEGORY_ID + ", " +
            CREATE_CATEGORY_NAME + ", " +
            CREATE_CATEGORY_MONTHLY_LIMIT + ", " +
            CREATE_CATEGORY_BASE_VALUE + ", " +
            CREATE_CATEGORY_VALUE_TYPE + ");";

    public static final String USER_TABLE = "users";
    private static final String USER_ID = "user_id";
    private static final String USER_USER_NAME = "user_name";
    private static final String CREATE_USER_ID = USER_ID+ ID_TYPE;
    private static final String CREATE_USER_USER_NAME = USER_USER_NAME + STRING_TYPE_200 + UNIQUE_MODIFIER;
    private static final String CREATE_USER_TABLE = CREATE_TABLE + USER_TABLE + "( "+
            CREATE_USER_ID + ", " +
            CREATE_USER_USER_NAME + ");";

    public static final String SHOPPING_LIST_TABLE = "shopping_list";
    private static final String SHOPPING_ID = "shopping_id";
    private static final String SHOPPING_ITEM_NAME = "item_name";
    private static final String SHOPPING_ITEM_VALUE = "value";
    private static final String SHOPPING_ITEM_REAL_VALUE = "real_value";
    private static final String SHOPPING_ITEM_QUANTITY = "quantity";
    private static final String SHOPPING_VALUE_DIFFERENCE = "value_difference";
    private static final String CREATE_SHOPPING_ID = SHOPPING_ID + ID_TYPE;
    private static final String CREATE_SHOPPING_ITEM_NAME = SHOPPING_ITEM_NAME + STRING_TYPE_200 + UNIQUE_MODIFIER;
    private static final String CREATE_SHOPPING_ITEM_VALUE = SHOPPING_ITEM_VALUE + INT_TYPE + DEFAULT_INT;
    private static final String CREATE_SHOPPING_ITEM_REAL_VALUE = SHOPPING_ITEM_REAL_VALUE + INT_TYPE + DEFAULT_INT;
    private static final String CREATE_SHOPPING_ITEM_QUANTITY = SHOPPING_ITEM_QUANTITY + STRING_TYPE_200;
    private static final String CREATE_SHOPPING_VALUE_DIFFERENCE = SHOPPING_VALUE_DIFFERENCE + INT_TYPE + DEFAULT_INT;
    private static final String CREATE_SHOPPING_TABLE = CREATE_TABLE + SHOPPING_LIST_TABLE + "( "+
            CREATE_SHOPPING_ID + ", " +
            CREATE_SHOPPING_ITEM_NAME + ", " +
            CREATE_SHOPPING_ITEM_VALUE + ", " +
            CREATE_SHOPPING_ITEM_REAL_VALUE + ", " +
            CREATE_SHOPPING_ITEM_QUANTITY + ", " +
            CREATE_SHOPPING_VALUE_DIFFERENCE + ");";
    // endregion

    public LimitTypes LIMIT_TYPES() {
        return limitTypes;
    }

    private LimitTypes limitTypes;

    public class LimitTypes{
        public int Steady() {
            return steady;
        }
        private int steady = 1;

        private int growing = 2;

        public int Growing() {
            return growing;
        }
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        limitTypes = new LimitTypes();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        Log.d(TAG, "onCreate: create " + USER_TABLE + " table + " + CREATE_USER_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        Log.d(TAG, "onCreate: create " + CATEGORY_TABLE + " table + " + CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_FINANCE_TABLE);
        Log.d(TAG, "onCreate: create " + FINANCE_TABLE+ " table + " + CREATE_FINANCE_TABLE);
        db.execSQL(CREATE_SHOPPING_TABLE);
        Log.d(TAG, "onCreate: create " + SHOPPING_LIST_TABLE + " table + " + CREATE_SHOPPING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE + USER_TABLE);
        db.execSQL(DROP_TABLE + CATEGORY_TABLE);
        onCreate(db);
    }

    /*public boolean addCategory(String name, int monthlyLimit, int limitType){
        return addCategory(name, monthlyLimit, limitType, 0);
    }*/

    public boolean addCategory(String name, int monthlyLimit, String limitType, int baseValue){
        //int dailyLimit = getDailyLimit(monthlyLimit, limitType, baseValue);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_NAME, name);
        contentValues.put(CATEGORY_MONTHLY_LIMIT, monthlyLimit);
        contentValues.put(CATEGORY_BASE_VALUE, baseValue);
        contentValues.put(CATEGORY_VALUE_TYPE, limitType);

        Log.d(TAG, "addCategory: Adding (name, dailyLimit, monthlyLimit)" + name + ", " + baseValue + ", " + monthlyLimit + ", " + limitType + " to " + CATEGORY_TABLE);
        long result = db.insert(CATEGORY_TABLE, null, contentValues);
        if (-1 == result){
            return  false;
        }
        return true;
    }

    public boolean addUser(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_USER_NAME, name);

        Log.d(TAG, "addCategory: Adding (name)" + name + " to " + USER_TABLE    );
        long result = db.insert(USER_TABLE, null, contentValues);
        if (-1 == result){
            return  false;
        }
        return true;
    }

    public boolean addShoppingListItem(String name, int value, int realValue, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SHOPPING_ITEM_NAME, name);
        contentValues.put(SHOPPING_ITEM_VALUE, value);
        contentValues.put(SHOPPING_ITEM_REAL_VALUE, realValue);
        contentValues.put(SHOPPING_ITEM_QUANTITY, quantity);

        Log.d(TAG, "addShoppingListItem: Adding (name, value, realValue, quantity)" + name + ", " + value + ", " + realValue + ", " + quantity + " to " + SHOPPING_LIST_TABLE);
        long result = db.insert(SHOPPING_LIST_TABLE, null, contentValues);
        if (-1 == result){
            return  false;
        }
        return true;
    }

    public boolean addFinance(int userID, int categoryID, int value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FINANCE_USER_ID, userID);
        contentValues.put(FINANCE_CATEGORY_ID, categoryID);
        contentValues.put(FINANCE_VALUE, value);

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        String formattedDate = df.format(c);

        contentValues.put(FINANCE_USAGE_DATE, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));

        Log.d(TAG, "addFinance: Adding (userID, categoryID, value, time)" + userID + ", " + value + ", " + categoryID + ", " + value + ", " + new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()) + " to " + FINANCE_TABLE);
        long result = db.insert(FINANCE_TABLE, null, contentValues);
        if (-1 == result){
            return  false;
        }
        return true;
    }

    public boolean removeCategory(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CATEGORY_TABLE, CATEGORY_NAME + " = '" + name + "'", null) > 0;
    }

    public boolean removeUser(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USER_TABLE, USER_USER_NAME + " = '" + name + "'", null) > 0;
    }

    public boolean removeShoppingListItem(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(SHOPPING_LIST_TABLE, SHOPPING_ITEM_NAME + " = '" + name + "'", null) > 0;
    }

    private int getDailyLimit(int monthlyLimit, int limitType, int baseValue) {
        int avg = Math.round(monthlyLimit / 31);
        if (limitType == LIMIT_TYPES().steady) {
            return avg;
        } else if (limitType == LIMIT_TYPES().growing) {
            Calendar calendar = Calendar.getInstance();
            return (calendar.get(Calendar.DAY_OF_MONTH) / 16) * (avg - baseValue) + baseValue;
        }
        return 0;
    }

    public Cursor getFullTable(String tableName){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + tableName;
        Log.d(TAG, "getFullTable: query: " + query);
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "getFullTable: query run successfully");

        return data;
    }

    public Cursor getFinance(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " +
                        FINANCE_ID + ", " +
                        USER_USER_NAME + ", " +
                        CATEGORY_NAME + ", " +
                        FINANCE_VALUE + ", " +
                        FINANCE_USAGE_DATE +
                        " FROM " + FINANCE_TABLE +
                        " LEFT JOIN " + USER_TABLE +
                        " on " + FINANCE_TABLE + "." + FINANCE_USER_ID + " = "  + USER_TABLE + "." + USER_ID +
                        " LEFT JOIN " + CATEGORY_TABLE +
                        " on " + FINANCE_TABLE + "." + FINANCE_CATEGORY_ID + " = "  + CATEGORY_TABLE + "." + CATEGORY_ID;
        Log.d(TAG, "getFullTable: query: " + query);
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "getFullTable: query run successfully");

        return data;
    }

    public Cursor getUsers(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE;
        Log.d(TAG, "getUsers: query: " + query);
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "getUsers: query run successfully");

        return data;
    }

    public Cursor getUsers(String name){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_USER_NAME + " = '" + name + "';";
        Log.d(TAG, "getUsers: query: " + query);
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "getUsers: query run successfully");

        return data;
    }

    public Cursor getCategories(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + CATEGORY_TABLE;
        Log.d(TAG, "getCategories: query: " + query);
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "getCategories: query run successfully");
        return data;
    }

    public Cursor getCategoryID(String name){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + CATEGORY_TABLE + " WHERE " + CATEGORY_NAME + " = '" + name + "';";
        Log.d(TAG, "getCategoryID: query: " + query);
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "getCategoryID: query run successfully");
        return  data;
    }

    public Cursor getCategoryName(int catID){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + CATEGORY_TABLE + " WHERE " + CATEGORY_ID + " = '" + catID + "';";
        Log.d(TAG, "getCategoryID: query: " + query);
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "getCategoryID: query run successfully");
        return  data;
    }

    public Cursor getShoppingList(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + SHOPPING_LIST_TABLE;
        Log.d(TAG, "getShoppingList: query: " + query);
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "getShoppingList: query run successfully");
        return data;
    }

    public Cursor getShoppingList(String itemName){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + SHOPPING_LIST_TABLE + " WHERE " + SHOPPING_ITEM_NAME + " = '" + itemName + "';";
        Log.d(TAG, "getShoppingList: query: " + query);
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "getShoppingList: query run successfully");
        return data;
    }

    public void rebuild(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(DROP_TABLE + USER_TABLE);
        db.execSQL(DROP_TABLE + CATEGORY_TABLE);
        db.execSQL(DROP_TABLE + SHOPPING_LIST_TABLE);
        db.execSQL(DROP_TABLE + FINANCE_TABLE);
        onCreate(db);
    }
}
