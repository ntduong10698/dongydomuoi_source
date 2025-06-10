package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductFile;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.FileRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl extends BaseServiceImpl<ProductFile, Integer, FileRepo> implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public FileServiceImpl(FileRepo repo) {
        super(repo, "ProductFile");
    }

    @Override
    public void saveAll(List<ProductFile> files) {
        try {
            repo.saveAll(files);
        }catch (Exception ex){
            logger.error("find file by product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<ProductFile> findByProduct(int id, int type) {
        try {
            return repo.findByProductId(id, type);
        }catch (Exception ex){
            logger.error("find file by product err {0}", ex);
            throw ex;
        }
    }
}
