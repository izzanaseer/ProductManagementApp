package com.example.navigation_smd_7a;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    Context context;
    int resource;
    public ProductAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null)
        {
            v = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView tvTitle = v.findViewById(R.id.tvProductTitle);
        ImageView ivEdit = v.findViewById(R.id.ivEdit);
        ImageView ivDelete = v.findViewById(R.id.ivDelete);

        Product p = getItem(position);
        tvTitle.setText(p.getTitle()+" : "+p.getPrice());

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder editDialog = new AlertDialog.Builder(context);
                editDialog.setTitle("Edit Record");

                View editView = LayoutInflater.from(context).inflate(R.layout.add_new_product_dialog_design, null, false);
                editDialog.setView(editView);

                EditText etTitle = editView.findViewById(R.id.etTitle);
                EditText etDate = editView.findViewById(R.id.etDate);
                EditText etPrice = editView.findViewById(R.id.etPrice);
                EditText etStatus = editView.findViewById(R.id.etStatus);

                etTitle.setText(p.getTitle());
                etDate.setText(p.getDate());
                etPrice.setText(String.valueOf(p.getPrice()));
                etStatus.setText(p.getStatus());


                editDialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = etTitle.getText().toString().trim();
                        String date = etDate.getText().toString().trim();
                        String price = etPrice.getText().toString().trim();
                        String status = etStatus.getText().toString().trim();

                        if (title.isEmpty() || date.isEmpty() || price.isEmpty() || status.isEmpty()){
                            Toast.makeText(context, "Something is Missing", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            ProductDB db = new ProductDB(context);
                            db.open();
                            db.updateProduct(p.getId(), title, date, Integer.parseInt(price), status);
                            db.close();

                            // Update the product object in the adapter list
                            p.setTitle(title);
                            p.setDate(date);
                            p.setPrice(Integer.parseInt(price));
                            p.setStatus(status);

                            notifyDataSetChanged();

                            Toast.makeText(context, "Record Updated", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

               editDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                   }
               });

                editDialog.show();
            }
        });




        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDB db = new ProductDB(context);
                db.open();
                db.remove(p.getId());
                db.close();
                remove(p);
                notifyDataSetChanged();
            }
        });

        return v;
    }
}
