package com.example.navigation_smd_7a;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class DeliveredFragment extends Fragment {

    private ListView lvDeliveredProducts;
    private ArrayList<Product> deliveredProducts;
    private ProductAdapter productAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeliveredFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DeliveredFragment newInstance(String param1, String param2) {
        DeliveredFragment fragment = new DeliveredFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_delivered, container, false);

        lvDeliveredProducts = view.findViewById(R.id.lvDeliveredProducts);
        loadDeliveredProducts();

        return view;
    }

    private void loadDeliveredProducts() {
        ProductDB db = new ProductDB(getContext());
        db.open();

        // Fetch only the delivered products
        deliveredProducts = db.fetchProductsByStatus("Delivered");
        db.close();

        // Set up the adapter
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_design, deliveredProducts);
        lvDeliveredProducts.setAdapter(productAdapter);


        lvDeliveredProducts.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = deliveredProducts.get(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Product Details");

            String productDetails = "Name: " + selectedProduct.getTitle() + "\n" +
                    "Price: " + selectedProduct.getPrice() + "\n" +
                    "Date: " + selectedProduct.getDate() + "\n" +
                    "Status: " + selectedProduct.getStatus() ;
            builder.setMessage(productDetails);

            // Setting positive button to dismiss the dialog
            builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

            builder.create().show();
        });
    }
}