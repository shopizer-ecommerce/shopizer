package com.salesmanager.admin.data.tax;

import java.io.Serializable;

/**
 * Created by umesh on 3/23/17.
 */
public class TaxClassData implements Serializable {

    private long id;
    private String code;
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
