package cn.hjgx.entity;

import cn.hjgx.entity.pagedto.PageDto;

public class ProductSpuAttrsTemplate extends PageDto{
    private Integer id;

    private String templateName;

    private String attrsTemplate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getAttrsTemplate() {
        return attrsTemplate;
    }

    public void setAttrsTemplate(String attrsTemplate) {
        this.attrsTemplate = attrsTemplate;
    }
}