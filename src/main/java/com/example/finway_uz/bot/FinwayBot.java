package com.example.finway_uz.bot;

import com.example.finway_uz.constant.Buttons;
import com.example.finway_uz.constant.ConstanTaUz;
import com.example.finway_uz.entity.Client;
import com.example.finway_uz.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FinwayBot extends TelegramLongPollingBot {
    final ClientRepository clientRepository;
    final TelegramServiceImpl service;

    @Override
    public String getBotUsername() {
        return "@finwayuzbot";
    }

    @Override
    public String getBotToken() {
        return "5424905357:AAG5hABc_q1TNRA2D1PLxkhbaJS7fPfCxTw";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        Client currentClient;

        if (update.hasMessage()) {
            Optional<Client> optionalClient = clientRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
            Message message = update.getMessage();
            if (message.hasText()) {
                if (message.getText().equals("/start")) {
                    if (optionalClient.isPresent()) {
                        currentClient = optionalClient.get();
                        currentClient.setName(message.getFrom().getFirstName());
                        currentClient.setState(State.START);
                        clientRepository.save(currentClient);
                    } else {
                        currentClient = new Client();
                        currentClient.setChatId(String.valueOf(update.getMessage().getChatId()));
                        currentClient.setState(State.START);
                        clientRepository.save(currentClient);
                    }
                    execute(service.start(update));
                } else {
                    currentClient = optionalClient.get(); //qaysi bosqichda
                    switch (currentClient.getState()) {
                        case State.START:
                            //menyudan 5 xil button bosishi mn
                            switch (update.getMessage().getText()) {
                                case ConstanTaUz.UZBEK:
                                    execute(service.informationAllThing(update));
                                    break;
//                                case ConstantRu.RUS:
//                                    execute();
//                                    break;

                            }
                            switch (update.getMessage().getText()) {
                                case Buttons.KURSGA_YOZILISH:
                                    execute(service.clientInformation(update));
                                    break;
                                case Buttons.LAKATSIYA:
                                    execute(service.laction(update));
                                    execute(service.locationSms(update));
                                    break;
                                case Buttons.BOGLANISH:
                                    execute(service.boglanish(update));
                                    break;
                                case Buttons.BACK:
                                    execute(service.informationAllThing(update));
                                    break;
                                case Buttons.BUGALTERIYA_HIZMATI:
                                    execute(service.bugalteriyaXizmati(update));
                                    break;
                                case Buttons.TEST:
                                    execute(service.test(update));
                                    break;
                            }
                            switch (update.getMessage().getText()) {
                                case Buttons.C1:
                                    execute(service.C1INFORMATION(update));
                                    break;
                                case Buttons.GLABUX:
                                    execute(service.glabux(update));
                                    break;
                                case Buttons.SOLIQ_K:
                                    execute(service.soliqkursi(update));
                                    break;
                                case Buttons.SHOGIRLDLIK:
                                    execute(service.shogirdlik(update));
                                    break;
                            }
                          switch (update.getMessage().getText()){
                              case Buttons.ROYHATDAN_OTISH -> execute(service.contact(update));
                          }
                            break;
                        case State.NAME:
                                String text1 = update.getMessage().getText();
                                optionalClient.get().setName(text1);
                                optionalClient.get().setState(State.START);
                                clientRepository.save(optionalClient.get());
                                execute(SendMessage.builder().chatId(1962294505+"").text(text1+ "  "+optionalClient.get().getKursnomi()).build());
                                execute(SendMessage.builder().chatId(String.valueOf(update.getMessage().getChatId())).text("jonatldi").build());
                                execute(service.clientInformation(update));
                    }

                }
            }
            else if (message.hasContact()){
                switch (optionalClient.get().getState()){
                    case State.CLIENT:
                        if (update.getMessage().hasContact()) {
                            String text = update.getMessage().getContact().getPhoneNumber();
                            optionalClient.get().setNumber(text);
                            clientRepository.save(optionalClient.get());
                            execute(SendMessage.builder().chatId(1962294505+"").text(text).build());
                        execute(service.sendName(update));
                        }

                }

            }

        }
        else if (update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();
            System.out.println(callbackQuery);
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(String.valueOf(callbackQuery.getMessage().getChatId()));
            editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());
            System.out.println(callbackQuery.getData());
            execute(editMessageText);
////kere
////            String[] split = callbackQuery.getData().split("#");
////
                execute(service.parseTest(update));
//
//
        }
    }
}
