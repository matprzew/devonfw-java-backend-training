package com.devonfw.app.java.order.general.common.api.exception;

import net.sf.mmm.util.nls.api.NlsMessage;

/**
 * @author MPRZEWOZ
 *
 */
public class BusinessException extends ApplicationBusinessException {

  /**
   * The constructor.
   *
   * @param message
   */
  public BusinessException(NlsMessage message) {

    super(message);
  }

}
