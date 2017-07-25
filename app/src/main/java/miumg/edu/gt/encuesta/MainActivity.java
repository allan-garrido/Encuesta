package miumg.edu.gt.encuesta;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SeekBar sbp3; //seekbar pregunta 3
    private TextView tvsbp3;  //texto seekbar
    private int progreso;

    private CheckBox p6cb; //chechbox pregunta 6
    private EditText p6et; //texto pregunta 6
    private RadioButton p1rb1;
    private RadioButton p1rb2;
    private RadioButton p2rb1;
    private RadioButton p2rb2;
    private RadioButton p4rb1;
    private RadioButton p4rb2;
    private ToggleButton p5tb;

    private List<Encuesta> encuestas;
    private ArrayAdapter<Encuesta> adapterEncuestas;
    private EncuestaSQLiteOpenHelper adminEncuestas;
    private ListView lv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbp3 = (SeekBar) findViewById(R.id.sbp3);
        tvsbp3 = (TextView) findViewById(R.id.tvsbp3);
        p6cb = (CheckBox) findViewById(R.id.p6cb);
        p6et = (EditText) findViewById(R.id.p6et);
        p1rb1 = (RadioButton) findViewById(R.id.p1rb1);
        p1rb2 = (RadioButton) findViewById(R.id.p1rb2);
        p2rb1 = (RadioButton) findViewById(R.id.p2rb1);
        p2rb2 = (RadioButton) findViewById(R.id.p2rb2);
        p4rb1 = (RadioButton) findViewById(R.id.p4rb1);
        p4rb2 = (RadioButton) findViewById(R.id.p4rb2);
        p5tb = (ToggleButton) findViewById(R.id.p5tb);
        lv1 = (ListView) findViewById(R.id.lv1);
        progreso = 0;

        encuestas = new ArrayList<>();
        adapterEncuestas = new ArrayAdapter<Encuesta>(this, android.R.layout.simple_list_item_1, encuestas);
        adminEncuestas = new EncuestaSQLiteOpenHelper(this, "encuesta", null,1);
        lv1.setAdapter(adapterEncuestas);

        sbp3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progreso = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                switch (progreso) {
                    case 0:
                        tvsbp3.setText("Muy satisfecho");
                        break;
                    case 1:
                        tvsbp3.setText("Satisfecho");
                        break;
                    case 2:
                        tvsbp3.setText("Ni satisfecho ni insatisfecho");
                        break;
                    case 3:
                        tvsbp3.setText("Insatisfecho");
                        break;
                    case 4:
                        tvsbp3.setText("Muy insatisfecho");
                        break;
                }
            }
        });

        showAll();
    }

    public void p6cbclic(View view){
        if(p6cb.isChecked())
            p6et.setVisibility(View.VISIBLE);
        else
            p6et.setVisibility(View.INVISIBLE);
    }

    public void enviar(View view) {
        if(!p1rb1.isChecked() && !p1rb2.isChecked() ||
           !p2rb1.isChecked() && !p2rb2.isChecked() ||
           !p4rb1.isChecked() && !p4rb2.isChecked())
            Toast.makeText(this, "No has contestado toda la encuesta", Toast.LENGTH_SHORT).show();
        else {
            SQLiteDatabase bd = adminEncuestas.getWritableDatabase();
            ContentValues tupla = new ContentValues();

            if(p1rb1.isChecked())
                tupla.put("pregunta1",true);
            else
                tupla.put("pregunta1",false);
            if(p2rb1.isChecked())
                tupla.put("pregunta2",true);
            else
                tupla.put("pregunta2",false);
            tupla.put("pregunta3", progreso);
            if(p4rb1.isChecked())
                tupla.put("pregunta4",true);
            else
                tupla.put("pregunta4",false);
            tupla.put("pregunta5",p5tb.isChecked());
            tupla.put("pregunta6",p6et.getText().toString());

            bd.insert("respuesta", null, tupla);
            bd.close();

            Toast.makeText(this, "Respuestas enviadas", Toast.LENGTH_SHORT).show();

            p6et.setText("");
            sbp3.setProgress(0);
            showAll();
        }
    }

    public void showAll() {
        SQLiteDatabase bd = adminEncuestas.getReadableDatabase();
        encuestas.clear();

        Cursor c = bd.rawQuery("select * from respuesta;", null);

        while(c.moveToNext()) {
            Encuesta eTemp = new Encuesta();

            eTemp.setPregunta1(c.getInt(c.getColumnIndex("pregunta1"))>0);
            eTemp.setPregunta2(c.getInt(c.getColumnIndex("pregunta2"))>0);
            eTemp.setPregunta3(c.getInt(c.getColumnIndex("pregunta3")));
            eTemp.setPregunta4(c.getInt(c.getColumnIndex("pregunta4"))>0);
            eTemp.setPregunta5(c.getInt(c.getColumnIndex("pregunta5"))>0);
            eTemp.setPregunta6(c.getString(c.getColumnIndex("pregunta6")));

            encuestas.add(eTemp);
        }
        adapterEncuestas.notifyDataSetChanged();
    }
}
