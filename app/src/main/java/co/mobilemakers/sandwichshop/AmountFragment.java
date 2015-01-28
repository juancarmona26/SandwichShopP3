package co.mobilemakers.sandwichshop;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AmountFragment extends Fragment {

    View view;
    private Button mButtonGoAhead;
    private EditText mEditTextAmountOfSandwiches;

    public AmountFragment() {
        // Required empty public constructor
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


            }
        });
    }


}
