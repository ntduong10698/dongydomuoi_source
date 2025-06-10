package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Information;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company.InfoRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoServiceImpl implements InfoService {

    private static final Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);

    @Autowired
    private InfoRepo infoRepo;


    @Override
    public Optional<Information> findById(int id) {
        try{
            return infoRepo.findById(id);
        }catch (Exception ex) {
            logger.error("find-contact-by-id-err : " + ex);
            throw ex;
        }
    }

    @Override
    public List<Information> findByComId(int comId) {
        try{
            return infoRepo.findByCompany(comId);
        }catch (Exception ex) {
            logger.error("find-contact-by-id-err : " + ex);
            throw ex;
        }
    }

    @Override
    public Optional<Information> save(Information information) {
        try{
            return Optional.of(infoRepo.save(information));
        }catch (Exception ex){
            logger.error("save-contact-err : " + ex);
            throw ex;
        }
    }

    @Override
    public List<Information> saveAll(Iterable<Information> information) {
        try{
            return infoRepo.saveAll(information);
        }catch (Exception ex){
            logger.error("save-contact-err : " + ex);
            throw ex;
        }
    }

    @Override
    public boolean deleted(int id) {
        try{
            return infoRepo.deleted(id) > 0;
        }catch (Exception ex){
            logger.error("delete-contact-err : " + ex);
            throw ex;
        }
    }
}
