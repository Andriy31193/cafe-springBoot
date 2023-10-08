package com.example.cafespringBoot.task;

import com.example.cafespringBoot.dao.beverageArchiveDAO.BeverageArchiveDao;
import com.example.cafespringBoot.dao.beverageDAO.BeverageDao;
import com.example.cafespringBoot.dao.dessertArchiveDAO.DessertArchiveDao;
import com.example.cafespringBoot.dao.dessertDAO.DessertDao;
import com.example.cafespringBoot.dao.employeeTransferDAO.EmployeeTransferDao;
import com.example.cafespringBoot.dao.staffDAO.StaffDao;
import com.example.cafespringBoot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutionRequests {

    @Autowired
    private BeverageDao beverageDao;
    @Autowired
    private DessertDao dessertDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private BeverageArchiveDao beverageArchiveDao;
    @Autowired
    private DessertArchiveDao dessertArchiveDao;
    @Autowired
    private EmployeeTransferDao employeeTransferDao;

    public void saveBeverage() {
        Beverage beverage = new Beverage(1L, "Americano", "Американо", 6.80, 3.8, 1L);
        beverageDao.save(beverage);
    }

    public void deleteBeverage(Long id) {
        Beverage beverage = new Beverage();
        beverage.setId(id);
        beverageDao.delete(beverage);
    }

    public void saveDessert() {
        Dessert dessert = new Dessert(1L, "Eclair", "Еклер", 7.26, 4.2, 1L);
        dessertDao.save(dessert);
    }

    public void deleteDessert(Long id) {
        Dessert dessert = new Dessert();
        dessert.setId(id);
        dessertDao.delete(dessert);
    }

    public void saveBarista() {
        Staff staffBarista = new Staff(1L, "Ivan Kamenuk", "+380963923746", "Ivan@gmail.com", "barista", 2L);
        staffDao.addBarista(staffBarista);
    }

    public void saveWaiter() {
        Staff staffWaiter = new Staff(1L, "Vasia Symchuk", "+380963945766", "Vasia@gmail.com", "waiter", 2L);
        staffDao.save(staffWaiter);
    }

    public void saveConfectioner() {
        Staff staffConfectioner = new Staff(1L, "Petro Romaniuk", "+380978323746", "Petro@gmail.com", "confectioner", 1L);
        staffDao.addConfectioner(staffConfectioner);
    }

    public void transferStaffToCafe(Long staffId, Long cafeId) {
        Staff staff = new Staff();
        staff.setId(staffId);
        staff.setCafeId(cafeId);
        staffDao.transferStaffToCafe(staff);
    }

    public void showBeveragesArchive() {
        List<BeverageArchive> beverageArchives = beverageArchiveDao.getAll();
        System.out.println("\n===== Beverages Archive =====");
        for (BeverageArchive beverageArchive : beverageArchives) {
            System.out.println("Name english: " + beverageArchive.getNameEnglish());
            System.out.println("Cafe id: " + beverageArchive.getCafeId());
            System.out.println("Deleted at: " + beverageArchive.getDeletedAt());
            System.out.println("---------------------------------------");
        }
    }

    public void showDessertsArchive() {
        List<DessertArchive> dessertArchives = dessertArchiveDao.getAll();
        System.out.println("\n===== Desserts Archive =====");
        for (DessertArchive dessertArchive : dessertArchives) {
            System.out.println("Name english: " + dessertArchive.getNameEnglish());
            System.out.println("Cafe id: " + dessertArchive.getCafeId());
            System.out.println("Deleted at: " + dessertArchive.getDeletedAt());
            System.out.println("---------------------------------------");
        }
    }

    public void showEmployeeArchive() {
        List<EmployeeTransfer> employeeTransfers = employeeTransferDao.getAll();
        System.out.println("\n===== Employee Transfer =====");
        for (EmployeeTransfer employeeTransfer : employeeTransfers) {
            System.out.println("Employee id: " + employeeTransfer.getEmployeeId());
            System.out.println("Old cafe id: " + employeeTransfer.getOldCafeId());
            System.out.println("New cafe id: " + employeeTransfer.getNewCafeId());
            System.out.println("Transfer date: " + employeeTransfer.getTransferDate());
            System.out.println("---------------------------------------");
        }
    }

}
