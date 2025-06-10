package com.bksoftwarevn.adminthuocdongy.userservice.service;

import com.bksoftwarevn.adminthuocdongy.userservice.entities.AppUser;
import com.bksoftwarevn.adminthuocdongy.userservice.entities.LoginForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<AppUser> findById(int id);

    Optional<AppUser> findByUsernameAndComId(String usernameAndComId);

    Optional<AppUser> checkLogin(LoginForm form);

    Optional<AppUser> findUserByEmailAndCom(String email, int comId);

    boolean checkRegister(String username, String email, int comId);

    Optional<AppUser> save(AppUser appUser);

    Page<AppUser> findByCompany(int comId, int id, String text, Pageable pageable);

    List<AppUser> getFeedBack(int comId);

    List<AppUser> findByIds(int comId, List<Integer> ids);
}
