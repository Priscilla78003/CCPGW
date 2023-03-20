
ALTER TABLE merchant_currency_map ADD COLUMN stationNumber VARCHAR(50);


CREATE TABLE repeats (systemTraceAuditNumber VARCHAR(10) PRIMARY KEY,
messageType VARCHAR(4),
primaryAccountNumber VARCHAR(50),
processingCode   VARCHAR(10),
transactionAmount INT,
expiryDate VARCHAR(5),
settlementDate VARCHAR(5),
transmissionDateTime      VARCHAR(15),
localTranTime             VARCHAR(10),
localTranDate             VARCHAR(10),
merchantType VARCHAR(20),        
posEntryMode VARCHAR(50), 
posConditionCode   VARCHAR(5),
acquiringInstitutionCode   VARCHAR(20),
retrievalRefNumber VARCHAR(20),
authorizationIdResponse VARCHAR(10),
terminalId    VARCHAR(20),
merchantId    VARCHAR(50), 
cardAcceptorNameLocation VARCHAR(125), 
currencyCode VARCHAR(4),        
originalDataElements VARCHAR(125),
messageReasonCode VARCHAR(125),
posDataCode   VARCHAR(20),
status INT) ENCRYPTED=YES;


ALTER TABLE authorise ADD COLUMN routing VARCHAR(100);

ALTER TABLE authorise ADD COLUMN settlementDate VARCHAR(5);

CREATE TABLE authorise (id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       transactionId VARCHAR(16),
                       messageType VARCHAR(4),
                       primaryAccountNumber VARCHAR(50),
                       processingCode   VARCHAR(10),
                       amount INT,
                       transmissionDateTime VARCHAR(15),
                       systemTraceAuditNumber VARCHAR(50),
                       localTranTime   VARCHAR(10),
                       localTranDate   VARCHAR(10),
                       expiryDate VARCHAR(5),
                       settlementDate VARCHAR(5),
                       posConditionCode   VARCHAR(5),
                       acquiringInstitutionCode   VARCHAR(20),
                       retrievalRef   VARCHAR(20),
                       terminalId    VARCHAR(20),
                       authIdResponse VARCHAR(10),
                       posDataCode   VARCHAR(20),
                       routing VARCHAR(50)) ENCRYPTED=YES; 
                        
                        
ALTER TABLE originaldata ADD COLUMN transactionId VARCHAR(16);                        
                        
Create TABLE originaldata (id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           transactionId VARCHAR(16),
                           messageType VARCHAR(4), 
                           systemTraceAuditNumber VARCHAR(6),
                           transmissionDateTime VARCHAR(10),
                           acquiringInstitutionCode VARCHAR(11),
                           forwardingInstitudeId VARCHAR(11)) ENCRYPTED=YES;  
                           
                           
                           
CREATE TABLE capture (id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       transactionId VARCHAR(16),
                       messageType VARCHAR(4),
                       primaryAccountNumber VARCHAR(50),
                       processingCode   VARCHAR(10),
                       transactionAmount INT,
                       transmissionDateTime VARCHAR(15),
                       systemTraceAuditNumber VARCHAR(50),
                       localTranTime   VARCHAR(10),
                       localTranDate   VARCHAR(10), 
                       posConditionCode   VARCHAR(5),
                       acquiringInstitutionCode   VARCHAR(20),
                       retrievalRefNumber   VARCHAR(20),
                       terminalId    VARCHAR(20),
                       posDataCode   VARCHAR(20),
                       routingInformation VARCHAR(50),
                       expiryDate VARCHAR(5)) ENCRYPTED=YES;
                       
CREATE TABLE country
(
   id     INT           AUTO_INCREMENT PRIMARY KEY NOT NULL,
   name    VARCHAR(50)   NOT NULL,
   alpha2  VARCHAR(15)   NOT NULL,
   alpha3  VARCHAR(15)   NOT NULL,
   code    INT           NOT NULL
)                       
                       
CREATE TABLE cardtype
(
   id    INT           AUTO_INCREMENT PRIMARY KEY NOT NULL,
   type  VARCHAR(50)   NOT NULL
)
CREATE TABLE bank
(
   id              INT           AUTO_INCREMENT PRIMARY KEY NOT NULL,
   name            VARCHAR(50)   NOT NULL,
   code            INT           NOT NULL,
   bankciphercode  VARCHAR(50)   NOT NULL
)
CREATE TABLE bankaccount
(
   id                      INT            AUTO_INCREMENT PRIMARY KEY NOT NULL,
   name                    VARCHAR(50)    NOT NULL,
   cif                     VARCHAR(50)    DEFAULT NULL,
   account_number          VARCHAR(50)    NOT NULL,
   creation_date           TIMESTAMP      DEFAULT current_timestamp() NOT NULL,
   bank_id                 INT            NOT NULL,
   type_id                 INT            NOT NULL,
   customer_id             INT            NOT NULL,
   to_account_description  VARCHAR(255)   DEFAULT NULL,
   costcenter              VARCHAR(50)    DEFAULT NULL,
   CONSTRAINT fk_bankaccount_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
)


