package testcase.testbase;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import db.ibatis.bean.HeroBaseInfo;

import java.io.Reader;
import java.sql.SQLException;

/**
 * test batch
 * Created by xiewen on 2016/8/23.
 */
public class TestBatch {


    public static void main(String[] args) throws Exception {
        Reader read = Resources.getResourceAsReader("db/ibatis/testbase/SqlMapConfig.xml");
        SqlMapClient  sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(read);
        read.close();

        test1(sqlMapClient);
    }
//21397

    public static void test1(SqlMapClient sqlMapClient){

        try{
            long t1 = System.currentTimeMillis();


            for (int j = 0; j < 20 ; j++) {


            sqlMapClient.startTransaction();
            sqlMapClient.startBatch();
            for (int i = 0+j*50; i <50*(j+1) ; i++) {
                HeroBaseInfo heroBaseInfo =  new HeroBaseInfo();
                heroBaseInfo.setUid((long)i);
                heroBaseInfo.setAccountId("zhangsan"+i);
                heroBaseInfo.setCreateTime(1000000L);
                heroBaseInfo.setDiamond(i);
                heroBaseInfo.setGold(i);
                heroBaseInfo.setLevel(i);
                heroBaseInfo.setServerId(i);
                heroBaseInfo.setVipLevel(i);
                heroBaseInfo.setNickName("张三"+i);
                heroBaseInfo.setLastLoginTime(1000000L);
                sqlMapClient.insert("insert", heroBaseInfo);
            }

            sqlMapClient.executeBatch();
            sqlMapClient.commitTransaction();


            sqlMapClient.startTransaction();
            sqlMapClient.startBatch();

            for (int i = 0+j*50; i <50*(j+1) ; i++) {
                HeroBaseInfo heroBaseInfo =  new HeroBaseInfo();
                heroBaseInfo.setUid((long)i);
                heroBaseInfo.setAccountId("李四"+i);
                heroBaseInfo.setCreateTime(1000000L);
                heroBaseInfo.setDiamond(i);
                heroBaseInfo.setGold(i);
                heroBaseInfo.setLevel(i);
                heroBaseInfo.setServerId(i);
                heroBaseInfo.setVipLevel(i);
                heroBaseInfo.setNickName("李四"+i);
                heroBaseInfo.setLastLoginTime(1000000L);
                sqlMapClient.update("update", heroBaseInfo);
            }
            sqlMapClient.executeBatch();
            sqlMapClient.commitTransaction();

            }



            System.out.println(  System.currentTimeMillis()- t1);
        } catch (Exception e){
            try {
                sqlMapClient.getCurrentConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {

        }
    }

}
