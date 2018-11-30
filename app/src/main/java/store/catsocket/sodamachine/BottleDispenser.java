package store.catsocket.sodamachine;

import java.util.ArrayList;

public class BottleDispenser {

    private static BottleDispenser bd = new BottleDispenser();

    private double money;
    private double price;
    private int input;

    public boolean more;

    public ArrayList<Bottle> alist;

    private String productMessage;

    public static BottleDispenser getInstance(){
        return bd;
    }

    public void setProductMessageText(String s){
        productMessage = s;
    }

    public double getMoney(){
        return money;
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

    public void buyBottle(int input) {

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
                        productMessage = "CACHUNCK! " + alist.get(input - 1).getName() + " dropped from the machine!";
                        alist.remove(input - 1);
                        more = false;
                    } else {
                        productMessage = "Insert money first!";
                        more = false;
                    }
                }
                more = false;
            }
        }
    }

    public void returnMoney() {
        String formattedMoney = String.format("%.02f", money);
        productMessage = "Clinck clinck. " + formattedMoney +"€ came out!";
        money -= money;
    }
}
