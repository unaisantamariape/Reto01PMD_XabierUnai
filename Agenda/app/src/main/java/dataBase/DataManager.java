package dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Wraps the logic for a SQLite database
 */
public class DataManager extends SQLiteOpenHelper {

    // Database Information
    private static final String DB_NAME = "tareas.db";

    // Database version
    private static final int DB_VERSION = 1;

    // Table Name
    public static final String TABLE_NAME = "Tarea";

    // Table columns
    private static final String ID = "id";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPCION = "descripción";
    private static final String COSTE = "coste";
    private static final String PRIORIDAD = "prioridad";
    private static final String FECHA = "fecha";
    private static final String REALIZADA = "realizada";


    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOMBRE + " TEXT NOT NULL, " +
            DESCRIPCION + " TEXT, " +
            COSTE + " INTEGER, " +
            PRIORIDAD + " TEXT, " +
            FECHA + " TEXT, " +
            REALIZADA + " INTEGER " +
            ");";

    private final Context context;

    public DataManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sQLiteDatabase);
    }

    //------------------------------ Select Nombres Pendientes ------------------------------//
    public ArrayList<String> selectNombresPendientes() {
        String query = " Select "+ NOMBRE + " FROM " + TABLE_NAME + " WHERE "+ REALIZADA+"=0";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        ArrayList<String> nombres = new ArrayList<>() ;
        int i = 0;
        while (cursor.moveToNext()) {
            nombres.add(cursor.getString(0).toString());
            i++;
        }
        cursor.close();
        sQLiteDatabase.close();
        return nombres;
    }

    //------------------------------ Select Nombres Realizados ------------------------------//
    public ArrayList<String> selectNombresRealizados() {
        String query = " Select "+ NOMBRE + " FROM " + TABLE_NAME + " WHERE "+ REALIZADA+"=1";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        ArrayList<String> nombres = new ArrayList<>() ;
        int i = 0;
        while (cursor.moveToNext()) {
            nombres.add(cursor.getString(0).toString());
            i++;
        }
        cursor.close();
        sQLiteDatabase.close();
        return nombres;
    }

    //------------------------------ Insert ------------------------------//
    public void insert (Tarea tarea) {
        ContentValues values = new ContentValues();

        //values.put(ID, cliente.getId());
        values.put(NOMBRE, tarea.getNombre());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_NAME, null, values);
        sQLiteDatabase.close();
    }


    //------------------------------ Delete by Name ------------------------------//
    public int deleteByName (String name) {
        int ret;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();

        ret = sQLiteDatabase.delete(TABLE_NAME, NOMBRE + "=?", new String[]{name});
        sQLiteDatabase.close();
        return ret;
    }

}
