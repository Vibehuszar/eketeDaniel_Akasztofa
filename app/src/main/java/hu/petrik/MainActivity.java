package hu.petrik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonCsokkent, buttonNovel, buttonTippel;
    private TextView textViewBetu, textViewSzo;
    private ImageView imageViewAkaszto;
    private int index;
    private String[] abc = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] szavak = {"ALMA", "TELEFON", "PROCESSZOR", "BURGER", "KUTYA", "MAJOM", "SONKA", "MIKROFON", "GEREBLYE", "CIGARETTA"};
    private String gondoltSzo;
    private String alsovonal = "";
    private String tippeltBetu = "";
    private int hibapont;
    private String hibasTippek = "";
    private AlertDialog.Builder builderJatekVege;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        buttonNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if (index > 25){
                    index = 0;


                }
                textViewBetu.setText(abc[index]);
                voltmar();


            }
        });

        buttonCsokkent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                if (index < 0){
                    index = 25;

                }
                textViewBetu.setText(abc[index]);
                voltmar();

            }
        });

        buttonTippel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tippeltBetu += abc[index];
                voltmar();
                if (gondoltSzo.contains(abc[index])){
                    Toast.makeText(MainActivity.this,"Helyes tipp", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < gondoltSzo.length(); i++) {
                        if (String.valueOf(gondoltSzo.charAt(i)).equals(abc[index])){
                            alsovonal = alsovonal.substring(0, i) + abc[index] + alsovonal.substring(i + 1);
                            textViewSzo.setText(alsovonal);
                            if (!alsovonal.contains("_")){
                                builderJatekVege.setTitle("Helyes megfejtés.").create();
                                builderJatekVege.show();
                            }
                        }
                    }
                }
                else if (hibasTippek.contains(abc[index])){
                    Toast.makeText(MainActivity.this,"Már volt tippelve", Toast.LENGTH_SHORT).show();
                }
                else{
                    helytelenTipp();
                }
            }
        });
    }

    private void init(){
        buttonCsokkent = findViewById(R.id.buttonCsokkent);
        buttonCsokkent.setBackgroundResource(R.drawable.buttonbackround);
        buttonNovel = findViewById(R.id.buttonNovel);
        buttonNovel.setBackgroundResource(R.drawable.buttonbackround);
        buttonTippel = findViewById(R.id.buttonTippel);
        textViewBetu = findViewById(R.id.textViewBetu);
        textViewSzo = findViewById(R.id.textViewSzo);
        imageViewAkaszto = findViewById(R.id.imageViewAkaszto);
        index = 0;
        hibapont = 0;
        textViewBetu.setTextColor(Color.BLACK);
        kitoltes();
        builderJatekVege = new AlertDialog.Builder(MainActivity.this);
        builderJatekVege.setCancelable(false)
                .setMessage("Szeretnél még egyet játszani?")
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ujJatek();
                    }
                })
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .create();
    }

    private void voltmar(){
        if (tippeltBetu.contains(abc[index])){
            textViewBetu.setTextColor(Color.RED);
        }
        else{
            textViewBetu.setTextColor(Color.BLACK);
        }
    }

    private void kitoltes(){
        Random rnd = new Random();
        int rndi = rnd.nextInt(9);
        gondoltSzo = szavak[rndi];
        for (int i = 0; i < gondoltSzo.length(); i++) {
            alsovonal += "_";
        }
        textViewSzo.setText(alsovonal);
    }

    private void helytelenTipp(){
        Toast.makeText(MainActivity.this,"Helytelen tipp", Toast.LENGTH_SHORT).show();
        hibapont++;
        hibasTippek += abc[index];
        switch (hibapont){
            case 1: imageViewAkaszto.setImageResource(R.drawable.akasztofa01);
                break;
            case 2: imageViewAkaszto.setImageResource(R.drawable.akasztofa02);
                break;
            case 3: imageViewAkaszto.setImageResource(R.drawable.akasztofa03);
                break;
            case 4: imageViewAkaszto.setImageResource(R.drawable.akasztofa04);
                break;
            case 5: imageViewAkaszto.setImageResource(R.drawable.akasztofa05);
                break;
            case 6: imageViewAkaszto.setImageResource(R.drawable.akasztofa06);
                break;
            case 7: imageViewAkaszto.setImageResource(R.drawable.akasztofa07);
                break;
            case 8: imageViewAkaszto.setImageResource(R.drawable.akasztofa08);
                break;
            case 9: imageViewAkaszto.setImageResource(R.drawable.akasztofa09);
                break;
            case 10: imageViewAkaszto.setImageResource(R.drawable.akasztofa10);
                break;
            case 11: imageViewAkaszto.setImageResource(R.drawable.akasztofa11);
                break;
            case 12: imageViewAkaszto.setImageResource(R.drawable.akasztofa12);
                break;
            case 13: imageViewAkaszto.setImageResource(R.drawable.akasztofa13);
                break;
            default: break;
        }
        if (hibapont == 13){
            builderJatekVege.setTitle("Nem sikerült kitalálni.").create();
            builderJatekVege.show();
        }
    }


    private void ujJatek(){
        index = 0;
        hibapont = 0;
        alsovonal = "";
        tippeltBetu = "";
        hibasTippek = "";
        imageViewAkaszto.setImageResource(R.drawable.akasztofa00);
        textViewBetu.setText(abc[index]);
        textViewBetu.setTextColor(Color.BLACK);
        kitoltes();

    }

}


