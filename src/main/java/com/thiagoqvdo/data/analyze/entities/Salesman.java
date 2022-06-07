package com.thiagoqvdo.data.analyze.entities;

import com.thiagoqvdo.data.analyze.exceptions.FailedToCreateReportException;

import java.util.Scanner;

public class Salesman extends FlatFile{
    private String cpf;
    private String name;
    private Float salary;
    private Integer amountOfSales;
    private Float totalAmountSold;
    private Float salesMean;

    private Salesman(Salesman.Builder builder) {
        super("001");
        this.cpf = builder.cpf;
        this.name = builder.name;
        this.salary = builder.salary;
        this.amountOfSales = builder.amountOfSales;
        this.totalAmountSold = builder.totalAmountSold;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Integer getAmountOfSales() {
        return amountOfSales;
    }

    public void setAmountOfSales(Integer amountOfSales) {
        this.amountOfSales = amountOfSales;
    }

    public Float getTotalAmountSold() {
        return totalAmountSold;
    }

    public void setTotalAmountSold(Float totalAmountSold) {
        this.totalAmountSold = totalAmountSold;
    }

    public Float getSalesMean() {
        return salesMean;
    }

    public void calculateSalesMean() {
        this.salesMean = this.totalAmountSold/this.amountOfSales;
    }

    public static class Builder {
        private String cpf;
        private String name;
        private Float salary;
        private Integer amountOfSales = 0;
        private Float totalAmountSold = Float.valueOf(0);
        private Float salesMean;

        public Builder() {
        }

        public Builder setCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSalary(Float salary) {
            this.salary = salary;
            return this;
        }

        public Builder setAmountOfSales(Integer amountOfSales) {
            this.amountOfSales = amountOfSales;
            return this;
        }

        public Builder setTotalAmountSold(Float totalAmountSold) {
            this.totalAmountSold = totalAmountSold;
            return this;
        }

        public Salesman build() {
            return new Salesman(this);
        }

        public Salesman build(Scanner scanner) {
            try {
                return new Builder()
                        .setCpf(scanner.next())
                        .setName(scanner.next())
                        .setSalary(scanner.nextFloat())
                        .build();
            } catch (Exception e) {
                throw new FailedToCreateReportException(e);
            }
        }
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", amountOfSales=" + amountOfSales +
                ", totalAmountSold=" + totalAmountSold +
                ", salesMean=" + salesMean +
                '}';
    }
}
