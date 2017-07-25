package miumg.edu.gt.encuesta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ALLAN GARRIDO on 25/07/2017.
 */

public class EncuestaSQLiteOpenHelper extends SQLiteOpenHelper {
    public EncuestaSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table respuesta(" +
                                "pregunta1 boolean, " +
                                "pregunta2 boolean, " +
                                "pregunta3 integer, " +
                                "pregunta4 boolean, " +
                                "pregunta5 boolean, " +
                                "pregunta6 text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
