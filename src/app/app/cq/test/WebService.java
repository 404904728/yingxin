package app.cq.test;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;


public class WebService {
	public static void main(String[] args) throws AxisFault{
		RPCServiceClient client = new RPCServiceClient();
		Options options = client.getOptions();
		//设置调用WebService的URL
		String address = "http://localhost:9999/yx/services/ScoreService";
		EndpointReference epf = new EndpointReference(address);
		options.setTo(epf);
		//options.setTimeOutInMilliSeconds(600000L);
		//options.setManageSession(true);
		//options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, true);
		QName qname = new QName("http://score.interface.hmq", "getMessage");
		String a = "aa";
		int b = 2;
		Object[] arg = new Object[]{a,b};
		//指定调用的方法和传递参数数据，及设置返回值的类型
		Object[] result = client.invokeBlocking(qname, arg, new Class[]{ String.class });
		System.out.println(result[0]);
	}

}
