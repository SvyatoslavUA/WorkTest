package main;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {

        //TODO Create User class with method createUser
        // User class fields: name, age;
        // Notice that we can only create user with createUser method without using constructor or builder
        final User user1 = User.createUser("Alice", 32);
        final User user2 = User.createUser("Bob", 19);
        final User user3 = User.createUser("Charlie", 20);
        final User user4 = User.createUser("John", 27);

        //TODO Create factory that can create a product for a specific type: Real or Virtual
        // Product class fields: name, price
        // Product Real class additional fields: size, weight
        // Product Virtual class additional fields: code, expiration date

        final Product realProduct1 = ProductFactory.createRealProduct("Product A", 20.50, 10, 25);
        final Product realProduct2 = ProductFactory.createRealProduct("Product B", 50, 6, 17);

        final Product virtualProduct1 = ProductFactory.createVirtualProduct("Product C", 100, "xxx", LocalDate.of(2023, 5, 12));
        final Product virtualProduct2 = ProductFactory.createVirtualProduct("Product D", 81.25, "yyy", LocalDate.of(2024, 6, 20));


        //TODO Create Order class with method createOrder
        // Order class fields: User, List<Price>
        // Notice that we can only create order with createOrder method without using constructor or builder
        final List<Order> orders = new ArrayList<>() {{
            add(Order.createOrder(user1, List.of(realProduct1, virtualProduct1, virtualProduct2)));
            add(Order.createOrder(user2, List.of(realProduct1, realProduct2)));
            add(Order.createOrder(user3, List.of(realProduct1, virtualProduct2)));
            add(Order.createOrder(user4, List.of(virtualProduct1, virtualProduct2, realProduct1, realProduct2)));
        }};

        //TODO 1). Create singleton class which will check the code is used already or not
        // Singleton class should have the possibility to mark code as used and check if code used
        // Example:
        // singletonClass.useCode("xxx")
        // boolean isCodeUsed = virtualProductCodeManager.isCodeUsed("xxx") --> true;
        // boolean isCodeUsed = virtualProductCodeManager.isCodeUsed("yyy") --> false;

        VirtualProductCodeManager.useCode("xxx");

        final boolean isCodeUsed = VirtualProductCodeManager.isCodeUsed("xxx");
        System.out.println("Is code used: " + isCodeUsed);

        final boolean isCodeUsed1 = VirtualProductCodeManager.isCodeUsed("yyy");
        System.out.println("Is code used: " + isCodeUsed1);
        System.out.println();

        //TODO 2). Create a functionality to get the most expensive ordered product
        final Product mostExpensive = getMostExpensiveProduct(orders);
        System.out.println("2. Most expensive product: " + mostExpensive + "\n");

        //TODO 3). Create a functionality to get the most popular product(product bought by most users) among users
        final Product mostPopular = getMostPopularProduct(orders);
        System.out.println("3. Most popular product: " + mostPopular + "\n");

        //TODO 4). Create a functionality to get average age of users who bought realProduct2
        final double averageAge = calculateAverageAge(realProduct2, orders);
        System.out.println("4. Average age is: " + averageAge + "\n");

        //TODO 5). Create a functionality to return map with products as keys and a list of users
        // who ordered each product as values
        final Map<Product, List<User>> productUserMap = getProductUserMap(orders);
        System.out.println("5. Map with products as keys and list of users as value \n");
        productUserMap.forEach((key, value) -> System.out.println("key: " + key + " " + "value: " + value + "\n"));

        //TODO 6). Create a functionality to sort/group entities:
        // a) Sort Products by price
        // b) Sort Orders by user age in descending order
        final List<Product> productsByPrice = sortProductsByPrice(List.of(realProduct1, realProduct2, virtualProduct1, virtualProduct2));
        System.out.println("6. a) List of products sorted by price: " + productsByPrice + "\n");
        final List<Order> ordersByUserAgeDesc = sortOrdersByUserAgeDesc(orders);
        System.out.println("6. b) List of orders sorted by user agge in descending order: " + ordersByUserAgeDesc + "\n");

        //TODO 7). Calculate the total weight of each order
        final Map<Order, Integer> result = calculateWeightOfEachOrder(orders);
        System.out.println("7. Calculate the total weight of each order \n");
        result.forEach((key, value) -> System.out.println("order: " + key + " " + "total weight: " + value + "\n"));
    }

    private static Product getMostExpensiveProduct(final List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getProductList().stream())
                .max(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    private static Product getMostPopularProduct(final List<Order> orders) {
        final List<Product> allProducts = new ArrayList<>();

        orders.forEach(order -> allProducts.addAll(order.getProductList()));

        final Map<Product, Integer> productCountMap = new HashMap<>();
        allProducts.forEach(product -> productCountMap.put(product, productCountMap.getOrDefault(product, 0) + 1));

        return productCountMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private static double calculateAverageAge(final Product product, final List<Order> orders) {
        final List<User> users = orders.stream()
                .filter(order -> order.getProductList().contains(product))
                .map(Order::getUser)
                .collect(Collectors.toList());

        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    private static Map<Product, List<User>> getProductUserMap(final List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getProductList().stream()
                        .map(product -> Map.entry(product, order.getUser())))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private static List<Product> sortProductsByPrice(final List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    public static class AgeDescComparator implements Comparator<Order> {
        @Override
        public int compare(final Order order1, final Order order2) {
            final int age1 = order1.getUser().getAge();
            final int age2 = order2.getUser().getAge();

            return Integer.compare(age2, age1);
        }
    }
    private static List<Order> sortOrdersByUserAgeDesc(final List<Order> orders) {
        final Comparator<Order> ageDescComparator = new AgeDescComparator();
        return orders.stream()
                .sorted(ageDescComparator)
                .collect(Collectors.toList());
    }

    private static Map<Order, Integer> calculateWeightOfEachOrder(final List<Order> orders) {
        return orders.stream()
                .collect(Collectors.toMap(order -> order, order -> order.getProductList().stream()
                                .filter(product -> product instanceof RealProduct)
                                .map(product -> (RealProduct) product)
                                .mapToInt(RealProduct::getWeight)
                                .sum()
                ));
    }
}
