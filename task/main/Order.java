package main;

import java.util.List;

public class Order {
    private User user;
    private List<Product> productList;

    private Order(final User user, final List<Product> productList) {
        this.user = user;
        this.productList = productList;
    }

    public static Order createOrder(final User user, final List<Product> productList) {
        return new Order(user, productList);
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(final List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", productList=" + productList +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Order order = (Order) o;

        if (getUser() != null ? !getUser().equals(order.getUser()) : order.getUser() != null) return false;
        return getProductList() != null ? getProductList().equals(order.getProductList()) : order.getProductList() == null;
    }

    @Override
    public int hashCode() {
        int result = getUser() != null ? getUser().hashCode() : 0;
        result = 31 * result + (getProductList() != null ? getProductList().hashCode() : 0);
        return result;
    }
}
