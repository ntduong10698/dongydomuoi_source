package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AppointmentService {

    Optional<Appointment> findById(int id) throws Exception;

    Optional<Appointment> save(Appointment appointment) throws Exception;

    int countNew(int comId);

    Boolean deleted(int id) throws Exception;

    Page<Appointment> findByCompany(int id, Pageable pageable) throws Exception;
}
