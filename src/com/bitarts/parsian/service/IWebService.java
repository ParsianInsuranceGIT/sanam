package com.bitarts.parsian.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(serviceName = "BitartsWebService" )
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface IWebService {
    public static final String SERVICE_NAME = "webService";

    @WebMethod
    public String createCredit(Integer amount, String description, String rcptName, String sarresidDate, String subsystemName, String type, String identifier, String codeNamayande , String codeVahedeSodor, String uniqueCode, Integer mohlatSarResid, Long bazaryabCode, String bazaryabName);

    @WebMethod
    public String createDebit(Integer amount, String description, String rcptName, String sarresidDate, String subsystemName, String type, String identifier, String codeNamayande, String codeVahedeSodor, String uniqueCode, Integer mohlatSarResid,  Long bazaryabCode, String bazaryabName);

    @WebMethod
    public String amountOfShenasePardakht(String input);

    @WebMethod
    public String amountOfShenasePardakhtSanam(String input);


    @WebMethod
    public String getGhestForUSSD(String shomareBimename);

    @WebMethod
    public String getGhestForUSSDSanam(String codeNamayande);

    @WebMethod
    public Integer queryRemainingWithIdentifier(String identifier, String subsystemName);

    @WebMethod
    public Integer queryRemainingWithTraceCode(String traceCode, String subsystemName);

    @WebMethod
    public Integer queryRemainingWithShenase(String shenase, String subsystemName);

    @WebMethod
    public String generateCustomerNumber(String namayandeCode, String typeSystem, String format);

    @WebMethod
    public String sanadRegister(String customerNumber, String format, String description, Integer amount, String rcptName, String sarresidDate, String subsystemName, String identifier, String time, String date, String codeErja, String pin, String codeNamayande, String codeVahedeSodor, String uniqueCodeEtebar, String uniqueCodeBedehi);

    @WebMethod
    public boolean updateShomareBimename(String shomarePishnehad, String shomareBimename, String pin);

    @WebMethod
    public String payByACH(String subsystemName, String codeMoshtariEtebar, String shenaseEtebar, Integer amount, String description, String rcptName, String identifier, String achTrackId, String codeNamayande, String codeVahedeSodor, String uniqueCode);

    @WebMethod
    public String payByInternet(String subsystemName, String codeMoshtariBedehi, String shenaseBedehi, Integer amount, String vosulDate, String vosulTime, String description, String rcptName, String identifier, String authId, String codeNamayande, String codeVahedeSodor, String uniqueCode);

    @WebMethod
    public String payByCash(String subsystemName, String payerName, Integer amount, String vosulDate, String description, String codeMoshtariEtebar, String shenaseBedehi, String codeNamayande, String codeVahedeSodor, String uniqueCode);

    @WebMethod
    public String payByHesabFiMaBeyn(String subsystemName, String payerName, Integer amount, String vosulDate, String description, String codeMoshtariEtebar, String shenaseBedehi, String shomareGharardad, String codeNamayande, String codeVahedeSodor, String uniqueCode);

    @WebMethod
    public String payByHesabFiMaBeynForElhaghie(String subsystemName, String payerName, Integer amount, String vosulDate, String description, String codeMoshtariEtebar, String shenaseEtebar, String shomareGharardad, String codeNamayande, String codeVahedeSodor, String uniqueCode);

    @WebMethod
    public String payByAzMahaleTablighatForElhaghie(String subsystemName, String payerName, Integer amount, String vosulDate, String description, String codeMoshtariEtebar, String shenaseEtebar, String shomareGharardad, String codeNamayande, String codeVahedeSodor, String uniqueCode);

    @WebMethod
    public String payByFish(String subsystemName, String payerName, String shomareHesab, String shomareFish, Integer amount, String vosulDate, String vosulTime, String description, String codeMoshtariEtebar, String shenaseBedehi, String codeNamayande, String codeVahedeSodor, String uniqueCode);

    @WebMethod
    public String payByPOS(String subsystemName, String payerName, String terminalNo, String transactionSerial, String referenceCode, String traceCode, Integer amount, String vosulDate, String vosulTime, String description, String codeMoshtariEtebar, String shenaseBedehi, String codeNamayande, String codeVahedeSodor, String uniqueCode);

    @WebMethod
    public String payByCheck(String subsystemName, String payerName, String serialNo, String branchName, String accountOwnerName, String sarresidDate, String checkType, String seri, String bankName, String branchCode, String bankAccountNumber, Integer amount, String vosulDate, String description, String codeMoshtariEtebar, String shenaseBedehi, String codeNamayande, String codeVahedeSodor, String uniqueCode);

    @WebMethod
    public String payByAzMahaleTablighat(String subsystemName, String payeeName, Integer amount, String vosulDate, String description, String shenaseBedehi, String codeNamayande, String codeVahedeSodor, String uniqueCode);

    @WebMethod
    public String getNamayandehAuthorized(Long namayandeId);

    @WebMethod
    public String estelamCalc(String birthDate, String nahvePardakht, long haghBimeAvalie, long haghBimePardakhti, int nerkhHaghBime, long sarmayeFot, int nerkhSarmayeFot, int durationCycle);
    //b-h
    @WebMethod
    public  String estelamVaziateBedehiVaEtebar(String input);
}
