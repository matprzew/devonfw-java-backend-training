package com.devonfw.app.java.order.orderservice.logic.api;

import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindCustomer;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindOrder;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageCustomer;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageOrder;

/**
 * Interface for Orderservice component.
 */
public interface Orderservice extends UcFindCustomer, UcManageCustomer, UcFindOrder, UcManageOrder {

}
