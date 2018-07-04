package db.ibatis.util;

/**
 * 时间戳生成类，能保证生成的时间戳是唯一的
 */
public class DBTimeStampGen {
	public static DBTimeStampGen timeStampGenerator = new DBTimeStampGen();
	private static long lastId = 0;
	/**
	 * @return long
	 * 生成的唯一时间戳 (微秒数的时间戳)
	 */
	public  synchronized long getTimeStamp(){
		long id = System.currentTimeMillis()*1000;
		if(id<=lastId){
			lastId++;
		}else{
			lastId=id;
		}
		return lastId;
	}
}
