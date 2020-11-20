package com.devonfw.app.java.order.general.service.impl.rest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.devonfw.app.java.order.general.service.base.test.RestServiceTest;
import com.devonfw.app.java.order.orderservice.logic.api.ItemService;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.service.api.rest.ItemServiceRestService;
import com.devonfw.module.service.common.api.client.config.ServiceClientConfigBuilder;

/**
 * This class tests the login functionality of {@link SecurityRestServiceImpl}.
 */
@RunWith(SpringRunner.class)
public class ItemServiceRestServiceImplTest extends RestServiceTest {

  @Inject
  private ItemService itemService;

  private static final String LOGIN = "waiter";

  private static final String PASSWORD = "waiter";

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(ItemServiceRestServiceImplTest.class);

  private ItemEto itemEto;

  @Override
  public void doSetUp() {

    this.itemEto = new ItemEto();
    this.itemEto.setName("Name");
    this.itemEto.setDescription("Description");
    this.itemEto.setPrice(10.00);
    this.itemEto.setId(1l);
    this.itemEto.setModificationCounter(0);
    this.itemService.saveItem(this.itemEto);

  }

  @Test
  public void shouldGetItemById() {

    ItemServiceRestService itemRestService = getServiceClientFactory().create(ItemServiceRestService.class,
        new ServiceClientConfigBuilder().host("localhost").authBasic().userLogin(LOGIN).userPassword(PASSWORD)
            .buildMap());
    ItemEto itemEto = itemRestService.findItem(1);
    assertThat(itemEto).isNotNull();
    assertThat(itemEto.getName()).isEqualTo("name");
  }

  /**
   * Performs the login as required by a JavaScript client.
   *
   * @param userName the username of the user
   * @param tmpPassword the password of the user
   * @return @ {@link ResponseEntity} containing containing a cookie in its header.
   */
  private ResponseEntity<String> login(String userName, String tmpPassword) {

    String tmpUrl = "http://localhost:" + String.valueOf(this.port) + "/services/rest/login";

    HttpEntity<String> postRequest = new HttpEntity<>(
        "{\"j_username\": \"" + userName + "\", \"j_password\": \"" + tmpPassword + "\"}", new HttpHeaders());

    ResponseEntity<String> postResponse = new RestTemplate().exchange(tmpUrl, HttpMethod.POST, postRequest,
        String.class);
    return postResponse;
  }

}
