package org.TelegramBot.ScheduleBot.schedule.Sender;

import org.TelegramBot.ScheduleBot.schedule.TelegramBot.ScheduleBot;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
public class ExecuteMessageSender {
    final BotConfig config;
    public ExecuteMessageSender(BotConfig config) {
        this.config = config;
        scheduleBot = new ScheduleBot(this.config);
    }
    ScheduleBot scheduleBot;



    public void ExecuteSendMessage(SendMessage sendMessage){
        try {
            scheduleBot.execute (sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
