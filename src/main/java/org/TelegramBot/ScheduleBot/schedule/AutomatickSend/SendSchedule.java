package org.TelegramBot.ScheduleBot.schedule.AutomatickSend;

import org.TelegramBot.ScheduleBot.schedule.DateBase.Group;
import org.TelegramBot.ScheduleBot.schedule.DateBase.GroupRepository;
import org.TelegramBot.ScheduleBot.schedule.ScheduleGroupFBK.OutScheduleThisDayUrlFBK;
import org.TelegramBot.ScheduleBot.schedule.Sender.MessageSender;
import org.TelegramBot.ScheduleBot.schedule.TelegramBot.ScheduleBot;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendSchedule {/*
    @Autowired
    GroupRepository groupRepository;
    long chatId = 941240371;
    final BotConfig config;

    public SendSchedule(BotConfig config) {
        this.config = config;
    }

    @Scheduled(cron = "20 * * 6 * *")
    public void send(){
        OutScheduleThisDayUrlFBK thisday = new OutScheduleThisDayUrlFBK(config);
        ScheduleBot scheduleBot = new ScheduleBot(config);
        MessageSender sendM = new MessageSender(config);
        XSSFWorkbook wb = scheduleBot.getWbFBK();
        System.out.println(wb.getNumberOfSheets());
        var group = groupRepository.findAll();
        for(Group g:group){
            if(chatId==g.getChatId()){
                scheduleBot.SendSchedule(chatId,g.getFaculty(),g.getNumberGroup(),"dayurl");
                sendM.sendMessage(chatId,"jfhsdfdsfs");
            }
        }

    }*/


}
