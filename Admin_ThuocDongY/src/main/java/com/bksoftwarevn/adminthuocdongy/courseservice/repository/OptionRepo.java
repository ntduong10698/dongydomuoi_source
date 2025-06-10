package com.bksoftwarevn.adminthuocdongy.courseservice.repository;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepo extends JpaRepository<QuestionOption, Integer> {

}
