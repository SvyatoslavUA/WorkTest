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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VirtualProduct that = (VirtualProduct) o;

        if (getCode() != null ? !getCode().equals(that.getCode()) : that.getCode() != null) return false;
        return getExpirationDate() != null ? getExpirationDate().equals(that.getExpirationDate()) : that.getExpirationDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode() != null ? getCode().hashCode() : 0;
        result = 31 * result + (getExpirationDate() != null ? getExpirationDate().hashCode() : 0);
        return result;
    }
}
