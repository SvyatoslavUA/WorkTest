package main;

import java.util.List;
public class Order {
    private User user;

    private List<Price> priceList;

    public Order(User user, List<Price> priceList) {
        this.user = user;
        this.priceList = priceList;
    }

    public static Order createOrder(User user, List<Product> productList){
        return new Order(user, productList);
    }

}
