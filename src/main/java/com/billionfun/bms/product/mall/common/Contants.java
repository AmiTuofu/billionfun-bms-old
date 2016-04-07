package com.billionfun.bms.product.mall.common;

/**
 * 
 * @ClassName: Contants
 * @Description: TODO 常量类
 * @author AmiTuofu
 * @date 2016年3月15日 下午10:31:48
 * 
 */
public class Contants {

	public static final String SESSION_USER = "SESSION_USER";

	public static final String REMIND_LIST_SQL = "SELECT NAME,DESCRIPTION,START_DATE,END_DATE,REMIND,REMIND_WAY,PLACE,STATUS,USER_ID,ID"
			+ " FROM BUS_EVENT E WHERE DATE_SUB(START_DATE, INTERVAL REMIND HOUR) < NOW() AND NOW() <=START_DATE AND  REMIND >0 AND NOTICE_COUNT=0 AND STATUS = 1 "
			+ " ORDER BY USER_ID DESC";

	public static String getFinishedEvent(String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CATEGORY_ID,NAME, (SELECT COUNT(ID) FROM BUS_EVENT WHERE CATEGORY_ID = TEMP.CATEGORY_ID AND STATUS = 2) AS FINISHED, SUM(COUNT) AS SUM  ");
		sql.append(
				"FROM (SELECT E.CATEGORY_ID ,dd.name as name,E.STATUS,COUNT(*) AS COUNT FROM BUS_EVENT E,bus_data_dictionary dd WHERE dd.id = e.category_id and dd.type_id = 1 and E.USER_ID = '")
				.append(userId);
		sql.append("' GROUP BY E.CATEGORY_ID,E.STATUS) TEMP ");
		sql.append("  GROUP BY TEMP.CATEGORY_ID ");
		return sql.toString();
	}
}
