import com.game.config.ProperitesConfig;
import com.game.config.TemplatesManager;
import com.game.context.ServiceManger;
import com.game.context.SpringHandler;
import com.game.gen.PrimaryKeyGen;
import com.game.mapping.manage.GotoManager;
import com.game.nio.websocket.WebSocketServer;

import log.Log4jInit;
import log.MemoryManager;


public class StartMain {

	public static void main(String[] args) {

		//初始化log4j
		Log4jInit.initConfig();

		//config文件
		new ProperitesConfig("config/protertiesfile/loadproperties.xml");

		//spring配置加载
		SpringHandler.loadSpring();

		//加载指令跳转配置
		GotoManager.load();

		//加载自增数据
		PrimaryKeyGen.getPrimaryKeyGen().init();

		//xls配置文件数据
		new TemplatesManager();

		//初始化业务管理器
		ServiceManger.getServcieManger().init();

		//TCP通讯
//		TCPServer.start();
		
		//HTTP通讯
//		HttpServer.start();
		
		//web通讯
		WebSocketServer.start();
		
		//启动内存检测
		MemoryManager.getInstance().initialize();

		/**
		 *  ---------------------------- Test Case -----------------------------------------------
		 */
//		Test.testBatchInsert();

//		Test.testBatchUpdate();
	}
}