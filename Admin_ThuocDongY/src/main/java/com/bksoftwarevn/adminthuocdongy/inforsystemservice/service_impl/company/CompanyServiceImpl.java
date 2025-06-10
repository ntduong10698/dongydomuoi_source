package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Company;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company.CompanyRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public List<Company> findAll() {
        try{
                return companyRepo.findAll();
        }catch (Exception ex){
            logger.error("find-all-company-err : " + ex);
            return null;
        }
    }

    @Override
    public Optional<Company> findById(int id) {
        try{
            return companyRepo.findById(id);
        }catch (Exception ex) {
            logger.error("find-company-by-id-err : " + ex);
            return Optional.empty();
        }
    }


    @Override
    public Optional<Company> save(Company company) {
        try{
            return Optional.of(companyRepo.save(company));
        }catch (Exception ex){
            logger.error("save-company-err : " + ex);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleted(int id) {
        try{
            return companyRepo.deleted(id) > 0;
        }catch (Exception ex){
            logger.error("delete-company-err : " + ex);
            return false;
        }
    }

}
