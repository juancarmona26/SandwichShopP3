package co.mobilemakers.sandwichshop;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class OrderFormFragment extends Fragment {

    public static final String LOG_TAG = OrderFormFragment.class.getName();
    View view;
    private int numberOfSandwiches = 0;

    public SandwichOrder sandwichOrder = new SandwichOrder();
    private Button mButtonPlaceOrder;
    private RadioButton mRadioButtonWheat;
    private RadioButton mRadioButtonWhite;
    private RadioButton mRadioButtonRye;
    private CheckBox mCheckBoxKetchup;
    private CheckBox mCheckBoxMayonnaise;
    private CheckBox mCheckBoxMustard;
    private CheckBox mCheckBoxPickleRelish;
    private CheckBox mCheckBoxGuacamole;

    final static String ORDER_STATE = "FORM_STATE";

    public static class SandwichOrder implements Parcelable {

        public static final Creator<SandwichOrder> CREATOR = new Creator<SandwichOrder>(){

            @Override
            public SandwichOrder createFromParcel(Parcel source) {
                return new SandwichOrder(source);
            }

            @Override
            public SandwichOrder[] newArray(int size) {
                return new SandwichOrder[size];
            }
        };

        public SandwichOrder() {

        }

        public SandwichOrder(Parcel in){
            in.readList(sandwichOrder, List.class.getClassLoader());
        }

        public List<List<String>> getSandwichOrder() {
            return sandwichOrder;
        }

        public void setSandwichOrder(List<List<String>> sandwichOrder) {
            this.sandwichOrder = sandwichOrder;
        }

        public List<List<String>> sandwichOrder = new ArrayList<>();

        // Parcelling part
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList (sandwichOrder);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main, container, false);
        prepareViewsToPlaceOrder();
        setButtonPlaceOrderEvent();
        if(getArguments() != null ) {
            numberOfSandwiches = getArguments().getInt("numberOfSandwiches");
            if(getArguments().getParcelable("sandwichOrder") != null)
                sandwichOrder = getArguments().getParcelable("sandwichOrder");
        }

        if (savedInstanceState != null) {
            SandwichOrder sandwichOrder = savedInstanceState.getParcelable(ORDER_STATE);
            Log.d(LOG_TAG, "savedInstance");
            if (sandwichOrder != null) {
                if (sandwichOrder.getSandwichOrder() != null ) {
                    this.sandwichOrder = sandwichOrder;
                }
            }
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "OnSaveInstanceStat log");
        outState.putParcelable(ORDER_STATE, sandwichOrder);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Onpause :D");
    }

    private void prepareViewsToPlaceOrder() {
        mButtonPlaceOrder = (Button) view.findViewById(R.id.button_place_order);
        mRadioButtonWheat = (RadioButton) view.findViewById(R.id.radio_button_wheat);
        mRadioButtonWhite = (RadioButton) view.findViewById(R.id.radio_button_white);
        mRadioButtonRye = (RadioButton) view.findViewById(R.id.radio_button_rye);
        mCheckBoxKetchup = (CheckBox) view.findViewById(R.id.check_box_ketchup);
        mCheckBoxMustard = (CheckBox) view.findViewById(R.id.check_box_Mustard);
        mCheckBoxMayonnaise = (CheckBox) view.findViewById(R.id.check_box_mayonnaise);
        mCheckBoxPickleRelish = (CheckBox) view.findViewById(R.id.check_box_pickle_relish);
        mCheckBoxGuacamole = (CheckBox) view.findViewById(R.id.check_box_guacamole);
    }

    private void setButtonPlaceOrderEvent() {
        mButtonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List <String> condiments = new ArrayList<>();
                numberOfSandwiches = numberOfSandwiches -  1;
                String bread ="";
                if(numberOfSandwiches >=1) {
                    bread = getBread(condiments);

                    if(!bread.equals("")) {
                     getCheckedCondiments(condiments);

                        goToAnotherOrder(condiments);
                } else {
                    Toast.makeText(getActivity(), "We can not make a Sandwich without bread", Toast.LENGTH_LONG).show();
                }
                } else {
                    bread = getBread(condiments);

                    if(!bread.equals("")) {
                        getCheckedCondiments(condiments);

                        goToAnotherOrder(condiments);
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), ConfirmationActivity.class);
                        intent.putExtra("parcelableSandwichOrders", sandwichOrder);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void goToAnotherOrder(List<String> condiments) {
        sandwichOrder.getSandwichOrder().add(condiments);

        OrderFormFragment orderFormFragment = new OrderFormFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("numberOfSandwiches",numberOfSandwiches);
        arguments.putParcelable("sandwichOrder", sandwichOrder);
        orderFormFragment.setArguments(arguments);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container_layout, orderFormFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void getCheckedCondiments(List<String> condiments) {
        if (mCheckBoxKetchup.isChecked()) {
            condiments.add("Ketchup");
        }
        if (mCheckBoxMustard.isChecked()) {
            condiments.add("Mustard");
        }
        if (mCheckBoxMayonnaise.isChecked()) {
            condiments.add("Mayonnaise");
        }
        if (mCheckBoxPickleRelish.isChecked()) {
            condiments.add("Pickle relish");
        }
        if (mCheckBoxGuacamole.isChecked()) {
            condiments.add("Guacamole");
        }
    }

    private String getBread(List<String> condiments) {
        String bread = "";
        if(mRadioButtonWhite.isChecked()){
            condiments.add("White");
            bread = "White";
        } else if(mRadioButtonWheat.isChecked()) {
            condiments.add("Wheat");
            bread = "Wheat";
        } else if(mRadioButtonRye.isChecked()) {
            condiments.add("Rye");
            bread = "Rye";
        }
        return bread;
    }

}
