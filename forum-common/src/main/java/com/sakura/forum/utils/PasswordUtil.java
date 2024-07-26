package com.sakura.forum.utils;

import com.sakura.forum.exception.user.UserPasswordNotMatchException;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordUtil {

    private static final int ITERATIONS = 10; // 迭代次数
    private static final int MEMORY = 65536; // 内存占用(KB)
    private static final int PARALLELISM = 2; // 并行度

    // 默认盐 就是16
    private static final Argon2 argon2 = Argon2Factory.create();

    /**
     * 使用 BCrypt 进行加密
     *
     * @param plainTextPassword 加密的密码
     * @return 加密后的密文
     */
    public static String encryption(String plainTextPassword) {
        return argon2.hash(ITERATIONS, MEMORY, PARALLELISM, plainTextPassword.toCharArray());
    }


    /**
     * 验证
     *
     * @param plainTextPassword 明文
     * @param storedHash        密文
     */
    public static void validate(String plainTextPassword, String storedHash) {
        if (!argon2.verify(storedHash, plainTextPassword.toCharArray())) {
            // 不匹配就抛出密码错误异常
            throw new UserPasswordNotMatchException();
        }
    }

}
