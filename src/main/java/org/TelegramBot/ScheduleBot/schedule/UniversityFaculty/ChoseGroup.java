package org.TelegramBot.ScheduleBot.schedule.UniversityFaculty;

import org.TelegramBot.ScheduleBot.schedule.Sender.EditMessageSender;
import org.TelegramBot.ScheduleBot.schedule.Sender.ExecuteMessageSender;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ChoseGroup {

    final BotConfig config;


    public ChoseGroup(BotConfig config) {
        this.config = config;
        executeMessageSender= new ExecuteMessageSender(this.config);
        editMessageSender = new EditMessageSender(this.config);
    }
    EditMessageSender editMessageSender;
    ExecuteMessageSender executeMessageSender;

    public void DaytimeFormOrExtramural(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Виберіть");
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var dayTime = new InlineKeyboardButton();
        var extramural = new InlineKeyboardButton();
        dayTime.setText("Денна форма");
        dayTime.setCallbackData("DAYTIME");
        extramural.setText("Заочна форма");
        extramural.setCallbackData("EXTRAMURAL");
        rowInLine.add(dayTime);
        rowInLine.add(extramural);
        rowsInLine.add(rowInLine);
        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);
        executeMessageSender.ExecuteSendMessage(message);
    }
    public void Faculty(long chatId,long messageId){
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(String.valueOf(chatId));
        editMessage.setText("Виберіть факультет");
        editMessage.setMessageId((int) messageId);
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var fem = new InlineKeyboardButton();

        fem.setText("ФЕМ");
        fem.setCallbackData("FEM");

        var fist = new InlineKeyboardButton();

        fist.setText("ФІСТ");
        fist.setCallbackData("FIST");

        rowInLine.add(fem);
        rowInLine.add(fist);

        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        var fpst = new InlineKeyboardButton();

        fpst.setText("ФПСТ");
        fpst.setCallbackData("FPST");

        var uf = new InlineKeyboardButton();

        uf.setText("ЮФ");
        uf.setCallbackData("UF");

        rowInLine2.add(fpst);
        rowInLine2.add(uf);

        List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();
        var fbk = new InlineKeyboardButton();

        fbk.setText("ФБК");
        fbk.setCallbackData("FBK");

        rowInLine3.add(fbk);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);
        rowsInLine.add(rowInLine3);

        markupInLine.setKeyboard(rowsInLine);
        editMessage.setReplyMarkup(markupInLine);
        editMessageSender.editMessageSender(editMessage);
    }
}
