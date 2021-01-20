package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * 执行抛出异常的类
 * @version 1.0
 * @Author 62760
 * @create 2021/1/20 19:00
 */
public class ExceptionCast {
    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}
