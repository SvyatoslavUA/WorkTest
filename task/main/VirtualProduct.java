package main;

import java.time.LocalDate;

public class VirtualProduct extends Product {
    private String code;
    private LocalDate expirationDate;

    public VirtualProduct(final String name, final double price, final String code, final LocalDate expirationDate) {
        super(name, price);
        this.code = code;
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "VirtualProduct{" +
                "name='" + getName() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", code='" + code + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
