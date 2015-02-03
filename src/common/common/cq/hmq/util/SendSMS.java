package common.cq.hmq.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SendSMS {

	public static void main(String[] args) throws IOException {
		//159893563  159888633
		//SendSMS.sendSMSGet("15123308745", "班级通知：我们班的数学作业是....");
		// SendSMS.sendSMSPost("15123308745", "教务处通知：请全校教师马上到教务处开会");
		SendSMS.reportGet();
	}

	/**
	 * 
	 * 发送短信
	 * 
	 * @param content
	 *            短信内容 70个字以内
	 * @param tel
	 *            电话号码以英文逗号分隔
	 * 
	 * @return 返回200成功 返回0 电话号码或内容为空
	 */
	public static int sendMsg(String content, String tel) {
		if (java.util.ResourceBundle.getBundle("config")
				.getString("sortMsg").equals("false")) {
			return -1;
		}
		if (tel.split(",").length > 50) {
			Integer.parseInt(sendSMSPost(tel, content));
		} else {
			Integer.parseInt(sendSMSGet(tel, content));
		}
		return 200;
	}

	/**
	 * get方式
	 * 
	 * @param Mobile
	 * @param Content
	 * @param send_time
	 * @return
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	public static String sendSMSGet(String Mobile, String Content) {
		URL url = null;
		String CorpID = "LKSDK0001369";
		String Pwd = "232380";
		String send_content;
		try {
			send_content = URLEncoder.encode(
					(Content + "【蓝图信产】").replaceAll("<br/>", " "), "GBK");
			url = new URL("http://mb345.com:999/ws/BatchSend2.aspx?CorpID="
					+ CorpID + "&Pwd=" + Pwd + "&Mobile=" + Mobile
					+ "&Content=" + send_content + "&Cell=&SendTime=");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader in = null;
		String line = "";
		String result = "";
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("异常");
			return "-1";
		}
		System.out.println(result);
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendSMSPost(String Mobile, String send_content) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		String CorpID = "LKSDK0001369";
		String Pwd = "232380";
		try {
			URL realUrl = new URL("http://mb345.com:999/ws/BatchSend2.aspx");
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print("CorpID="
					+ CorpID
					+ "&Pwd="
					+ Pwd
					+ "&Mobile="
					+ Mobile
					+ "&Content="
					+ URLEncoder.encode(
							(send_content + "【蓝图信产】").replaceAll("<br/>", " "),
							"GBK") + "&Cell=&SendTime=");
			out.flush();
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
			return "-1";
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(result);
		return result;
	}

	/**
	 * 
	 * 159653393$$$$$15123870585$$$$$2014-06-09
	 * 11:51:25$$$$$1$$$$$DELIVRD$$$$$2014-06-09 11:52:08|||
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	public static String reportGet() throws MalformedURLException,
			UnsupportedEncodingException {
		URL url = null;
		String CorpID = "LKSDK0001369";
		String Pwd = "232380";
		url = new URL("http://mb345.com:999/ws/GetReportSMS.aspx?CorpID="
				+ CorpID + "&Pwd=" + Pwd);
		BufferedReader in = null;
		String line = "";
		String result = "";
		System.out.println("开始");
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("异常");
			return "-1";
		}
		System.out.println(result);
		return result;
	}
}
