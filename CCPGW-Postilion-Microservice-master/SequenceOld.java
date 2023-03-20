//package com.truteq;
//
//import java.io.IOException;
//import org.apache.log4j.Logger;
//
//
///**
// * This service will supply the user with incrementing integer(long) values. The
// * bottom and top can be specified at startup or configure time. When the top is
// * reached it will simply wrap around and start supplying values from the bottom
// * again. You can slo specify a text file into which the current value is saved
// * at regular intervals. When this service starts up, it will read the last
// * value from that file.
// *
// * @author dvdm
// * @created December 5, 2002
// */
//
//public class SequenceOld {
//
//  protected Logger mLogger = Logger.getLogger(SequenceOld.class);  
//  /** The file into which the last known sequence is saved. */
//  protected java.lang.String mFileLocation = null;
//
//  /** Description of the Field */
//  protected long mSaveInterval = 1000;
//
//  /**
//   * The plasce where the sequence will start. The smallest value a application
//   * will get.
//   */
//  protected long mBottom = 0;
//
//  /** The biggest value a application will ever get. */
//  protected long mTop = 0;
//
//  /** My current value. */
//  protected long mCurrent = mBottom;
//
//  /** Description of the Field */
//  private final java.lang.StringBuffer mToStringBuffer = new java.lang.StringBuffer();
//
//
//
//  /** Constructor for the Sequence object */
//  public SequenceOld() {
//    mBottom = 0;
//    mTop = java.lang.Long.MAX_VALUE;
//    mCurrent = mBottom;
//  }
//
//
//
//  /**
//   * Constructor for the Sequence object
//   *
//   * @param aFileLocation Description of Parameter
//   */
//  public SequenceOld(
//      java.lang.String aFileLocation) {
//    mFileLocation = aFileLocation;
//    mSaveInterval = 60000;
//    mBottom = 0;
//    mTop = java.lang.Long.MAX_VALUE;
//  }
//
//
//  /**
//   * Constructor for the Sequence object
//   *
//   * @param aFileLocation Description of Parameter
//   * @param aSaveInterval Description of Parameter
//   */
//  public SequenceOld(
//      java.lang.String aFileLocation,
//      long aSaveInterval) {
//    mFileLocation = aFileLocation;
//    mSaveInterval = aSaveInterval;
//    mBottom = 0;
//    mTop = java.lang.Long.MAX_VALUE;
//  }
//
//
//  /**
//   * Constructor for the Sequence object
//   *
//   * @param aFileLocation Description of Parameter
//   * @param aSaveInterval Description of Parameter
//   * @param aBottom Description of Parameter
//   * @param aTop Description of Parameter
//   */
//  public SequenceOld(
//      java.lang.String aFileLocation,
//      long aSaveInterval,
//      long aBottom,
//      long aTop) {
//    mFileLocation = aFileLocation;
//    mSaveInterval = aSaveInterval;
//    mBottom = aBottom;
//    mTop = aTop;
//  }
//
//
//  /**
//   * Gets the serviceState attribute of the Sequence object
//   *
//   * @return The serviceState value
//   */
//  public int getServiceState() {
//    return 100;
//  }
//
//
//
//  /**
//   * Gets the Current attribute of the Sequence object
//   *
//   * @return The Current value
//   */
//  public synchronized long getCurrent() {
//    return mCurrent;
//  }
//
//
//
//  /**
//   * Supplies this object with its configuration.
//   *
//   * @param aConfiguration Description of the Parameter
//   * @exception com.truteq.ConfigurationException if there is someting wrong
//   *      with the supplied configuration.
//   */
//  public void configure(
//        com.truteq.Configuration aConfiguration)
//      throws com.truteq.ConfigurationException {
//    mFileLocation = aConfiguration.getChild("file").getAsString();
//    mSaveInterval = aConfiguration.getChild("saveinterval").getAsLong();
//
//    try {
//      mBottom = aConfiguration.getChild("bottom").getAsLong();
//    } catch (java.lang.Exception vException) {
//      mBottom = 0;
//    }
//
//    try {
//      mTop = aConfiguration.getChild("top").getAsLong();
//    } catch (java.lang.Exception vException) {
//      mTop = java.lang.Long.MAX_VALUE;
//    }
//
//    if (mBottom >= mTop) {
//      throw new com.truteq.ConfigurationException("\"bottom\" can not be bigger than \"top\"!!!");
//    } else {
//    }
//
//    java.io.File vFile = new java.io.File(mFileLocation);
//    if (vFile.canWrite()) {
//    } else {
//      try {
//        if (vFile.createNewFile()) {
//          if (vFile.canWrite()) {
//          } else {
//            throw new com.truteq.ConfigurationException(mFileLocation + " is not writable");
//          }
//        } else {
//          throw new com.truteq.ConfigurationException("Unable to create file: " + mFileLocation);
//        }
//      } catch (java.io.IOException vException) {
//        throw new com.truteq.ConfigurationException(mFileLocation + " is not writable");
//      }
//    }
//  }
//
//
//
//  /**
//   * reconfigure.... not doing anyting at the moment.
//   *
//   * @param aConfiguration Description of the Parameter
//   * @exception com.truteq.ConfigurationException Description of the Exception
//   */
//
//  public void reconfigure(
//      com.truteq.Configuration aConfiguration)
//    throws com.truteq.ConfigurationException {
//
//  }
//
//
//
//  /**
//   * Start this service. Read the file for last values.
//   *
//   * @exception com.truteq.ServiceException Description of the Exception
//   */
//  public void start()
//    throws com.truteq.ServiceException {
//    java.io.BufferedReader vReader = null;
//    java.lang.String vCurrent = "";
//
//    try {
//      vReader = new java.io.BufferedReader(new java.io.FileReader(mFileLocation));
//      vCurrent = vReader.readLine();
//      mCurrent = java.lang.Long.parseLong(vCurrent);
//    } catch (java.io.FileNotFoundException vException) {
//      mLogger.error("Unable to find the sequence file- default \"current\" to \"bottom\".", vException);
//      mCurrent = mBottom;
//    } catch (java.io.IOException vException) {
//      mLogger.error("Error reading the sequence file- default \"current\" to \"bottom\".", vException);
//      mCurrent = mBottom;
//    } catch (java.lang.NumberFormatException vException) {
//      mLogger.error("Error parsing sequence file contents.  Default \"current\" to \"bottom\".", vException);
//      mCurrent = mBottom;
//    } finally {
//      if (vReader == null) {
//      } else {
//        try {
//          vReader.close();
//        } catch (java.io.IOException vException) {
//          mLogger.error(vException.getMessage(), vException);
//        }
//      }
//    }
//
////    if (mSaveInterval < 0) {
////    } else {
////      com.truteq.util.Timer vTimer = (com.truteq.util.Timer) com.truteq.lang.Locator.getService("Timer");
////      if (vTimer == null) {
////        throw new com.truteq.ServiceException("Unable to find the \"Timer\" service!");
////      } else {
////        vTimer.addTask(new SequenceFileSaver(this, mFileLocation), mSaveInterval);
////      }
////    }
//  }
//
//
//
//  /**
//   * Stop this service.
//   *
//   * @exception com.truteq.ServiceException Description of the Exception
//   */
//  public void stop()
//    throws com.truteq.ServiceException {
//  }
//
//
//
//  /**
//   * Return the next sequence number in this sequence.
//   *
//   * @return Description of the Return Value
//   */
//  public synchronized long next() {
//    if (mCurrent > mTop) {
//      mCurrent = mBottom;
//    } else {
//    }
//    return mCurrent++;
//  }
//
//
//  public synchronized long nextWithFlush() {
//    long vNewValue = next();
//    flushToDisk();
//    return vNewValue;
//  }
//
//
//  public long getBottom() {
//    return mBottom;
//  }
//
//
//  public long getTop() {
//    return mTop;
//  }
//
//
//  public void setBottom(
//      long aBottom) {
//    mBottom = aBottom;
//  }
//
//
//  public void setTop(
//      long aTop) {
//    mTop = aTop;
//  }
//
//
//  public void setCurrentValue(
//      long aValue) {
//    mCurrent = aValue;
//  }
//
//
//  public java.lang.String toString() {
//    mToStringBuffer.setLength(0);
//    mToStringBuffer.append(super.toString())
//        .append(", current: ")
//        .append(mCurrent)
//        .append(" [")
//        .append(mBottom)
//        .append(", ")
//        .append(mTop)
//        .append("]");
//    return mToStringBuffer.toString();
//  }
//
//
//
//  /**
//   * Return the current value of the sequence. Does not modify any of the
//   * internal values. Should really only be used to save it to some or other
//   * place.
//   *
//   * @return Description of the Return Value
//   */
//  protected synchronized long current() {
//    return mCurrent;
//  }
//
//
//  public void setSaveInterval(
//      long aSaveInterval) {
//    mSaveInterval = aSaveInterval;
//  }
//
//
//
//  protected void flushToDisk() {
//    java.io.Writer vWriter = null;
//    try {
//      vWriter = new java.io.FileWriter(mFileLocation);
//      java.lang.String vCurrentSequence = java.lang.Long.toString(mCurrent);
//      vWriter.write(vCurrentSequence, 0, vCurrentSequence.length());
//    } catch (IOException vException) {
//      mLogger.error(vException.getMessage(), vException);
//    } finally {
//      if (vWriter == null) {
//      } else {
//        try {
//          vWriter.flush();
//          vWriter.close();
//        } catch (java.io.IOException vException) {
//          mLogger.error(vException.getMessage(), vException);
//        }
//      }
//    }
//  }
//}
