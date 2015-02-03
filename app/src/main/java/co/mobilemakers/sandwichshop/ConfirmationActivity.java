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

    View view;
    public static final String LOG_TAG = ConfirmationActivity.class.getName();
    private String mBread;
//    private List<String> mSelectedCondiments;
    private TextView mTextViewSandwichOrder;
    private TextView mTextViewCondimentsSelected;
    private Button mButtonOk;
    private Button mButtonCancel;
    private OrderFormFragment.SandwichOrder sandwichOrder;
    private List<List<String>> sandwichOrders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Log.d(LOG_TAG, "On create Confirmation");
//        mBread = getIntent().getStringExtra("bread");
//        mSelectedCondiments = (List<String>) getArguments().getSerializable("condiments");
        sandwichOrder = getIntent().getExtras().getParcelable("parcelableSandwichOrders");

        sandwichOrders = sandwichOrder.getSandwichOrder();
        StringBuffer orderToDisplay = new StringBuffer();

        for(int i =0; i <sandwichOrders.size(); i ++){
            orderToDisplay.append("Sandwich ").append(i+1).append(": ");
            for (int j= 0; j < sandwichOrders.get(i).size(); j++) {
                orderToDisplay.append(sandwichOrders.get(i).get(j) + " ° ");
            }
            orderToDisplay.append(" \n\n");
        }
        prepareViewsToShowConfirmation();
        setValuesToTextsViews(orderToDisplay);
        setButtonConfirmationEvents();
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
        Log.d("Prueba", "En activiti saving instance");

    }
}
