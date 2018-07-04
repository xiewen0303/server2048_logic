package com.game.core;

/**
 * IService
 * Created by xiewen on 2016/11/9.
 */
public interface IService {
    /**
     * 启动时
     */
    void init();
    
    /**
     * 登录时需要再加到内存中的数据
     * @param uid
     */
    void load(long uid);
    
    /**
     * 登陆业务处理(如登录需要检测、或推送数据给客服端)
     */
    void login(long uid);

    /**
     * 登出
     * @param uid
     */
    void offline(long uid);

    /**
     * 游戏正常退出
     */
    void exit();
}
