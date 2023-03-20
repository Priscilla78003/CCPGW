package com.truteq;

/**
 * This presents an interface with which the configuration for a object can be
 * retreived.
 *
 * @author    dvdm
 * @created   December 5, 2002
 */
public interface Configuration {


  /**
   * Returns the configuration of the child as a configuration object.
   *
   * @param aChildName                             Description of the Parameter
   * @return                                       The child value
   * @exception ConfigurationException             Description of the Exception
   */
  Configuration getChild(
      java.lang.String aChildName)
    throws ConfigurationException;


  /**
   * Return all the child nodes of this configuration in a iteration.
   *
   * @return   The children value
   */
  java.util.Iterator getChildren();


  /**
   * Return this node's name
   *
   * @return   The name value
   */
  java.lang.String getName();


  /**
   * Return this node as a string.
   *
   * @return   The asString value
   */
  java.lang.String getAsString();


  /**
   * Return this node as a boolean. <code>1 == true<br>
   * 0 == false<br>
   * "true" == true<br>
   * "false" == false</code>
   *
   * @return   The asBoolean value
   */
  boolean getAsBoolean();


  /**
   * Return this node as a integer.
   *
   * @return   The asInt value
   */
  int getAsInt();


  /**
   * Return this node as a long integer.
   *
   * @return   The asLong value
   */
  long getAsLong();


  /**
   * Return this node as a float.
   *
   * @return   The asFloat value
   */
  float getAsFloat();


  /**
   * Return the parameter with the suppled name as a string.
   *
   * @param aParameterName  Description of the Parameter
   * @return                The parameterAsString value
   */
  java.lang.String getParameterAsString(
      java.lang.String aParameterName);


  /**
   * Return the parameter with the suppled name as a boolean. <code>1 == true<br>
   * 0 == false<br>
   * "true" == true<br>
   * "false" == false</code>
   *
   * @param aParameterName  Description of the Parameter
   * @return                The parameterAsBoolean value
   */
  boolean getParameterAsBoolean(
      java.lang.String aParameterName);


  /**
   * Return the parameter with the suppled name as a integer.
   *
   * @param aParameterName  Description of the Parameter
   * @return                The parameterAsInt value
   */
  int getParameterAsInt(
      java.lang.String aParameterName);


  long getParameterAsLong(
      java.lang.String aParameterName);


  /**
   * Return the parameter with the suppled name as a float.
   *
   * @param aParameterName  Description of the Parameter
   * @return                The parameterAsFloat value
   */
  float getParameterAsFloat(
      java.lang.String aParameterName);
}

