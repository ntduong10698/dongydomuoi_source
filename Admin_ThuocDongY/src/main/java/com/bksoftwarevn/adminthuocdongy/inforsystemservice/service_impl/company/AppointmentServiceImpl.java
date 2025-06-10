package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Appointment;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company.AppointmentRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepo appointmentRepo;

    @Override
    public Optional<Appointment> findById(int id) throws Exception {
        return appointmentRepo.findById(id);
    }

    @Override
    public Optional<Appointment> save(Appointment appointment) throws Exception {
        return Optional.of(appointmentRepo.save(appointment));
    }

    @Override
    public int countNew(int comId) {
        return appointmentRepo.countNew(comId);
    }

    @Override
    public Boolean deleted(int id) throws Exception {
        return appointmentRepo.deleted(id) > 0;
    }

    @Override
    public Page<Appointment> findByCompany(int id, Pageable pageable) throws Exception {
        return appointmentRepo.findByCompany(id, pageable);
    }
}
