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
@XmlRootElement(name = "sitemap")
@XmlAccessorType(XmlAccessType.FIELD)
public class SiteMap {

    @XmlElement(name = "loc")
    private String loc;
}
