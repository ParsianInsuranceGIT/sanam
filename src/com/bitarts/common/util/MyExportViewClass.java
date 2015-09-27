package com.bitarts.common.util;

import org.displaytag.export.ExcelView;
import org.displaytag.export.excel.DefaultHssfExportView;
import org.displaytag.export.excel.ExcelHssfView;

public class MyExportViewClass extends ExcelHssfView{

    protected String escapeColumnValue(java.lang.Object value) {

            String returnValue = null;
            if(value instanceof String) {
                String strValue = (String)value;

                if(strValue.trim().contains("&nbsp;")) {
                    returnValue = strValue.replace("&nbsp;"," ");
                }
                else {
                    returnValue = super.escapeColumnValue(value);
                }
            } else {
                returnValue = super.escapeColumnValue(value);
            }
            return returnValue;
    }
}