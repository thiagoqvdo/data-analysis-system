package com.thiagoqvdo.data.analyze.entities;

public class PurchasedItem {
    private Integer id;
    private Integer quantity;
    private Float price;

    private PurchasedItem(PurchasedItem.Builder builder) {
        this.id = builder.id;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public static class Builder {
        private Integer id;
        private Integer quantity;
        private Float price;

        public Builder() {
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPrice(Float price) {
            this.price = price;
            return this;
        }

        public PurchasedItem build() {
            return new PurchasedItem(this);
        }
    }

    @Override
    public String toString() {
        return "PurchasedItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
