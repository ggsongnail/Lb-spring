package com.spoom.controller.order;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.spoom.entity.order.OrderArtificial;
import com.spoom.entity.order.OrderArtificialFeeFinal;
import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderProduct;
import com.spoom.entity.order.OrderProductFinal;
import com.spoom.entity.order.OrderRefuse;
import com.spoom.service.artificial.ArtificialFeeService;
import com.spoom.service.material.MaterialProductService;
import com.spoom.service.order.OrderArtificialFeeService;
import com.spoom.service.order.OrderLbService;
import com.spoom.service.order.OrderProductService;
import com.spoom.service.order.OrderRefuseService;
import com.spoom.util.excel.ExcelParams;
import com.spoom.util.excel.ExportUtil;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("orderlb")
public class OrderLbController {
	@Autowired
	private OrderLbService orderService;
	
	@Autowired
	private MaterialProductService materialProductService;
	
	@Autowired
	private ArtificialFeeService artificialFeeService;
	
	@Autowired
	private OrderProductService orderProductService;
	
	@Autowired
	private OrderArtificialFeeService orderArtificialFeeService;
	
	@Autowired
	private OrderRefuseService orderRefuseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "order";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public OrderLb getOrder(@PathVariable int id){
		OrderLb order = orderService.findById(id);
		return order;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public OrderLb saveOrder(@RequestBody OrderLb order){
		orderService.save(order);
		return order;
	}
	
	
	@ResponseBody
	@RequestMapping(value="materialsbill/save",method = RequestMethod.POST)
	@Transactional
	public void saveMaterialsBill(@RequestBody Map<String,String> map){
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			OrderLb order = mapper.readValue(map.get("orderLb"), OrderLb.class);
			CollectionType plistType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, OrderProduct.class);
			List<OrderProduct> orderProducts = (List<OrderProduct>) mapper.readValue(map.get("materials") , plistType);
			orderService.save(order);
			orderProductService.batchUpdate(orderProducts);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="artificialsbill/save",method = RequestMethod.POST)
	@Transactional
	public void saveArtificialsBill(@RequestBody Map<String,String> map){
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			OrderLb order = mapper.readValue(map.get("orderLb"), OrderLb.class);
			CollectionType plistType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, OrderProduct.class);
			List<OrderArtificial> orderArtificials = (List<OrderArtificial>) mapper.readValue(map.get("artificals") , plistType);
			orderService.save(order);
			orderArtificialFeeService.batchUpdate(orderArtificials);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="final/save",method = RequestMethod.POST)
	@Transactional
	public OrderLb saveFinalOrder(@RequestBody Map map){
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			OrderLb order = mapper.readValue(map.get("orderLb").toString(), OrderLb.class);
			
			CollectionType plistType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, OrderProductFinal.class);
			List<OrderProductFinal> pFinals = (List<OrderProductFinal>) mapper.readValue(map.get("confMaterial").toString(), plistType);
			
			CollectionType mlistType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, OrderArtificialFeeFinal.class);
			List<OrderArtificialFeeFinal> mFinals = (List<OrderArtificialFeeFinal>) mapper.readValue(map.get("confMan").toString(), mlistType);
			
			List<OrderProduct> opList = new ArrayList<OrderProduct>();
			for(OrderProductFinal op:pFinals){
				OrderProduct orderProduct  = orderProductService.findById(op.getoId());
				if(orderProduct!=null){
					orderProduct.setDifCount(op.getDifCount());
					orderProduct.setDifTotal(op.getDifTotal());
					//orderProductService.save(orderProduct);
					opList.add(orderProduct);
				}else{
					OrderProduct ghost = new OrderProduct();
					ghost.setOrderId(op.getOrderId());
					ghost.setProductId(op.getmId());
					ghost.setDifCount(op.getDifCount());
					ghost.setDifTotal(op.getDifTotal());
					//orderProductService.save(ghost);
					opList.add(ghost);
				}
			}
			
			List<OrderArtificial> oaList = new ArrayList<OrderArtificial>();
			for(OrderArtificialFeeFinal op:mFinals){
				OrderArtificial orderArtificialFee  = orderArtificialFeeService.findById(op.getoId());
				if(orderArtificialFee!=null){
					orderArtificialFee.setDifCount(op.getDifCount());
					orderArtificialFee.setDifTotal(op.getDifTotal());
					//orderArtificialFeeService.save(orderArtificialFee);
					oaList.add(orderArtificialFee);
				}else{
					OrderArtificial ghost = new OrderArtificial();
					ghost.setOrderId(op.getOrderId());
					ghost.setArtificialFeeId(op.getmId());
					ghost.setDifCount(op.getDifCount());
					ghost.setDifTotal(op.getDifTotal());
					//orderArtificialFeeService.save(ghost);
					oaList.add(ghost);
				}
			}
			
			
			
			orderService.save(order);
			orderProductService.batchUpdate(opList);
			orderArtificialFeeService.batchUpdate(oaList);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//orderService.save(order);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.POST)
	public List<OrderLb> listjson(@RequestBody Map<String,Object> params){
		Sort sort = new Sort(Sort.Direction.DESC,"signingDate");
		return orderService.findAll(getWhereClause(params),sort);
	}
	
	@ResponseBody
	@RequestMapping(value="validity/{field}/{parameter}/{id}",method = RequestMethod.GET)
	public Boolean getOrder(@PathVariable String field,@PathVariable String parameter,@PathVariable int id){
		OrderLb order = null;
		if("orderNo".equals(field))
			order = orderService.findByOrderNo(parameter);
		if("sysNo".equals(field))
			order = orderService.findBySysNo(parameter);
		if(order!=null&&order.getId()!=id)
			return false;
		return true;
	}
	
	private Specification<OrderLb> getWhereClause(final Map<String,Object> params){
		System.out.println(params);
		final SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		return new Specification<OrderLb>() {
			public Predicate toPredicate(Root<OrderLb> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				try {
					List<Predicate> predicates = new ArrayList<Predicate>();
					for (Map.Entry<String, Object> entry : params.entrySet()) {
						if(entry.getValue()!=null){
							String[] params = entry.getKey().split("_");
							String field = params[0];
							String type = params[1];
							if(field.toUpperCase().contains("DATE")){
								if(type.toUpperCase().equals("GTE")){
									//Date.class用 util的不要用sql的
									predicates.add(cb.greaterThanOrEqualTo(root.get(field).as(Date.class), sf.parse(entry.getValue().toString())));
								}
								if(type.toUpperCase().equals("LTE")){
									predicates.add(cb.lessThanOrEqualTo(root.get(field).as(Date.class), sf.parse(entry.getValue().toString())));
								}
							}else{
								predicates.add(cb.like(root.get(field).as(String.class), "%"+entry.getValue().toString()+"%"));
							}
						}
					}
					Predicate[] p = new Predicate[predicates.size()];  
			        return cb.and(predicates.toArray(p)); 
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return null; 
			}
		};
	}
	
	@ResponseBody
	@RequestMapping(value="finalorder/json/{orderId}",method = RequestMethod.GET)
	public Map finalorder(@PathVariable int orderId){
		List<OrderProductFinal> orderProductFinals = materialProductService.getOrderProductFinals(orderId);
		List<OrderArtificialFeeFinal> orderArtificialFeeFinal = artificialFeeService.getOrderArtificialFeeFinals(orderId);
		List<OrderProductFinal> orderProductFinalsHis = materialProductService.getOrderProductFinalsHis(orderId);
		List<OrderArtificialFeeFinal> orderArtificialFeeFinalHis = artificialFeeService.getOrderArtificialFeeFinalsHis(orderId);
		OrderLb orderLb = orderService.findById(orderId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("materials",orderProductFinals);
		map.put("mans", orderArtificialFeeFinal);
		map.put("materialsHis", orderProductFinalsHis);
		map.put("mansHis", orderArtificialFeeFinalHis);
		map.put("orderLb", orderLb);
		return map;
	}
	
	//excel导出
	@ResponseBody
	@RequestMapping(value="export/excel",method = RequestMethod.GET)
	public void exportExcel(@RequestParam(value="beginDate") Date beginDate,@RequestParam(value="endDate") Date endDate,HttpServletRequest request,HttpServletResponse response){
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			response.reset();// 清空输出流
			String fileName = "立邦刷新-订单明细";
			final String userAgent = request.getHeader("USER-AGENT");
			String finalFileName = null;
			if (StringUtils.contains(userAgent, "MSIE")) {// IE浏览器
				finalFileName = URLEncoder.encode(fileName, "UTF8");
			} else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
				finalFileName = new String(fileName.getBytes(), "ISO8859-1");
			} else {
				finalFileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
			}
			response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + ".xlsx");// 这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
			response.setContentType("application/vnd.ms-excel");

			// 创建一个workbook 对应一个excel应用文件
			XSSFWorkbook workBook = new XSSFWorkbook();
			writeOrderDetail(workBook, beginDate, endDate);
			writeMainProducts(workBook, beginDate, endDate);
			writeAssistProducts(workBook, beginDate,endDate);
			writeRefuse(workBook, beginDate,endDate);
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

    }
	
	
	public XSSFWorkbook writeOrderDetail(XSSFWorkbook workBook,Date beginDate,Date endDate){
		List<OrderLb> list = orderService.findForExcel(ExcelParams.getOrderSQL(ExcelParams.orderParams), beginDate,endDate);
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = workBook.createSheet("销售订单数据");
		if(list.size()==0)
			return workBook;
		ExportUtil exportUtil = new ExportUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		// 构建表头
		XSSFRow headRow = sheet.createRow(0);
		XSSFCell cell = null;
		for (int i = 0; i < ExcelParams.orderChinese.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(ExcelParams.orderChinese[i]);
		}
		// 构建表体数据
		//List<OrderLb> list = orderService.findForExcel(ExcelParams.getOrderSQL(ExcelParams.orderParams), "2017-03-02", "2017-04-02");
		for(int r=0;r<list.size();r++){
			XSSFRow bodyRow = sheet.createRow(r + 1);
			Map<String,Object> row = new HashMap<String,Object>();
	        row = (Map) list.get(r);  
			for(int c=0;c<ExcelParams.orderParams.length;c++){
				cell = bodyRow.createCell(c);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue((row.get(ExcelParams.orderParams[c])+"").equals("null")?"":row.get(ExcelParams.orderParams[c])+"");
			}
		}
		
		return workBook;
	}
	
	public XSSFWorkbook writeMainProducts(XSSFWorkbook workBook,Date beginDate,Date endDate){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		List<String> pList = materialProductService.getMainProductsCombo();
		List<Map> list = orderProductService.findForExcel(ExcelParams.getMaterialSQL(pList), beginDate, endDate);
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = workBook.createSheet("主要材料销售明细");
		if(list.size()==0)
			return workBook;
		ExportUtil exportUtil = new ExportUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		// 构建表头
		XSSFRow headRow = sheet.createRow(0);
		XSSFCell cell = null;
		cell = headRow.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("系统编号");
		cell = headRow.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("客户姓名");
		cell = headRow.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("合同编号");
		for (int i = 0; i < pList.size(); i++) {
			cell = headRow.createCell(i+3);
			cell.setCellStyle(headStyle);
			cell.setCellValue(pList.get(i));
		}
		// 构建表体数据
		for(int r=0;r<list.size();r++){
			XSSFRow bodyRow = sheet.createRow(r + 1);
			Map row = (Map) list.get(r);
			cell = bodyRow.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(row.get("sys_no")+"");
			cell = bodyRow.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue((row.get("customer")+"").equals("null")?"":row.get("customer")+"");
			cell = bodyRow.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue((row.get("order_no")+"").equals("null")?"":row.get("order_no")+"");
			for(int c=0;c<pList.size();c++){
				cell = bodyRow.createCell(c+3);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue((row.get(pList.get(c))+"").equals("null")?"":row.get(pList.get(c))+"");
			}
		}
		return workBook;
	}
	
	public XSSFWorkbook writeAssistProducts(XSSFWorkbook workBook,Date beginDate,Date endDate){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		List<String> pList = materialProductService.getAssistProductsCombo();
		List<Map> list = orderProductService.findForExcel(ExcelParams.getMaterialSQL(pList), beginDate,endDate);
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = workBook.createSheet("辅助材料销售明细");
		if(list.size()==0)
			return workBook;
		ExportUtil exportUtil = new ExportUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		// 构建表头
		XSSFRow headRow = sheet.createRow(0);
		XSSFCell cell = null;
		cell = headRow.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("系统编号");
		cell = headRow.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("客户姓名");
		cell = headRow.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("合同编号");
		for (int i = 0; i < pList.size(); i++) {
			cell = headRow.createCell(i+3);
			cell.setCellStyle(headStyle);
			cell.setCellValue(pList.get(i));
		}
		// 构建表体数据
		for(int r=0;r<list.size();r++){
			XSSFRow bodyRow = sheet.createRow(r + 1);
			Map row = (Map) list.get(r);
			cell = bodyRow.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(row.get("sys_no")+"");
			cell = bodyRow.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue((row.get("customer")+"").equals("null")?"":row.get("customer")+"");
			cell = bodyRow.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue((row.get("order_no")+"").equals("null")?"":row.get("order_no")+"");
			for(int c=0;c<pList.size();c++){
				cell = bodyRow.createCell(c+3);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue((row.get(pList.get(c))+"").equals("null")?"":row.get(pList.get(c))+"");
			}
		}
		return workBook;
	}
	
	public XSSFWorkbook writeRefuse(XSSFWorkbook workBook,Date beginDate,Date endDate){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		List<Map> list = orderProductService.findForExcel(ExcelParams.getRefuseSQL(), beginDate,endDate);
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = workBook.createSheet("拒绝订单情况");
		if(list.size()==0)
			return workBook;
		ExportUtil exportUtil = new ExportUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		
		// 构建表头
		XSSFRow headRow = sheet.createRow(0);
		XSSFCell cell = null;
		cell = headRow.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("系统编号");
		cell = headRow.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("客户姓名");
		cell = headRow.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("施工地址");
		cell = headRow.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("联系电话");
		cell = headRow.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("合同编号");
		int max = orderRefuseService.getMaxCount(beginDate, endDate);
		int i = 5;
		for(int r=1;r<=max;r++){
			cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue("第"+r+"次跟进销售专员");
			cell = headRow.createCell(i+1);
			cell.setCellStyle(headStyle);
			cell.setCellValue("第"+r+"次洽谈时间");
			cell = headRow.createCell(i+2);
			cell.setCellStyle(headStyle);
			cell.setCellValue("第"+r+"次洽谈方式");
			cell = headRow.createCell(i+3);
			cell.setCellStyle(headStyle);
			cell.setCellValue("第"+r+"次拒绝原因");
			cell = headRow.createCell(i+4);
			cell.setCellStyle(headStyle);
			cell.setCellValue("第"+r+"次拒绝解决方案");
			i = i+5;
		}
		String[] orderRefuses = ExcelParams.orderRefuses;
		//构建表体数据
		for(int c=0;c<list.size();c++){
			HashMap<String,Object> map = (HashMap<String, Object>) list.get(c);
			XSSFRow bodyRow = sheet.createRow(c+1);
			int s = 0;
			for(String str:orderRefuses){
				if(s==5){
					String[] strs1 = map.get(str)==null?new String[max]:map.get(str).toString().split(",");
					String[] strs2 = map.get(orderRefuses[s+1])==null?new String[max]:map.get(orderRefuses[s+1]).toString().split(",");
					String[] strs3 = map.get(orderRefuses[s+2])==null?new String[max]:map.get(orderRefuses[s+2]).toString().split(",");
					String[] strs4 = map.get(orderRefuses[s+3])==null?new String[max]:map.get(orderRefuses[s+3]).toString().split(",");
					String[] strs5 = map.get(orderRefuses[s+4])==null?new String[max]:map.get(orderRefuses[s+4]).toString().split(",");
						strs1 = strs1.length<max?Arrays.copyOf(strs1,max):strs1;
						strs2 = strs2.length<max?Arrays.copyOf(strs2,max):strs2;
						strs3 = strs3.length<max?Arrays.copyOf(strs3,max):strs3;
						strs4 = strs4.length<max?Arrays.copyOf(strs4,max):strs4;
						strs5 = strs5.length<max?Arrays.copyOf(strs5,max):strs5;
						
					for(int ss=0;ss<max;ss++){
						cell = bodyRow.createCell(s);
						cell.setCellStyle(bodyStyle);
						cell.setCellValue(strs1[ss]==null?"":strs1[ss]);
						
						cell = bodyRow.createCell(s+1);
						cell.setCellStyle(bodyStyle);
						cell.setCellValue(strs2[ss]==null?"":strs2[ss]);
						
						cell = bodyRow.createCell(s+2);
						cell.setCellStyle(bodyStyle);
						cell.setCellValue(strs3[ss]==null?"":strs3[ss]);
						
						cell = bodyRow.createCell(s+3);
						cell.setCellStyle(bodyStyle);
						cell.setCellValue(strs4[ss]==null?"":strs4[ss]);
						
						cell = bodyRow.createCell(s+4);
						cell.setCellStyle(bodyStyle);
						cell.setCellValue(strs5[ss]==null?"":strs5[ss]);
						
						s = s+5;
					}
					break;
				}else{
					cell = bodyRow.createCell(s);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(map.get(str)==null?"":map.get(str)+"");
					s++;
				}
			}
		}
		return workBook;
	}
}
