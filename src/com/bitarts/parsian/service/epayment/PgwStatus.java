package com.bitarts.parsian.service.epayment;

import org.apache.axis.types.UnsignedByte;

public interface PgwStatus {
    //#region Common Statuses (0-9)
    public static final UnsignedByte Successful = new UnsignedByte(0);
    public static final UnsignedByte PreRequest = new UnsignedByte(2);;
    public static final UnsignedByte Timeout = new UnsignedByte(3);

    //#endregion
    //#region Financial Errors (10-19)
    public static final UnsignedByte InvalidCard = new UnsignedByte(10);
    public static final UnsignedByte ExpiredCard = new UnsignedByte(11);
    public static final UnsignedByte IncorrectPin = new UnsignedByte(12);
    public static final UnsignedByte InsufficientFund = new UnsignedByte(13);
    public static final UnsignedByte InvalidMerchantMaxTransAmount = new UnsignedByte(14);
    public static final UnsignedByte InvalidDailyMaxTransAmount = new UnsignedByte(15);
    public static final UnsignedByte InvalidBIN = new UnsignedByte(16);
    public static final UnsignedByte InvalidCashCard = new UnsignedByte(17);
    public static final UnsignedByte InvalidTransAfterSettlement = new UnsignedByte(18);
    //#endregion
    //#region Security Errors (20-29)
    public static final UnsignedByte AccessViolation = new UnsignedByte(20);
    public static final UnsignedByte InvalidAuthority = new UnsignedByte(21);
    public static final UnsignedByte MerchantAuthenticationFailed = new UnsignedByte(22);
    //#endregion
    //#region Logical Statuses (30-49)
    public static final UnsignedByte SaleIsAlreadyDoneSuccessfully = new UnsignedByte(30);
    public static final UnsignedByte SaleIsVoidedSuccessfully = new UnsignedByte(31);
    public static final UnsignedByte SaleIsReversaledSuccessfully = new UnsignedByte(32);
    public static final UnsignedByte ValidFailureCountPassed = new UnsignedByte(33);
    public static final UnsignedByte InvalidMerchantOrder = new UnsignedByte(34);
    public static final UnsignedByte Inconsistency = new UnsignedByte(35);
    public static final UnsignedByte SaleIsAlreadyVoidedSuccessfully = new UnsignedByte(36);
    public static final UnsignedByte SaleIsAlreadyReversaledSuccessfully = new  UnsignedByte(37);
    public static final UnsignedByte RefundAmountIsUpperThanOrderAmount = new UnsignedByte(38);
    public static final UnsignedByte RefundAmountIsUpperThanCountOfOrdersAmount = new UnsignedByte(39);
    public static final UnsignedByte RefundOrderIsInvalid = new UnsignedByte(40);
    //#endregion
    //#region Sale Tracing Statuses (50-59)
    public static final UnsignedByte Pending = new UnsignedByte(50);
    public static final UnsignedByte OrderReceived = new UnsignedByte(51);
    public static final UnsignedByte InProgress = new UnsignedByte(52);
    public static final UnsignedByte EnquiriedByMerchant = new UnsignedByte(53);
    public static final UnsignedByte CardInfoReceived = new UnsignedByte(54);
    //#endregion
    //#region Bank Switch related errors (60-79)
    public static final UnsignedByte ReceiveError = new UnsignedByte(60);
    public static final UnsignedByte SendError = new UnsignedByte(61);
    public static final UnsignedByte MerchantNotLogin = new UnsignedByte(62);
    public static final UnsignedByte FormatError = new UnsignedByte(63);
    public static final UnsignedByte InvalidCardReader = new UnsignedByte(64);
    public static final UnsignedByte InvalidProductCodes = new UnsignedByte(65);
    public static final UnsignedByte IssuerOrSwitchInoperative = new UnsignedByte(66);
    public static final UnsignedByte ReconcileError = new UnsignedByte(67);
    public static final UnsignedByte RecordNotFound = new UnsignedByte(68);
    public static final UnsignedByte ReEnterTransaction = new UnsignedByte(69);
    public static final UnsignedByte Referral = new UnsignedByte(70);
    public static final UnsignedByte SESystemMlfunction = new UnsignedByte(71);
    public static final UnsignedByte SN = new UnsignedByte(72);
    public static final UnsignedByte TraceNumberNotFound = new UnsignedByte(73);
    public static final UnsignedByte TransNotPermitted2Term = new UnsignedByte(74);
    public static final UnsignedByte BadTerminalId = new UnsignedByte(75);
    public static final UnsignedByte BankNotSupportedBySwitch = new UnsignedByte(76);
    public static final UnsignedByte BatchNumberNotFound = new UnsignedByte(77);
    public static final UnsignedByte DuplicateTransmission = new UnsignedByte(78);
    public static final UnsignedByte TransNotOK = new UnsignedByte(79);
    public static final UnsignedByte UnNoneError = new UnsignedByte(80);
    public static final UnsignedByte LanTimeout = new UnsignedByte(81);

    //#endregion
    //#region Application Errors (90-99)
    public static final UnsignedByte ExceptionRaised = new UnsignedByte(90);
    public static final UnsignedByte DatabaseError = new UnsignedByte(91);
    //#endregion
}
