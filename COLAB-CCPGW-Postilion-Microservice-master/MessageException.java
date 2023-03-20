package com.truteq.protocol;



/**
 * This exception is thrown by the implementation of the <code>Message</code>
 * class.
 *
 * @author Deon van der Merwe
 * @created 05 December 2002
 */
public class MessageException
     extends java.lang.Exception {


  /** Constructor for the MessageException object */
  public MessageException() {
  }



  /**
   * Constructor for the MessageException object
   *
   * @param aMessage Description of Parameter
   */
  public MessageException(
      java.lang.String aMessage) {
    super(aMessage);
  }
}

