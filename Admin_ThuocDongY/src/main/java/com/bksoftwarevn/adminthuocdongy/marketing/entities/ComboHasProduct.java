package com.bksoftwarevn.adminthuocdongy.marketing.entities;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.ComboProductKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "combo_has_san_pham")
public class ComboHasProduct implements Serializable {

    @EmbeddedId
    private ComboProductKey id;

    @ManyToOne
    @MapsId("comboId")
    @JoinColumn(name = "combo_id")
    private Combo combo;
}
