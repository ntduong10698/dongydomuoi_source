package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "part_detail")
public class PartDetail {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    private String url;

    private String link;

    @Column(name = "title")
    private String title;

    @Column(name = "position")
    private String position;

    @Column(name = "type")
    private Integer type;

    private int stt;

    private boolean enable;

    private String text;

    @Column(name = "deleted")
    private Boolean deleted;

}
