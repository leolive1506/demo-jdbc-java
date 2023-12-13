import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {
    public static void main(String[] args) throws Exception {
        Department department = new Department(1, "Books");
        System.out.println(department);

        Seller seller = new Seller(1, "Leonardo", "leonardo@gmail.com", new Date(), 50000.00, department);
        System.out.println(seller);
        
        SellerDao sellerDao = DaoFactory.createSellerDao();
    }
}
