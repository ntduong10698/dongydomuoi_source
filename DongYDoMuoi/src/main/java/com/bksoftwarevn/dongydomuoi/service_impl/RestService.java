package com.bksoftwarevn.dongydomuoi.service_impl;

import com.bksoftwarevn.dongydomuoi.json.JsonResult;
import com.bksoftwarevn.dongydomuoi.json.RestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class RestService {

    @Autowired
    private RestTemplate template;

    JSONParser parser = new JSONParser();

    private static final String SERVER_URL = "https://dev.bksoftwarevn.com/";

    @Value("${urlCDN}")
    private String urlCDN;

    @Value("${urlCDNConfig}")
    private String urlCDNConfig;

    public String callGetFile(String uri) {
        String url = urlCDN.concat(uri);
        try {
            return template.getForObject(url, String.class);
        } catch (Exception ex) {
            log.error("rest call get file " + url + " err {0}", ex);
            throw ex;
        }
    }

    public String callGetFileConfig(String uri) {
        String url = urlCDNConfig.concat(uri);
        try {
            return template.getForObject(url, String.class);
        } catch (Exception ex) {
            log.error("rest call get file " + url + " err {0}", ex);
            throw ex;
        }
    }

    public Object callGet(RestBuilder rest) {
        StringBuilder url = new StringBuilder(SERVER_URL + rest.getService() + "/" + rest.getUri() + "?");
        rest.getParams().forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
        try {
            JsonResult data = template.getForObject(url.toString(), JsonResult.class);
            if (data.getData() == null) return null;
            return data.getData();
        } catch (Exception ex) {
            log.error("rest call " + url + " err {0}", ex);
            throw ex;
        }
    }

    public JSONObject callGetJson(RestBuilder rest) throws Exception {
        StringBuilder url = new StringBuilder(SERVER_URL + rest.getService() + "/" + rest.getUri() + "?");
        rest.getParams().forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
        try {
            String rs = template.getForObject(url.toString(), String.class);
            JSONObject jsonObject = (JSONObject) parser.parse(rs);
            return (JSONObject) jsonObject.get("data");
        } catch (Exception ex) {
            log.error("rest call " + url + " err {0}", ex);
            throw ex;
        }
    }

    public Object callPut(RestBuilder rest) {
        StringBuilder url = new StringBuilder(SERVER_URL + rest.getService() + "/" + rest.getUri() + "?");
        rest.getParams().forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
        try {
            template.put(url.toString(), null);
            return null;
        } catch (Exception ex) {
            log.error("rest call " + url + " err {0}", ex);
            throw ex;
        }
    }

    public List<Object> callGetList(RestBuilder rest) {
        StringBuilder url = new StringBuilder(SERVER_URL + rest.getService() + "/" + rest.getUri() + "?");
        rest.getParams().forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
        System.out.println(url.toString());
        try {
            JsonResult data = template.getForObject(url.toString(), JsonResult.class);
            if (data.getData() == null) return null;
            return (List<Object>) data.getData();
        } catch (Exception ex) {
            log.error("rest call " + url + " err {0}", ex);
            throw ex;
        }
    }
}
