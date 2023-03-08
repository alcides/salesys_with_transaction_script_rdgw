package pt.ul.fc.css.example.demo.services;

import pt.ul.fc.css.example.demo.business.ApplicationException;
import pt.ul.fc.css.example.demo.business.SaleTransactionScripts;

public class SaleService {

  private SaleTransactionScripts saleTS;

  public SaleService(SaleTransactionScripts saleTS) {
    this.saleTS = saleTS;
  }

  public int newSale(int vat) throws ApplicationException {
    return saleTS.newSale(vat);
  }

  public void addProductToSale(int saleId, int productCode, int qty) throws ApplicationException {
    saleTS.addProductToSale(saleId, productCode, qty);
  }

  public double getSaleDiscount(int saleId) throws ApplicationException {
    return saleTS.getSaleDiscount(saleId);
  }
}
