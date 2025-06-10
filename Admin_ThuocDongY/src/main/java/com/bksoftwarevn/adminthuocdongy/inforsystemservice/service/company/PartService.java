package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Part;

import java.util.List;
import java.util.Optional;

public interface PartService {

    Optional<Part> findById(int id);

    Optional<Part> save(Part part);

    Boolean deleted(int id);

    void setVideo(int id, boolean video);

    List<Part> findByCompany(int id, String code);

}
