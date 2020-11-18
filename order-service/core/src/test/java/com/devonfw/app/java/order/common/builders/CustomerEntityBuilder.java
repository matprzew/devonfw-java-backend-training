package com.devonfw.app.java.order.common.builders;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.persistence.EntityManager;

import com.devonfw.app.java.order.orderservice.dataaccess.api.CustomerEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.OrderEntity;

/**
 * Test data builder for CustomerEntity generated with cobigen.
 */
public class CustomerEntityBuilder {

  private List<Consumer<CustomerEntity>> parameterToBeApplied;

  /**
   * The constructor.
   */
  public CustomerEntityBuilder() {

    this.parameterToBeApplied = new LinkedList<>();
    fillMandatoryFields();
    fillMandatoryFields_custom();
  }

  /**
   * @param firstname the firstname to add.
   * @return the builder for fluent population of fields.
   */
  public CustomerEntityBuilder firstname(final String firstname) {

    this.parameterToBeApplied.add(target -> target.setFirstname(firstname));

    return this;
  }

  /**
   * @param lastname the lastname to add.
   * @return the builder for fluent population of fields.
   */
  public CustomerEntityBuilder lastname(final String lastname) {

    this.parameterToBeApplied.add(target -> target.setLastname(lastname));

    return this;
  }

  /**
   * @param orders the orders to add.
   * @return the builder for fluent population of fields.
   */
  public CustomerEntityBuilder orders(final Set<OrderEntity> orders) {

    this.parameterToBeApplied.add(target -> target.setOrders(orders));

    return this;
  }

  /**
   * @return the populated CustomerEntity.
   */
  public CustomerEntity createNew() {

    CustomerEntity customerentity = new CustomerEntity();
    for (Consumer<CustomerEntity> parameter : this.parameterToBeApplied) {
      parameter.accept(customerentity);
    }
    return customerentity;
  }

  /**
   * Might be enriched to users needs (will not be overwritten)
   */
  private void fillMandatoryFields_custom() {

  }

  /**
   * Fills all mandatory fields by default. (will be overwritten on re-generation)
   */
  private void fillMandatoryFields() {

  }

  /**
   * @param em the {@link EntityManager}
   * @return the CustomerEntity
   */
  public CustomerEntity persist(EntityManager em) {

    CustomerEntity customerentity = createNew();
    em.persist(customerentity);
    return customerentity;
  }

  /**
   * @param em the {@link EntityManager}
   * @param quantity the quantity
   * @return a list of CustomerEntity
   */
  public List<CustomerEntity> persistAndDuplicate(EntityManager em, int quantity) {

    List<CustomerEntity> customerentityList = new LinkedList<>();
    for (int i = 0; i < quantity; i++) {
      CustomerEntity customerentity = createNew();
      // TODO alter at least values with unique key constraints to prevent from exceptions while persisting
      em.persist(customerentity);
      customerentityList.add(customerentity);
    }

    return customerentityList;
  }

}
