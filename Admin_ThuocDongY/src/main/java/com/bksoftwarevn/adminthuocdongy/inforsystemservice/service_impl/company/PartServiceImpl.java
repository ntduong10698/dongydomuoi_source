package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Part;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company.PartRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.PartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartServiceImpl.class);

    @Autowired
    private PartRepo partRepo;

    @Override
    public Optional<Part> findById(int id) {
        try{
            return partRepo.findById(id);
        }catch (Exception ex) {
            LOGGER.error("find-part-by-id-and-xoa : " + ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Part> save(Part part) {
        try{
            return Optional.of(partRepo.save(part));
        }catch (Exception ex) {
            LOGGER.error("save-part-error : " + ex);
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleted(int id) {
        try{
            return partRepo.delete(id) > 0;
        }catch (Exception ex){
            LOGGER.error("delete-part-error : " + ex);
            return false;
        }
    }

    @Override
    public void setVideo(int id, boolean video) {
        try{
             partRepo.setVideo(id, video);
        }catch (Exception ex){
            LOGGER.error("delete-part-error : " + ex);
            throw ex;
        }
    }

    @Override
    public List<Part> findByCompany(int id, String code) {
        try{
            return partRepo.findByCompany(id, code);
        }catch (Exception ex){
            LOGGER.error("delete-part-error : " + ex);
            return null;
        }
    }
}
