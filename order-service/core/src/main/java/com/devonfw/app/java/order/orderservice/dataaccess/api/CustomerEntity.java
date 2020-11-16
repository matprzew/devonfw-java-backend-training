package com.devonfw.app.java.order.orderservice.dataaccess.api;

import java.util.Set;

import javax.persistence.OneToMany;

import com.devonfw.app.java.order.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.app.java.order.orderservice.common.api.Customer;

/**
 * @author MPRZEWOZ
 */
public class CustomerEntity extends ApplicationPersistenceEntity implements Customer {

  private String firstname;

  private String lastname;

  private Set<OrderEntity> orders;

  private static final long serialVersionUID = 1L;

  /**
   * @return firstname
   */
  @Override
  public String getFirstname() {

    return this.firstname;
  }

  /**
   * @param firstname new value of {@link #getfirstname}.
   */
  @Override
  public void setFirstname(String firstname) {

    this.firstname = firstname;
  }

  /**
   * @return lastname
   */
  @Override
  public String getLastname() {

    return this.lastname;
  }

  /**
   * @param lastname new value of {@link #getlastname}.
   */
  @Override
  public void setLastname(String lastname) {

    this.lastname = lastname;
  }

  @OneToMany(mappedBy = "owner")
  public Set<OrderEntity> getOrders() {

    return this.orders;
  }

}
