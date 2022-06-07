package com.thiagoqvdo.data.analyze;

import com.thiagoqvdo.data.analyze.services.AppService;

public class Main {
    public static void main(String[] args) {
        AppService service = new AppService();
        service.watchInputDirectory();
    }
}
