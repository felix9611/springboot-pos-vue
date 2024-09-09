package com.fixedasset.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Case Captcha Exception
 *
 * @Author WaiterXiaoYY
 * @Date 2022/1/18 19:37
 * @Version 1.0
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg) {
        super(msg);
    }
}
