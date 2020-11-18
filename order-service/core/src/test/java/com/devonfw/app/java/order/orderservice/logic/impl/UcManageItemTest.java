package com.devonfw.app.java.order.orderservice.logic.impl;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.devonfw.app.java.order.general.common.api.exception.BusinessException;
import com.devonfw.app.java.order.general.common.api.exception.ItemNotFoundException;
import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.impl.usecase.UcManageItemImpl;
import com.devonfw.module.test.common.base.ComponentTest;

/**
 * @author MPRZEWOZ
 *
 */
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class UcManageItemTest extends ComponentTest {

  /**
   *
   */
  private static final double PRICE_25_00 = 25.00;

  /**
   *
   */
  private static final String ITEM_NAME = "Item";

  private ItemEto itemEto;

  private ItemEntity itemEntity;

  @Inject
  private UcManageItemImpl ucManageItem;

  @Override
  public void doSetUp() {

    this.itemEto = new ItemEto();
    // itemEto.setId(1l);
    this.itemEto.setName(ITEM_NAME);
    this.itemEto.setDescription("Description");
    this.itemEto.setPrice(20.00);

    this.ucManageItem.saveItem(this.itemEto);
  }

  @Override
  protected void doTearDown() {

  }

  @Test
  public void shouldIncreaseItemsPrice() {

    // when
    ItemEto result = this.ucManageItem.increaseItemPrice(ITEM_NAME, PRICE_25_00);
    // then
    assertThat(result).isNotNull();
    assertThat(result.getPrice()).isEqualTo(PRICE_25_00);

  }

  @Test(expected = ItemNotFoundException.class)
  public void shouldThrowItemNotFoundExceptionWhenItemWasNotFound() {

    // when
    ItemEto result = this.ucManageItem.increaseItemPrice("Not exisiting name", PRICE_25_00);
  }

  @Test(expected = BusinessException.class)
  public void shouldThrowBusinessExceptionForNewPriceLowerThanOld() {

    // when
    ItemEto result = this.ucManageItem.increaseItemPrice(ITEM_NAME, 10.00);
  }

}