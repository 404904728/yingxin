package common.cq.hmq.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import common.cq.hmq.pojo.User;

public class ExportUtil {

	/**
	 * 可以自己写其他扩展
	 * 
	 * @param map
	 * @param web
	 * @param request
	 * @param response
	 */
	public static void export(Map<String, Object> map, HSSFWorkbook web) {
		HSSFSheet sheet = web.createSheet("用户");
		sheet.setDefaultColumnWidth(40);
		HSSFRow row = sheet.createRow(0);// 创建Excel的sheet的一行
		row.createCell(0).setCellValue("用户账号");
		row.createCell(1).setCellValue("用户姓名");
		int i = 1;
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) map.get("User");
		for (User user : users) {
			HSSFRow rowData = sheet.createRow(i);// 创建Excel的sheet的一行
			rowData.createCell(0).setCellValue(user.getNo());
			rowData.createCell(1).setCellValue(user.getName());
			i++;
		}
	}

}
