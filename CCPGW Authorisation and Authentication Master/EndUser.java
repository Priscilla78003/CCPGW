/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.model.adapter.plugins.icbs;

import com.google.gson.Gson;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class EndUser {

    private String cif;
    private String branchId;
    private String MemoFlag;
    private String rqMemoFlag;
    private String ticklerFlag;
    private String rqTicklerFlag;
    private String shortName;
    private String derivedShortName;
    private String primOfficerCode;
    private String secondaryOfficerCode;
    private String extraOfficer1Code;
    private String extraOfficer2Code;
    private Date openDt;
    private String inqLevelCode;
    private String maintLevelCode;
    private String custLocaleCode;
    private Date lastContactDt;
    private Date lastMaintDt;
    private String custStatusCode;
    private String ownHomeCode;
    private String empIncomeAmt;
    private String empIncomeSource;
    private String yearsAtJob;
    private String professionCode;
    private String empStatus;
    private String busEmailAddr;
    private String personName;
    private Address address;        
    private String parsedFlag;
    private String custType;
    private List<Phone> phoneNum;
    private String emailAddr;
    private String genderCode;
    private String raceCode;
    private String taxId;
    private String sICCode;
    private Date birthDt;
    private String nationalId;
    private String dependentsNum;
    private String contactName;
    private String contactTitle;
    private String language;
    private String citizenCode;
    private String residenceCode;
    private String withholdingCode;
    private String withholdingPercent;
    private String taxInfoCode;
    private String passportId;
    private String solicitableCode;
    private String accomCode;
    private String socioEconCode;
    private String salutation;
    private String employeeCode;
    private String preferredCustCode;
    private String mktSegCode;
    private String MaritalStatusCode;
    private String sendMailCode;
    private Date moveInDt;
    private Date deathDt;
    private String cashExclusionCode;
    private String cashExclusionLimitAmt;
    private String custExtractCode;
    private String taxIdCertCode;
    private Date tINActivityDt;
    private String taxIdTypeCode;
    private String w8W9Num;
    private List<Account> accounts;        
    private String uDFCuusr3;
    private AliasName aliasname;        
    private String moveInConfCode;
    private String evePhoneAvailCode;
    private String custDocCode;
    private Date custDocActivityDt;
    private String telexNum;
    private String telexAnswerBackNum;
    private String activityLevelCode;
    private String custClassCode;
    private String sourceCode;
    private String lifeStageCode;
    private String behavioralCode;
    private String responsivenessCode;
    private String contactPrefText;
    private String userKey;
    private String custMailAcctCode;
    private String custMailCustCode;
    private String custLinkage;   
    
    
    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }     

    /**
     * @return the cif
     */
    public String getCif() {
        return cif;
    }

    /**
     * @param cif the cif to set
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the MemoFlag
     */
    public String getMemoFlag() {
        return MemoFlag;
    }

    /**
     * @param MemoFlag the MemoFlag to set
     */
    public void setMemoFlag(String MemoFlag) {
        this.MemoFlag = MemoFlag;
    }

    /**
     * @return the rqMemoFlag
     */
    public String getRqMemoFlag() {
        return rqMemoFlag;
    }

    /**
     * @param rqMemoFlag the rqMemoFlag to set
     */
    public void setRqMemoFlag(String rqMemoFlag) {
        this.rqMemoFlag = rqMemoFlag;
    }

    /**
     * @return the ticklerFlag
     */
    public String getTicklerFlag() {
        return ticklerFlag;
    }

    /**
     * @param ticklerFlag the ticklerFlag to set
     */
    public void setTicklerFlag(String ticklerFlag) {
        this.ticklerFlag = ticklerFlag;
    }

    /**
     * @return the rqTicklerFlag
     */
    public String getRqTicklerFlag() {
        return rqTicklerFlag;
    }

    /**
     * @param rqTicklerFlag the rqTicklerFlag to set
     */
    public void setRqTicklerFlag(String rqTicklerFlag) {
        this.rqTicklerFlag = rqTicklerFlag;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return the derivedShortName
     */
    public String getDerivedShortName() {
        return derivedShortName;
    }

    /**
     * @param derivedShortName the derivedShortName to set
     */
    public void setDerivedShortName(String derivedShortName) {
        this.derivedShortName = derivedShortName;
    }

    /**
     * @return the primOfficerCode
     */
    public String getPrimOfficerCode() {
        return primOfficerCode;
    }

    /**
     * @param primOfficerCode the primOfficerCode to set
     */
    public void setPrimOfficerCode(String primOfficerCode) {
        this.primOfficerCode = primOfficerCode;
    }

    /**
     * @return the secondaryOfficerCode
     */
    public String getSecondaryOfficerCode() {
        return secondaryOfficerCode;
    }

    /**
     * @param secondaryOfficerCode the secondaryOfficerCode to set
     */
    public void setSecondaryOfficerCode(String secondaryOfficerCode) {
        this.secondaryOfficerCode = secondaryOfficerCode;
    }

    /**
     * @return the extraOfficer1Code
     */
    public String getExtraOfficer1Code() {
        return extraOfficer1Code;
    }

    /**
     * @param extraOfficer1Code the extraOfficer1Code to set
     */
    public void setExtraOfficer1Code(String extraOfficer1Code) {
        this.extraOfficer1Code = extraOfficer1Code;
    }

    /**
     * @return the extraOfficer2Code
     */
    public String getExtraOfficer2Code() {
        return extraOfficer2Code;
    }

    /**
     * @param extraOfficer2Code the extraOfficer2Code to set
     */
    public void setExtraOfficer2Code(String extraOfficer2Code) {
        this.extraOfficer2Code = extraOfficer2Code;
    }

    /**
     * @return the openDt
     */
    public Date getOpenDt() {
        return openDt;
    }

    /**
     * @param openDt the openDt to set
     */
    public void setOpenDt(Date openDt) {
        this.openDt = openDt;
    }

    /**
     * @return the inqLevelCode
     */
    public String getInqLevelCode() {
        return inqLevelCode;
    }

    /**
     * @param inqLevelCode the inqLevelCode to set
     */
    public void setInqLevelCode(String inqLevelCode) {
        this.inqLevelCode = inqLevelCode;
    }

    /**
     * @return the maintLevelCode
     */
    public String getMaintLevelCode() {
        return maintLevelCode;
    }

    /**
     * @param maintLevelCode the maintLevelCode to set
     */
    public void setMaintLevelCode(String maintLevelCode) {
        this.maintLevelCode = maintLevelCode;
    }

    /**
     * @return the custLocaleCode
     */
    public String getCustLocaleCode() {
        return custLocaleCode;
    }

    /**
     * @param custLocaleCode the custLocaleCode to set
     */
    public void setCustLocaleCode(String custLocaleCode) {
        this.custLocaleCode = custLocaleCode;
    }

    /**
     * @return the lastContactDt
     */
    public Date getLastContactDt() {
        return lastContactDt;
    }

    /**
     * @param lastContactDt the lastContactDt to set
     */
    public void setLastContactDt(Date lastContactDt) {
        this.lastContactDt = lastContactDt;
    }

    /**
     * @return the lastMaintDt
     */
    public Date getLastMaintDt() {
        return lastMaintDt;
    }

    /**
     * @param lastMaintDt the lastMaintDt to set
     */
    public void setLastMaintDt(Date lastMaintDt) {
        this.lastMaintDt = lastMaintDt;
    }

    /**
     * @return the custStatusCode
     */
    public String getCustStatusCode() {
        return custStatusCode;
    }

    /**
     * @param custStatusCode the custStatusCode to set
     */
    public void setCustStatusCode(String custStatusCode) {
        this.custStatusCode = custStatusCode;
    }

    /**
     * @return the ownHomeCode
     */
    public String getOwnHomeCode() {
        return ownHomeCode;
    }

    /**
     * @param ownHomeCode the ownHomeCode to set
     */
    public void setOwnHomeCode(String ownHomeCode) {
        this.ownHomeCode = ownHomeCode;
    }

    /**
     * @return the empIncomeAmt
     */
    public String getEmpIncomeAmt() {
        return empIncomeAmt;
    }

    /**
     * @param empIncomeAmt the empIncomeAmt to set
     */
    public void setEmpIncomeAmt(String empIncomeAmt) {
        this.empIncomeAmt = empIncomeAmt;
    }

    /**
     * @return the empIncomeSource
     */
    public String getEmpIncomeSource() {
        return empIncomeSource;
    }

    /**
     * @param empIncomeSource the empIncomeSource to set
     */
    public void setEmpIncomeSource(String empIncomeSource) {
        this.empIncomeSource = empIncomeSource;
    }

    /**
     * @return the yearsAtJob
     */
    public String getYearsAtJob() {
        return yearsAtJob;
    }

    /**
     * @param yearsAtJob the yearsAtJob to set
     */
    public void setYearsAtJob(String yearsAtJob) {
        this.yearsAtJob = yearsAtJob;
    }

    /**
     * @return the professionCode
     */
    public String getProfessionCode() {
        return professionCode;
    }

    /**
     * @param professionCode the professionCode to set
     */
    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    /**
     * @return the empStatus
     */
    public String getEmpStatus() {
        return empStatus;
    }

    /**
     * @param empStatus the empStatus to set
     */
    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    /**
     * @return the busEmailAddr
     */
    public String getBusEmailAddr() {
        return busEmailAddr;
    }

    /**
     * @param busEmailAddr the busEmailAddr to set
     */
    public void setBusEmailAddr(String busEmailAddr) {
        this.busEmailAddr = busEmailAddr;
    }

    /**
     * @return the personName
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * @param personName the personName to set
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the parsedFlag
     */
    public String getParsedFlag() {
        return parsedFlag;
    }

    /**
     * @param parsedFlag the parsedFlag to set
     */
    public void setParsedFlag(String parsedFlag) {
        this.parsedFlag = parsedFlag;
    }

    /**
     * @return the custType
     */
    public String getCustType() {
        return custType;
    }

    /**
     * @param custType the custType to set
     */
    public void setCustType(String custType) {
        this.custType = custType;
    }

    /**
     * @return the emailAddr
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * @param emailAddr the emailAddr to set
     */
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    /**
     * @return the genderCode
     */
    public String getGenderCode() {
        return genderCode;
    }

    /**
     * @param genderCode the genderCode to set
     */
    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    /**
     * @return the raceCode
     */
    public String getRaceCode() {
        return raceCode;
    }

    /**
     * @param raceCode the raceCode to set
     */
    public void setRaceCode(String raceCode) {
        this.raceCode = raceCode;
    }

    /**
     * @return the taxId
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * @param taxId the taxId to set
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    /**
     * @return the sICCode
     */
    public String getsICCode() {
        return sICCode;
    }

    /**
     * @param sICCode the sICCode to set
     */
    public void setsICCode(String sICCode) {
        this.sICCode = sICCode;
    }

    /**
     * @return the birthDt
     */
    public Date getBirthDt() {
        return birthDt;
    }

    /**
     * @param birthDt the birthDt to set
     */
    public void setBirthDt(Date birthDt) {
        this.birthDt = birthDt;
    }

    /**
     * @return the nationalId
     */
    public String getNationalId() {
        return nationalId;
    }

    /**
     * @param nationalId the nationalId to set
     */
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    /**
     * @return the dependentsNum
     */
    public String getDependentsNum() {
        return dependentsNum;
    }

    /**
     * @param dependentsNum the dependentsNum to set
     */
    public void setDependentsNum(String dependentsNum) {
        this.dependentsNum = dependentsNum;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the contactTitle
     */
    public String getContactTitle() {
        return contactTitle;
    }

    /**
     * @param contactTitle the contactTitle to set
     */
    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the citizenCode
     */
    public String getCitizenCode() {
        return citizenCode;
    }

    /**
     * @param citizenCode the citizenCode to set
     */
    public void setCitizenCode(String citizenCode) {
        this.citizenCode = citizenCode;
    }

    /**
     * @return the residenceCode
     */
    public String getResidenceCode() {
        return residenceCode;
    }

    /**
     * @param residenceCode the residenceCode to set
     */
    public void setResidenceCode(String residenceCode) {
        this.residenceCode = residenceCode;
    }

    /**
     * @return the withholdingCode
     */
    public String getWithholdingCode() {
        return withholdingCode;
    }

    /**
     * @param withholdingCode the withholdingCode to set
     */
    public void setWithholdingCode(String withholdingCode) {
        this.withholdingCode = withholdingCode;
    }

    /**
     * @return the withholdingPercent
     */
    public String getWithholdingPercent() {
        return withholdingPercent;
    }

    /**
     * @param withholdingPercent the withholdingPercent to set
     */
    public void setWithholdingPercent(String withholdingPercent) {
        this.withholdingPercent = withholdingPercent;
    }

    /**
     * @return the taxInfoCode
     */
    public String getTaxInfoCode() {
        return taxInfoCode;
    }

    /**
     * @param taxInfoCode the taxInfoCode to set
     */
    public void setTaxInfoCode(String taxInfoCode) {
        this.taxInfoCode = taxInfoCode;
    }

    /**
     * @return the passportId
     */
    public String getPassportId() {
        return passportId;
    }

    /**
     * @param passportId the passportId to set
     */
    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    /**
     * @return the solicitableCode
     */
    public String getSolicitableCode() {
        return solicitableCode;
    }

    /**
     * @param solicitableCode the solicitableCode to set
     */
    public void setSolicitableCode(String solicitableCode) {
        this.solicitableCode = solicitableCode;
    }

    /**
     * @return the accomCode
     */
    public String getAccomCode() {
        return accomCode;
    }

    /**
     * @param accomCode the accomCode to set
     */
    public void setAccomCode(String accomCode) {
        this.accomCode = accomCode;
    }

    /**
     * @return the socioEconCode
     */
    public String getSocioEconCode() {
        return socioEconCode;
    }

    /**
     * @param socioEconCode the socioEconCode to set
     */
    public void setSocioEconCode(String socioEconCode) {
        this.socioEconCode = socioEconCode;
    }

    /**
     * @return the salutation
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * @param salutation the salutation to set
     */
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    /**
     * @return the employeeCode
     */
    public String getEmployeeCode() {
        return employeeCode;
    }

    /**
     * @param employeeCode the employeeCode to set
     */
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     * @return the preferredCustCode
     */
    public String getPreferredCustCode() {
        return preferredCustCode;
    }

    /**
     * @param preferredCustCode the preferredCustCode to set
     */
    public void setPreferredCustCode(String preferredCustCode) {
        this.preferredCustCode = preferredCustCode;
    }

    /**
     * @return the mktSegCode
     */
    public String getMktSegCode() {
        return mktSegCode;
    }

    /**
     * @param mktSegCode the mktSegCode to set
     */
    public void setMktSegCode(String mktSegCode) {
        this.mktSegCode = mktSegCode;
    }

    /**
     * @return the MaritalStatusCode
     */
    public String getMaritalStatusCode() {
        return MaritalStatusCode;
    }

    /**
     * @param MaritalStatusCode the MaritalStatusCode to set
     */
    public void setMaritalStatusCode(String MaritalStatusCode) {
        this.MaritalStatusCode = MaritalStatusCode;
    }

    /**
     * @return the sendMailCode
     */
    public String getSendMailCode() {
        return sendMailCode;
    }

    /**
     * @param sendMailCode the sendMailCode to set
     */
    public void setSendMailCode(String sendMailCode) {
        this.sendMailCode = sendMailCode;
    }

    /**
     * @return the moveInDt
     */
    public Date getMoveInDt() {
        return moveInDt;
    }

    /**
     * @param moveInDt the moveInDt to set
     */
    public void setMoveInDt(Date moveInDt) {
        this.moveInDt = moveInDt;
    }

    /**
     * @return the deathDt
     */
    public Date getDeathDt() {
        return deathDt;
    }

    /**
     * @param deathDt the deathDt to set
     */
    public void setDeathDt(Date deathDt) {
        this.deathDt = deathDt;
    }

    /**
     * @return the cashExclusionCode
     */
    public String getCashExclusionCode() {
        return cashExclusionCode;
    }

    /**
     * @param cashExclusionCode the cashExclusionCode to set
     */
    public void setCashExclusionCode(String cashExclusionCode) {
        this.cashExclusionCode = cashExclusionCode;
    }

    /**
     * @return the cashExclusionLimitAmt
     */
    public String getCashExclusionLimitAmt() {
        return cashExclusionLimitAmt;
    }

    /**
     * @param cashExclusionLimitAmt the cashExclusionLimitAmt to set
     */
    public void setCashExclusionLimitAmt(String cashExclusionLimitAmt) {
        this.cashExclusionLimitAmt = cashExclusionLimitAmt;
    }

    /**
     * @return the custExtractCode
     */
    public String getCustExtractCode() {
        return custExtractCode;
    }

    /**
     * @param custExtractCode the custExtractCode to set
     */
    public void setCustExtractCode(String custExtractCode) {
        this.custExtractCode = custExtractCode;
    }

    /**
     * @return the taxIdCertCode
     */
    public String getTaxIdCertCode() {
        return taxIdCertCode;
    }

    /**
     * @param taxIdCertCode the taxIdCertCode to set
     */
    public void setTaxIdCertCode(String taxIdCertCode) {
        this.taxIdCertCode = taxIdCertCode;
    }

    /**
     * @return the tINActivityDt
     */
    public Date gettINActivityDt() {
        return tINActivityDt;
    }

    /**
     * @param tINActivityDt the tINActivityDt to set
     */
    public void settINActivityDt(Date tINActivityDt) {
        this.tINActivityDt = tINActivityDt;
    }

    /**
     * @return the taxIdTypeCode
     */
    public String getTaxIdTypeCode() {
        return taxIdTypeCode;
    }

    /**
     * @param taxIdTypeCode the taxIdTypeCode to set
     */
    public void setTaxIdTypeCode(String taxIdTypeCode) {
        this.taxIdTypeCode = taxIdTypeCode;
    }

    /**
     * @return the w8W9Num
     */
    public String getW8W9Num() {
        return w8W9Num;
    }

    /**
     * @param w8W9Num the w8W9Num to set
     */
    public void setW8W9Num(String w8W9Num) {
        this.w8W9Num = w8W9Num;
    }

    /**
     * @return the accounts
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * @return the uDFCuusr3
     */
    public String getuDFCuusr3() {
        return uDFCuusr3;
    }

    /**
     * @param uDFCuusr3 the uDFCuusr3 to set
     */
    public void setuDFCuusr3(String uDFCuusr3) {
        this.uDFCuusr3 = uDFCuusr3;
    }

    /**
     * @return the aliasame
     */
    public AliasName getAliasname() {
        return aliasname;
    }

    /**
     * @param aliasname the aliasname to set
     */
    public void setAliasname(AliasName aliasname) {
        this.aliasname = aliasname;
    }

    /**
     * @return the moveInConfCode
     */
    public String getMoveInConfCode() {
        return moveInConfCode;
    }

    /**
     * @param moveInConfCode the moveInConfCode to set
     */
    public void setMoveInConfCode(String moveInConfCode) {
        this.moveInConfCode = moveInConfCode;
    }

    /**
     * @return the evePhoneAvailCode
     */
    public String getEvePhoneAvailCode() {
        return evePhoneAvailCode;
    }

    /**
     * @param evePhoneAvailCode the evePhoneAvailCode to set
     */
    public void setEvePhoneAvailCode(String evePhoneAvailCode) {
        this.evePhoneAvailCode = evePhoneAvailCode;
    }

    /**
     * @return the custDocCode
     */
    public String getCustDocCode() {
        return custDocCode;
    }

    /**
     * @param custDocCode the custDocCode to set
     */
    public void setCustDocCode(String custDocCode) {
        this.custDocCode = custDocCode;
    }

    /**
     * @return the custDocActivityDt
     */
    public Date getCustDocActivityDt() {
        return custDocActivityDt;
    }

    /**
     * @param custDocActivityDt the custDocActivityDt to set
     */
    public void setCustDocActivityDt(Date custDocActivityDt) {
        this.custDocActivityDt = custDocActivityDt;
    }

    /**
     * @return the telexNum
     */
    public String getTelexNum() {
        return telexNum;
    }

    /**
     * @param telexNum the telexNum to set
     */
    public void setTelexNum(String telexNum) {
        this.telexNum = telexNum;
    }

    /**
     * @return the telexAnswerBackNum
     */
    public String getTelexAnswerBackNum() {
        return telexAnswerBackNum;
    }

    /**
     * @param telexAnswerBackNum the telexAnswerBackNum to set
     */
    public void setTelexAnswerBackNum(String telexAnswerBackNum) {
        this.telexAnswerBackNum = telexAnswerBackNum;
    }

    /**
     * @return the activityLevelCode
     */
    public String getActivityLevelCode() {
        return activityLevelCode;
    }

    /**
     * @param activityLevelCode the activityLevelCode to set
     */
    public void setActivityLevelCode(String activityLevelCode) {
        this.activityLevelCode = activityLevelCode;
    }

    /**
     * @return the custClassCode
     */
    public String getCustClassCode() {
        return custClassCode;
    }

    /**
     * @param custClassCode the custClassCode to set
     */
    public void setCustClassCode(String custClassCode) {
        this.custClassCode = custClassCode;
    }

    /**
     * @return the sourceCode
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * @param sourceCode the sourceCode to set
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /**
     * @return the lifeStageCode
     */
    public String getLifeStageCode() {
        return lifeStageCode;
    }

    /**
     * @param lifeStageCode the lifeStageCode to set
     */
    public void setLifeStageCode(String lifeStageCode) {
        this.lifeStageCode = lifeStageCode;
    }

    /**
     * @return the behavioralCode
     */
    public String getBehavioralCode() {
        return behavioralCode;
    }

    /**
     * @param behavioralCode the behavioralCode to set
     */
    public void setBehavioralCode(String behavioralCode) {
        this.behavioralCode = behavioralCode;
    }

    /**
     * @return the responsivenessCode
     */
    public String getResponsivenessCode() {
        return responsivenessCode;
    }

    /**
     * @param responsivenessCode the responsivenessCode to set
     */
    public void setResponsivenessCode(String responsivenessCode) {
        this.responsivenessCode = responsivenessCode;
    }

    /**
     * @return the contactPrefText
     */
    public String getContactPrefText() {
        return contactPrefText;
    }

    /**
     * @param contactPrefText the contactPrefText to set
     */
    public void setContactPrefText(String contactPrefText) {
        this.contactPrefText = contactPrefText;
    }

    /**
     * @return the userKey
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * @param userKey the userKey to set
     */
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the custMailAcctCode
     */
    public String getCustMailAcctCode() {
        return custMailAcctCode;
    }

    /**
     * @param custMailAcctCode the custMailAcctCode to set
     */
    public void setCustMailAcctCode(String custMailAcctCode) {
        this.custMailAcctCode = custMailAcctCode;
    }

    /**
     * @return the custMailCustCode
     */
    public String getCustMailCustCode() {
        return custMailCustCode;
    }

    /**
     * @param custMailCustCode the custMailCustCode to set
     */
    public void setCustMailCustCode(String custMailCustCode) {
        this.custMailCustCode = custMailCustCode;
    }

    /**
     * @return the custLinkage
     */
    public String getCustLinkage() {
        return custLinkage;
    }

    /**
     * @param custLinkage the custLinkage to set
     */
    public void setCustLinkage(String custLinkage) {
        this.custLinkage = custLinkage;
    }

    /**
     * @return the phoneNum
     */
    public List<Phone> getPhoneNum() {
        return phoneNum;
    }

    /**
     * @param phoneNum the phoneNum to set
     */
    public void setPhoneNum(List<Phone> phoneNum) {
        this.phoneNum = phoneNum;
    }



    
}
