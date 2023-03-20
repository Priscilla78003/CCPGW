package com.truteq;


/**
 * Thrown by a object when the configuration is incorrect.
 */
public class ConfigurationException
      extends java.lang.Exception {


  /**
   * Default constructor
   */
  public ConfigurationException () {
  }



  /**
   * Basic constructor.
   *
   * @param <code>aMessage</code> A description of the exception.
   */
  public ConfigurationException (
        java.lang.String aMessage) {
    super (aMessage);
  }
}
