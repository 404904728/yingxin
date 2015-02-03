package common.cq.hmq.util;

/**
 * 用于返回json数据的封装
 * 
 * @author cqmonster
 * 
 */
public class Json {

	// 0：成功 1：信息 2：警告 3：错误 默认为成功
	public static int SUCCESS = 0;
	public static int ERROR = 1;

	/**
	 * 返回json字符串的封装
	 * 
	 * @return
	 */
	public static String write(int type, String msg) {
		return write(type, msg, null, null);
	}

	public static String write(int type, String msg, Long id, String msgId) {
		return "{\"type\":" + type + ",\"msg\":\"" + msg + "\",\"id\":" + id
				+ ",\"msgId\":" + msgId + "}";
	}

}
