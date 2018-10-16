package student.pxl.be.mealapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalMealsDB extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Meals";

    public LocalMealsDB(Context context){
       super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCALMEALS_TABLE = "CREATE TABLE LOCALMEALS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "ingredients TEXT," +
                "thumbnail TEXT)"; //TODO: thumbnail from camera
        db.execSQL(CREATE_LOCALMEALS_TABLE);
        //TODO: FAVORITEMEALS DB
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS LOCALMEALS");

        //create fresh table
        this.onCreate(db);
    }


}
