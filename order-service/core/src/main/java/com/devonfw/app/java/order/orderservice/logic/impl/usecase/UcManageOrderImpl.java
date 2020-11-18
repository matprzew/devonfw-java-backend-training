package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import java.util.Objects;

import javax.inject.Named;

import net.sf.mmm.util.nls.base.NlsMessagePlain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.general.common.api.exception.ObjectCannotBeEmptyException;
import com.devonfw.app.java.order.orderservice.dataaccess.api.OrderEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageOrder;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractOrderUc;

/**
 * Use case implementation for modifying and deleting Orders
 */
@Named
@Validated
@Transactional
public class UcManageOrderImpl extends AbstractOrderUc implements UcManageOrder {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UcManageOrderImpl.class);

  @Override
  public boolean deleteOrder(long orderId) {

    OrderEntity order = getOrderRepository().find(orderId);
    getOrderRepository().delete(order);
    LOG.debug("The order with id '{}' has been deleted.", orderId);
    return true;
  }

  @Override
  public OrderEto saveOrder(OrderEto order) {

    Objects.requireNonNull(order, "order");

    OrderEntity orderEntity = getBeanMapper().map(order, OrderEntity.class);

    // initialize, validate orderEntity here if necessary
    OrderEntity resultEntity = getOrderRepository().save(orderEntity);
    LOG.debug("Order with id '{}' has been created.", resultEntity.getId());
    return getBeanMapper().map(resultEntity, OrderEto.class);
  }

  @Override
  public OrderCto saveOrderWithItemsAndOwner(OrderCto order) {

    checkIfObjectIsNotNull(order);
    OrderEntity orderEntity = getBeanMapper().map(order, OrderEntity.class);
    return getBeanMapper().map(getOrderRepository().save(orderEntity), OrderCto.class);
  }

  /**
   * @param order
   */
  private void checkIfObjectIsNotNull(OrderCto order) {

    if (Objects.isNull(order)) {
      throw new ObjectCannotBeEmptyException(new NlsMessagePlain("Object cannot be null"));
    }
  }
}
