package db.batching;

//import com.pps.core.async.AsyncService;
//import com.pps.core.async.HeartBeator;
//import com.pps.core.async.IAsyncTask;
//import com.pps.core.async.IHeartBeat;

/**
 * 
 * 插入批处理的DAO方法类
 */
public class BatchDao  { //extends AsyncService implements IHeartBeat {

//	/**
//	 * 延时1分钟
//	 */
//	private final static int HEARTBEAT_DELAY = BatchConfig.DELAYED_TIME;
//	private long heartBeatTime = 0; // 降低心跳的实时性来提高性能
//
//	private final static String NO_CONFIG_EXCEPTION = "NO_CONFIG_EXCEPTION, at file of batchCreation.xml";
//
//	private final ExecutorService inner = Executors.newSingleThreadExecutor();
//
//	public BatchDao() {
//		HeartBeator.add(this);
//	}
//
//	private DataSource dataSource;
//
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
//
//	@SuppressWarnings("rawtypes")
//	private Map<Class, ArrayList<Object>> creationMap = new HashMap<Class, ArrayList<Object>>();
//
//	private Connection getConnection() throws SQLException {
//		return dataSource.getConnection();
//	}
//
//	/**
//	 * 放回连接池,<br>
//	 * 连接池已经重写了关闭连接的方法
//	 */
//	private static void close(Connection conn) {
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 持久化对象<br>
//	 * 
//	 * @param obj
//	 * @throws Exception
//	 */
//	public void create(final Object obj) {
//		this.accept(new IAsyncTask() {
//
//			@SuppressWarnings("rawtypes")
//			@Override
//			public void execute() throws Exception {
//				/*
//				 * 如果没有配置, 则抛出异常
//				 */
//				BatchBean batchBean = BatchConfig.getBatchBean(obj.getClass());
//				if (batchBean == null || !batchBean.getOperation().equals(BatchConfig.CREATION)) {
//					throw new Exception(NO_CONFIG_EXCEPTION + ", class: " + obj.getClass().getName());
//				}
//				/*
//				 * 加入需要持久化的的对象MAP
//				 */
//				Class clz = obj.getClass();
//				ArrayList<Object> objectList = creationMap.get(clz);
//				if (objectList == null) {
//					objectList = new ArrayList<Object>();
//					creationMap.put(clz, objectList);
//				}
//				/*
//				 * 如果不存在，就添加
//				 */
//				if (!objectList.contains(obj)) {
//					objectList.add(obj);
//				}
//
//			}
//		});
//
//	}
//
//	/**
//	 * 批处理
//	 * 
//	 * @throws Exception
//	 */
//	private void batch() throws Exception {
//
//		/*
//		 * 创建批处理
//		 */
//		batchCreate();
//
//		/*
//		 * FIXME 扩展点....
//		 */
//
//	}
//
//	@SuppressWarnings("rawtypes")
//	private void batchCreate() throws Exception {
//		if (creationMap.size() == 0)
//			return;
//		/*
//		 * 执行前的拷贝
//		 */
//		final Map<Class, ArrayList<Object>> tempCreationMap = new HashMap<Class, ArrayList<Object>>();
//		tempCreationMap.putAll(creationMap);
//		creationMap.clear();
//
//		/*
//		 * 切换内部线程处理
//		 */
//		inner.execute(new Runnable() {
//
//			@Override
//			public void run() {
//				try {
//					/*
//					 * 执行批处理
//					 */
//					executeBatch(tempCreationMap);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			}
//		});
//
//	}
//
//	/**
//	 * 批处理的执行<br>
//	 * 在内部线程里调用
//	 */
//	@SuppressWarnings("rawtypes")
//	private void executeBatch(final Map<Class, ArrayList<Object>> tempMap)
//			throws Exception {
//		for (Class clz : tempMap.keySet()) {
//			BatchBean batchBean = BatchConfig.getBatchBean(clz);
//
//			List<String> getters = batchBean.getGetterList();
//
//			String sql = batchBean.getSql();
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			try {
//				conn = getConnection();
//				conn.setAutoCommit(false);
//				pstmt = conn.prepareStatement(sql);
//				ArrayList<Object> objList = tempMap.get(clz);
//				for (Object obj : objList) {
//
//					int i = 1;
//					for (String getter : getters) {
//
//						Object value = obj.getClass().getDeclaredMethod(getter)
//								.invoke(obj);
//						pstmt.setObject(i++, value);
//					}
//
//					pstmt.addBatch();
//				}
//
//				pstmt.executeBatch();
//				conn.commit();
//			} catch (SQLException e) {
//				pstmt.clearBatch();
//				e.printStackTrace();
//			} finally {
//				pstmt.close();
//				close(conn);
//			}
//
//		}
//	}
//
//	/**
//	 * 内部机制, 永远不能调用此方法
//	 */
//	@Override
//	public void tick(long now) {
//		onHeartBeat(now);
//
//	}
//
//	/**
//	 * 定时处理
//	 */
//	private void onHeartBeat(final long now) {
//		this.accept(new IAsyncTask() {
//
//			@Override
//			public void execute() throws Exception {
//				if (now - heartBeatTime >= HEARTBEAT_DELAY) {
//					heartBeatTime = now;
//					/*
//					 * 定时批处理
//					 */
//					batch();
//				}
//			}
//		});
//
//	}
//
//	/**
//	 * <br>
//	 * 关机时, 或业务上要求立即执行时 调用<br>
//	 * 不必等待定时处理,调用后, DAO线程将立即处理<br>
//	 * 在没有了在线玩家的情况下,可以保证数据的完整性<br>
//	 * 设计上, 没有在shutDownHook的线程里运行, 如何保证执行完毕 ? <br>
//	 * 建议在shutDownHook的最后一步调用Thread.sleep(?) <br>
//	 * <br>
//	 */
//	public void doImmediately() {
//		this.accept(new IAsyncTask() {
//
//			@Override
//			public void execute() throws Exception {
//
//				batch();
//			}
//		});
//	}
//	
//	/**
//	 * 立即执行
//	 * @param obj
//	 */
//	public void doImmediately(final Object obj) {
//		inner.execute(new Runnable() {
//
//			@SuppressWarnings("rawtypes")
//			@Override
//			public void run() {
//				Class clz = obj.getClass();
//				BatchBean batchBean = BatchConfig.getBatchBean(clz);
//
//				if (batchBean==null)
//					return;
//				
//				List<String> getters = batchBean.getGetterList();
//
//				String sql = batchBean.getSql();
//				Connection conn = null;
//				PreparedStatement pstmt = null;
//				try {
//					conn = getConnection();
//					conn.setAutoCommit(false);
//					pstmt = conn.prepareStatement(sql);
//
//					int i = 1;
//					for (String getter : getters) {
//
//						Object value = obj.getClass().getDeclaredMethod(getter)
//								.invoke(obj);
//						pstmt.setObject(i++, value);
//					}
//
//					pstmt.execute();
//					conn.commit();
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					try {
//						pstmt.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//					close(conn);
//				}
//			}
//		});
//	}

}
