package com.bksoftwarevn.adminthuocdongy.uploadservice.config;

public class VideoConstants {
    public static final String VIDEO = System.getProperty( "catalina.base") + "/webapps/resources/micro-upload";
    //public static final String VIDEO = "D:\\upload";

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String VIDEO_CONTENT = "video/";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String ACCEPT_RANGES = "Accept-Ranges";
    public static final String BYTES = "bytes";
    public static final int BYTE_RANGE = 1024;
}
