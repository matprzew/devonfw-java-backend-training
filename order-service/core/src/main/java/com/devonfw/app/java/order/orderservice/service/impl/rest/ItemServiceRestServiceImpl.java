package com.devonfw.app.java.order.orderservice.service.impl.rest;

import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.http.util.Asserts;
import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.logic.api.ItemService;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.service.api.rest.ItemServiceRestService;

/**
 * @author MPRZEWOZ
 *
 */
@Named("ItemServiceRestService")
public class ItemServiceRestServiceImpl implements ItemServiceRestService {

  @Inject
  private ItemService itemService;

  @Override
  public ItemEto findItem(long id) {

    Asserts.check((Objects.nonNull(id) && 0 < id), "Id cannot be null or lower than 0");
    return this.itemService.findItem(id);
  }

  @Override
  public boolean deleteItem(long id) {

    Asserts.check((Objects.nonNull(id) && 0 < id), "Id cannot be null or lower than 0");
    return this.itemService.deleteItem(id);
  }

  @Override
  public ItemEto increaseItemPrice(String name, Double price) {

    return this.itemService.increaseItemPrice(name, price);
  }

  @Override
  public Set<ItemEto> findItemsLike(String name) {

    return this.itemService.findItemsLike(name);
  }

  @Override
  public ItemEto saveItem(ItemEto item) {

    return this.itemService.saveItem(item);
  }

  @Override
  public Page<ItemEto> findItems(ItemSearchCriteriaTo criteria) {

    return this.itemService.findItems(criteria);

  }

}
