package testcase.testBatchAndOnce;

import com.game.context.SpringHandler;
import db.ibatis.bean.HeroBaseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * test Batch
 * Created by xiewen on 2016/8/26.
 */
public class Test2 {

    public static void main(String[] args) {
        //spring配置加载
        test();
    }

    public static void test() {

        SpringHandler.loadSpring();
//        IHelpDaos obj = (IHelpDaos) SpringHandler.loadSpring().getSpringContext().getBean("testDaos");
        final IHelpDaos obj = (IHelpDaos) SpringHandler.loadSpring().getSpringContext().getBean("helpDaos");

        new Thread(new Runnable() {
            @Override
            public void run() {
//                for (int temp = 0; temp < 100; temp++) {
                int temp = 0;
                    List<HeroBaseInfo> lists = new ArrayList<>();
                // for (int i = 0 + temp*1000 ; i < 1000*(temp+1); i++) {
                         for (int i = 0 ; i <2; i++) {
                        HeroBaseInfo heroBaseInfo = new HeroBaseInfo();
                        heroBaseInfo.setUid((long) i);
                        heroBaseInfo.setAccountId("zhangsan" + i);
                        heroBaseInfo.setCreateTime(1000000L);
                        heroBaseInfo.setDiamond(i);
                        heroBaseInfo.setGold(i);
                        heroBaseInfo.setLevel(i);
                        heroBaseInfo.setServerId(i);
                        heroBaseInfo.setVipLevel(i);
                        heroBaseInfo.setExp(i);
                        heroBaseInfo.setNickName("张三" + i);
                        heroBaseInfo.setLastLoginTime(1000000L);
                        lists.add(heroBaseInfo);
                    }
                    try {
                        obj.batchSaveX(lists);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                }
            }
        }).start();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 100000 ; i < 200000; i++) {
//                    HeroBaseInfo heroBaseInfo = new HeroBaseInfo();
//                    heroBaseInfo.setUid((long) i);
//                    heroBaseInfo.setAccountId("zhangsan" + i);
//                    heroBaseInfo.setCreateTime(1000000L);
//                    heroBaseInfo.setDiamond(i);
//                    heroBaseInfo.setGold(i);
//                    heroBaseInfo.setLevel(i);
//                    heroBaseInfo.setServerId(i);
//                    heroBaseInfo.setVipLevel(i);
//                    heroBaseInfo.setExp(i);
//                    heroBaseInfo.setNickName("张三" + i);
//                    heroBaseInfo.setLastLoginTime(1000000L);
//                    obj.batchSaveOnce(heroBaseInfo);
//                }
//            }
//        }).start();



    }

}
