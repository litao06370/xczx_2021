package com.xuecheng.manage_cms.service;

import com.sun.org.apache.bcel.internal.generic.IFNULL;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import freemarker.core.OptInTemplateClassResolver;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * @version 1.0
 * @Author 62760
 * @create 2021/1/13 11:24
 */
@Service
public class CmsPageService {
    @Autowired
    CmsPageRepository cmsPageRepository;

    /**
     * 查询列表(带分页)
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        Example example = null;
//        非空判断
        if (queryPageRequest != null) {
//      查询条件匹配器
            ExampleMatcher matching = ExampleMatcher.matching();
//      查询条件对象示例
            CmsPage cmsPage = new CmsPage();
            if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
                cmsPage.setSiteId(queryPageRequest.getSiteId());
            }
            if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
                cmsPage.setPageAliase(queryPageRequest.getPageAliase());
                matching = matching.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
            }
            example = Example.of(cmsPage, matching);
        }

//        page和size 根据用户习惯和 mongodb 查询方式, 对页码数字进行处理
        System.out.println("page的值是: ?" + page);
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 10;
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);

        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());

        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    /**
     * 新增页面
     * @param cmsPage
     * @return
     */
    public CmsPageResult addPage(CmsPage cmsPage) {
        /*
        1.判断接收的页面参数是否为空
        2.判断该页面在数据库中是否已存在
        (根据三个参数确保唯一性,数据库中以这三个数据创建了索引)
        3.存入数据(id设置null,自动给定)
         */
        if (ObjectUtils.isEmpty(cmsPage)) {
            return new CmsPageResult(CommonCode.FAIL,null);
        }
        CmsPage one = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (!ObjectUtils.isEmpty(one)) {
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        cmsPage.setPageId(null);

        return new CmsPageResult(CommonCode.SUCCESS,cmsPageRepository.save(cmsPage));
    }

    /**
     * 根据Object_id 查询页面
     * @param id
     * @return
     */
    public CmsPage findById(String id) {

        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            return cmsPage;
        }
        return null;
    }

    /**
     * 修改页面
     * @param id
     * @param cmsPage
     * @return
     */
    public CmsPageResult updatePage(String id, CmsPage cmsPage){
        if (cmsPage == null) {

        }
            CmsPage one = this.findById(id);
            if (one == null) {
                ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
            }
                //更新模板id
                one.setTemplateId(cmsPage.getTemplateId());
                //更新所属站点
                one.setSiteId(cmsPage.getSiteId());
                //更新页面别名
                one.setPageAliase(cmsPage.getPageAliase());
                //更新页面名称
                one.setPageName(cmsPage.getPageName());
                //更新访问路径
                one.setPageWebPath(cmsPage.getPageWebPath());
                //更新物理路径
                one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
                //执行更新
                cmsPageRepository.save(one);
                if (one != null) {
                    return new CmsPageResult(CommonCode.SUCCESS, one);
                }



        return new CmsPageResult(CommonCode.FAIL, null);


    }


    public ResponseResult deletePage(String id){
        CmsPage one = this.findById(id);
        if (one != null) {
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
