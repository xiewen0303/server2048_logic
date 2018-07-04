package testcase;

import db.ibatis.bean.HeroBaseInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * test
 * Created by xiewen on 2016/8/15.
 */
public class Test {

    public static void test1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date(1392339881000L)));
    }

    public static void testBatchInsert(){
        for (int i = 0; i <1000000 ; i++) {
            HeroBaseInfo heroBaseInfo =  new HeroBaseInfo();
            heroBaseInfo.setUid((long)i);
            heroBaseInfo.setAccountId("zhangsan"+i);
            heroBaseInfo.setCreateTime(1000000L);
            heroBaseInfo.setDiamond(i);
            heroBaseInfo.setGold(i);
            heroBaseInfo.setLevel(i);
            heroBaseInfo.setServerId(i);
            heroBaseInfo.setVipLevel(i);
            heroBaseInfo.setExp(i);
            heroBaseInfo.setNickName("张三"+i);
            heroBaseInfo.setLastLoginTime(1000000L);
            heroBaseInfo.insert();
        }
    }



    public static void testBatchUpdate(){
        for (int i = 0; i <1000000 ; i++) {
            HeroBaseInfo heroBaseInfo =  new HeroBaseInfo();
            heroBaseInfo.setUid((long)i);
            heroBaseInfo.setAccountId("李四"+i);
            heroBaseInfo.setCreateTime(1000000L);
            heroBaseInfo.setDiamond(i);
            heroBaseInfo.setGold(i);
            heroBaseInfo.setLevel(i);
            heroBaseInfo.setServerId(i);
            heroBaseInfo.setVipLevel(i);
            heroBaseInfo.setExp(i);
            heroBaseInfo.setNickName("李四"+i);
            heroBaseInfo.setLastLoginTime(1000000L);
            heroBaseInfo.update();
        }
    }
}
