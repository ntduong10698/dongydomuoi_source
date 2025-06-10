package com.bksoftwarevn.adminthuocdongy.marketing.service_impl;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Popup;
import com.bksoftwarevn.adminthuocdongy.marketing.repository.PopupRepo;
import com.bksoftwarevn.adminthuocdongy.marketing.service.PopupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PopupServiceImpl extends BaseServiceImpl<Popup, Integer, PopupRepo> implements PopupService {

    public PopupServiceImpl(PopupRepo repo) {
        super(repo, "Popup");
    }

    @Override
    public List<Popup> findByCom(int comId) {
        try {
            return repo.findByCom(comId);
        }catch (Exception ex){
            log.error("find Popup by company err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void setAllHide(int comId) {
        try {
             repo.setAllHide(comId);
        }catch (Exception ex){
            log.error("find Popup by company err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void setShow(int id) {
        try {
            repo.setShow(id);
        }catch (Exception ex){
            log.error("find Popup by company err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Optional<Popup> findShowed(int comId) {
        try {
            return repo.findShowed(comId);
        }catch (Exception ex){
            log.error("find Popup showed err {0}", ex);
            throw ex;
        }
    }
}
