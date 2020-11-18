package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import javax.inject.Named;

import net.sf.mmm.util.nls.api.NlsMessage;
import net.sf.mmm.util.nls.base.NlsMessagePlain;

import com.devonfw.app.java.order.general.common.api.exception.BusinessException;
import com.devonfw.app.java.order.general.common.api.exception.ItemNotFoundException;
import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractItemUc;

/**
 * @author MPRZEWOZ
 *
 */
@Named
public class UcManageItemImpl extends AbstractItemUc implements UcManageItem {

  @Override
  public boolean deleteItem(long itemId) {

    boolean canElementBeDeleted = (getItemRepository().find(itemId) != null) ? true : false;
    getItemRepository().deleteById(itemId);
    return canElementBeDeleted;
  }

  @Override
  public ItemEto saveItem(ItemEto item) {

    ItemEntity itemEntity = getBeanMapper().map(item, ItemEntity.class);
    return getBeanMapper().map(getItemRepository().save(itemEntity), ItemEto.class);
  }

  @Override
  public ItemEto increaseItemPrice(String name, Double price) {

    ItemEntity itemEntity = getItemRepository().findByName(name);

    if (itemEntity != null) {
      checkIfGivenPriceIsHigherThanCurrent(price, itemEntity, name);
      itemEntity.setPrice(price);
    } else
      throw new ItemNotFoundException();

    return getBeanMapper().map(getItemRepository().save(itemEntity), ItemEto.class);
  }

  /**
   * @param price
   * @param itemEntity
   * @param message
   */
  private void checkIfGivenPriceIsHigherThanCurrent(Double price, ItemEntity itemEntity, String name) {

    NlsMessage message = new NlsMessagePlain(
        "Item: " + name + " price: " + itemEntity.getPrice() + "is higher or equal to given price: " + price);
    if (itemEntity.getPrice() >= price) {
      throw new BusinessException(message);
    }
    ;
  }
}
