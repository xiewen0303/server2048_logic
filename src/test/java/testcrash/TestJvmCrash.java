package testcase.testcrash;

import log.MemoryManager;

/**
 * 测试jvm
 * Created by xiewen on 2016/10/28.
 */
public class TestJvmCrash {
    public static void main(String[] args) {
        int k = 1024 * 1024 *(1024+500);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(k +"\t" + 2147483645/(1024*1024)+"MB");
        byte[] b2 = new byte[2147483645];
        new Thread(new Runnable() {
            @Override
            public void run() {
                MemoryManager.getInstance().initialize();
            }
        },"Test1==").start();
//        System.out.println(b2.length / (1024*1024)+"MB");
//
//
//        try {
//            Thread.sleep(500*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}