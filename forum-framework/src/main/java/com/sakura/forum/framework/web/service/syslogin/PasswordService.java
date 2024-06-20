package com.sakura.forum.framework.web.service.syslogin;

import com.sakura.forum.exception.user.UserPasswordNotMatchException;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

/**
 * 密码服务
 *
 * @author modakai
 */
@Service
public class PasswordService {


    private static final int ITERATIONS = 10; // 迭代次数
    private static final int MEMORY = 65536; // 内存占用(KB)
    private static final int PARALLELISM = 2; // 并行度

    // 默认盐 就是16
    private static final Argon2 argon2 = Argon2Factory.create();

    public static void main(String[] args) {
        PasswordService passwordService = new PasswordService();
        System.out.println(passwordService.encryption("123abc"));
    }

    /**
     * 使用 BCrypt 进行加密
     *
     * @param plainTextPassword 加密的密码
     * @return 加密后的密文
     */
    public String encryption(String plainTextPassword) {
        return argon2.hash(ITERATIONS, MEMORY, PARALLELISM, plainTextPassword.toCharArray());
    }


    /**
     * 验证
     *
     * @param plainTextPassword 明文
     * @param storedHash        密文
     */
    public void validate(String plainTextPassword, String storedHash) {
        if (!argon2.verify(storedHash, plainTextPassword.toCharArray())) {
            // 不匹配就抛出密码错误异常
            throw new UserPasswordNotMatchException();
        }

    }

}
