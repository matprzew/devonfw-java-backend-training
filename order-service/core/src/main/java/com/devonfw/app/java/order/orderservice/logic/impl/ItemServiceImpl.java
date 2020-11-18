package com.devonfw.app.java.order.orderservice.logic.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.general.logic.base.AbstractComponentFacade;
import com.devonfw.app.java.order.orderservice.logic.api.ItemService;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;

/**
 * @author MPRZEWOZ
 *
 */
@Named
public class ItemServiceImpl extends AbstractComponentFacade implements ItemService {
  @Inject
  private UcFindItem ucFindItem;

  @Inject
  private UcManageItem ucManageItem;

  @Override
  public ItemEto findItem(long id) {

    return this.ucFindItem.findItem(id);
  }

  @Override
  public Page<ItemEto> findItems(ItemSearchCriteriaTo criteria) {

    return this.ucFindItem.findItems(criteria);
  }

  @Override
  public boolean deleteItem(long itemId) {

    return this.ucManageItem.deleteItem(itemId);
  }

  @Override
  public ItemEto saveItem(ItemEto item) {

    return this.ucManageItem.saveItem(item);
  }

  @Override
  public ItemEto increaseItemPrice(String name, Double price) {

    return this.ucManageItem.increaseItemPrice(name, price);
  }

  @Override
  public Page<ItemEto> findItemsLike(ItemSearchCriteriaTo criteria) {

    return this.ucFindItem.findItemsLike(criteria);
  }

  @Override
  public Set<ItemEto> findItemsLike(String name) {

    return this.ucFindItem.findItemsLike(name);
  }

}
