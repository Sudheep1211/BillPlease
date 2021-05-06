package sg.edu.rp.c346.id20024402.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView userAmt;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup paymentMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editAmtInput);
        numPax = findViewById(R.id.editNumInput);
        totalBill = findViewById(R.id.textTotalBill);
        userAmt = findViewById(R.id.textUserAmount);
        svs = findViewById(R.id.toggleButtonSvs);
        gst = findViewById(R.id.toggleButtonGst);
        split = findViewById(R.id.buttonSplit);
        reset = findViewById(R.id.buttonReset);
        discount = findViewById(R.id.editDiscountInput);
        paymentMode = findViewById(R.id.rgPayment);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().trim().length() != 0 && numPax.getText().toString().trim().length() != 0) {
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }

                    if (discount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    String mode = " in cash";
                    if (paymentMode.getCheckedRadioButtonId() == R.id.radioButtonPaynow) {
                        mode = " via PayNow to 912345678";
                    }

                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(numPax.getText().toString());
                    if (numPerson != 1) {
                        userAmt.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + mode);
                    }else {
                        userAmt.setText("Each Pays: $" + newAmt + mode);
                    }


                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
                userAmt.setText("");
                totalBill.setText("");
            }
        });


    }

}