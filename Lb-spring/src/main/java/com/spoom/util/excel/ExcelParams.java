package com.spoom.util.excel;

import java.util.List;

public class ExcelParams {
	public static String[] orderChinese = {"系统编号","客户姓名","联系电话","施工地址","小区名称","订单状态","合同编号","预收定金","跟进销售专员","是否选择套餐服务","乳胶漆内墙金额合计","艺术漆金额小计","木器漆及水性专业漆金额合计","基辅材金额合计","人工费金额合计","管理费及税金","订单应收合计","折后实收金额","施工组长","预计施工人数","预计施工日期","预计施工天数","实际开工日期","实际完工日期","已收款金额","备注"};
	public static String[] orderParams = {"sys_no","customer","tel","build_address","uptown_name","status","order_no","deposit","customer_commissioner","sets","latex_price_real","art_price_real","wood_water_price_real","assist_price_real","artificial_price_real","budget","gross","gross_final","build_captain","build_people","expected_date","build_days","begin_build_date","done_build_date","part_money","remark"};
	public static String[] orderRefuses = {"sys_no","customer","build_address","tel","order_no","people","talk_time","talk_way","reason","plan"};
	
	public static String getOrderSQL(String[] params){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from order_lb where signing_date > ?1 and signing_date < ?2");//signing_date > ?1 and signing_date < ?2
		return sb.toString();
	}
	
	public static String getMaterialSQL(List<String> list){
		StringBuffer sb = new StringBuffer();
		sb.append("select rd.order_id,rd.sys_no,rd.customer, ");
		for(int i=0;i<list.size();i++){
			if(i==list.size()-1){
				sb.append("MAX(CASE WHEN rd.combo= '"+list.get(i)+"' THEN rd.count END) AS '"+list.get(i)+"' ");
			}else{
				sb.append("MAX(CASE WHEN rd.combo= '"+list.get(i)+"' THEN rd.count END) AS '"+list.get(i)+"', ");
			}
		}
		sb.append("from (select op.order_id,ol.sys_no,ol.customer, concat_ws('/', op.product_name, op.standard, op.price) as combo, op.count from order_product op ");
		sb.append("left join order_lb ol on ol.id = op.order_id  where ol.signing_date > ?1 and ol.signing_date < ?2) rd ");//ol.signing_date > ?1 and ol.signing_date < ?2
	    sb.append("GROUP BY rd.order_id,rd.sys_no,rd.customer"); 
		return sb.toString();
	}
	
	public static String getRefuseSQL(){
		String sql = "SELECT rd.sys_no, "+
	       "rd.customer, "+
	       "rd.build_address, "+
	       "rd.tel, "+
	       "rd.order_no, "+
	       "GROUP_CONCAT(rd.people ORDER BY rd.talk_time DESC) people, "+
	       "GROUP_CONCAT(talk_time ORDER BY rd.talk_time DESC) talk_time, "+
	       "GROUP_CONCAT(talk_way ORDER BY rd.talk_time DESC) talk_way, "+
	       "GROUP_CONCAT(reason ORDER BY rd.talk_time DESC) reason, "+
	       "GROUP_CONCAT(plan ORDER BY rd.talk_time DESC) plan "+
	  "FROM( "+
	"SELECT ol.sys_no, ol.customer, ol.build_address, ol.tel, ol.order_no, os.people, os.talk_time, os.talk_way, os.reason, os.plan "+
	  "FROM order_refuse os "+
	  "LEFT JOIN order_lb ol on os.order_id= ol.id "+
	 "where ol.signing_date> ?1 "+
	   "and signing_date< ?2) rd "+
	 "GROUP BY rd.sys_no, "+
	         "rd.customer, "+
	         "rd.build_address, "+
	         "rd.tel, "+
	         "rd.order_no";
		return sql;
	}
}
