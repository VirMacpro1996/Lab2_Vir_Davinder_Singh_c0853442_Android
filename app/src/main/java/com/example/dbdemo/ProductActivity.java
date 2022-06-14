package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.dbdemo.databinding.ActivityProductBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {


    private static final String FILE_KEY = "users" ;
    private ActivityProductBinding binding;

    SQLiteDatabase sqLiteDatabase;
    private SharedPreferences sharedPreferences;
    private List<ProductModel> productModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        Button add1 = findViewById(R.id.btn_add1);

        sharedPreferences = this.getSharedPreferences(this.FILE_KEY, Context.MODE_PRIVATE);

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(
                        ProductActivity.this, MainActivity.class
                ));
            }
        });

        sqLiteDatabase = openOrCreateDatabase("products_db", MODE_PRIVATE, null);
        createTables();
        // load data from db
        if(sharedPreferences.getBoolean("is_first_turn", true)) {
            addDb();
        }
        loadEmployees();


    }


    private void createTables() {

        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                "id INTEGER NOT NULL CONSTRAINT products_pk PRIMARY KEY AUTOINCREMENT , " +
                "name VARCHAR(20) NOT NULL ,  " +
                "description VARCHAR(20) NOT NULL , " +
                "price DOUBLE NOT NULL );";

        sqLiteDatabase.execSQL(sql);
    }


    private void isFirst(boolean data) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_first_turn",data );
        editor.apply();
    }

    private  void addDb()
    {

        String sql1 = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql1,new String[]{"Audi R8" , "The first-generation Audi R8 launched in the U.S. market for the 2008 model year. The R8 is a two-seat sports car that's based on the Audi Le Mans Quattro concept car. The first R8 models came standard with a V8 engine and were available with a V10. The R8's convertible body style, known as the Spyder, was also introduced for 2008. Audi rolled out a redesigned R8 for 2017, following a one-year hiatus. That marked the beginning of the current R8 generation, which includes the 2022 model. This generation features updated exterior styling, a more advanced infotainment system, and a standard V10 engine (the V8 was discontinued)." ,
                "80000"});
        String sql2 = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql2,new String[]{"Jaguar F-type" , "The F-Type name was first used on a pair of completely unrelated concepts as far back as 1982, when Jaguar realised that the XJ-S had grown too large in size and weight to be classed as a proper successor to the E-Type. Then, two new projects, codenamed the XJ41 (coupé) and XJ42 (convertible) got to an advanced state of development by 1988, however the project was cancelled when Ford Motor Company took over Jaguar in 1989, and the newly installed management determined that upgrading the company's dated production facilities was a higher priority. The XJ-S was given a major facelift (being renamed the XJS) instead as a stop gap solution. The XJ41/XJ42 studies ultimately evolved into the Aston Martin DB7 and the Jaguar XK8 which were launched in 1994 and 1996 respectively" ,
                "78000"});
        String sql3 = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql3,new String[]{"jaguar" , "The F-Type name was first used on a pair of completely unrelated concepts as far back as 1982, when Jaguar realised that the XJ-S had grown too large in size and weight to be classed as a proper successor to the E-Type. Then, two new projects, codenamed the XJ41 (coupé) and XJ42 (convertible) got to an advanced state of development by 1988, however the project was cancelled when Ford Motor Company took over Jaguar in 1989, and the newly installed management determined that upgrading the company's dated production facilities was a higher priority. The XJ-S was given a major facelift (being renamed the XJS) instead as a stop gap solution. The XJ41/XJ42 studies ultimately evolved into the Aston Martin DB7 and the Jaguar XK8 which were launched in 1994 and 1996 respectively" ,
                "78787"});
        String sql4 = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql4,new String[]{"Mercedes" , "The F-Type name was first used on a pair of completely unrelated concepts as far back as 1982, when Jaguar realised that the XJ-S had grown too large in size and weight to be classed as a proper successor to the E-Type. Then, two new projects, codenamed the XJ41 (coupé) and XJ42 (convertible) got to an advanced state of development by 1988, however the project was cancelled when Ford Motor Company took over Jaguar in 1989, and the newly installed management determined that upgrading the company's dated production facilities was a higher priority. The XJ-S was given a major facelift (being renamed the XJS) instead as a stop gap solution. The XJ41/XJ42 studies ultimately evolved into the Aston Martin DB7 and the Jaguar XK8 which were launched in 1994 and 1996 respectively" ,
                "45000"});
        String sql5 = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql5,new String[]{"Land Rover" , "The F-Type name was first used on a pair of completely unrelated concepts as far back as 1982, when Jaguar realised that the XJ-S had grown too large in size and weight to be classed as a proper successor to the E-Type. Then, two new projects, codenamed the XJ41 (coupé) and XJ42 (convertible) got to an advanced state of development by 1988, however the project was cancelled when Ford Motor Company took over Jaguar in 1989, and the newly installed management determined that upgrading the company's dated production facilities was a higher priority. The XJ-S was given a major facelift (being renamed the XJS) instead as a stop gap solution. The XJ41/XJ42 studies ultimately evolved into the Aston Martin DB7 and the Jaguar XK8 which were launched in 1994 and 1996 respectively" ,
                "123999"});
        String sql6 = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql6,new String[]{"Audi R8" , "The first-generation Audi R8 launched in the U.S. market for the 2008 model year. The R8 is a two-seat sports car that's based on the Audi Le Mans Quattro concept car. The first R8 models came standard with a V8 engine and were available with a V10. The R8's convertible body style, known as the Spyder, was also introduced for 2008. Audi rolled out a redesigned R8 for 2017, following a one-year hiatus. That marked the beginning of the current R8 generation, which includes the 2022 model. This generation features updated exterior styling, a more advanced infotainment system, and a standard V10 engine (the V8 was discontinued)." ,
                "80000"});
        String sql7 = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql7,new String[]{"Jaguar F-type" , "The F-Type name was first used on a pair of completely unrelated concepts as far back as 1982, when Jaguar realised that the XJ-S had grown too large in size and weight to be classed as a proper successor to the E-Type. Then, two new projects, codenamed the XJ41 (coupé) and XJ42 (convertible) got to an advanced state of development by 1988, however the project was cancelled when Ford Motor Company took over Jaguar in 1989, and the newly installed management determined that upgrading the company's dated production facilities was a higher priority. The XJ-S was given a major facelift (being renamed the XJS) instead as a stop gap solution. The XJ41/XJ42 studies ultimately evolved into the Aston Martin DB7 and the Jaguar XK8 which were launched in 1994 and 1996 respectively" ,
                "78000"});
        String sql8 = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql8,new String[]{"jaguar" , "The F-Type name was first used on a pair of completely unrelated concepts as far back as 1982, when Jaguar realised that the XJ-S had grown too large in size and weight to be classed as a proper successor to the E-Type. Then, two new projects, codenamed the XJ41 (coupé) and XJ42 (convertible) got to an advanced state of development by 1988, however the project was cancelled when Ford Motor Company took over Jaguar in 1989, and the newly installed management determined that upgrading the company's dated production facilities was a higher priority. The XJ-S was given a major facelift (being renamed the XJS) instead as a stop gap solution. The XJ41/XJ42 studies ultimately evolved into the Aston Martin DB7 and the Jaguar XK8 which were launched in 1994 and 1996 respectively" ,
                "78787"});
        String sql9 = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql9,new String[]{"Mercedes" , "The F-Type name was first used on a pair of completely unrelated concepts as far back as 1982, when Jaguar realised that the XJ-S had grown too large in size and weight to be classed as a proper successor to the E-Type. Then, two new projects, codenamed the XJ41 (coupé) and XJ42 (convertible) got to an advanced state of development by 1988, however the project was cancelled when Ford Motor Company took over Jaguar in 1989, and the newly installed management determined that upgrading the company's dated production facilities was a higher priority. The XJ-S was given a major facelift (being renamed the XJS) instead as a stop gap solution. The XJ41/XJ42 studies ultimately evolved into the Aston Martin DB7 and the Jaguar XK8 which were launched in 1994 and 1996 respectively" ,
                "45000"});
        String sql10 = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql10,new String[]{"Land Rover" , "The F-Type name was first used on a pair of completely unrelated concepts as far back as 1982, when Jaguar realised that the XJ-S had grown too large in size and weight to be classed as a proper successor to the E-Type. Then, two new projects, codenamed the XJ41 (coupé) and XJ42 (convertible) got to an advanced state of development by 1988, however the project was cancelled when Ford Motor Company took over Jaguar in 1989, and the newly installed management determined that upgrading the company's dated production facilities was a higher priority. The XJ-S was given a major facelift (being renamed the XJS) instead as a stop gap solution. The XJ41/XJ42 studies ultimately evolved into the Aston Martin DB7 and the Jaguar XK8 which were launched in 1994 and 1996 respectively" ,
                "123999"});
         isFirst(false);
    }
    private void loadEmployees() {
        productModelList = new ArrayList<>();
        /// read data from db

        String sql = "SELECT *  FROM products";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()) {
            do {
                // fetch info from columns
                productModelList.add(
                        new ProductModel(
                        cursor.getInt(0) ,
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3)

                )
                );

            }while(cursor.moveToNext());
            cursor.close();
        }
        ProductAdapter productAdapter = new ProductAdapter(
                this,
                R.layout.list_layout_product,
                productModelList,
                sqLiteDatabase
        );
        binding.lvEmployees.setAdapter(productAdapter);
    }
}