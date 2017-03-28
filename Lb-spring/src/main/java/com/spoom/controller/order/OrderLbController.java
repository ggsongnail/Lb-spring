package com.spoom.controller.order;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.order.OrderArtificial;
import com.spoom.entity.order.OrderArtificialFeeFinal;
import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderProduct;
import com.spoom.entity.order.OrderProductFinal;
import com.spoom.service.artificial.ArtificialFeeService;
import com.spoom.service.material.MaterialProductService;
import com.spoom.service.order.OrderArtificialFeeService;
import com.spoom.service.order.OrderLbService;
import com.spoom.service.order.OrderProductService;
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
		/*if(StringUtils.isBlank(order.getOrderNo())){
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = sf.format(new Date());
			order.setOrderNo(time);
		}*/
		orderService.save(order);
		return order;
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
		final SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
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
	
	@ResponseBody
	@RequestMapping(value="final/material/save",method = RequestMethod.POST)
	public OrderLb saveFinalMaterial(@RequestBody List<OrderProductFinal> ops){
		List<OrderProduct> opList = new ArrayList<OrderProduct>();
		for(OrderProductFinal op:ops){
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
		orderProductService.batchUpdate(opList);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="final/man/save",method = RequestMethod.POST)
	public OrderLb saveFinalMan(@RequestBody List<OrderArtificialFeeFinal> ops){
		List<OrderArtificial> oaList = new ArrayList<OrderArtificial>();
		for(OrderArtificialFeeFinal op:ops){
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
		orderArtificialFeeService.batchUpdate(oaList);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="export/excel",method = RequestMethod.GET)
	public void exportExcel(@RequestParam(value="beginDate") String beginDate,@RequestParam(value="endDate") String endDate,
			HttpServletResponse response){
		ServletOutputStream outputStream = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			outputStream = response.getOutputStream();  
	        String fileName = new String(("立邦刷新－销售订单").getBytes(), "ISO8859_1");  
	        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式 
			List<OrderLb> list = orderService.findForExcel(ExcelParams.getOrderSQL(ExcelParams.orderParams), sf.parse(beginDate), sf.parse(endDate));
			// 创建一个workbook 对应一个excel应用文件
			XSSFWorkbook workBook = new XSSFWorkbook();
			// 在workbook中添加一个sheet,对应Excel文件中的sheet
			XSSFSheet sheet = workBook.createSheet("销售订单数据");
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
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

    }  
	
	@ResponseBody
	@RequestMapping(value="export/material/excel",method = RequestMethod.GET)
	public void exportExcel2(@RequestParam(value="beginDate") String beginDate,@RequestParam(value="endDate") String endDate,
			HttpServletResponse response){
		ServletOutputStream outputStream = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			outputStream = response.getOutputStream();  
	        String fileName = new String(("立邦刷新－主要材料销售明细").getBytes(), "ISO8859_1");  
	        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式 
	        List<String> pList = materialProductService.getMaterialProducts();
			List<List<String>> list = orderProductService.findForExcel(ExcelParams.getMaterialSQL(pList), sf.parse(beginDate), sf.parse(endDate));
			// 创建一个workbook 对应一个excel应用文件
			XSSFWorkbook workBook = new XSSFWorkbook();
			// 在workbook中添加一个sheet,对应Excel文件中的sheet
			XSSFSheet sheet = workBook.createSheet("主要材料销售明细");
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
			//List<OrderLb> list = orderService.findForExcel(ExcelParams.getOrderSQL(ExcelParams.orderParams), "2017-03-02", "2017-04-02");
			//System.out.println(list);
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
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

    }
}
