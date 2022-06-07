package com.thiagoqvdo.data.analyze.repositories;

import com.thiagoqvdo.data.analyze.entities.Customer;
import com.thiagoqvdo.data.analyze.entities.Sale;
import com.thiagoqvdo.data.analyze.entities.Salesman;

import java.util.List;
import java.util.Vector;

public class DataRepository {
    private final Integer SALESMAN_INITIAL_CAPACITY=20, SALESMAN_CAPACITY_INCREMENT=20;
    private final Integer CUSTOMER_INITIAL_CAPACITY=100, CUSTOMER_CAPACITY_INCREMENT=200;
    private final Integer SALE_INITIAL_CAPACITY=300, SALE_CAPACITY_INCREMENT=500;

    private final List<Salesman> salesmanList;
    private final List<Customer> customerList;
    private final List<Sale> saleList;

    public DataRepository() {
        salesmanList = new Vector<>(SALESMAN_INITIAL_CAPACITY, SALESMAN_CAPACITY_INCREMENT);
        customerList = new Vector<>(CUSTOMER_INITIAL_CAPACITY, CUSTOMER_CAPACITY_INCREMENT);
        saleList = new Vector<>(SALE_INITIAL_CAPACITY, SALE_CAPACITY_INCREMENT);
    }

    public List<Salesman> getSalesmanList() {
        return salesmanList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void addToSalesmanList(Salesman newSalesman) {
        salesmanList.add(newSalesman);
    }

    public void addToCustomerList(Customer newCustomer) {
        customerList.add(newCustomer);
    }

    public void addToSaleList(Sale newSale) {
        saleList.add(newSale);
    }
}
