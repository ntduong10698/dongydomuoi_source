package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lich_su_thay_doi_sitemap")
public class HistoryChangeSitemap implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private ConfigSitemap configSitemap;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date time;


}
