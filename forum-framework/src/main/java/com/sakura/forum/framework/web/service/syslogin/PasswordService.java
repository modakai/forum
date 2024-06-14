package com.sakura.forum.framework.web.service.syslogin;

import cn.dev33.satoken.secure.BCrypt;
import com.sakura.forum.exception.user.UserPasswordNotMatchException;
import org.springframework.stereotype.Service;

/**
 * 密码服务
 *
 * @author modakai
 */
@Service
public class PasswordService {


    private static final int SALT_LENGTH = 16;
    // 加盐的次数
    private static final String salt = BCrypt.gensalt(SALT_LENGTH);

    /**
     * 使用 BCrypt 进行加密
     *
     * @param password 加密的密码
     * @return 加密后的密文
     */
    public String encryption(String password) {
        return BCrypt.hashpw(password, salt);
    }


    /**
     * 验证
     *
     * @param candidate_password 明文
     * @param stored_hash        密文
     */
    public void validate(String candidate_password, String stored_hash) {
        if (!BCrypt.checkpw(candidate_password, stored_hash)) {
            // 不匹配就抛出密码错误异常
            throw new UserPasswordNotMatchException();
        }
    }

}
