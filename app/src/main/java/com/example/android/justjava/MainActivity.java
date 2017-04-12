/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.android.justjava.R.id.checkChoco;
import static com.example.android.justjava.R.id.checkWhippedCream;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This is GLOBAL Variable declaration.
     */
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        String userName =((EditText) findViewById(R.id.userNameField)).getText().toString();
        Log.v("MainActivity", "Username is " + userName);

        boolean hasWhippedCream = ((CheckBox) findViewById(checkWhippedCream)).isChecked();
//        Log.v("MainActivity", "Whipped cream  " + hasWhippedCream);

        boolean hasChoco = ((CheckBox) findViewById(checkChoco)).isChecked();
//        Log.v("MainActivity", "Chocolate please  " + hasChoco);

        int price = calculatePrice(quantity, hasWhippedCream, hasChoco);
//        Log.v("MainActivity", "The price is " + price);

        String orderSummary = createOrderSummary(price, hasWhippedCream, hasChoco, userName);
        displayMessage(orderSummary);
    }


    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the total price
     * @itemPrice is the price of one cup of coffee
     * @quantity is the number of cups of coffee
     *
     */
    private int calculatePrice(int quantity, boolean hasWhippedCream, boolean hasChoco) {
        int basePrice = 5;
        Log.v("MainActivity", "Initial base price is " + basePrice);

        if (hasWhippedCream) { basePrice += 1; };
        if (hasChoco) { basePrice += 2; };
        Log.v("MainActivity", "The base price is " + basePrice);


        int price = quantity * basePrice;
        return price;
    }

    /**
     * Creates summary of order.
     *
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChoco is whether or not the user wants whipped cream topping
     * @param userName is the actual name of the user as introduced in the EditText field
     * @totalPrice is the total price of the order
     * @return a text for Order Summary
     *
     */
    private String createOrderSummary(int totalPrice, boolean hasWhippedCream, boolean hasChoco, String userName) {
        String priceMessage = "Name: " + userName;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChoco;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + totalPrice;
        priceMessage += "\nThank you!";
        return priceMessage;
    }


    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(
                R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}