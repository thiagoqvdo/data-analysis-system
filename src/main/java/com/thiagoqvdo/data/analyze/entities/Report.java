package com.thiagoqvdo.data.analyze.entities;

import java.util.Calendar;

public class Report {
    private Integer amountOfClientsInInputFile;
    private Integer amountOfSalesmanInInputFile;
    private Integer idOfTheMostExpensiveSale;
    private Salesman worstSalesman;

    public Report() {
    }

    private Report(Report.Builder builder) {
        this.amountOfClientsInInputFile = builder.amountOfClientsInInputFile;
        this.amountOfSalesmanInInputFile = builder.amountOfSalesmanInInputFile;
        this.idOfTheMostExpensiveSale = builder.idOfTheMostExpensiveSale;
        this.worstSalesman = builder.worstSalesman;
    }

    public Integer getAmountOfClientsInInputFile() {
        return amountOfClientsInInputFile;
    }

    public void setAmountOfClientsInInputFile(Integer amountOfClientsInInputFile) {
        this.amountOfClientsInInputFile = amountOfClientsInInputFile;
    }

    public Integer getAmountOfSalesmanInInputFile() {
        return amountOfSalesmanInInputFile;
    }

    public void setAmountOfSalesmanInInputFile(Integer amountOfSalesmanInInputFile) {
        this.amountOfSalesmanInInputFile = amountOfSalesmanInInputFile;
    }

    public Integer getIdOfTheMostExpensiveSale() {
        return idOfTheMostExpensiveSale;
    }

    public void setIdOfTheMostExpensiveSale(Integer idOfTheMostExpensiveSale) {
        this.idOfTheMostExpensiveSale = idOfTheMostExpensiveSale;
    }

    public Salesman getWorstSalesman() {
        return worstSalesman;
    }

    public void setWorstSalesman(Salesman worstSalesman) {
        this.worstSalesman = worstSalesman;
    }

    public static class Builder {
        private Integer amountOfClientsInInputFile;
        private Integer amountOfSalesmanInInputFile;
        private Integer idOfTheMostExpensiveSale;
        private Salesman worstSalesman;

        public Builder setAmountOfClientsInInputFile(Integer amountOfClientsInInputFile) {
            this.amountOfClientsInInputFile = amountOfClientsInInputFile;
            return this;
        }

        public Builder setAmountOfSalesmanInInputFile(Integer amountOfSalesmanInInputFile) {
            this.amountOfSalesmanInInputFile = amountOfSalesmanInInputFile;
            return this;
        }

        public Builder setIdOfTheMostExpensiveSale(Integer idOfTheMostExpensiveSale) {
            this.idOfTheMostExpensiveSale = idOfTheMostExpensiveSale;
            return this;
        }

        public Builder setWorstSalesman(Salesman worstSalesman) {
            this.worstSalesman = worstSalesman;
            return this;
        }

        public Report build() {
            return new Report(this);
        }
    }

    @Override
    public String toString() {
        return "Report: "+ Calendar.getInstance().getTime() +"\n" +
                "Amount of clients = " + amountOfClientsInInputFile + "\n" +
                "Amount of salesmen = " + amountOfSalesmanInInputFile + "\n" +
                "Id of the most expensive sale = " + idOfTheMostExpensiveSale + "\n" +
                "Worst salesman = " + worstSalesman;
    }
}
