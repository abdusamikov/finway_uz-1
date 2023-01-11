package com.example.finway_uz.service;

import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramService {
    SendMessage start(Update update);
    SendLocation laction(Update update);
    SendMessage clientInformation(Update update);
    SendMessage boglanish(Update update);
    SendMessage C1INFORMATION (Update update);
    SendMessage contact(Update update);
    SendMessage bugalteriyaXizmati(Update update);
    SendMessage sendName(Update update);
    SendMessage glabux(Update update);
    SendMessage shogirdlik(Update update);
    SendMessage soliqkursi(Update update);
    SendMessage test(Update update);
//    EditMessageText parseTest(Update update);


}

