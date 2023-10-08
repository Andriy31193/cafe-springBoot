package com.example.cafespringBoot;

import com.example.cafespringBoot.dao.beverageDAO.BeverageDao;
import com.example.cafespringBoot.dao.cafeDAO.CafeDao;
import com.example.cafespringBoot.dao.staffDAO.StaffDao;
import com.example.cafespringBoot.model.Beverage;
import com.example.cafespringBoot.model.Staff;
import com.example.cafespringBoot.service.CafeDbInitializer;
import com.example.cafespringBoot.task.ExecutionRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppStarter {

    @Autowired
    private CafeDbInitializer cafeDbInitializer;
    @Autowired
    private ExecutionRequests executionRequests;

    @Bean
    public ApplicationRunner init() {
        return args -> {
            cafeDbInitializer.initializeDatabase();
//            executionRequests.deleteBeverage(7L);
//            executionRequests.deleteDessert(2L);
//            executionRequests.transferStaffToCafe(3L, 2L);
//            executionRequests.showBeveragesArchive();
//            executionRequests.showDessertsArchive();
//            executionRequests.showEmployeeArchive();
        };
    }
}
