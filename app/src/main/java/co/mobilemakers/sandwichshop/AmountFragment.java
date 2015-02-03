package co.mobilemakers.sandwichshop;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AmountFragment extends Fragment {

    View view;
    private Button mButtonGoAhead;
    private EditText mEditTextAmountOfSandwiches;

    public AmountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.amount_of_sandwich, container,false);
        prepareViews();
        setButtonEvent();

        return view;
    }

    private void prepareViews(){
        mButtonGoAhead = (Button)  view.findViewById(R.id.button_go_ahead);
        mEditTextAmountOfSandwiches = (EditText) view.findViewById(R.id.edit_text_amount_of_sandwiches);
    }

    private void setButtonEvent(){
        mButtonGoAhead.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int numberOfSandwiches;
                if(!TextUtils.isEmpty(mEditTextAmountOfSandwiches.getText())) {
                    numberOfSandwiches = Integer.parseInt(mEditTextAmountOfSandwiches.getText().toString());
                    if(numberOfSandwiches >= 1 && numberOfSandwiches  <= 5) {
                        OrderFormFragment orderFormFragment = new OrderFormFragment();
                        Bundle arguments = new Bundle();
                        arguments.putInt("numberOfSandwiches",numberOfSandwiches);
                        orderFormFragment.setArguments(arguments);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.main_container_layout, orderFormFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    } else {
                        Toast.makeText(getActivity(), "Enter until five Sandwiches", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please Select number of Sandwiches, (Until Five)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
