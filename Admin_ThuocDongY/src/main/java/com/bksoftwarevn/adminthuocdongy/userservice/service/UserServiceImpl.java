package com.bksoftwarevn.adminthuocdongy.userservice.service;

import com.bksoftwarevn.adminthuocdongy.userservice.entities.AppUser;
import com.bksoftwarevn.adminthuocdongy.userservice.entities.LoginForm;
import com.bksoftwarevn.adminthuocdongy.userservice.repo.UserRepo;
import com.bksoftwarevn.adminthuocdongy.userservice.utils.EncodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public Optional<AppUser> findById(int id) {
        try {
            return userRepo.findById(id);
        } catch (Exception ex) {
            logger.error("find user by id error {0}", ex);
            throw new RuntimeException("cant find user id {0}", ex);
        }
    }

    @Override
    public Optional<AppUser> findByUsernameAndComId(String usernameAndComId) {
        try {
            String username = usernameAndComId.split("-")[0];
            int comId = Integer.parseInt(usernameAndComId.split("-")[1]);
            return userRepo.findByUsername(username, comId);
        } catch (Exception ex) {
            logger.error("find user by username error {0}", ex);
            throw new RuntimeException("cant find username {0}", ex);
        }
    }

    @Override
    public Optional<AppUser> checkLogin(LoginForm form) {
        try {
            return userRepo.checkLogin(form.getUsername(), EncodeUtil.getSHA256(form.getPassword()), form.getComId());
        } catch (Exception ex) {
            logger.error("login error {0}", ex);
            throw ex;
        }
    }

    @Override
    public Optional<AppUser> findUserByEmailAndCom(String email, int comId) {
        try {
            return userRepo.findByEmail(email, comId);
        } catch (Exception ex) {
            logger.error("find error {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean checkRegister(String username, String email, int comId) {
        try {
            return userRepo.checkRegister(username, email, comId) > 0;
        } catch (Exception ex) {
            logger.error("check error {0}", ex);
            throw ex;
        }
    }

    @Override
    public Optional<AppUser> save(AppUser appUser) {
        try {
            return Optional.of(userRepo.save(appUser));
        } catch (Exception ex) {
            logger.error("save error {0}", ex);
            throw new RuntimeException("save err {0}", ex);
        }
    }

    @Override
    public Page<AppUser> findByCompany(int comId, int id, String text, Pageable pageable) {
        try {
            return userRepo.findByCompany(comId, id, text, pageable);
        } catch (Exception ex) {
            logger.error("find by com err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<AppUser> getFeedBack(int comId) {
        try {
            return userRepo.getFeedback(comId);
        } catch (Exception ex) {
            logger.error("find by com err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<AppUser> findByIds(int comId, List<Integer> ids) {
        try {
            return userRepo.findByIds(comId, ids);
        } catch (Exception ex) {
            logger.error("find by com err {0}", ex);
            throw ex;
        }
    }
}
