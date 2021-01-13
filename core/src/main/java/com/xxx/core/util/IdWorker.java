package com.xxx.core.util;

import java.util.UUID;

/**
 * @author Ian
 * @date 2021/1/13 10:19
 */
public class IdWorker {

    /**
     * 主机和进程的机器码
     */
    private static SnowflakeIdWorker WORKER = new SnowflakeIdWorker();

    public static long getId() {
        return WORKER.nextId();
    }

    public static String getIdStr() {
        return String.valueOf(WORKER.nextId());
    }

    /**
     * <p>
     * 有参构造器
     * </p>
     *
     * @param workerId     工作机器 ID
     * @param datacenterId 序列号
     */
    public static void initSequence(long workerId, long datacenterId) {
        WORKER = new SnowflakeIdWorker(workerId, datacenterId);
    }

    /**
     * <p>
     * 获取去掉"-" UUID
     * </p>
     */
    public static synchronized String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
