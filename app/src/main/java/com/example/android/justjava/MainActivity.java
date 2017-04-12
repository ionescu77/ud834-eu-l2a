/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
    boolean takeAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.take_away:
                if (checked)
                    takeAway = true;
                    break;
            case R.id.drink_here:
                if (checked)
                    takeAway = false;
                    break;
        }
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

        String orderSummary = createOrderSummary(price, hasWhippedCream, hasChoco, takeAway, userName);

        displayMessage(orderSummary);

//        sendEmail("Coffe Order", orderSummary);
    }

    public void sendEmail(String subject, String messageText) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));           // only email app to handle thisraz
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, messageText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the total price
     * @param hasWhippedCream  does the client want Whipped Cream
     * @param hasChoco   does the client want Chocolate topping
     * @param quantity is the number of cups of coffee
     *
     */
    private int calculatePrice(int quantity, boolean hasWhippedCream, boolean hasChoco) {
        int basePrice = 5;
        Log.v("MainActivity", "Initial base price is " + basePrice);

        if (hasWhippedCream) { basePrice += 1; }
        if (hasChoco) { basePrice += 2; }
        Log.v("MainActivity", "The base price is " + basePrice);


       return quantity * basePrice;
    }

    /**
     * Creates summary of order.
     *
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChoco is whether or not the user wants whipped cream topping
     * @param userName is the actual name of the user as introduced in the EditText field
     * @param totalPrice is the total price of the order
     * @return a text for Order Summary
     *
     */
    private String createOrderSummary(int totalPrice, boolean hasWhippedCream, boolean hasChoco, boolean takeAway, String userName) {
        String priceMessage = "Name: " + userName;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChoco;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + totalPrice;
        priceMessage += "\nTake away: " + takeAway;
        priceMessage += "\nThank you!";
        return priceMessage;
    }


    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot order more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
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