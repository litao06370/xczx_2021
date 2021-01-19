package com.xuecheng.api.config.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.*;

/**
 * 定义接口
 */
@Api(value = "cms页面管理接口",description = "详情:提供页面的CRUD等")
public interface CmsPageControllerApi {
    /**
     * 页面查询
     * @param page(分页参数)
     * @param size(分页参数)
     * @param queryPageRequest(查询条件)
     * @return
     */
    @ApiOperation("分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",paramType = "path",dataType = "int",required = true),
            @ApiImplicitParam(name = "size",value = "每页记录数",paramType = "path",dataType = "int",required = true)
    })
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 添加页面
     * @param cmsPage
     * @return
     */
    @ApiOperation("添加页面")
    CmsPageResult add(CmsPage cmsPage);

    @ApiOperation("根据ID,查询页面")
    CmsPage findById(String id);

    @ApiOperation("修改页面")
    CmsPageResult edit(String id, CmsPage cmsPage);

}
