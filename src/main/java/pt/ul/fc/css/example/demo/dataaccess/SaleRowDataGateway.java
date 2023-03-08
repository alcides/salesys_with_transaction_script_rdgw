package pt.ul.fc.css.example.demo.dataaccess;

import java.util.Date;
import pt.ul.fc.css.example.demo.business.SaleStatus;

/**
 * An in-memory representation of a customer table record.
 *
 * <p>Notes: 1. See the notes for the CustomerRowGateway class
 *
 * <p>2. Java makes available two Date classes (in fact, more in Java 8, but we will address it
 * later (with JPA)): one in the package java.util, which is the one we normally use, and another in
 * java.sql, which is a subclass of java.util.date and that transforms the milliseconds
 * representation according to the "Date type of databases". For more information refer to
 * http://download.oracle.com/javase/6/docs/api/java/sql/Date.html.
 *
 * <p>3. When creating a new sale, we only pass the date and customer id parameters to the
 * constructor. Moreover, attribute open is always set to 'O'. The remaining attributes are either
 * set automatically (id) or when closing the sale (totalSale and totalDiscount). Also, the open
 * attribute is set to 'C' upon payment.
 *
 * @author fmartins @Version 1.2 (13/02/2015)
 */
public class SaleRowDataGateway {

  // Sale attributes

  /** The select a sale by Id SQL statement */
  private static final String GET_SALE_SQL = ""; // TODO: program me!

  /** The insert sale SQL statement */
  private static final String INSERT_SALE_SQL = ""; // TODO: program me!

  // Constants for conversion of status

  // 1. constructor

  /**
   * Creates a new sale given the customer Id and the date it occurs.
   *
   * @param customerId The customer Id the sale belongs to
   * @param date The date the sale took place
   */
  public SaleRowDataGateway(int customerId, Date date) {
    // TODO Auto-generated method stub
  }

  // 2. getters and setters

  public int getId() {
    // TODO Auto-generated method stub
    return 0;
  }

  public SaleStatus getStatus() {
    // TODO Auto-generated method stub
    return null;
  }

  public int getCustomerId() {
    // TODO Auto-generated method stub
    return 0;
  }

  public double getDiscount() {
    // TODO Auto-generated method stub
    return 0;
  }

  // 3. interaction with the repository (a memory map in this simple example)

  /** Stores the information in the repository */
  public void insert() throws PersistenceException {
    // TODO: program me!
  }

  /**
   * Gets a sale by its id
   *
   * @param id The sale id to search for
   * @return The new object that represents an in-memory sale
   * @throws PersistenceException In case there is an error accessing the database.
   */
  public static SaleRowDataGateway find(int id) throws PersistenceException {
    return null;
    // TODO: program me!
  }
}
