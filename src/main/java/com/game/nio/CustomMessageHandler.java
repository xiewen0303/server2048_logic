package com.game.nio;



public class CustomMessageHandler {
	 
//	@Override
//	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
//			throws Exception {
//		try {
//			ByteBuffer buffer = (ByteBuffer) e.getMessage();
//			int len = buffer.capacity(); 
//			int tId=buffer.getInt();
//			int msgCode = buffer.getInt();
//			System.err.println("msgCode="+msgCode+"==:"+ (new Date()));
//			byte[] dst = new byte[len-8];
//			buffer.get(dst);			
//			final MsgGeneral msgGeneral = MessageCode.getMsg(tId,msgCode, dst); 
//			if(msgGeneral==null){
////				Player playerTemp=new Player();
////				playerTemp.setCtx(ctx);
////				playerTemp.sendMessage("msgCode="+msgCode+"不是游戏服务器的协议！");
//				System.out.println("msgCode="+msgCode+"不是游戏服务器的协议！");
//				return;
//			}
//			if(ctx.getAttachment() != null) {
//				final Player player = (Player) ctx.getAttachment();
//				System.out.println("接受数据uid="+(player.getUser()!=null?player.getUser().getUid():0));
//				System.out.println(msgGeneral.toString());
//				long time1= System.currentTimeMillis();
// 
//				msgGeneral.handler(player);
//				
//				long time2= System.currentTimeMillis();
//				logger.info(msgGeneral.getClass().getSimpleName()+"处理时间"+(time2-time1)+"毫秒");
//			}else {
//				Player p = new Player();
//				p.setCtx(ctx);
//				ctx.setAttachment(p);// 绑定player
//				msgGeneral.handler(p);
//			} 
//			System.out.println(msgGeneral.toString());
//		 
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		super.messageReceived(ctx, e);
//	}
// 
}
