package com.game.context;

import com.game.core.IService;
import com.game.mapping.manage.MappingContainer;
import com.game.service.bag.service.BagService;
import com.game.service.hero.service.HeroService;
import com.game.service.login.service.LoginService;
import com.game.service.map.service.CopyService;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务逻辑管理器
 * Created by xiewen on 2016/11/12.
 */
public class ServiceManger {
    /**
     * 业务逻辑管理器
     */
    private List<IService> offServices = new ArrayList<>();
    private List<IService> loginServices = new ArrayList<>();

    private static ServiceManger serviceManger = new ServiceManger();

    public static ServiceManger getServcieManger() {
        return serviceManger;
    }

    public void init(){
        //离线处理的顺序
    	offServices.add(MappingContainer.getWorker(CopyService.class));
        offServices.add(MappingContainer.getWorker(BagService.class));
        
        //这两个尽量放在最后
        offServices.add(MappingContainer.getWorker(LoginService.class));
        offServices.add(MappingContainer.getWorker(HeroService.class)); 
        

        //登陆处理的顺序
        loginServices.add(MappingContainer.getWorker(LoginService.class));
        loginServices.add(MappingContainer.getWorker(HeroService.class));
        loginServices.add(MappingContainer.getWorker(BagService.class));
        loginServices.add(MappingContainer.getWorker(CopyService.class));
    }

    public void login(long uid) {
        for (IService service : loginServices) {
            service.login(uid);
        }
    }
    
    public void load(long uid) {
        for (IService service : loginServices) {
            service.load(uid);
        }
    }
    

    public void offline(long uid){
        for (IService service : offServices) {
            service.offline(uid);
        }
    }
    
    public static <T extends IService> T getService(Class<T> clazz){
		return MappingContainer.getWorker(clazz);
	}
}
