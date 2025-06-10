package com.bksoftwarevn.adminthuocdongy.newsservice.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bai_viet")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int type;

    private String name;

    private String image;

    private String video;

    private boolean enableVideo;

    private String preview;

    private String content;

    private String tag;

    private String alias;

    private String author;

    private int view;

    private boolean highLight;

    //for event
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creatTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    private String address;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "danh_muc_id")
    private NCategory category;

}
