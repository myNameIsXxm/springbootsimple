package gr.xxm.controller;

import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.xxm.helper.WSServerServiceStub;
import gr.xxm.helper.WSServerServiceStub.MainRequest;
import gr.xxm.helper.WSServerServiceStub.MainResponse;
import javassist.tools.rmi.RemoteException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Configuration()
public class MainController {

	@Value("${yb.body}")
    private String body;
	@Value("${yb.type}")
	private String type;
	
	@RequestMapping("/")
	public String welcome(ModelMap map) {
		log.info("enter into index");
		return "index";
	}

	@RequestMapping("/yb")
	@ResponseBody
	public String yb(ModelMap map) throws AxisFault, java.rmi.RemoteException {

		MainRequest mainRequest = new MainRequest();
		mainRequest.setTradeType(type);
		mainRequest.setSender("330799");
		mainRequest.setBody(body);
		mainRequest.setExtra("");
		try {
			MainResponse response = new WSServerServiceStub().main(mainRequest);
			return "正常返回信息：" + response.getMainResponse();
		} catch (RemoteException e) {
			return "异常信息：" + e.getMessage();
		}
	}
}
