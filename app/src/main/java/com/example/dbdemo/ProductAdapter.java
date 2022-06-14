package com.example.dbdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductAdapter extends ArrayAdapter {

    Context context;
    int layoutRes;
    List<ProductModel> productModelList;
    SQLiteDatabase sqLiteDatabase;

    public ProductAdapter(@NonNull Context context, int resource,
                          @NonNull List<ProductModel> productModelList
                                , SQLiteDatabase sqLiteDatabase) {
        super(context, resource, productModelList);
        this.context = context;
        this.productModelList = productModelList;
        layoutRes = resource;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public int getCount() {
        return productModelList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null ) v = inflater.inflate(layoutRes , null);
        TextView nameTv = v.findViewById(R.id.row_name);
        TextView descriptionTv = v.findViewById(R.id.row_description);
        TextView priceTv = v.findViewById(R.id.row_price);
        //TextView joiningDateTv = v.findViewById(R.id.row_joining_date);

        ProductModel productModel =  productModelList.get(position);
        nameTv.setText(productModel.getName());
        descriptionTv.setText(productModel.getDescription());
        priceTv.setText("CAD $" + String.valueOf(productModel.getPrice()));
       // joiningDateTv.setText(productModel.getDescription());

        // update btn
    v.findViewById(R.id.btn_update).setOnClickListener(view -> {

        updateEmployee(productModel);
    });
        // delete btn
        v.findViewById(R.id.btn_delete).setOnClickListener(view -> {

            deleteEmployee(productModel);
        });
        return v;
    }

    private void updateEmployee(ProductModel productModel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

       LayoutInflater inflater = LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.dialog_update_product,null);
       builder.setView(view);

       AlertDialog dialog = builder.create();

       dialog.show();


        EditText nameEt = view.findViewById(R.id.et_name);
        EditText priceEt = view.findViewById(R.id.et_price);
        EditText descriptionEt = view.findViewById(R.id.et_description);

       // String[] departmentArray = context.getResources().getStringArray(R.array.department);

       // int position = Arrays.asList(departmentArray).indexOf(productModel.getDepartment());

        nameEt.setText(productModel.getName());
        priceEt.setText(String.valueOf(productModel.getPrice()));
        descriptionEt.setText(productModel.getDescription());
       // departmentSpinner.setSelection(position);

        view.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameEt.getText().toString().trim();
                String price = priceEt.getText().toString().trim();
                String description  = descriptionEt.getText().toString().trim();
              //  String department = departmentSpinner.getSelectedItem().toString();


                if (name.isEmpty())
                {
                    nameEt.setError("name field is empty");
                    nameEt.requestFocus();
                    return ;
                }

                if (price.isEmpty())
                {
                    priceEt.setError("salary field is empty");
                    priceEt.requestFocus();
                    return ;
                }

                String sql = "UPDATE products SET name = ? , description = ? , price = ? WHERE id = ? ";
                sqLiteDatabase.execSQL(sql ,
                        new String[]{
                                name,
                                description,
                                price,
                                String.valueOf(productModel.getId())
                        });

                loadEmployees();
                dialog.dismiss();
            }

        });

    }
    private void  loadEmployees(){

        String sql = "SELECT *  FROM products";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        productModelList.clear();

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
        notifyDataSetChanged();

    }

    private void deleteEmployee(ProductModel productModel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

              String sql = "DELETE FROM products WHERE id = ?";
              sqLiteDatabase.execSQL(sql , new Integer[]{productModel.getId()});
              // load employees
                       loadEmployees();
                    }
                });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "The Product (" + productModel.getName() + ") is not Deleted"
                        , Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();

    }
}
