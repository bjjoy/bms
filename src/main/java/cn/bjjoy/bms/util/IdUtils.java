package cn.bjjoy.bms.util;

import java.util.UUID;

/**
 * 生成id, uuid
 */
public class IdUtils {

    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
