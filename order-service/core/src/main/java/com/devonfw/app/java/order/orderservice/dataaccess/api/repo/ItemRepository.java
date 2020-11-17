package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import static com.querydsl.core.alias.Alias.$;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.LikePatternSyntax;
import com.devonfw.module.jpa.dataaccess.api.QueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.devonfw.app.java.order.orderservice.common.api.Item;

/**
 * @author MPRZEWOZ
 */
public interface ItemRepository extends DefaultRepository<ItemEntity> {

	/**
	 * @param criteria
	 * @return Page<ItemEntity>
	 */
	default Page<ItemEntity> findByCriteria(ItemSearchCriteriaTo criteria) {

    ItemEntity alias = newDslAlias();
    JPAQuery<ItemEntity> query = newDslQuery(alias);
    String name = criteria.getName();
    if (name != null && !name.isEmpty()) {
      QueryUtil.get().whereString(query, $(alias.getName()), name, criteria.getNameOption());
    }

    String description = criteria.getDescription();
    if (StringUtils.isNoneEmpty(description)) {
      QueryUtil.get().whereString(query, $(alias.getDescription()), description, criteria.getDescriptionOption());
    }

    Double price = criteria.getPrice();
    if (price != null) {
      query.where($(alias.getPrice()).eq(price));
    }
    addOrderBy(query, alias, criteria.getPageable().getSort());
    return QueryUtil.get().findPaginated(criteria.getPageable(), query, true);
  }

	/**
	 * Add sorting to the given query on the given alias
	 *
	 * @param query to add sorting to
	 * @param alias to retrieve columns from for sorting
	 * @param sort specification of sorting
	 */
	public default void addOrderBy(JPAQuery<ItemEntity> query, ItemEntity alias, Sort sort) {

    if (sort != null && sort.isSorted()) {
      Iterator<Order> it = sort.iterator();
      while (it.hasNext()) {
        Order next = it.next();
        switch (next.getProperty()) {
          case "description":
            if (next.isAscending()) {
              query.orderBy($(alias.getDescription()).asc());
            } else {
              query.orderBy($(alias.getDescription()).desc());
            }
            break;
          case "name":
            if (next.isAscending()) {
              query.orderBy($(alias.getName()).asc());
            } else {
              query.orderBy($(alias.getName()).desc());
            }
            break;
          case "price":
            if (next.isAscending()) {
              query.orderBy($(alias.getPrice()).asc());
            } else {
              query.orderBy($(alias.getPrice()).desc());
            }
            break;
          default:
            throw new IllegalArgumentException("Sorted by the unknown property '" + next.getProperty() + "'");
        }
      }
    }
  }

	default Page<ItemEntity> findByName(ItemSearchCriteriaTo criteria) {

    ItemEntity alias = newDslAlias();
    JPAQuery<ItemEntity> query = newDslQuery(alias);
    String name = criteria.getName();
    if (name != null && !name.isEmpty()) {
      QueryUtil.get().whereLike(query, $(alias.getName()), "*" + criteria.getName() + "*", LikePatternSyntax.GLOB, true,
          true);

      addOrderBy(query, alias, criteria.getPageable().getSort());
    }
    return QueryUtil.get().findPaginated(criteria.getPageable(), query, true);
  }

}
