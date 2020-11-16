package com.devonfw.app.java.order.orderservice.dataaccess.api;

import javax.persistence.Entity;

import com.devonfw.app.java.order.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.app.java.order.orderservice.common.api.Item;

/**
 * @author MPRZEWOZ
 */
@Entity(name = "Item")
public class ItemEntity extends ApplicationPersistenceEntity implements Item {

  private String description;

  private String name;

  private Double price;

  private static final long serialVersionUID = 1L;

  /**
   * @return description
   */
  public String getDescription() {

    return this.description;
  }

  /**
   * @param description new value of {@link #getdescription}.
   */
  public void setDescription(String description) {

    this.description = description;
  }

  /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return price
   */
  public Double getPrice() {

    return this.price;
  }

  /**
   * @param price new value of {@link #getprice}.
   */
  public void setPrice(Double price) {

    this.price = price;
  }

}
