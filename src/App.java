import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {
    public static void main(String[] args) throws Exception {
        // SellerDao sellerDao = DaoFactory.createSellerDao();
        // System.out.println("=== TEST 1: seler findById ===");
        // Seller seller = sellerDao.findById(3);
        // System.out.println(seller);

        // System.out.println("\n=== TEST 2: seler findByDepartment ===");
        // Department department = new Department(2, null);
        // List<Seller> list = sellerDao.findByDepartment(department);
        // list.forEach(System.out::println);

        // System.out.println("\n=== TEST 3: seler findAll ===");
        // list = sellerDao.findAll();
        // list.forEach(System.out::println);

        // System.out.println("\n=== TEST 4: seler insert ===");
        // Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        // sellerDao.insert(newSeller);
        // System.out.println("New id = " + newSeller.getId());

        // System.out.println("\n=== TEST 5: seler update ===");
        // seller = sellerDao.findById(1);
        // seller.setName("Marth waine");
        // sellerDao.update(seller);
        // System.out.println("Update completed");

        // System.out.println("\n=== TEST 6: seler delete ===");
        // sellerDao.deleteById(8);
        // System.out.println("Delete completed");

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("\n=== TEST 1: department insert ===");
        Department department = new Department(null, "D3");
        departmentDao.insert(department);
        System.out.println("New id = " + department.getId());

        System.out.println("\n=== TEST 2: department findById ===");
        department = departmentDao.findById(department.getId());
        System.out.println(department);

        System.out.println("\n=== TEST 3: department findAll ===");
        List<Department> departments = departmentDao.findAll();
        departments.forEach(System.out::println);

        System.out.println("\n=== TEST 4: department updateById ===");
        department.setName("D4");
        departmentDao.update(department);
        System.out.println("Update completed");

        System.out.println("\n=== TEST 5: department deleteById ===");
        departmentDao.deleteById(department.getId());
        System.out.println("Delete completed");
    }
}
