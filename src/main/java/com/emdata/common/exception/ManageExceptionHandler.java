package com.emdata.common.exception;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
@Slf4j
public class ManageExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ServiceException.class)
    public R<?> handleRRException(ServiceException e) {
        log.error(e.getMessage() , e);
        return R.failed(e.getMessage());
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public R<?> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage() , e);
        return R.failed("数据库中已存在该记录");
    }


    @ExceptionHandler(Exception.class)
    public R<?> handleException(Exception e) {
        log.error(e.getMessage() , e);
        return R.failed("操作失败");
    }

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?> HttpRequestMethodNotSupportedException(Exception e) {
        log.error(e.getMessage() , e);
        return R.failed("没有权限，请联系管理员授权");
    }

    /**
     * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public R<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error(e.getMessage() , e);
        return R.failed("文件大小超出10MB限制, 请压缩或降低文件质量! ");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public R<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage() , e);
        return R.failed("字段太长,超出数据库字段的长度");
    }

    @ExceptionHandler(PoolException.class)
    public R<?> handlePoolException(PoolException e) {
        log.error(e.getMessage() , e);
        return R.failed("Redis 连接异常!");
    }

}
