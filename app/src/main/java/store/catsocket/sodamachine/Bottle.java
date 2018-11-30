package store.catsocket.sodamachine;

public class Bottle {

    private String name = "Pepsi Max";
    private String manufacturer = "Pepsi";
    private float total_energy = 0.3f;
    private float size = 0.5f;
    private double price = 1.80;

    public Bottle(){

    }

    public Bottle(String n, String m, float e, float s, double p) {

        name = n;
        manufacturer = m;
        total_energy = e;
        size = s;
        price = p;
    }

    public String getName(){

        return name;

    }

    public void setName(String n){

        name = n;

    }

    public double getPrice(){

        return price;

    }

    public void setPrice(double p){

        price = p;
    }

    public float getSize(){

        return size;

    }

    public void setSize(float s){

        size = s;
    }
}
