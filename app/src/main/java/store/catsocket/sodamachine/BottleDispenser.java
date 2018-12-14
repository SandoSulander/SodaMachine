package store.catsocket.sodamachine;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BottleDispenser {

    private static BottleDispenser bd = new BottleDispenser();

    private double money;
    private double price;
    private int input;

    public boolean more;

    public ArrayList<Bottle> alist;

    private String productMessage;
    private String receiptText;

    public static BottleDispenser getInstance(){
        return bd;
    }

    public void setProductMessageText(String s){
        productMessage = s;
    }

    public double getMoney(){
        return money;
    }

    public String getReceiptText(){
        return receiptText;
    }
    public String getProductMessageText(){
        return productMessage;
    }

    private BottleDispenser() {

        alist = new ArrayList<>();
        money = 0;
        more = true;
        input = 0;


        Bottle bottle1 = new Bottle();
        bottle1.getName();
        bottle1.getPrice();
        bottle1.getSize();

        Bottle bottle2 = new Bottle();
        bottle2.getName();
        bottle2.setPrice(2.2);
        bottle2.setSize(1.5f);

        Bottle bottle3 = new Bottle();
        bottle3.setName("Coca-Cola Zero");
        bottle3.setPrice(2.0);
        bottle3.getSize();

        Bottle bottle4 = new Bottle();
        bottle4.setName("Coca-Cola Zero");
        bottle4.setPrice(2.5);
        bottle4.setSize(1.5f);

        Bottle bottle5 = new Bottle();
        bottle5.setName("Fanta Zero");
        bottle5.setPrice(1.95);
        bottle5.getSize();

        Bottle bottle6 = new Bottle();
        bottle6.setName("Fanta Zero");
        bottle6.setPrice(1.95);
        bottle6.getSize();

        alist.add(bottle1);
        alist.add(bottle2);
        alist.add(bottle3);
        alist.add(bottle4);
        alist.add(bottle5);
        alist.add(bottle6);

    }


    public void addMoney(double addedMoney) {
        if (addedMoney == 0.0){
            productMessage = "No money added!";
        }else {
            money += addedMoney;
            productMessage = "Clinck! Money added to the machine!";
        }
    }

    public void buyBottleInput(int input) {

        more = true;
        while (more) {
            if (alist.isEmpty()) {
                productMessage = "No products available!";
                more = false;
            } else {
                if (input > alist.size() || input < 1) {
                    productMessage = "Wrong input!";
                    more = false;
                } else {
                    price = alist.get(input - 1).getPrice();
                    if (money >= price) {
                        money -= alist.get(input - 1).getPrice();
                        money = Math.round(money *100.0)/100.0;
                        productMessage = "CACHUNCK! " + alist.get(input - 1).getName() + " dropped from the machine!";
                        receiptText = "You have purchased "+ alist.get(input - 1).getName()+ " for " + alist.get(input - 1).getPrice()+"€ \n"+"Thank you!";
                        alist.remove(input - 1);
                        more = false;
                    } else if (money < price){
                        productMessage = "Insert money first!";
                        more = false;
                    }
                }
                more = false;
            }
        }
    }

    public void buyBottleSelect(int index){
        price = alist.get(index).getPrice();
        if (money >= price){
            money -= alist.get(index).getPrice();
            money = Math.round(money *100.0)/100.0;
            productMessage = "CACHUNCK! " + alist.get(index).getName() + " dropped from the machine!";
            receiptText = "You have purchased "+ alist.get(index).getName()+ " for " + alist.get(index).getPrice()+"€ \n"+"Thank you!";
            alist.remove(index);
        } else {
            productMessage = "Insert money first!";
        }
    }

    public void returnMoney() {
        String formattedMoney = String.format("%.02f", money);
        productMessage = "Clinck clinck. " + formattedMoney +"€ came out!";
        money -= money;
    }

    public void productList(){
        String line = "";
        int number = 0;

        for (int i = 0; i < alist.size(); i++) {

            number = i + 1;

            line = line + "\n" + number + ". Name: " + alist.get(i).getName() + " Size: " + alist.get(i).getSize() + "l " + "Price: " + alist.get(i).getPrice() + "€";
        }

        productMessage = line;
    }

    public void saveReceipt(Context ctx) {

        String fileNameTxt = "Receipt.txt";
        String newReceiptText = getReceiptText();
        FileOutputStream fos = null;
        try {
            fos = ctx.openFileOutput(fileNameTxt, ctx.MODE_PRIVATE);
            fos.write(newReceiptText.getBytes());
            Toast.makeText(ctx, "Saved to " + ctx.getFilesDir() + "/" + fileNameTxt,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(ctx, "File not found!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ctx, "Error saving!", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ctx, "Error saving!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
