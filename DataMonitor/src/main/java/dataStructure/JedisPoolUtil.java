package dataStructure;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description: jedis连接池
 * @author: lijiayu
 * @create: 2020-04-15 11:13
 */
public class JedisPoolUtil {
    private static JedisPool pool;

    //创建pool方法
    private static void createJedisPool() {
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(100);
        // 设置最大阻塞时间，毫秒数
        config.setMaxWaitMillis(1000);
        // 设置空间连接
        config.setMaxIdle(10);
        // 创建连接池
        pool = new JedisPool(config, "192.168.31.201", 6379, 2000, null, 3);
    }

    //多线程环境下初始化pool
    private static synchronized void poolInit() {
        if (pool == null) createJedisPool();
    }

    //获取jedis连接
    public static Jedis getJedis() {
        if (pool == null) poolInit();
        return pool.getResource();
    }

    //归还jedis连接
    public static void returnRes(Jedis jedis) {
        pool.returnResource(jedis);
    }

    // 关闭连接池
    public static void close() {
        pool.close();
    }
}