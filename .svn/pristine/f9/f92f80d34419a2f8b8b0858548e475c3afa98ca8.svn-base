package db.ibatis.innerMessage;

import com.game.core.excutehandler.GameThreadFactory;
import db.ibatis.DBConst;
import db.ibatis.TaskPrepareService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * DB转发器
 * Created by xiewen on 2016/8/8.
 */
public class DBDispatch {

    /**
     * 接收待处理db的线程（准备）
     */
    static ScheduledExecutorService prepareExcutor = Executors.newSingleThreadScheduledExecutor(new GameThreadFactory("DB_RECEIVE"));

    /**
     * 持久化到db的线程（处理）
     */
    static ExecutorService processExcutor = Executors.newSingleThreadExecutor(new GameThreadFactory("DB_PERSISTENT"));

    static{
        starHeartBeat();
    }

    public static void main(String[] args) {
        starHeartBeat();
    }

    public static void starHeartBeat(){
        prepareExcutor.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                    tick();
            }
        }, 1000, DBConst.INTERVAL, TimeUnit.MILLISECONDS);
    }

    /**
     * 每次心跳检测
     */
    public static void tick(){
        dispatchPer(new Runnable() {
            @Override
            public void run() {
                TaskPrepareService.getTaskPrepareService().moveToTaskProcess();
            }
        });
    }

    /**
     * 处理接收线程
     * @param runnable
     */
    public static void dispatchRec(Runnable runnable){
        prepareExcutor.execute(runnable);
    }

    /**
     * 持久化db数据处理线程
     * @param runnable
     */
    public static void dispatchPer(Runnable runnable){
        try {
            processExcutor.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
