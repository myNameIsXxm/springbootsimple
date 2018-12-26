package gr.xxm.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import gr.xxm.entity.YdzfDz;
import gr.xxm.helper.WSServerServiceStub;
import gr.xxm.helper.WSServerServiceStub.MainRequest;
import gr.xxm.helper.WSServerServiceStub.MainResponse;
import gr.xxm.repository.YdzfDzRepository;
import javassist.tools.rmi.RemoteException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class YbTask {

	@Autowired
	private YdzfDzRepository ydzfDzRepository;

	@Scheduled(cron = "0 0 0 * * ?")
	public void task2() {
		log.info("每天0点执行一次。开始……");
		Iterable<YdzfDz> list = ydzfDzRepository.findAll();
		List<YdzfDz> ydzfDzs = new ArrayList<YdzfDz>();
		for (YdzfDz bean : list) {
			ydzfDzs.add(bean);
		}
		Collections.sort(ydzfDzs);
		Date ekd001 = null;
		int total = 0;
		int tiaoshu = 0;
		for (YdzfDz bean : ydzfDzs) {
			if (bean.getRETCODE() == null || bean.getRETCODE().equals("")) {
				if (ekd001 == null || bean.getEKD001().equals(ekd001)) {
					ekd001 = bean.getEKD001();
					total += bean.getTOTAL();
					tiaoshu += bean.getTIAOSHU();
				} else {
					ekd001 = bean.getEKD001();
					total = 0;
					tiaoshu = 0;
					MainRequest mainRequest = new MainRequest();
					mainRequest.setTradeType("8107");
					mainRequest.setSender("330799");
					mainRequest.setBody(total + "~" + tiaoshu);
					mainRequest.setExtra("");
					try {
						new WSServerServiceStub().main(mainRequest);
					} catch (java.rmi.RemoteException e) {
						log.error(e.getMessage());
					}
				}
				MainRequest mainRequest = new MainRequest();
				mainRequest.setTradeType("8104");
				mainRequest.setSender("330799");
				mainRequest.setBody(new SimpleDateFormat("yyy-MM-dd").format(
						bean.getEKD001() + "~" + bean.getTOTAL() + "~" + bean.getTIAOSHU() + "~" + bean.getBODY()));
				mainRequest.setExtra("");
				try {
					MainResponse response = null;
					try {
						response = new WSServerServiceStub().main(mainRequest);
					} catch (java.rmi.RemoteException e) {
						bean.setRETCODE("-1");
						bean.setRETMSG(e.getMessage());
					}
					bean.setRETCODE("0");
					bean.setRETMSG(response.getMainResponse());
				} catch (RemoteException e) {
					bean.setRETCODE("-1");
					bean.setRETMSG(e.getMessage());
				}
			}
		}
		log.info("每天0点执行一次。结束。");
	}

}
