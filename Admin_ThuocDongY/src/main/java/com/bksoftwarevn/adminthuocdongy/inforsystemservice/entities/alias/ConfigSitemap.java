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
@Table(name = "cau_hinh_sitemap")
public class ConfigSitemap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int companyId;

    @Column(name = "url_directory")
    private String urlDirectory;

    private int status;

    private boolean deleted;
}
