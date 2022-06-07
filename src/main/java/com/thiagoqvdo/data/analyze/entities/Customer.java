package com.thiagoqvdo.data.analyze.entities;

import com.thiagoqvdo.data.analyze.exceptions.FailedToCreateReportException;

import java.util.Scanner;

public class Customer extends FlatFile{
    private String cnpj;
    private String name;
    private BusinessArea businessArea;

    public enum BusinessArea{
        RURAL, URBAN
    }

    protected Customer(Customer.Builder builder) {
        super("002");
        this.cnpj = builder.cnpj;
        this.name = builder.name;
        this.businessArea = builder.businessArea;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BusinessArea getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(BusinessArea businessArea) {
        this.businessArea = businessArea;
    }

    public static class Builder {
        private String cnpj;
        private String name;
        private BusinessArea businessArea;

        public Builder() {
        }

        public Builder setCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBusinessArea(BusinessArea businessArea) {
            this.businessArea = businessArea;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }

        public Customer build(Scanner scanner) {
            try {
                return new Builder()
                        .setCnpj(scanner.next())
                        .setName(scanner.next())
                        .setBusinessArea(BusinessArea
                                .valueOf(scanner.next().toUpperCase()))
                        .build();
            } catch (Exception e) {
                throw new FailedToCreateReportException(e);
            }
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cnpj='" + cnpj + '\'' +
                ", name='" + name + '\'' +
                ", businessArea=" + businessArea +
                '}';
    }
}
