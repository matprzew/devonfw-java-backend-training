package com.devonfw.app.java.order.orderservice.service.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderSearchCriteriaTo;

/**
 * The service interface for REST calls in order to execute the logic of component {@link Orderservice}.
 */
@Path("/orderservice/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface OrderserviceRestService {

  /**
   * Delegates to {@link Orderservice#findOrder}.
   *
   * @param id the ID of the {@link OrderEto}
   * @return the {@link OrderEto}
   */
  @GET
  @Path("/order/{id}/")
  public OrderEto getOrder(@PathParam("id") long id);

  /**
   * Delegates to {@link Orderservice#saveOrder}.
   *
   * @param order the {@link OrderEto} to be saved
   * @return the recently created {@link OrderEto}
   */
  @POST
  @Path("/order/")
  public OrderEto saveOrder(OrderEto order);

  /**
   * Delegates to {@link Orderservice#deleteOrder}.
   *
   * @param id ID of the {@link OrderEto} to be deleted
   */
  @DELETE
  @Path("/order/{id}/")
  public void deleteOrder(@PathParam("id") long id);

  /**
   * Delegates to {@link Orderservice#findOrderEtos}.
   *
   * @param searchCriteriaTo the pagination and search criteria to be used for finding orders.
   * @return the {@link Page list} of matching {@link OrderEto}s.
   */
  @Path("/order/search")
  @POST
  public Page<OrderEto> findOrders(OrderSearchCriteriaTo searchCriteriaTo);

  /**
   * Delegates to {@link Orderservice#findOrderCto}.
   *
   * @param id the ID of the {@link OrderCto}
   * @return the {@link OrderCto}
   */
  @GET
  @Path("/order/cto/{id}/")
  public OrderCto getOrderCto(@PathParam("id") long id);

  /**
   * Delegates to {@link Orderservice#findOrderCtos}.
   *
   * @param searchCriteriaTo the pagination and search criteria to be used for finding orders.
   * @return the {@link Page list} of matching {@link OrderCto}s.
   */
  @Path("/order/cto/search")
  @POST
  public Page<OrderCto> findOrderCtos(OrderSearchCriteriaTo searchCriteriaTo);

  /**
   * Delegates to {@link Orderservice#findCustomer}.
   *
   * @param id the ID of the {@link CustomerEto}
   * @return the {@link CustomerEto}
   */
  @GET
  @Path("/customer/{id}/")
  public CustomerEto getCustomer(@PathParam("id") long id);

  /**
   * Delegates to {@link Orderservice#saveCustomer}.
   *
   * @param customer the {@link CustomerEto} to be saved
   * @return the recently created {@link CustomerEto}
   */
  @POST
  @Path("/customer/")
  public CustomerEto saveCustomer(CustomerEto customer);

  /**
   * Delegates to {@link Orderservice#deleteCustomer}.
   *
   * @param id ID of the {@link CustomerEto} to be deleted
   */
  @DELETE
  @Path("/customer/{id}/")
  public void deleteCustomer(@PathParam("id") long id);

  /**
   * Delegates to {@link Orderservice#findCustomerEtos}.
   *
   * @param searchCriteriaTo the pagination and search criteria to be used for finding customers.
   * @return the {@link Page list} of matching {@link CustomerEto}s.
   */
  @Path("/customer/search")
  @POST
  public Page<CustomerEto> findCustomers(CustomerSearchCriteriaTo searchCriteriaTo);

  /**
   * Delegates to {@link Orderservice#findCustomerCto}.
   *
   * @param id the ID of the {@link CustomerCto}
   * @return the {@link CustomerCto}
   */
  @GET
  @Path("/customer/cto/{id}/")
  public CustomerCto getCustomerCto(@PathParam("id") long id);

  /**
   * Delegates to {@link Orderservice#findCustomerCtos}.
   *
   * @param searchCriteriaTo the pagination and search criteria to be used for finding customers.
   * @return the {@link Page list} of matching {@link CustomerCto}s.
   */
  @Path("/customer/cto/search")
  @POST
  public Page<CustomerCto> findCustomerCtos(CustomerSearchCriteriaTo searchCriteriaTo);

}
