package main;

public class RealProduct extends Product {
    private double size;
    private int weight;

    public RealProduct(final String name, final double price, final double size, final int weight) {
        super(name, price);
        this.size = size;
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(final double size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(final int weight) {
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
