package com.bksoftwarevn.adminthuocdongy.cartservice.entities.key;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "bill")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "note_bill")
    private String noteBill;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_address")
    private String customerAddress;

    private String customerNote;

    @Column(name = "co_name")
    private String CoName;

    private String promotion;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    private Boolean checked;

    @ManyToOne
    @JoinColumn(name = "source_product_id")
    private SourceOrder sourceOrder;

    @ManyToOne
    @JoinColumn(name = "invoice_form_id")
    private InvoiceTemplate invoiceTemplate;

    @Column(name = "total_money")
    private Double totalMoney;

}
