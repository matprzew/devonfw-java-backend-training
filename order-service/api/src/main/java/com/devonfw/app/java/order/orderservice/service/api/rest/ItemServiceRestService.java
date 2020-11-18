package com.devonfw.app.java.order.orderservice.service.api.rest;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.module.rest.common.api.RestService;

/**
 * @author MPRZEWOZ
 *
 */
@Path("/orderservice/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ItemServiceRestService extends RestService {

  @GET
  @Path("/items/{id}")
  public ItemEto findItem(@PathParam("id") long id);

  @DELETE
  @Path("/items/{id}")
  public boolean deleteItem(@PathParam("id") long id);

  @GET
  @Path("/items/{name}/{price}")
  public ItemEto increaseItemPrice(@PathParam("name") String name, @PathParam("price") Double price);

  @GET
  @Path("/items/name/{name}")
  public Set<ItemEto> findItemsLike(@PathParam("name") String name);

  // @GET
  // @Path("/items/{criteria}")
  // public Page<ItemEto> findItems(@PathParam("criteria") ItemSearchCriteriaTo criteria);

  @PUT
  @Path("/items")
  public ItemEto saveItem(@RequestBody ItemEto item);

}
