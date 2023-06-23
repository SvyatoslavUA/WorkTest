package main;

import java.time.LocalDate;

public class ProductFactory {
    public static Product createRealProduct(final String name, final double price, final double size, final int weight) {
        return new RealProduct(name, price, size, weight);
    }

    public static Product createVirtualProduct(final String name, final double price, final String code, final LocalDate expirationDate) {
        return new VirtualProduct(name, price, code, expirationDate);
    }
}
