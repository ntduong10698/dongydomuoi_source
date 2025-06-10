package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

    @Query(value = "from Appointment b where b.id = ?1")
    Optional<Appointment> findById(int id);

    @Query(value = "from Appointment b where b.branch.company.id = ?1 and b.deleted = false order by b.id desc")
    Page<Appointment> findByCompany(int id, Pageable pageable);

    @Query("select count (x) from Appointment x where x.branch.company.id = ?1 and x.deleted = false and x.status = 0")
    int countNew(int companyId);

    @Transactional
    @Modifying
    @Query("update Appointment b set b.deleted = true where b.id = ?1")
    int deleted(int id);

}
