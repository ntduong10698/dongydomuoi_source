package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.PartDetail;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company.PartDetailRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.PartDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartDetailServiceImpl implements PartDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartDetailServiceImpl.class);

    @Autowired
    private PartDetailRepo partDetailRepo;

    @Override
    public Optional<PartDetail> findById(int id) {
        try{
            return partDetailRepo.findById(id);
        }catch (Exception ex) {
            LOGGER.error("find-part-by-id-and-xoa : " + ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<PartDetail> save(PartDetail part) {
        try{
            return Optional.of(partDetailRepo.save(part));
        }catch (Exception ex) {
            LOGGER.error("save-part-detail-error : " + ex);
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleted(int id) {
        try{
            return partDetailRepo.deleted(id) > 0;
        }catch (Exception ex){
            LOGGER.error("delete-part-detail-error : " + ex);
            return false;
        }
    }

    @Override
    public List<PartDetail> findByPartId(int id) {
        try{
            return partDetailRepo.findByPartId(id);
        }catch (Exception ex){
            LOGGER.error("find-by-part-detail-error : " + ex);
            return null;
        }
    }
}
