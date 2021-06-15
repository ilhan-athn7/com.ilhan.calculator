package com.ilhan.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText giris_metin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        giris_metin = findViewById(R.id.txt_veri);
        giris_metin.setSelection(giris_metin.length());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            giris_metin.setShowSoftInputOnFocus(false); // Android sürümü lollipop ve üstü ise dokunmatik klavye açılmayacak.
        }
        giris_metin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.metin).equals(giris_metin.getText().toString())){
                    giris_metin.setText("");
                }
            }
        });
    }

    private void metin_duzenle(String metne_ekle){
        if (getString(R.string.metin).equals(giris_metin.getText().toString())){
            giris_metin.setText("");
        }
        String metin=giris_metin.getText().toString();
        int imlec_yeri=giris_metin.getSelectionStart();
        String metin_sol=metin.substring(0,imlec_yeri);
        String metin_sag=metin.substring(imlec_yeri);
        giris_metin.setText(String.format("%s%s%s",metin_sol,metne_ekle,metin_sag));
        giris_metin.setSelection(imlec_yeri + 1);

    }

    private void metin_sil(){
        int imlec_yeri=giris_metin.getSelectionStart();
        int metin_uzunlugu=giris_metin.getText().length();
        if (imlec_yeri != 0 && metin_uzunlugu != 0){
            giris_metin.getText().replace(imlec_yeri - 1 , imlec_yeri, "");
            giris_metin.setSelection(imlec_yeri - 1);
        }
    }

    public void giris_sifir(View view){
        metin_duzenle("0");
    }
    public void giris_bir(View view){
        metin_duzenle("1");
    }
    public void giris_iki(View view){
        metin_duzenle("2");
    }
    public void giris_uc(View view){
        metin_duzenle("3");
    }
    public void giris_dort(View view){
        metin_duzenle("4");
    }
    public void giris_bes(View view){
        metin_duzenle("5");
    }
    public void giris_alti(View view){
        metin_duzenle("6");
    }
    public void giris_yedi(View view){
        metin_duzenle("7");
    }
    public void giris_sekiz(View view){
        metin_duzenle("8");
    }
    public void giris_dokuz(View view){
        metin_duzenle("9");
    }
    public void giris_nokta(View view){
        metin_duzenle(".");
    }
    public void giris_temizle(View view){
        giris_metin.setText(getString(R.string.metin));
        giris_metin.setSelection(giris_metin.length());
    }
    public void giris_parantez(View view){
        int imlec_yeri = giris_metin.getSelectionStart();
        int acik_parantez = 0;
        int kapali_parantez = 0;
        int metin_uzunlugu = giris_metin.length();

        for (int i = 0; i < imlec_yeri; i++) {
            if (giris_metin.getText().toString().substring(i, i+1).equals("(")){
                acik_parantez += 1;
            }
            if (giris_metin.getText().toString().substring(i, i+1).equals(")")){
                kapali_parantez += 1;
            }
        }
        if (acik_parantez == kapali_parantez || giris_metin.getText().toString().substring(metin_uzunlugu-1, metin_uzunlugu).equals("(")){
            metin_duzenle("(");
        }
        else if (kapali_parantez < acik_parantez && !giris_metin.getText().toString().substring(metin_uzunlugu-1, metin_uzunlugu).equals("(")){
            metin_duzenle(")");
        }
        giris_metin.setSelection(imlec_yeri + 1);
    }
    public void giris_ust(View view){
        metin_duzenle("^");
    }
    public void giris_bol(View view){
        metin_duzenle("÷");
    }
    public void giris_carp(View view){
        metin_duzenle("×");
    }
    public void giris_cikar(View view){
        metin_duzenle("-");
    }
    public void giris_topla(View view){
        metin_duzenle("+");
    }
    public void giris_hesapla(View view){
        String islem_girisi = giris_metin.getText().toString();
        islem_girisi = islem_girisi.replaceAll("÷","/");
        islem_girisi = islem_girisi.replaceAll("×","*");
        Expression islem = new Expression(islem_girisi);
        String sonuc = String.valueOf(islem.calculate());
        giris_metin.setText(sonuc);
        giris_metin.setSelection(sonuc.length());
    }
    public void giris_poz_neg(View view){
            giris_metin.setSelection(0);
            metin_duzenle("-");
            giris_metin.setSelection(giris_metin.length());
    }
    public void giris_sil(View view){
        metin_sil();
    }
}