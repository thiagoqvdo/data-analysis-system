package com.thiagoqvdo.data.analyze;

import java.util.Calendar;
import java.util.List;

public class Mock {
    protected List<String> lines1, lines2;
    protected List<String> expectedLines1, expectedLines2;

    public Mock() {
        lines1 = List.of(
                "001ç1234567891234çDiegoç50000",
                "001ç3245678865434çRenatoç40000.99",
                "002ç2345675434544345çJose da SilvaçRural",
                "002ç2345675433444345çEduardoPereiraçRural",
                "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego",
                "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato");

        lines2 = List.of(
                "001ç3814269771234çPedroç22000",
                "001ç4235276883451çRodrigoç36000.90",
                "002ç8231672184652345çMarioçRural",
                "002ç1287612938724135çPericlesçRural",
                "003ç3ç[1-4-9.5,2-26-1.50,3-30-3.40]çPedro",
                "003ç08ç[1-12-7.5,2-44-1,3-26-4.10]çRodrigo");

        expectedLines1 = List.of("Report: "+ Calendar.getInstance().getTime() ,"Amount of clients = 2",
                "Amount of salesmen = 2",
                "Id of the most expensive sale = 10",
                "Worst salesman = Salesman{cpf='3245678865434', name='Renato', salary=40000.99, amountOfSales=1, totalAmountSold=393.5, salesMean=393.5}");

        expectedLines2 = List.of("Report: "+Calendar.getInstance().getTime() ,"Amount of clients = 2",
                "Amount of salesmen = 2",
                "Id of the most expensive sale = 8",
                "Worst salesman = Salesman{cpf='3814269771234', name='Pedro', salary=22000.0, amountOfSales=1, totalAmountSold=179.0, salesMean=179.0}");

    }
}
