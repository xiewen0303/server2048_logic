package testcase.nioread;

import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import io.netty.util.internal.SystemPropertyUtil;

import java.nio.channels.spi.SelectorProvider;

/**
 * test read
 * Created by xiewen on 2016/12/1.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(SelectorProvider.provider());

        System.out.println(Runtime.getRuntime().availableProcessors() * 2);
        System.out.println(SystemPropertyUtil.getInt("io.netty.eventLoopThreads", Runtime.getRuntime().availableProcessors() * 2));
    }
}
