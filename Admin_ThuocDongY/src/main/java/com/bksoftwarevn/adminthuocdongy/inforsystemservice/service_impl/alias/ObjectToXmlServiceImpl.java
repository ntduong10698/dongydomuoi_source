package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.ObjectToXmlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import java.io.FileOutputStream;

@Service
public class ObjectToXmlServiceImpl<T> implements ObjectToXmlService<T> {

    private static final Logger logger = LoggerFactory.getLogger(ObjectToXmlServiceImpl.class);


    @Override
    public boolean ObjectToXml(JAXBContext contextObj, T t, String directoryFile) throws Exception {
        boolean rs = false;
        try {
            javax.xml.bind.Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // Write data to console
            marshallerObj.marshal(t, System.out);
            // Write data to file xml
            marshallerObj.marshal(t, new FileOutputStream(directoryFile));
            rs = true;
        } catch (Exception e) {
            logger.error("object to xml err {0}", e);
            throw e;
        }
        return rs;
    }

    private class Marshaller {
    }
}