CREATE TABLE sessionuser
(
   id         BIGINT        AUTO_INCREMENT PRIMARY KEY NOT NULL,
   datetime   TIMESTAMP     DEFAULT current_timestamp() NOT NULL,
   sessionid  VARCHAR(50)   NOT NULL,
   user_id    INT           NOT NULL,
   CONSTRAINT fk_session_users FOREIGN KEY (user_id) REFERENCES users (id)
) ENCRYPTED=YES;

CREATE UNIQUE INDEX uuser_id
   ON sessionuser (user_id ASC);


CREATE TABLE address
(
   id           INT            AUTO_INCREMENT PRIMARY KEY NOT NULL,
   line1        VARCHAR(100)   NOT NULL,
   line2        VARCHAR(100)   NOT NULL,
   line3        VARCHAR(100)   NOT NULL,
   postal_code  INT            NOT NULL,
   state        VARCHAR(50)    NOT NULL,
   city         VARCHAR(50)    NOT NULL,
   country      VARCHAR(50)    NOT NULL,
   customer_id  INT            NOT NULL,
   CONSTRAINT fk_address_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
)  ENCRYPTED=YES;

CREATE TABLE users
(
   id               INT            AUTO_INCREMENT PRIMARY KEY NOT NULL,
   secretkey        VARCHAR(100)   NOT NULL,
   username         VARCHAR(50)    NOT NULL,
   password         VARCHAR(100)   NOT NULL,
   marketcomms      BIT            NOT NULL,
   customer_id      INT            NOT NULL,
   role             VARCHAR(50)    DEFAULT NULL,
   totp             BIT            DEFAULT NULL,
   termsconditions  BIT            DEFAULT b'1' NOT NULL,
   acceptedon       TIMESTAMP      DEFAULT current_timestamp() NOT NULL
   CONSTRAINT fk_users_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
)  ENCRYPTED=YES;


CREATE TABLE merchantadditional2
(
   logo         LONGTEXT       DEFAULT NULL,
   description  VARCHAR(250)   DEFAULT NULL,
   merchant_id  INT            DEFAULT NULL
)  ENCRYPTED=YES;

CREATE TABLE merchantdocuments
(
   document1           LONGTEXT   DEFAULT NULL,
   document1_1         LONGTEXT   DEFAULT NULL,
   document2           LONGTEXT   DEFAULT NULL,
   document2_1         LONGTEXT   DEFAULT NULL,
   document3           LONGTEXT   DEFAULT NULL,
   document3_1         LONGTEXT   DEFAULT NULL,
   document3_2         LONGTEXT   DEFAULT NULL,
   merchant_id         INT        DEFAULT NULL,
   document_signature  LONGTEXT   DEFAULT NULL
)  ENCRYPTED=YES;


CREATE TABLE customer
(
   id              INT           AUTO_INCREMENT PRIMARY KEY NOT NULL,
   creation_date   TIMESTAMP     DEFAULT current_timestamp() NOT NULL,
   name            VARCHAR(50)   NOT NULL,
   last_name       VARCHAR(50)   NOT NULL,
   email           VARCHAR(50)   NOT NULL,
   phone_number    VARCHAR(50)   NOT NULL,
   dateofbirth     DATE          NOT NULL,
   status          VARCHAR(50)   NOT NULL,
   balance         INT           NOT NULL,
   ref_user_id     INT           DEFAULT NULL,
   ref_address_id  INT           DEFAULT NULL,
   salutation      VARCHAR(10)   DEFAULT NULL
)  ENCRYPTED=YES;

