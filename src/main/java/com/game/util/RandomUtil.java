package com.game.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import log.LogUtil;

public class RandomUtil {
	
	private static Random random = new Random();
	
	/**
	 * 从数组里面随机出指定的个数数据出来
	 * @param data
	 * @param count
	 * @return
	 */
	public static <T> List<T> getRandomTargetCount(List<T> data,int count){
		
		if(data == null || data.size() < count){
			return null;
		}
		
		List<T> temp = new ArrayList<T>(data);
		List<T> result = new ArrayList<T>(); 
		
		for(int i =0; i<count;i ++){
			int index = getRandom(temp.size());
			result.add(temp.remove(index));
		}
		return result;
	}

	/**
	 * 获得[0,n)之间的随机数
	 * @param n
	 * @return
	 */
	public static int getRandom(int n){
		return random.nextInt(n);
	}
	
	/**
	 * 获得[min,max]区间的随机数据
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomHasMax(int min,int max){
		return random.nextInt(max-min+1)+min;
	}
	
	/**
	 * 获得[min,max)区间的随机数据
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomNoMax(int min,int max){ 
		return (int)(random.nextFloat()*(max-min))+min;
	}
	
	/**
	 * 根据权重获得随机索引
	 * @param weights
	 * @return weights [] index, -1表示没有(权重总和为0则表示没有)
	 */
	public static int randomByWeights(int[] weights) {
		int length = weights.length;
		int totalWeight = 0;
		for (int i = 0; i < length; i++) {
			totalWeight += weights[i];
		}
		
		if (totalWeight <= 0)
			return -1;
		
		int hit = getRandomNoMax(1,totalWeight);

		totalWeight = 0;
		for (int i = 0; i < length; i++) {
			totalWeight += weights[i];
			if (totalWeight >= hit)
				return i;
		}

		return 0;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i <20; i++) {
			System.out.println(RandomUtil.getRandom(5));
		}
	}
	
	/**
	 * 根据权重获得随机索引
	 * @param weights
	 * @return key, -1表示没有(权重总和为0则表示没有)
	 */
	public static <T> T randomByWeights(Map<T,Integer> weights) {
		
		 if(weights == null){
			 LogUtil.error("weights is null");
			 return null;
		 }
	
		int totalWeight = 0;
		
		for (int weightValue : weights.values()) {
			totalWeight += weightValue;
		}
		
		if (totalWeight <= 0){
			LogUtil.error("totalWeight <0");
			return null;
		}
		
		
		int hit = getRandomHasMax(1,totalWeight);

		totalWeight = 0;
		
		for (Entry<T, Integer> entry : weights.entrySet()) {
			totalWeight += entry.getValue();
			if(totalWeight >= hit){
				return entry.getKey();
			}
		}
		
		return null; 
	}
}
