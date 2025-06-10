package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "urlset", namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
@XmlAccessorType(XmlAccessType.FIELD)
public class UrlSet {

    @XmlElement(name = "url")
    private List<UrlSiteMap> urlset;

}
