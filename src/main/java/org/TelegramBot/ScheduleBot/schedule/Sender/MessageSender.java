package org.TelegramBot.ScheduleBot.schedule.Sender;

import org.TelegramBot.ScheduleBot.schedule.TelegramBot.ScheduleBot;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageSender {
    final BotConfig config;
    public MessageSender(BotConfig config) {
        this.config = config;
        scheduleBot = new ScheduleBot(this.config);
    }
    ScheduleBot scheduleBot;



    public void sendMessage(long chatId,String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(chatId);
        try {
            scheduleBot.execute (sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
