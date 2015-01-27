package co.mobilemakers.sandwichshop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ConfirmationActivity extends ActionBarActivity {

    private String mBread;
    private List<String> mSelectedCondiments;
    private TextView mTextViewBreadSelected;
    private TextView mTextViewCondimentsSelected;
    private Button mButtonOk;
    private Button mButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        mBread = getIntent().getStringExtra("bread");
        mSelectedCondiments = (List<String>) getIntent().getSerializableExtra("condiments");

        prepareViewsToShowConfirmation();
        setValuesToTextsViews();
        setButtonConfirmationEvents();


    }

    private void prepareViewsToShowConfirmation(){
        mTextViewBreadSelected = (TextView) findViewById(R.id.text_view_bread_selected);
        mTextViewCondimentsSelected = (TextView) findViewById(R.id.text__view_condiments_selected);
        mButtonOk = (Button) findViewById(R.id.button_ok);
        mButtonCancel = (Button) findViewById(R.id.button_cancel);
    }

    private void setValuesToTextsViews() {
        StringBuffer condimentsTextToShow = new StringBuffer();
        mTextViewBreadSelected.setText(mBread);

        if(mSelectedCondiments.size() != 0 ) {
            for (int i = 0; i < mSelectedCondiments.size(); i++){
                condimentsTextToShow.append("- ").append(mSelectedCondiments.get(i)).append("\n");
            }
            mTextViewCondimentsSelected.setText(condimentsTextToShow);
        } else {
            mTextViewCondimentsSelected.setText("No condiments selected");
        }
    }

    private void setButtonConfirmationEvents() {
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your Order has been Placed", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your order has been canceled", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirmation, menu);
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
