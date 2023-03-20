package com.truteq;


/**
 * Thrown by a service if something with the <code>start</code> or
 *    <code>stop</code> is wrong.
 */
public class ServiceException
      extends java.lang.Exception {


  /**
   * Default constructor
   */
  public ServiceException () {
  }



  /**
   * Basic constructor.
   *
   * @param <code>aMessage</code> A description of the exception.
   */
  public ServiceException (
        java.lang.String aMessage) {
    super (aMessage);
  }
}
