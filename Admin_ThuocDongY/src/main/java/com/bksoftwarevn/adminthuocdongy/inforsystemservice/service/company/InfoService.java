package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Information;

import java.util.List;
import java.util.Optional;

public interface InfoService {

    Optional<Information> findById(int id);

    List<Information> findByComId(int comId);

    Optional<Information> save(Information information);

    List<Information> saveAll(Iterable<Information> information);

    boolean deleted(int id);
}
