package com.bksoftwarevn.adminthuocdongy.marketing.service;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Popup;

import java.util.List;
import java.util.Optional;

public interface PopupService extends BaseService<Popup> {

    List<Popup> findByCom(int comId);

    void setAllHide(int comId);

    void setShow(int id);

    Optional<Popup> findShowed(int comId);
}
