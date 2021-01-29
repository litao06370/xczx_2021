package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.config.cms.CmsConfigControllerApi;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigControllerApi {
    @Autowired
    CmsPageService cmsPageService;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getModel/{id}")
    @Override
    public CmsConfig getModel(@PathVariable("id") String id) {

        return cmsPageService.getConfigById(id);
    }


    @GetMapping("/getModel/aa")
    public Map getModel() {
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/getModel/5a791725dd573c3574ee333f", Map.class);
        Map body = forEntity.getBody();
        System.out.println("aaa");
        return body;
    }

}
