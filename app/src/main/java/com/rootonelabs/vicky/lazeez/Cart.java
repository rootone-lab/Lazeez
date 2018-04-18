 package com.rootonelabs.vicky.lazeez;

import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rootonelabs.vicky.lazeez.Common.Common;
import com.rootonelabs.vicky.lazeez.Database.Database;
import com.rootonelabs.vicky.lazeez.Model.Order;
import com.rootonelabs.vicky.lazeez.Model.Request;
import com.rootonelabs.vicky.lazeez.ViewHolder.CartAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

 public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;
    Button btnPlaceOrder;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");


        //Init
        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView)findViewById(R.id.total);
        btnPlaceOrder = (Button)findViewById(R.id.btnPlaceOrder);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        loadListFood();

    }

     private void showAlertDialog() {
         AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
         alertDialog.setTitle("One More Step!");
         alertDialog.setMessage("Enter your Address: ");

         final EditText edtAddress = new EditText(Cart.this);
         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                 LinearLayout.LayoutParams.MATCH_PARENT,
                 LinearLayout.LayoutParams.MATCH_PARENT
         );
         edtAddress.setLayoutParams(lp);
         alertDialog.setView(edtAddress);
         alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

         alertDialog.setPositiveButton("GO!", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 //Create new Request
                 Request request = new Request(
                         Common.currentUser.getPhone(),
                         Common.currentUser.getName(),
                         edtAddress.getText().toString(),
                         txtTotalPrice.getText().toString(),
                         cart
                 );
                 //Submit to Firebase
                 //We will use System.CurrentMilli as key
                 requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);
                 //Delete Cart
                 new Database(getBaseContext()).cleanCart();
                 Toast.makeText(Cart.this, "Thank You, Order Placed!", Toast.LENGTH_SHORT).show();
             }
         });

         alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 dialogInterface.dismiss();
             }
         });

         alertDialog.show();
     }

     private void loadListFood() {
            cart = new Database(this).getCarts();
            adapter = new CartAdapter(cart,this);
            recyclerView.setAdapter(adapter);

            //Calculate total price

         int total = 0;
          for(Order order:cart)
              total = (Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
         Locale locale = new Locale("en", "US");
         NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

         txtTotalPrice.setText(fmt.format(total));

     }
 }
