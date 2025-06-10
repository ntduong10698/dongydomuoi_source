package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "url")
@XmlAccessorType(XmlAccessType.FIELD)
public class UrlSiteMap {

    @XmlElement(name = "loc")
    private String loc;

    @XmlElement(name = "lastmod")
    private String lastmod;

    @XmlElement(name = "changefreq")
    private String changefreq;

    @XmlElement(name = "priority")
    private double priority;
}
