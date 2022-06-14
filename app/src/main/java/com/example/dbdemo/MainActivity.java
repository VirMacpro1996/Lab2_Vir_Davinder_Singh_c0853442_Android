package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.dbdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{


    private static final String DATABASE_NAME = "products_db";
    private ActivityMainBinding binding;
    // SQlite DB

    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(this);
        binding.tvDisplay.setOnClickListener(this);

        // inilialize the sqlite DB
        sqLiteDatabase  = openOrCreateDatabase(DATABASE_NAME , MODE_PRIVATE, null );

        // create - table in sqlite db

        createTables();






    }


    private void createTables() {

        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                "id INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT , " +
                "name VARCHAR(20) NOT NULL ,  " +
                "description VARCHAR(20) NOT NULL , " +
                "price DOUBLE NOT NULL );";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_add :
                addEmployee();
               // Log.d("MainActivity","onclick : add - ");
                break;

            case R.id.tv_display :
                startActivity(new Intent(
                        MainActivity.this , ProductActivity.class
                ));
              //  Log.d("MainActivity" , " onClick : display - ");
                break;
        }
    }

    private void addEmployee() {

        String name = binding.etName.getText().toString().trim();
        String price = binding.etPrice.getText().toString().trim();
        String description = binding.etDescription.getText().toString().trim();


        if (name.isEmpty())
        {
            binding.etName.setError("Name field is empty");
            binding.etName.requestFocus();
            return ;
        }

        if (price.isEmpty())
        {
            binding.etPrice.setError("Price field is empty");
            binding.etPrice.requestFocus();
            return ;
        }
        if (description.isEmpty())
        {
            binding.etDescription.setError("Description field is empty");
            binding.etDescription.requestFocus();
            return ;
        }

        // insert into db
        String sql = "INSERT INTO products ( name, description  , price )" +
                "VALUES (?,?,?)" ;
        sqLiteDatabase.execSQL(sql,new String[]{name , description  , price});

        clearFields();

    }


    private void clearFields(){
        binding.etName.setText("");
        binding.etPrice.setText("");
        binding.etDescription.setText("");
        binding.etName.clearFocus();
        binding.etPrice.clearFocus();
        binding.etDescription.clearFocus();

    }
}