package co.mobilemakers.sandwichshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class OrderFormActivity extends ActionBarActivity {

    public static final String LOG_TAG = OrderFormActivity.class.getName();
    private Button mButtonPlaceOrder;
    private RadioButton mRadioButtonWheat;
    private RadioButton mRadioButtonWhite;
    private RadioButton mRadioButtonRye;
    private CheckBox mCheckBoxKetchup;
    private CheckBox mCheckBoxMayonnaise;
    private CheckBox mCheckBoxMustard;
    private CheckBox mCheckBoxPickleRelish;
    private CheckBox mCheckBoxGuacamole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareViewsToPlaceOrder();
        setButtonPlaceOrderEvent();
    }

    private void prepareViewsToPlaceOrder() {
        mButtonPlaceOrder = (Button) findViewById(R.id.button_place_order);
        mRadioButtonWheat = (RadioButton) findViewById(R.id.radio_button_wheat);
        mRadioButtonWhite = (RadioButton) findViewById(R.id.radio_button_white);
        mRadioButtonRye = (RadioButton) findViewById(R.id.radio_button_rye);
        mCheckBoxKetchup = (CheckBox) findViewById(R.id.check_box_ketchup);
        mCheckBoxMustard = (CheckBox) findViewById(R.id.check_box_Mustard);
        mCheckBoxMayonnaise = (CheckBox) findViewById(R.id.check_box_mayonnaise);
        mCheckBoxPickleRelish = (CheckBox) findViewById(R.id.check_box_pickle_relish);
        mCheckBoxGuacamole = (CheckBox) findViewById(R.id.check_box_guacamole);
    }

    private void setButtonPlaceOrderEvent() {
        mButtonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Evento Place Order Confirmed");
                List <String> condiments = new ArrayList<>();
                String bread = "";
                if(mRadioButtonWhite.isChecked()){
                    bread = "White";
                } else if(mRadioButtonWheat.isChecked()) {
                    bread = "Wheat";
                } else if(mRadioButtonRye.isChecked()) {
                    bread = "Rye";
                }

                if(!bread.equals("")) {
                    if(mCheckBoxKetchup.isChecked()) {
                        condiments.add("Ketchup");
                    }
                    if(mCheckBoxMustard.isChecked()) {
                        condiments.add("Mustard");
                    }
                    if(mCheckBoxMayonnaise.isChecked()) {
                        condiments.add("Mayonnaise");
                    }
                    if(mCheckBoxPickleRelish.isChecked()) {
                        condiments.add("Pickle relish");
                    }
                    if(mCheckBoxGuacamole.isChecked()) {
                        condiments.add("Guacamole");
                    }

                    Intent intent = new Intent(OrderFormActivity.this, ConfirmationActivity.class);
                    intent.putExtra("bread", bread);
                    Serializable serializable = (Serializable) condiments;
                    intent.putExtra("condiments", serializable);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "We can not make a Sandwich without bread", Toast.LENGTH_LONG).show();
                }



            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
