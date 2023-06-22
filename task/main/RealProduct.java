package main;

public class RealProduct extends Product {
    private double size;
    private int weight;

    public RealProduct(String name, double price, double size, int weight) {
        super(name, price);
        this.size = size;
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RealProduct{" +
                "name='" + getName() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", size=" + size +
                ", weight=" + weight +
                '}';
    }
}
