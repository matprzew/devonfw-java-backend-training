package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.devonfw.app.java.order.orderservice.dataaccess.api.CustomerEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.OrderEntity;
import com.devonfw.module.test.common.base.ComponentTest;

/**
 * @author MPRZEWOZ
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class CustomerRepositoryTest extends ComponentTest {

  @Inject
  private CustomerRepository customerRepository;

  @Override
  public void doSetUp() {

  }

  @Override
  protected void doTearDown() {

    super.doTearDown();
    this.customerRepository.deleteAll();
  }

  @Test
  public void shouldRemoveCustomer() {

    // given
    CustomerEntity customerEntity = new CustomerEntity();
    Set<OrderEntity> ordersSet = new HashSet<>();
    customerEntity.setFirstname("Firstname");
    customerEntity.setId(100l);
    customerEntity.setLastname("Lastname");
    customerEntity.setModificationCounter(0);
    customerEntity.setOrders(ordersSet);
    Long customerId = this.customerRepository.save(customerEntity).getId();
    // when
    this.customerRepository.deleteById(customerId);
    // then
    List<CustomerEntity> result = this.customerRepository.findAll();

    assertThat(result).isEmpty();

  }
}
