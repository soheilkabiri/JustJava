

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This Method Check Status of WhippedCreamCheckBox.
     *
     * @return boolean status of WhippedCreamCheck
     */
    public boolean whippedCreamCheck() {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_check_box);
        return whippedCreamCheckBox.isChecked();
    }

    public boolean chocolateCheck() {
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_check_box);
        return chocolateCheckBox.isChecked();
    }
    public String nameMining(){
        EditText editText = findViewById(R.id.name_edit_text);
        return editText.getText().toString();
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        createOrderSummary(number);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int num) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }
    public int priceCalculate(){
        int price =5;
        if(whippedCreamCheck()){
            price+=1;
        }
        if(chocolateCheck()){
            price+=2;
        }
        return price*number;

    }

    /**
     * This method displays summary of orders
     *
     * @param number of the
     */
    private void createOrderSummary(int number) {
        int price =priceCalculate();
        boolean whippedCream = whippedCreamCheck();
        boolean hasChocolate = chocolateCheck();
        String name=nameMining();
       String emailContent= "Name: "+name+ "\nAdd whipped Cream? " + whippedCream + "\nAdd chocolate?" + hasChocolate + "\nQuantity: " + number + "\nTotal: $" + price + "\n Thank you!";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "garichi@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order");
        intent.putExtra(Intent.EXTRA_TEXT, emailContent);
        startActivity(Intent.createChooser(intent, "Send Email"));
    }


    public void increment(View view) {
        if(number==100){
            Toast.makeText(this,"Sorry We Cant Help you With this order!",Toast.LENGTH_LONG).show();
            return;
        }
        else {
            number++;
            display(number);
        }
    }

    public void decrement(View view) {
        if( number==1){
            Toast.makeText(this,"Sorry We Cant Help you With this order!",Toast.LENGTH_LONG).show();
            return;
        }
        else {
            number--;
            display(number);
        }
    }
}