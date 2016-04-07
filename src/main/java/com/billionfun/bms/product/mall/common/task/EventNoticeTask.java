package com.billionfun.bms.product.mall.common.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.billionfun.bms.product.mall.common.ConfigInfo;
import com.billionfun.bms.product.mall.common.utils.EmailUtil;
import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.service.BusEventService;
import com.billionfun.bms.product.mall.vo.BusEventVO;

@Component
public class EventNoticeTask {
	@Autowired
	private BusEventService eventService;
	@Autowired
	private ConfigInfo configInfo;
	
	/**
	 * 
	 * @Title: notice 
	 * @Description: TODO 每30分钟，查询出已经到提醒时间的事件，并发邮件提醒
	 * @param  
	 * @return void
	 * @throws
	 */
	@Scheduled(cron = "0 0/1 * * * ?")  
    public void notice() {  
        List<BusEventVO> listVos = eventService.getRemindList();
        Map<String, List<BusEventVO>> map = new HashMap<String, List<BusEventVO>>();
        String userId = "";
        List<BusEventVO> listRef = null;
        for (int i = 0; i < listVos.size(); i++) {
        	BusEventVO vo = listVos.get(i);
        	if(!vo.getUserId().equals(userId)){
        		if(i!=0){
        			map.put(userId, listRef);
        		}
        		listRef = new ArrayList<BusEventVO>();
        		userId = vo.getUserId();
        	}
        	listRef.add(vo);
        	eventService.updateNoticeCount(vo.getId());
		}
        if(!StringUtil.empty(listRef)){
        	map.put(userId, listRef);
        }
        
        for(Map.Entry<String, List<BusEventVO>> entry :map.entrySet()){
        	List<BusEventVO> list = entry.getValue();
        	Map model = new HashMap();
        	model.put("list", list);
        	EmailUtil.sendEmail(model, "事件提醒", configInfo.getVelocityEventRemind(),
    				new String[] { list.get(0).getEmail() }, new String[] {});
        }
    } 
}
