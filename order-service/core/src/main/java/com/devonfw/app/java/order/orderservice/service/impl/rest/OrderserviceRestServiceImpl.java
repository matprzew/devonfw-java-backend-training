package com.devonfw.app.java.order.orderservice.service.impl.rest;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.service.api.rest.OrderserviceRestService;

/**
 * The service implementation for REST calls in order to execute the logic of component {@link Orderservice}.
 */
@Named("OrderserviceRestService")
public class OrderserviceRestServiceImpl implements OrderserviceRestService {

  @Inject
  private Orderservice orderservice;

  @Override
  public OrderEto getOrder(long id) {

    return this.orderservice.findOrder(id);
  }

  @Override
  public OrderEto saveOrder(OrderEto order) {

    return this.orderservice.saveOrder(order);
  }

  @Override
  public void deleteOrder(long id) {

    this.orderservice.deleteOrder(id);
  }

  @Override
  public Page<OrderEto> findOrders(OrderSearchCriteriaTo searchCriteriaTo) {

    return this.orderservice.findOrders(searchCriteriaTo);
  }

  @Override
  public OrderCto getOrderCto(long id) {

    return this.orderservice.findOrderCto(id);
  }

  @Override
  public Page<OrderCto> findOrderCtos(OrderSearchCriteriaTo searchCriteriaTo) {

    return this.orderservice.findOrderCtos(searchCriteriaTo);
  }

  @Override
  public CustomerEto getCustomer(long id) {

    return this.orderservice.findCustomer(id);
  }

  @Override
  public CustomerEto saveCustomer(CustomerEto customer) {

    return this.orderservice.saveCustomer(customer);
  }

  @Override
  public void deleteCustomer(long id) {

    this.orderservice.deleteCustomer(id);
  }

  @Override
  public Page<CustomerEto> findCustomers(CustomerSearchCriteriaTo searchCriteriaTo) {

    return this.orderservice.findCustomers(searchCriteriaTo);
  }

  @Override
  public CustomerCto getCustomerCto(long id) {

    return this.orderservice.findCustomerCto(id);
  }

  @Override
  public Page<CustomerCto> findCustomerCtos(CustomerSearchCriteriaTo searchCriteriaTo) {

    return this.orderservice.findCustomerCtos(searchCriteriaTo);
  }

}
