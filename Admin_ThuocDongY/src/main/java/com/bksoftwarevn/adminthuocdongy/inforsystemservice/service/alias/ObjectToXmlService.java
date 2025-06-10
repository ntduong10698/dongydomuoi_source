package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias;

import javax.xml.bind.JAXBContext;

public interface ObjectToXmlService<T> {

    boolean ObjectToXml(JAXBContext contextObj, T t, String directoryFile) throws Exception;
}
