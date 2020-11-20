package com.devonfw.app.java.order.general.common.api.security;

import javax.inject.Named;

import com.devonfw.module.security.common.api.accesscontrol.AccessControlGroup;
import com.devonfw.module.security.common.base.accesscontrol.AccessControlConfig;

/**
 * @author MPRZEWOZ
 *
 */
@Named
public class ApplicationAccessControlConfig extends AccessControlConfig {

  public static final String APP_ID = "OrderService";

  public static final String PREFIX = APP_ID + ".";

  public static final String PERMISSION_FIND_ITEM = PREFIX + "FindItem";

  public static final String PERMISSION_FIND_ORDER = PREFIX + "FindOrder";

  public static final String PERMISSION_CREATE_ORDER = PREFIX + "CreateOrder";

  public static final String PERMISSION_DELETE_ORDER = PREFIX + "DeleteOrder";

  public static final String PERMISSION_ADD_ITEM = PREFIX + "AddItem";

  public static final String PERMISSION_REMOVE_ITEM = PREFIX + "RemoveItem";

  public static final String PERMISSION_EDIT_ITEM = PREFIX + "EditItem";

  public static final String PERMISSION_REMOVE_CUSTOMER = PREFIX + "RemoveCustomer";

  public static final String GROUP_READ_MASTER_DATA = PREFIX + "ReadMasterData";

  public static final String GROUP_CUSTOMER = PREFIX + "Customer";

  public static final String GROUP_EMPLOYEE = PREFIX + "Employee";

  public static final String GROUP_ADMIN = PREFIX + "Admin";

  public ApplicationAccessControlConfig() {

    super();
    AccessControlGroup readMasterData = group(GROUP_READ_MASTER_DATA, PERMISSION_FIND_ITEM);
    AccessControlGroup customer = group(GROUP_CUSTOMER, readMasterData, PERMISSION_FIND_ORDER, PERMISSION_CREATE_ORDER,
        PERMISSION_DELETE_ORDER);
    AccessControlGroup employee = group(GROUP_EMPLOYEE, customer, PERMISSION_ADD_ITEM, PERMISSION_EDIT_ITEM,
        PERMISSION_REMOVE_ITEM);
    AccessControlGroup admin = group(GROUP_ADMIN, employee, PERMISSION_REMOVE_CUSTOMER);
  }
}
