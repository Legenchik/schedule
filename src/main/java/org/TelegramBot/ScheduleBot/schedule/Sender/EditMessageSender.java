package org.TelegramBot.ScheduleBot.schedule.Sender;

import org.TelegramBot.ScheduleBot.schedule.TelegramBot.ScheduleBot;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EditMessageSender {
    final BotConfig config;
    public EditMessageSender(BotConfig config) {
        this.config = config;
        scheduleBot = new ScheduleBot(this.config);
    }
    ScheduleBot scheduleBot;



    public void editMessageSender(EditMessageText msg){
        try {
            scheduleBot.execute (msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
