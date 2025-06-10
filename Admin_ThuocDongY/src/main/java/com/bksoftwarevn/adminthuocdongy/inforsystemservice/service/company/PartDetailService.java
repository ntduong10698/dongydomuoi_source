package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.PartDetail;

import java.util.List;
import java.util.Optional;

public interface PartDetailService {

    Optional<PartDetail> findById(int id);

    Optional<PartDetail> save(PartDetail partDetail);

    Boolean deleted(int id);

    List<PartDetail> findByPartId(int id);
}
