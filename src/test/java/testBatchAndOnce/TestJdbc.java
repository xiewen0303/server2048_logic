package testcase.testBatchAndOnce;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * Created by xiewen on 2016/8/23.
 */
public class TestJdbc {
    private static Logger LOG = LogManager.getLogger("java.sql.PreparedStatement1");

    public static void main(String[] args) {
            try {
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_test?" +"user=root&password=root");
               conn.setAutoCommit(false);
               long t1 = System.currentTimeMillis();
               String sql = "insert into hero_base_info"+
                        "( `uid` , `nick_name` , `server_id` , `account_id` , `create_time` , `level` , `gold` , `diamond` , `vip_level` , `exp` , `last_login_time` )"+
                        "values"+
                        "(  ? , ? , ? ,? ,? ,? ,? , ?, ? ,? ,? )";
                PreparedStatement pst = conn.prepareStatement(sql);
                for (int i = 1; i < 1000; i++) {
                        pst.setLong(1, i);
                        pst.setString(2, i+"name");
                        pst.setInt(3, i);
                        pst.setString(4, i+"account");
                        pst.setLong(5, i);
                        pst.setInt(6, i);
                        pst.setInt(7, i);
                        pst.setInt(8, i);
                        pst.setInt(9, i);
                        pst.setInt(10, i);
                        pst.setLong(11, i);
                        pst.addBatch();    //添加一次预定义参数
                }
                //System.out.println(JSONObject.fromObject(pst).toString());
                pst.executeBatch();



                conn.commit();
                System.out.println(System.currentTimeMillis() - t1);
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
