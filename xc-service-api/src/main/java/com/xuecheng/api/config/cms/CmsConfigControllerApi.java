package com.xuecheng.api.config.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 定义接口
 */
@Api(value = "cms_config配置信息接口", description = "cms_config配置信息接口:提供页面的配置信息")
public interface CmsConfigControllerApi {

    @ApiOperation("根据id查询配置信息")
    CmsConfig getModel(String id);


}
