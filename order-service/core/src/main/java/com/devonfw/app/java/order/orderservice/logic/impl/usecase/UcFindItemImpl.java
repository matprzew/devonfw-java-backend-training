package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import java.util.Set;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractItemUc;

/**
 * @author MPRZEWOZ
 *
 */
@Named
@Validated
@Transactional
public class UcFindItemImpl extends AbstractItemUc implements UcFindItem {

  private static final Logger LOG = LoggerFactory.getLogger(UcFindItemImpl.class);

  @Override
  public ItemEto findItem(long id) {

    ItemEntity itemEntity;
    LOG.debug("Get Item with id {} from database.", id);
    itemEntity = getItemRepository().getOne(id);

    return getBeanMapper().map(itemEntity, ItemEto.class);
  }

  @Override
  public Page<ItemEto> findItems(ItemSearchCriteriaTo criteria) {

    Page<ItemEntity> itemEntities = getItemRepository().findByCriteria(criteria);
    Page<ItemEto> itemEtos = itemEntities.map(c -> getBeanMapper().map(c, ItemEto.class));
    return itemEtos;
  }

  @Override
  public Page<ItemEto> findItemsLike(ItemSearchCriteriaTo criteria) {

    Page<ItemEntity> itemEntities = getItemRepository().findByNameLike(criteria);
    Page<ItemEto> itemEtos = itemEntities.map(c -> getBeanMapper().map(c, ItemEto.class));
    return itemEtos;
  }

  @Override
  public Set<ItemEto> findItemsLike(String name) {

    return getBeanMapper().mapSet(getItemRepository().findByNameContainingIgnoreCase(name), ItemEto.class);
  }

}
