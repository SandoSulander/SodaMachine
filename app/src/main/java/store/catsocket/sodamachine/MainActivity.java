package store.catsocket.sodamachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private EditText inputNumber;
    private TextView textView, moneyView, insertMoneyView;
    private SeekBar insertMoneyBar;
    private ProgressBar progressBar;
    private Spinner itemSpinner;
    private Spinner sizeSpinner;
    private int receipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputNumber = (EditText) findViewById(R.id.inputNumber);

        textView = (TextView) findViewById(R.id.textView);
        moneyView = (TextView) findViewById(R.id.moneyView);
        insertMoneyView = (TextView) findViewById(R.id.insertMoneyView);

        insertMoneyBar = (SeekBar) findViewById(R.id.insertMoneyBar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        itemSpinner = (Spinner) findViewById(R.id.itemSpinner);
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);

        String[] arrayItems = new String[]{"Pepsi", "Pepsi Max", "Coca-Cola", "Coca-Cola Zero", "Fanta", "Fanta Zero"};
        String[] arraySizes = new String[]{"0.33", "0.5", "1.5"};

        ArrayAdapter aa1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayItems);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(aa1);

        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arraySizes);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(aa2);

        insertMoneyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double moneyProgress = (double) progress / 10.0;
                String stringMoneyProgress = Double.toString(moneyProgress);
                progressBar.setProgress((int) moneyProgress);
                insertMoneyView.setText(stringMoneyProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });
    }


    BottleDispenser bd = BottleDispenser.getInstance();
    ArrayList<Bottle> alist = bd.alist;

    public void coinButton(View v) {

        String addedMoney = insertMoneyView.getText().toString();
        Double doubleAddedMoney = Double.parseDouble(addedMoney);
        bd.addMoney(doubleAddedMoney);
        double getMoney = Math.round(bd.getMoney()*100.0)/100.0;
        String money = Double.toString(getMoney);
        moneyView.setText(money + "€");
        insertMoneyBar.setProgress(0);
        Toast.makeText(this, bd.getProductMessageText(), Toast.LENGTH_SHORT).show();
    }

    public void buyButtonInput(View v) {
        String input = "";
        input = inputNumber.getText().toString();
        String isMoney = moneyView.getText().toString();
        if (!isMoney.equals("0.0 €") && !input.equals("")) {
            int finalInput = Integer.parseInt(input);
            bd.buyBottleInput(finalInput);
            if (!bd.getProductMessageText().equals("Insert money first!")){
                String money = Double.toString(bd.getMoney());
                moneyView.setText(money + "€");
                bd.saveReceipt(this);
                Toast.makeText(this, bd.getProductMessageText(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, bd.getProductMessageText(), Toast.LENGTH_SHORT).show();
            }
        } else if (input.equals("")){
            Toast.makeText(this, "Empty fields!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Insert money first!", Toast.LENGTH_SHORT).show();
        }
        bd.productList();
        textView.setText(bd.getProductMessageText());

    }

    public void buyButtonSelect(View v){
        String itemName = itemSpinner.getSelectedItem().toString();
        String itemSize = sizeSpinner.getSelectedItem().toString();
        float floatItemSize = Float.parseFloat(itemSize);

        for (int i = 0; i < alist.size(); i++) {
            if (itemName == alist.get(i).getName() && floatItemSize == alist.get(i).getSize()){
                int index = i;
                bd.buyBottleSelect(index);
                String money = Double.toString(bd.getMoney());
                moneyView.setText(money + "€");
                bd.saveReceipt(this);
                Toast.makeText(this, bd.getProductMessageText(), Toast.LENGTH_SHORT).show();
                bd.productList();
                textView.setText(bd.getProductMessageText());
                break;
            } else{
                Toast.makeText(this, "No product available!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void returnButton(View v) {
        bd.returnMoney();
        String money = Double.toString(bd.getMoney());
        moneyView.setText(money + "€");
        Toast.makeText(this, bd.getProductMessageText(), Toast.LENGTH_SHORT).show();
    }

    public void listButton(View v) {
            bd.productList();
            textView.setText(bd.getProductMessageText());
        }


    public void quitButton(View v) {
        finish();
        System.exit(0);
    }

}
