package testcase;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

/**
 * testByte
 * Created by xiewen on 2016/10/17.
 */
public class TByte {

    public static void main(String[] args) {
        ByteBuf testbufferNew = PooledByteBufAllocator.DEFAULT.heapBuffer(18);
        byte[] bytes = new byte[]{0,0,0,14,0,0,0,1,};
    }
}
