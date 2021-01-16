package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


/**
 * @version 1.0
 * @Author 62760
 * @create 2021/1/12 19:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {



    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll() {
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> pageInfo = cmsPageRepository.findAll(pageable);
//        List content = pageInfo.getContent();
//        for (Object o : content) {
//            System.out.println(o);
//        }
        System.out.println("=================pageInfo");
        System.out.println(pageInfo);

    }

    @Test
    public void testSave(){
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageId("t01");
        cmsPage.setPageName("test.html");
        cmsPage.setSiteId("测试fdafadsfas");
        cmsPage.setPageAliase("测试页面");
        cmsPage.setTemplateId("safdasf123");
        cmsPageRepository.save(cmsPage);

    }

    @Test
    public void testUpdate(){
        Optional<CmsPage> optional = cmsPageRepository.findById("t01");
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            cmsPage.setPageAliase("测试页面02");
            cmsPageRepository.save(cmsPage);
            System.out.println("修改成功! 测试页面02");

        }

    }

    @Test
    public void testFindByPageName(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("test.html");
        System.out.println("根据页面名称查询...");
        System.out.println(cmsPage);

    }

    @Test
    public void findAllByExample(){
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        CmsPage cmsPage = new CmsPage();
//        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
//        cmsPage.setPageId("t01");
        cmsPage.setPageAliase("详情");
        ExampleMatcher matching = ExampleMatcher.matching();
        Example<CmsPage> example = Example.of(cmsPage, matching.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains()));
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);
        System.out.println("=================pageInfo:");
        System.out.println(all.getContent());
    }
}
