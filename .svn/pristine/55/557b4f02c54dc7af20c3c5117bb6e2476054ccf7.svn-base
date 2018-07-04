package log;


/**
 * 游戏日志业务打印类
 */
public class LogPrintHandle {

	/**分隔字符**/
	public static final String JOIN_CHAR = "\t";
	/**数据null字符**/
	public static final String LINE_CHAR = "-";
	
//	//------------------角色上下线-------------------------
//	public static final int ROLE_IN_OR_OUT = 1;
//	
//	public static final int ROLE_IO_ACCOUNT = 1;//账号登陆
//	public static final int ROLE_IO_IN = 2;//角色登陆
//	public static final int ROLE_IO_OUT = 3;//角色登出
//	
//	//------------------创建角色-------------------------
//	public static final int CREATE_ROLE = 2;
//	
//	public static final int CREATE_ACCOUNT = 1;//账号创建
//	public static final int CREATE_USER = 2;//角色创建
//	
//	//------------------场景内怪物掉落-------------------------
//	public static final int STAGE_MONSTER_DROP = 3;
//	
//	//------------------放入背包物品记录-------------------------
//	public static final int CONTAINER_GET_GOODS= 4; 
//	
//	//------------------消耗背包物品记录---------------------
//	public static final int CONTAINER_REMOVE_GOODS= 5;
//	
//	//-----------------主线任务日志----------------------------
//	public static final int ZHUXIAN_TASK = 6;
//	
//	//-----------------聊天-----------------------------------------------------------
//	public static final int CHAT_LOG = 7;
//	
//	public static final int CHAT_PRIVATE = 1;//私聊
//	public static final int CHAT_WORLD = 2;//世界
//	public static final int CHAT_GUILD = 3;//公会
//	public static final int CHAT_LABA = 4;//喇叭
//	public static final int CHAT_TEAM = 5;//队伍
//	
//	//-----------------消耗数值（元宝，金币，礼券）日志----------------------------
//	public static final int CONSUME_NUM_LOG = 8; 
//	
//	public static final int CONSUME_BAG_OPEN_SLOT = 1; 
//	public static final int CONSUME_STORAGE_OPEN_SLOT = 2;
//	public static final int CONSUME_TRADE = 3;//交易
//	public static final int CONSUME_EQUIP_QH = 4;
//	public static final int CONSUME_LEARN_SKILL = 5;//学习技能
//	public static final int CONSUME_TASKDAY_FINISH = 6;//元宝完成日常任务
//	public static final int CONSUME_JINGJI = 7;//竞技场
//	public static final int CONSUME_ZUOQI_SJ = 8;//坐骑升阶
//	public static final int CONSUME_PAIMAI_GM = 9;//拍卖购买
//	public static final int CONSUME_GUILD = 10;//门派
//	public static final int CONSUME_OFFLINE_EXP = 11;//离线经验
//	public static final int CONSUME_RESOURCE_BACK = 12;//资源找回
//	public static final int CONSUME_OPEN_GIFT = 13;//礼包使用
//	public static final int CONSUME_MALL = 14;//商城消耗
//	public static final int CONSUME_LQDXBX = 15;//领取多选礼箱奖励 
//	public static final int CONSUME_FUBEN = 16;//副本
//	public static final int CONSUME_YABIAO = 17;//押镖
//	public static final int CONSUME_CHIBANG_SJ = 18;//坐骑升阶
//	
//	/**备注**/
//	public static final int CBZ_TRADE = 1;//交易
//	public static final int CBZ_EQUIP_QH = 2;
//	public static final int CBZ_BAG_OPEN_SLOT = 3; 
//	public static final int CBZ_STORAGE_OPEN_SLOT = 4; 
//	public static final int CBZ_LEARN_ZD_SKILL = 5;//学习主动技能
//	public static final int CBZ_TASKDAY_FINISH = 6;//元宝完成日常任务
//	public static final int CBZ_JINGJI_BUY_COUNT = 7;//竞技场买次数
//	public static final int CBZ_JINGJI_MIAO_CD = 8;//竞技场秒CD
//	public static final int CBZ_JINGJI_GUWU = 9;//竞技场鼓舞
//	public static final int CBZ_LEVEL_UP_ZD_SKILL = 10;//升级主动技能
//	public static final int CBZ_LEARN_BD_SKILL = 11;//学习被动技能
//	public static final int CBZ_LEVEL_UP_BD_SKILL = 12;//升级被动技能 
//	public static final int CBZ_ZUOQI_SJ = 13;//坐骑升阶
//	public static final int CBZ_PAIMAI_GM = 14;//拍卖购买
//	public static final int CBZ_GUILD_JUANXIAN = 15;//门派捐献
//	public static final int CBZ_OFFLINE_EXP = 16;//离线经验
//	public static final int CBZ_RESOURCE_BACK = 17;//资源找回
//	public static final int CBZ_OPEN_GIFT = 18;//礼包使用
//	public static final int CBZ_MALL = 19;//商城消耗
//	public static final int CBZ_LQDXBX = 20;//领取多选礼箱奖励
//	public static final int CBZ_FUBEN_SHOUHU = 21;//守护副本重置
//	public static final int CBZ_YABIAO = 23;//押镖
//	public static final int CBZ_FUBEN_SHOUHU_SD = 22;//守护副本扫荡
//	public static final int CBZ_CHIBANG_SJ = 23;//坐骑升阶
//	 
//	
//	//-----------------获得数值（元宝，金币，礼券）日志----------------------------
//	public static final int GET_NUM_LOG = 9;
//		
//	public static final int GET_TRADE = 1;//交易获得
//	public static final int GET_USE_PROP = 2; 
//	public static final int GET_GOODS_PICKUP = 3;
//	public static final int GET_FUBEN = 4; 
//	public static final int GET_EMAIL = 5; //邮件获得
//	public static final int GET_TASKDAY_FINISH = 6;//每日任务完成获得的奖励
//	public static final int GET_DAZUO = 7;//打坐获得
//	public static final int GET_TASK = 8; //主线任务
//	public static final int GET_JINGJI = 9; //竞技场
//	public static final int GET_SELL_GOODS = 10; //物品出售
//	public static final int GET_GIFT_CARD = 11;//礼包卡
//	public static final int GET_OFFLINE_EXP = 12;//离线经验
//	public static final int GET_RESOURCE_BACK = 13;//资源找回
//	public static final int GET_OPEN_GIFT = 14;//打开礼包卡
//	public static final int GET_SEVEN_LOGIN_GIFT = 15;//领取七日登陆奖励
//	public static final int GET_DATI_AWARD=17;//直接领取答题奖励
//	
//	
//	/**备注**/
//	public static final int GBZ_TRADE = 1;//交易获得
//	public static final int GBZ_USE_PROP = 2; 
//	public static final int GBZ_GOODS_PICKUP = 3;
//	public static final int GBZ_FUBEN = 4; 
//	public static final int GBZ_EMAIL = 5; //邮件获得
//	public static final int GBZ_TASKDAY_FINISH = 6;//每日任务完成获得的奖励
//	public static final int GBZ_DAZUO = 7;//打坐获得
//	public static final int GBZ_TASK = 8; //主线任务
//	public static final int GBZ_JINGJI_RANK = 9; //竞技场排名奖励
//	public static final int GBZ_SELL_GOODS = 10; //物品出售
//	public static final int GBZ_GIFT_CARD = 11;//礼包卡
//	public static final int GBZ_OFFLINE_EXP = 12;//离线经验
//	public static final int GBZ_RESOURCE_BACK = 13;//资源找回
//	public static final int GBZ_OPEN_GIFT = 14;//打开礼包卡
//	public static final int GBZ_SEVEN_LOGIN_GIFT = 15;//领取七日登陆奖励
//	public static final int GBZ_SHOUHU_FUBEN = 16;//守护副本奖励
//	public static final int GBZ_DATI_AWARD=17;//直接领取答题奖励
//	
//	//----------------技能--------------------------------------------------------
//	public static final int SKILL = 10;
//	
//	public static final int SKILL_TYPE_ZD_LEARN = 1;//主动学习
//	public static final int SKILL_TYPE_ZD_LEVEL_UP = 2;//主动升级
//	public static final int SKILL_TYPE_BD_LEARN = 3;//被动学习
//	public static final int SKILL_TYPE_BD_LEVEL_UP = 4;//被动升级
//	
//	//----------------竞技--------------------------------------------------------
//	public static final int JINGJI_GIFT = 11;//竞技排名奖励
//	public static final int JINGJI_DUIHUAN = 12;//竞技物品兑换
//	
//	//----------------交易--------------------------------------------------------
//	public static final int TRADE = 13;
//	
//	//----------------装备回收--------------------------------------------------------
//	public static final int EQUIP_RECYCLE = 14;
//	
//	//----------------装备强化--------------------------------------------------------
//	public static final int EQUIP_QH = 15;
//	
//	//----------------副本--------------------------------------------------------
//	public static final int FUBEN = 16;
//	
//	public static final int FUBEN_TYPE_TIAOZHAN = 1;//挑战
//	public static final int FUBEN_TYPE_SAODANG = 2;//扫荡
//	
//	//----------------坐骑升阶--------------------------------------------------------
//	public static final int ZUOQI_SJ = 17;
//	
	//----------------签到--------------------------------------------------------
	public static final int ASSIGN = 18;
//	
//	public static final int ASSIGN_RETROACTIVE = 1;//一键补签
//	public static final int ASSIGN_IN = 2;//签到
//	
//	//----------------签到领奖--------------------------------------------------------
//	public static final int ASSIGN_REWARD = 19;
//
//	//----------------在线奖励--------------------------------------------------------
//	public static final int ONLINE_REWARD = 20;
//	
//	//----------------礼包卡兑换--------------------------------------------------------
//	public static final int GIFT_CARD = 21;	
//	
//	//----------------七日奖励--------------------------------------------------------
//	public static final int SEVEN_DAY_REWARD = 22;	
//	
//	//----------------离线经验--------------------------------------------------------
//	public static final int OFFLINE_EXP = 23;	
//	
//	//----------------资源找回--------------------------------------------------------
//	public static final int RESOURCE_BACK = 24;
//	
//	//----------------用户登陆gate信息---------------------------
//	public static final int LOGIN_GATE_INFO = 25;
//	
//	//----------------首次充值日志---------------------------
//	public static final int PLAYER_FIRST_RECHARE = 26;
//
//
//	
//	//-----------------在线人数---------------------------------
//	public static final int ONLINE_COUNT = 28;
//	
//	//-----------------在线时长---------------------------------
//	public static final int ONLINE_TIME = 29;
//
//
//	
//	//----------------翅膀升阶--------------------------------------------------------
//	public static final int CHIBANG_SJ = 27;
	
	/**
	 * 打印日志
	 * @param bigType 日志大类类型
	 * @param message 主体数据
	 */
	public static void printLog(int bigType,String message){
		StringBuffer buff = new StringBuffer();
		buff.append(bigType).append(JOIN_CHAR).append(message);
		
		LogUtil._1MinuteLog(buff.toString());
	}
	
//	/**
//	 * 把物品map转成所日志所需的json格式
//	 * @param goods
//	 * @param result	json结果对象，可以为null
//	 * @return
//	 */
//	public static JSONArray getLogGoodsParam(Map<String,Integer> goods,JSONArray result){
//		if(result == null){
//			result = new JSONArray();
//		}
//		if(goods != null && goods.size() > 0){
//			for (Entry<String,Integer> entry : goods.entrySet()) {
//				Map<String,Object> goodsData = new HashMap<String, Object>();
//				goodsData.put("goodsId", entry.getKey());
//				goodsData.put("count", entry.getValue());
//				result.add(goodsData);
//			}
//		}
//		return result;
//	}
}
