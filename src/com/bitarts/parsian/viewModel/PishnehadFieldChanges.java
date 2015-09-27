package com.bitarts.parsian.viewModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Jul 20, 2011
 * Time: 8:05:33 PM
 */
public class PishnehadFieldChanges {


    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public static enum ChangeType {
        FIELD, LIST
    }

    private boolean option = false;
    private boolean valid = true;
    private ChangeType changeType;
    private String subject;
    private String fromValue;
    private String toValue;
    private List fromValues;
    private List toValues;
    private String category;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFromValue() {
        return fromValue;
    }

    public void setFromValue(String fromValue) {
        this.fromValue = fromValue;
    }

    public String getToValue() {
        return toValue;
    }

    public void setToValue(String toValue) {
        this.toValue = toValue;
    }

    public List getFromValues() {

        return fromValues;
    }

    public void setFromValues(List fromValues) {
        this.fromValues = fromValues;
    }

    public List getToValues() {
        return toValues;
    }

    public void setToValues(List toValues) {
        this.toValues = toValues;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public PishnehadFieldChanges(String subject, String fromValue, String toValue, boolean option, boolean valid) {
        this.subject = subject;
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.changeType = ChangeType.FIELD;
        this.option = option;
        this.valid = valid;
    }

    public PishnehadFieldChanges(String subject, String fromValue, String toValue) {
        this(subject, fromValue, toValue, false, true);
    }

    public PishnehadFieldChanges(String subject, String fromValue, String toValue, String category) {
        this(subject, fromValue, toValue, false, true);
        this.category = category;
    }

    public PishnehadFieldChanges(String subject, String fromValue, String toValue, boolean option) {
        this(subject, fromValue, toValue, option, true);
    }

    public PishnehadFieldChanges(String subject, String fromValue, String toValue, boolean option, String category) {
        this(subject, fromValue, toValue, option, true);
        this.category = category;
    }

    public PishnehadFieldChanges(String subject, List fromValues, List toValues) {
        this.subject = subject;
        this.fromValues = fromValues;
        this.toValues = toValues;
        this.changeType = ChangeType.LIST;
    }

    public PishnehadFieldChanges() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isOption() {
        return option;
    }

    public void setOption(boolean option) {
        this.option = option;
    }
}
