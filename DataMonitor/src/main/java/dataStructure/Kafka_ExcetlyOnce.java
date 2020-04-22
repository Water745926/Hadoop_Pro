package dataStructure;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @description: 事物操作
 * @author: lijiayu
 * @create: 2020-04-15 10:21
 */
public class Kafka_ExcetlyOnce {
    public static void main(String[] args) {

        /**
         * mysql事务机制
         */
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.192.100:3306/","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.setAutoCommit(false);//开启事务
            //     真正的代码......
            con.commit();//try的最后提交事务
        } catch (Exception exception) {
            //con.rollback();//回滚事务
            exception.printStackTrace();//打印
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /**
         * redis事务机制
         */
        Jedis jedis = JedisPoolUtil.getJedis();
        Transaction tran = jedis.multi(); // 开启事务
        tran.set("12", "12"); // 商品数量-1
        List<Object> list = tran.exec(); // 执行事务
        System.out.println(list);
    }
}