package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepo<T, KEY> extends JpaRepository<T, KEY> {

    Optional<T> findDataById(int id);

    int deleteDataById(int id);
}
