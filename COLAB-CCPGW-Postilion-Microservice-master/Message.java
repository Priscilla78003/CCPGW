package com.truteq.protocol;

/**
 * A simple message interface. All message (like tounits, or to/from clients)
 * must implement this interface.
 *
 *@author Deon van der Merwe
 *@created 17 December 2002
 */
public interface Message {

  public final static int USSD = 3;
  public final static int SMS = 4;
  public final static int SMS_DELIVER = 5;
  public final static int GAIN = 6;
  public final static int TLV = 7;
  public final static int SMPP = 8;
  public final static int AT = 9;
  public final static int SMTP = 10;
  public final static int POP3 = 11;
  public final static int FILE = 12;
  public final static int COUNTER = 13;
  public final static int TEXT = 14;
  public final static int XML = 15;
  public final static int XML_RESPONSE = 16;
  public final static int XML_REQUEST = 17;
  public final static int XML_INPUT = 18;
  public final static int XML_RESULT = 19;
  public final static int FOUR_X_FOUR = 20;

  public final static int POSITION_REQUEST = 21;
  public final static int POSITION_RESPONSE = 22;
  public final static int POSITION_ERROR = 23;
  public final static int CELLID_POSITION_REQUEST = 24;
  public final static int CELLID = 25;
  public final static int CELLID_POSITION_RESPONSE = 26;

  public final static int BILLING_DEBIT = 30;
  public final static int BILLING_CREDIT = 31;
  public final static int BILLING_CHECK = 32;
  public final static int BILLING_EVENT = 33;

  public final static int SSMI = 40;

  public final static int DFI = 41;
  public final static int DTI = 42;

  public final static int MSISDN_LOOKUP = 43;
  public final static int MNP_LOOKUP = 44;

  public final static int DOLPHIN = 50;

  public final static int SPP = 60;

  public final static int SNMP4J = 70;
  public final static int SNMP = 71;

  public final static int VALIPORT = 80;

  public final static int MMS = 90;
  public final static int MMS_DELIVER = 779;

  public final static int RADIUS_PACKET = 91;

  public final static int WAP_PUSH = 92;

  public final static int MAP = 93;

  public final static int IMSI_REQUEST = 94;

  public final static int SRI_REQUEST = 95;
  
  public final static int MNP_REQUEST = 96;

  public final static int DIAMETER_PACKET = 97;

  public final static int MO_SRI_REQUEST = 98;

  public final static int ELSTER_METER_A100 = 100;
  public final static int ELSTER_METER_A1700 = 101;
  public final static int ELSTER_METER_A100C = 102;
  public final static int ELSTER_METER_A1100 = 103;

  public final static int CRUISER = 200;
  public final static int CRUISER_YAT = 201;

  public final static int TTALK = 1000;

  public final static int OMC = 2000;

  public final static int MX2 = 3000;
  public final static int ESB_CLIENT = 3001;
  public final static int ESB_SERVER = 3002;
  public final static int ESB_PROXY = 3003;
  public final static int ESB_SMS = 3004;
  public final static int ESB_GPRS = 3005;

  public final static int MROUTE = 4000;

  public final static int PSNI = 5000;

  public final static int SEMA_USSD_WASP = 6000;

  public final static int SEMA_CIMS = 6001;

  public final static int MTN_UXML = 6002;

  public final static int LCDD = 6003;

  public final static int CCI = 6004;

  public final static int SS7 = 6005;

  public final static int ISO8583 = 6006;

  public final static int DEMO_NETCARE_SYSMAN = 7000;

  public final static int DEMO_NETCARE_TRULOC = 7001;

  public final static int CELLC_CRE = 8000;

  public final static int VMOBILE_PSBI = 8001;

  public final static int MTC_CSI = 9000;

  public final static int PRN = 8002;

  public final static int SEND_AUTH_INFO = 8003;

  public final static int LOCATION_UPDATE = 8004;

  public final static int SMS_NOTIFY = 8005;

  public final static int USP = 8006;  
  
  public final static int PIF = 8007;

  public final static int SMS_MESSAGE_ID = 8008;

  public final static int CDR = 60040;

  public static final int CIMD2 = 777;
  public static final int CIMD2_MO_HOLDER = 9777;

  public static final int SIP = 776;

  public static final int SIP_STATUS = 774;

  public static final int SIP_REQUEST = 773;

  public static final int SMS_3GPP = 772;//application/vnd.3gpp.sms

  public static final int SMS_3GPP2 = 771;//application/vnd.3gpp2.sms

  public static final int MSRP = 770;

  public static final int THIRD_PARTY_NOTIFY = 9001;//IP_SMSC

  public final static int SMS_STATUS = 778;
  public final static int SMS_SUBMIT_REPORT = 780;
  public final static int SMS_SUBMIT_REPORT_RESP = 781;
  public final static int SMS_DELIVER_RESP = 782;

  public final static int COMMAND_POISON_PILL = 783;

  public final static int ADVERT_MESSAGE = 784;

  public final static int MOBILESTATION_AVAILABLE = 8009;

  public final static int DNP3_FRAME = 8010;

  public final static int APIGW_REQUEST_WRAPPER_JSON = 10000;


  public int getType();


  /**
   * Write this message out to the supplied writer.
   *
   *@param aOutputStream Description of Parameter
   *@exception MessageException Description of Exception
   */
  public void write(
      java.io.DataOutput aOutputStream)
       throws com.truteq.protocol.MessageException;


  /**
   * Read this message from the supplied read.
   *
   *@param aInputStream Description of Parameter
   *@exception MessageException Description of Exception
   */
  public void read(
      java.io.DataInput aInputStream)
       throws com.truteq.protocol.MessageException;
}
