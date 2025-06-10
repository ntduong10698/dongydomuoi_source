package com.bksoftwarevn.adminthuocdongy.newsservice.service;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NAttachment;

import java.util.List;

public interface NAttachmentService extends BaseService<NAttachment>{

    List<NAttachment> findByNews(int newsId);

    List<NAttachment> saveAll(List<NAttachment> datas);
}
