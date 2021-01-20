package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * 自定义异常类型
 *
 * @version 1.0
 * @Author 62760
 * @create 2021/1/20 18:46
 */
public class CustomException extends RuntimeException {

    //    错误代码
    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        super("错误代码:" + resultCode.code() + "; 错误信息:" + resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
