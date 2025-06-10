package com.bksoftwarevn.adminthuocdongy.cartservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Status;
import com.bksoftwarevn.adminthuocdongy.cartservice.repository.StatusRepo;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatusServiceImpl extends BaseServiceImpl<Status, Integer, StatusRepo> implements StatusService {

    private static final Logger logger = LoggerFactory.getLogger(StatusServiceImpl.class);

    public StatusServiceImpl(StatusRepo repo) {
        super(repo, "status");
    }

    @Override
    public List<Status> findAll() {
        try {
            return repo.findAll();
        } catch (Exception ex) {
            logger.error("find all Status err {0}", ex);
            return null;
        }
    }


}
