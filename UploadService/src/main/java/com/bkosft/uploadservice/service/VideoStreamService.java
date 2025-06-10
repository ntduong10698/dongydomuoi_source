package com.bkosft.uploadservice.service;

import org.springframework.http.ResponseEntity;

public interface VideoStreamService {

    public ResponseEntity<byte[]> prepareContent(String company, String fileName, String fileType, String range);
}
