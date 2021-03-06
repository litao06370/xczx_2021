package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * @program: xczx_2021
 * @description: ${description}
 * @author: Tony
 * @create: 2021-01-29 12:40
 **/
@Controller
public class CmsPagePreviewController extends BaseController {

    @Autowired
    CmsPageService cmsPageService;

    /**
     * 页面预览
     * @param pageId
     */
    @RequestMapping(value = "/cms/preview/{pageId}",method = RequestMethod.GET)
    public void preview(@PathVariable("pageId") String pageId) {
        String pageHtml = cmsPageService.getPageHtml(pageId);
        ServletOutputStream ops;
        try {
            ops = response.getOutputStream();
            ops.write(pageHtml.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
