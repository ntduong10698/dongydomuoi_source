package com.bksoftwarevn.adminthuocdongy.newsservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NAttachment;
import com.bksoftwarevn.adminthuocdongy.newsservice.repository.NAttachmentRepo;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NAttachmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NAttachmentServiceImpl extends BaseServiceImpl<NAttachment, Integer, NAttachmentRepo> implements NAttachmentService {

    public NAttachmentServiceImpl(NAttachmentRepo repo) {
        super(repo, "Attachment");
    }

    @Override
    public List<NAttachment> findByNews(int newsId) {
        try {
            return repo.findByNews(newsId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<NAttachment> saveAll(List<NAttachment> datas) {
        try {
            return repo.saveAll(datas);
        }catch (Exception e){
            throw e;
        }
    }
}
