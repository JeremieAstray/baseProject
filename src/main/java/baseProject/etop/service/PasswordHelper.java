package baseProject.etop.service;

import baseProject.etop.modules.baseModules.entity.TUserEntity;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by pcc on 2015/3/8.
 */
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private final int hashIterations = 2;

    public void encryptPassword(TUserEntity user) {


        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
}
