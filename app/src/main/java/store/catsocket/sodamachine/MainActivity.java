package store.catsocket.sodamachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private EditText inputNumber;
    private TextView textView, moneyView, insertMoneyView;
    private SeekBar insertMoneyBar;
    private ProgressBar progressBar;

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

    public void coinButton(View v){

        String addedMoney = insertMoneyView.getText().toString();
        Double doubleAddedMoney = Double.parseDouble(addedMoney);
        bd.addMoney(doubleAddedMoney);
        String money = Double.toString(bd.getMoney());
        moneyView.setText(money+"€");
        Toast.makeText(this, bd.getProductMessageText(), Toast.LENGTH_SHORT).show();
    }

    public void buyButton(View v){
        String input = inputNumber.getText().toString();
        if (!input.equals("")){
            int finalInput = Integer.parseInt(input);
            bd.buyBottle(finalInput);
            String money = Double.toString(bd.getMoney());
            moneyView.setText(money+"€");
            Toast.makeText(this, bd.getProductMessageText(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Empty fields!", Toast.LENGTH_SHORT).show();
        }
        String line = "";
        int number = 0;

        for(int i = 0; i < alist.size(); i++) {

            number = i + 1;

            line = line + "\n" + number + ". Name: " + alist.get(i).getName() + " Size: " + alist.get(i).getSize() + "l " + "Price: " + alist.get(i).getPrice() + "€";
            textView.setText(line);
        }

    }


    public void returnButton(View v){
        bd.returnMoney();
        String money = Double.toString(bd.getMoney());
        moneyView.setText(money+"€");
        Toast.makeText(this, bd.getProductMessageText(), Toast.LENGTH_SHORT).show();
    }

    public void listButton(View v){


        String line = "";
        int number = 0;

        for(int i = 0; i < alist.size(); i++) {

            number = i + 1;

            line = line + "\n" + number + ". Name: " + alist.get(i).getName() + " Size: " + alist.get(i).getSize() + "l " + "Price: " + alist.get(i).getPrice()+"€";
            textView.setText(line);
        }

    }

    public void quitButton(View v){
        finish();
        System.exit(0);
    }


}