CREATE TABLE merchant
(
   id                     INT             AUTO_INCREMENT PRIMARY KEY NOT NULL,
   guid                   VARCHAR(255)    NOT NULL,
   businessname           VARCHAR(50)     NOT NULL,
   businessnumber         VARCHAR(50)     NOT NULL,
   rsapublickey           VARCHAR(255)    NOT NULL,
   rsaprivatekey          VARCHAR(1024)   NOT NULL,
   secretkey              VARCHAR(255)    DEFAULT NULL,
   customer_id            INT             NOT NULL,
   cardacceptorname       VARCHAR(50)     DEFAULT NULL,
   phone_number           VARCHAR(50)     DEFAULT NULL,
   non3D                  BIT             DEFAULT NULL,
   currencyAbbrev         VARCHAR(10)     DEFAULT NULL,
   refund                 BIT             DEFAULT NULL,
   notifEmail             VARCHAR(255)    DEFAULT NULL,
   notifURL               VARCHAR(255)    DEFAULT NULL,
   failURL                VARCHAR(255)    DEFAULT NULL,
   cancelURL              VARCHAR(255)    DEFAULT NULL,
   successURL             VARCHAR(255)    DEFAULT NULL,
   integrate              VARCHAR(50)     DEFAULT NULL,
   linked_account         VARCHAR(100)    DEFAULT NULL,
   fee_code               VARCHAR(100)    DEFAULT NULL,
   external_merchant_id   VARCHAR(100)    DEFAULT NULL,
   merchant_segment       VARCHAR(100)    DEFAULT NULL,
   application_status_id  INT             DEFAULT 1 NOT NULL,
   progress               INT             DEFAULT 0 NOT NULL,
   fee_id                 INT             DEFAULT 1 NOT NULL
) ENCRYPTED=YES;


CREATE TABLE merchant_currency_map
(
   id                        INT             PRIMARY KEY NOT NULL,
   merchant_name             VARCHAR(120)    DEFAULT NULL,
   merchant_no               VARCHAR(8)      DEFAULT NULL,
   terminal_no               VARCHAR(8)      DEFAULT NULL,
   currency                  VARCHAR(8)      DEFAULT NULL,
   email_address             VARCHAR(120)    DEFAULT NULL,
   auto_generated_mid        VARCHAR(80)     DEFAULT NULL,
   auto_generated_tid        VARCHAR(80)     DEFAULT NULL,
   mcc                       VARCHAR(80)     DEFAULT NULL,
   merchant_account_code     VARCHAR(2000)   DEFAULT NULL,
   cardAcceptorNameLocation  VARCHAR(125)    DEFAULT NULL
) ENCRYPTED=YES;

CREATE TABLE orders
(
   order_id             VARCHAR(50)    DEFAULT NULL,
   amount               INT            DEFAULT NULL,
   customer_first_name  VARCHAR(50)    NOT NULL,
   customer_last_name   VARCHAR(50)    NOT NULL,
   customer_email       VARCHAR(50)    NOT NULL,
   description          VARCHAR(150)   NOT NULL,
   currency_code        INT            NOT NULL,
   merchant_id          INT            NOT NULL,
   datetime             TIMESTAMP      DEFAULT current_timestamp() NOT NULL,
   callback_key         VARCHAR(50)    DEFAULT NULL,
   callback_url         VARCHAR(255)   DEFAULT NULL,
   transaction_ref      VARCHAR(255)   DEFAULT NULL,
   status_value         INT            DEFAULT NULL,
   status               VARCHAR(255)   DEFAULT NULL,
   status_description   VARCHAR(255)   DEFAULT NULL
) ENCRYPTED=YES;

ALTER TABLE orders
  ADD CONSTRAINT fk_orders_merchant FOREIGN KEY (merchant_id)
  REFERENCES merchant (id)
  ON UPDATE RESTRICT
  ON DELETE CASCADE;

CREATE TABLE tempcard
(
   name                 VARCHAR(50)    NOT NULL,
   number               VARCHAR(50)    NOT NULL,
   cvv                  INT            NOT NULL,
   expiry_month         INT            NOT NULL,
   expiry_year          INT            NOT NULL,
   parequest            LONGTEXT       DEFAULT NULL,
   paresponse           LONGTEXT       DEFAULT NULL,
   sessionid            VARCHAR(50)    NOT NULL,
   order_id             INT            NOT NULL,
   merchant_key         VARCHAR(255)   DEFAULT NULL,
   datetime             TIMESTAMP      DEFAULT current_timestamp() NOT NULL,
   createdate           TIMESTAMP      DEFAULT current_timestamp() NOT NULL,
   addressline1         VARCHAR(500)   DEFAULT NULL,
   addressline2         VARCHAR(500)   DEFAULT NULL,
   city                 VARCHAR(100)   DEFAULT NULL,
   country              VARCHAR(100)   DEFAULT NULL,
   postal_code          VARCHAR(10)    DEFAULT NULL,
   state                VARCHAR(100)   DEFAULT NULL,
   payment_method_code  VARCHAR(100)   DEFAULT NULL
) ENCRYPTED=YES;
 
