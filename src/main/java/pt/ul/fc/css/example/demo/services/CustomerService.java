package pt.ul.fc.css.example.demo.services;

import pt.ul.fc.css.example.demo.business.ApplicationException;
import pt.ul.fc.css.example.demo.business.CustomerTransactionScripts;
import pt.ul.fc.css.example.demo.business.DiscountType;

public class CustomerService {

  private CustomerTransactionScripts customerTS;

  public CustomerService(CustomerTransactionScripts customerTS) {
    this.customerTS = customerTS;
  }

  public void addCustomer(int vat, String denomination, int phoneNumber, DiscountType discountType)
      throws ApplicationException {
    customerTS.addCustomer(vat, denomination, phoneNumber, discountType);
  }
}
