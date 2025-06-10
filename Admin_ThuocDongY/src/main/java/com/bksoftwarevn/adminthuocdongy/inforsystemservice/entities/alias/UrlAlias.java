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
@Table(name = "url_alias")
public class UrlAlias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String alias;

    private String url;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "danh_muc_alias_id")
    private DanhMucAlias danhMucAlias;

    @Column(name = "date_change")
    @Temporal(value = TemporalType.DATE)
    private Date dateChange;

    private boolean deleted;

}
