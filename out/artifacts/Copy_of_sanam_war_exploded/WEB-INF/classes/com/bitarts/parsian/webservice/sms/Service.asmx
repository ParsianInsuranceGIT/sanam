<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://www.SMS.parsianInsurance.ir/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://www.SMS.parsianInsurance.ir/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://www.SMS.parsianInsurance.ir/">
      <s:element name="InsertSMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Passwrd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="FirstName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="LastName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="CellPhone" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SMSText" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Description" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="PolicyID" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="AppName" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InsertSMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="InsertSMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="InsertSMSSoapIn">
    <wsdl:part name="parameters" element="tns:InsertSMS" />
  </wsdl:message>
  <wsdl:message name="InsertSMSSoapOut">
    <wsdl:part name="parameters" element="tns:InsertSMSResponse" />
  </wsdl:message>
  <wsdl:portType name="ServiceSoap">
    <wsdl:operation name="InsertSMS">
      <wsdl:input message="tns:InsertSMSSoapIn" />
      <wsdl:output message="tns:InsertSMSSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="ServiceSoap" type="tns:ServiceSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="InsertSMS">
      <soap:operation soapAction="http://www.SMS.parsianInsurance.ir/InsertSMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="ServiceSoap12" type="tns:ServiceSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="InsertSMS">
      <soap12:operation soapAction="http://www.SMS.parsianInsurance.ir/InsertSMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="Service">
    <wsdl:port name="ServiceSoap" binding="tns:ServiceSoap">
      <soap:address location="http://ittestserver/smswebservice/service.asmx" />
    </wsdl:port>
    <wsdl:port name="ServiceSoap12" binding="tns:ServiceSoap12">
      <soap12:address location="http://ittestserver/smswebservice/service.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>