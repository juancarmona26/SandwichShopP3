package co.mobilemakers.sandwichshop;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ConfirmationActivity extends ActionBarActivity {

    public static final String LOG_TAG = ConfirmationActivity.class.getName();
    private TextView mTextViewSandwichOrder;
    private Button mButtonOk;
    private Button mButtonCancel;
    private OrderFormFragment.SandwichOrder sandwichOrder;
    private List<List<String>> sandwichOrders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Log.d(LOG_TAG, "On create Confirmation");
        sandwichOrder = getIntent().getExtras().getParcelable("parcelableSandwichOrders");
        sandwichOrders = sandwichOrder.getSandwichOrder();
        StringBuffer orderToDisplay = new StringBuffer();
        organizeSandwichOrderStrings(orderToDisplay);
        prepareViewsToShowConfirmation();
        setValuesToTextsViews(orderToDisplay);
        setButtonConfirmationEvents();
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_green);
//
//        setSupportActionBar(toolbar);
    }

    private void organizeSandwichOrderStrings(StringBuffer orderToDisplay) {
        for(int i =0; i <sandwichOrders.size(); i ++){
            orderToDisplay.append("Sandwich ").append(i+1).append(": ");
            for (int j= 0; j < sandwichOrders.get(i).size(); j++) {
                orderToDisplay.append(sandwichOrders.get(i).get(j) + " ° ");
            }
            orderToDisplay.append(" \n\n");
        }
    }

    private void prepareViewsToShowConfirmation(){
        mTextViewSandwichOrder = (TextView) findViewById(R.id.text_view_orders);
        mButtonOk = (Button) findViewById(R.id.button_ok);
        mButtonCancel = (Button) findViewById(R.id.button_cancel);
    }

    private void setValuesToTextsViews(StringBuffer orderToDisplay) {
        mTextViewSandwichOrder.setText(orderToDisplay);
    }

    private void setButtonConfirmationEvents() {
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your Order has been Placed", Toast.LENGTH_LONG).show();

            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your order has been canceled", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState);
    }
}
