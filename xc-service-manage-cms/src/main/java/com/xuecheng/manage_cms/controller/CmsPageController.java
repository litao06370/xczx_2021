package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.config.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.service.CmsPageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    public final String API_URL = "/cms/page";

    @Autowired
    CmsPageService cmsPageService;

    @GetMapping(API_URL + "/list/{page}/{size}")
    @Override
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {

        return cmsPageService.findList(page, size, queryPageRequest);
    }

    @PostMapping(API_URL + "/add")
    @Override
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        CmsPageResult result = cmsPageService.add(cmsPage);
        return result;
    }

    @GetMapping(API_URL + "/get/{id}")
    @Override
    public CmsPage findById(@PathVariable("id") String id) {
        return cmsPageService.findById(id);
    }

    @PutMapping(API_URL + "/edit/{id}")
    @Override
    public CmsPageResult edit(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return cmsPageService.updatePage(id, cmsPage);
    }

    /**
     * 自定义建议测试
     *
     * @return
     */
    @GetMapping("/test1")
    @ApiOperation("简易测试连接成功与否")
    public String test1() {
        return "test1 successs!";
    }
}
