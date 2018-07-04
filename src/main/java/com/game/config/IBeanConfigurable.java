package com.game.config;

import java.util.Map;

public interface IBeanConfigurable {
//	/**
//	 * 如果XML表里没有配置templateId,或找不到替代的属性,则将rowNumber当成templateId
//	 * @param rowNumber
//	 * @param row
//	 */
//	public void parse(int rowNumber, Cell[] row);
	
	/**
	 * 如果XML表里没有配置templateId,或找不到替代的属性,则将rowNumber当成templateId
	 * @param rowNumber
	 * @param row
	 */
	public void parse(Map<String,String> templateData);
	
	/**
	 * 模板ID号
	 * @return
	 */
	public Object getTemplateId();

}
