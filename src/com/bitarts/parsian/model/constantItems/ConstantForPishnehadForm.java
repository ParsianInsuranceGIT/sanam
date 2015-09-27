package com.bitarts.parsian.model.constantItems;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/24/11
 * Time: 3:45 PM
 */
@Entity
@Table(name="tbl_const_pishnehad_form")
public class ConstantForPishnehadForm implements Serializable {
    public static enum ConstantItemKey {
        NESBAT_BA_BIME_SHODE,SHERKAT_HAYE_BIME,PISHVAND
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "constant_item_id")
    private Integer id;
    @Column(name = "constant_item_key")
    @Enumerated(EnumType.STRING)
    private ConstantItemKey constantItemKey;
    @Column(name = "constant_item_value")
    private String constantItemValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ConstantItemKey getConstantItemKey() {
        return constantItemKey;
    }

    public void setConstantItemKey(ConstantItemKey constantItemKey) {
        this.constantItemKey = constantItemKey;
    }

    public String getConstantItemValue() {
        return constantItemValue;
    }

    public void setConstantItemValue(String constantItemValue) {
        this.constantItemValue = constantItemValue;
    }
}
