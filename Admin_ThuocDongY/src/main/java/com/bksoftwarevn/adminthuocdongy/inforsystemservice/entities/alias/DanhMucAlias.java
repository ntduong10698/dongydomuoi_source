package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "danh_muc_alias")
public class DanhMucAlias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tan_suat_thay_doi_alias_id")
    private TanSuatThayDoiAlias tanSuatThayDoiAlias;

    @Column(name = "do_quan_trong")
    private double doQuanTrong;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "config_id")
    private ConfigSitemap configSitemap;

    private boolean deleted;
}
