package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.module.test.common.base.ComponentTest;

/**
 * @author MPRZEWOZ
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ItemRepositoryTest extends ComponentTest {

  private static final String GERMANY = "Germany";

  private static final String FRANCE = "France";

  private static final String POLAND = "Poland";

  private Long id = 100l;

  private ItemEntity onionEntity;

  private ItemEntity expensiveOnionEntity;

  private ItemEntity sausageEntity;

  private ItemEntity potatoEntity;

  private ItemEntity camembertEntity;

  @Inject
  private ItemRepository itemRepository;

  @Override
  public void doSetUp() {

    prepareDataForPaginationTest();

    this.sausageEntity = prepareEntity(this.sausageEntity, GERMANY, "Sausage", 20.00);
    this.expensiveOnionEntity = prepareEntity(this.expensiveOnionEntity, POLAND, "expensiveOnion", 20.00);
    this.camembertEntity = prepareEntity(this.camembertEntity, FRANCE, "Camembert", 5.00);

    this.itemRepository.save(this.expensiveOnionEntity);
    this.itemRepository.save(this.sausageEntity);
    this.itemRepository.save(this.camembertEntity);
  }

  @Override
  protected void doTearDown() {

    super.doTearDown();
    this.itemRepository.deleteAll();
  }

  /**
   *
   */
  private void prepareDataForPaginationTest() {

    addEntityXTimes(this.onionEntity, 22, POLAND, "Onion", 10.00);
    addEntityXTimes(this.potatoEntity, 20, POLAND, "Potato", 5.00);
  }

  @Test
  public void shouldFindAllItems() {

    // when
    List<ItemEntity> foundItems = this.itemRepository.findAll();

    // then
    assertThat(foundItems).hasSize(45);
  }

  @Test
  public void shouldReturn3ElementsOnlyPagination3AndSortByDescription() {

    // given
    Sort sort = Sort.by("description");
    Pageable pageable = PageRequest.of(0, 3, sort);
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    criteria.setPageable(pageable);
    // when
    Page<ItemEntity> foundItems = this.itemRepository.findByCriteria(criteria);

    // then
    assertThat(foundItems).hasSize(3);
    assertThat(checkIfListContainsItemWithGivenDescription(foundItems, POLAND)).isTrue();
    assertThat(checkIfListContainsItemWithGivenDescription(foundItems, FRANCE)).isTrue();
    assertThat(checkIfListContainsItemWithGivenDescription(foundItems, GERMANY)).isTrue();
    assertThat(foundItems.stream().findFirst().get().getDescription()).isEqualTo(FRANCE);

  }

  @Test
  public void shouldReturn20ElementsWithPrice10() {

    // given
    Sort sort = Sort.by("name");
    Pageable pageable = PageRequest.of(0, 20, sort);
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    criteria.setPrice(10.00);
    criteria.setPageable(pageable);
    // when
    Page<ItemEntity> foundItems = this.itemRepository.findByCriteria(criteria);

    // then
    assertThat(foundItems).hasSize(20);
    assertThat(foundItems.stream().findFirst().get().getName()).isEqualTo("Onion0");
    assertThat(foundItems.stream().allMatch(c -> c.getName().contains("Onion"))).isTrue();

  }

  @Test
  public void shouldReturn20PotatoesSortedAscending() {

    // given
    Sort sort = Sort.by("name");
    Pageable pageable = PageRequest.of(0, 20, sort);
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    criteria.setName("pot");
    criteria.setPageable(pageable);
    // when
    Page<ItemEntity> foundItems = this.itemRepository.findByName(criteria);

    // then
    assertThat(foundItems).hasSize(20);
    assertThat(foundItems.stream().findFirst().get().getName()).isEqualTo("Potato0");
    assertThat(foundItems.stream().allMatch(c -> c.getName().contains("Potato"))).isTrue();

  }

  @Test
  public void shouldChangePotato0PriceTo22() {

    // when
    ItemEntity item = this.itemRepository.changeItemsPrice("Potato0", 22.00);
    // then
    assertThat(item.getPrice()).isEqualTo(22.00);
  }

  // TODO use description
  // TODO use name
  // TODO use description and name
  // TODO use description and name and price
  // TODO use name and price
  // TODO use description and price

  private boolean checkIfListContainsItemWithGivenDescription(Page<ItemEntity> listOfEntities,
      String givenDescription) {

    return listOfEntities.stream().anyMatch(c -> c.getDescription().equals(givenDescription));
  }

  private void addEntityXTimes(ItemEntity entity, int times, String description, String name, Double price) {

    for (int i = 0; i < times; i++) {
      entity = prepareEntity(entity, description, name + i, price);
      this.itemRepository.save(entity);
    }
  }

  /**
  *
  */
  private ItemEntity prepareEntity(ItemEntity entity, String description, String name, Double price) {

    entity = new ItemEntity();
    entity.setDescription(description);
    entity.setName(name);
    entity.setPrice(price);
    entity.setId(this.id++);
    entity.setModificationCounter(0);
    return entity;
  }

}
