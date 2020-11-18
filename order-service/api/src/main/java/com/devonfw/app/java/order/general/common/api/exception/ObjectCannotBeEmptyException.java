package com.devonfw.app.java.order.general.common.api.exception;

import net.sf.mmm.util.nls.api.NlsMessage;

/**
 * @author MPRZEWOZ
 *
 */
public class ObjectCannotBeEmptyException extends ApplicationTechnicalException {

  /**
   * The constructor.
   * @param message
   */
  public ObjectCannotBeEmptyException(NlsMessage message) {

    super(message);
  }

}
