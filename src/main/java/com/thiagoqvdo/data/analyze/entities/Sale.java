package com.thiagoqvdo.data.analyze.entities;

import com.thiagoqvdo.data.analyze.exceptions.FailedToCreateReportException;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Sale extends FlatFile {
    private Integer saleId;
    private List<PurchasedItem> itemList;
    private String salesmanName;
    private Float totalPrice = Float.valueOf(0);

    private Sale(Sale.Builder builder) {
        super("003");
        this.saleId = builder.saleId;
        this.itemList = builder.itemList;
        this.salesmanName = builder.salesmanName;
        this.totalCalculate();
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public List<PurchasedItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<PurchasedItem> itemList) {
        this.itemList = itemList;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void totalCalculate() {
        itemList.forEach(purchasedItem -> {
            this.totalPrice = this.totalPrice + (purchasedItem.getPrice() * purchasedItem.getQuantity());
        });
    }

    public static class Builder {
        private Integer saleId;
        private List<PurchasedItem> itemList;
        private String salesmanName;

        public Builder() {
        }

        public Builder setSaleId(Integer saleId) {
            this.saleId = saleId;
            return this;
        }

        public Builder setItemList(List<PurchasedItem> itemList) {
            this.itemList = itemList;
            return this;
        }

        public Builder setSalesmanName(String salesmanName) {
            this.salesmanName = salesmanName;
            return this;
        }

        public Sale build() {
            return new Sale(this);
        }

        public Sale build(Scanner scanner) {
            try {
                return new Builder()
                        .setSaleId(scanner.nextInt())
                        //get the data of the purchased items from the file
                        .setItemList(Arrays.stream(scanner.next().replaceAll("[\\[\\]]", "").split(","))
                                .map(purchasedItemData -> {
                                    Scanner scanItem = new Scanner(purchasedItemData).useLocale(Locale.US).useDelimiter("-");
                                    return new PurchasedItem.Builder()
                                            .setId(scanItem.nextInt())
                                            .setQuantity(scanItem.nextInt())
                                            .setPrice(scanItem.nextFloat())
                                            .build();
                                }).collect(Collectors.toList()))
                        .setSalesmanName(scanner.next())
                        .build();
            } catch (Exception e) {
                throw new FailedToCreateReportException(e);
            }
        }
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", itemList=" + itemList +
                ", salesmanName='" + salesmanName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
