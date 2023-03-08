package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pt.ul.fc.css.example.demo.business.ApplicationException;
import pt.ul.fc.css.example.demo.business.CustomerTransactionScripts;
import pt.ul.fc.css.example.demo.business.DiscountType;
import pt.ul.fc.css.example.demo.dataaccess.DataSourceWrapper;
import pt.ul.fc.css.example.demo.dataaccess.PersistenceException;
import pt.ul.fc.css.example.demo.services.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
class SaleSysTests {

  @Test
  void addCustomerIsInDatabase() throws ApplicationException, PersistenceException, SQLException {

    CustomerTransactionScripts customerTS = new CustomerTransactionScripts();
    CustomerService cs = new CustomerService(customerTS);
    String companyName = "Doces amargos, Lda";
    cs.addCustomer(503_543_560, companyName, 919191919, DiscountType.NO_DISCOUNT);

    try (DataSourceWrapper dsw = new DataSourceWrapper()) {
      PreparedStatement ps = dsw.prepare("SELECT COUNT(*) FROM customer WHERE DESIGNATION = ?");

      ps.setString(1, companyName);
      ResultSet s = ps.executeQuery();
      s.next();
      assertEquals(1, s.getInt(1));
    }
  }
}
