package com.example.finway_uz.bot;

import com.example.finway_uz.constant.Buttons;
import com.example.finway_uz.constant.ConstanTaUz;
import com.example.finway_uz.constant.ConstantRu;
import com.example.finway_uz.entity.Client;
import com.example.finway_uz.repository.ClientRepository;
import com.example.finway_uz.service.TelegramService;
import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {
    final ClientRepository clientRepository;
    int trueCount = 0;
    int elseCount = 0;
////    StaticTelegramBot telegramBot = new StaticTelegramBot();
//
//    List<Test> tests = new ArrayList<>(Arrays.asList(
//            new Test("Hisob raqamdan kassava pul olib kelindi", List.of("Dt 5710 Kt 5110 ",
//                    "Dt 5110 Kt5010", "Dt 5010 5110", "Dt 5330 Kt5710"), 2),
//            new Test("Oylikdan fuqarolardan olinadigan daromad solig'iga 12% ushlab qolindi", List.of("Dt 6710 Kt 6411 ",
//                    "Dt 6411 Kt 6710", "Dt 5110 Kt 6411", "Dt 6411 Kt 5110"), 0),
//            new Test("Bahor korxonasi tasischisi ustavga 9 000$ pul qo'ydi", List.of("Dt 5110 Kt 4610",
//                    "Dt 4610 Kt 5110", "Dt 8330 Kt 5110", "Dt 5110 Kt 8330"), 0)
////            new Test("\"Bahor\" korxonasi 80 000$ ustav registratsiya qilindi", List.of("Dt 4610 Kt 5110",
////                    "Dt 5010 Kt 8330", "Dt 5110 Kt 4610", "Dt 4610 Kt 8330 "), 3),
////            new Test("\"Bahor\" korxonasiga mol yetkazib beruvchilaridan tovar keldi", List.of("Dt 6010 Kt 2910",
////                    "Dt 6010 Kt 2810", "Dt 2910 Kt 6010 ", "Dt 2910 Kt 5110"), 2),
////            new Test("Direktorga oylik hisoblandi", List.of("Dt 9430 Kt 6710",
////                    "Dt 9410 Kt 6710", "Dt 9330 Kt 6412", "Dt 9420 Kt 6710 "), 3),
////            new Test("Korxona qo'shni do'konga 3-sonli shartnomaga asosan hisobfaktura berdi", List.of(
////                    "Dt 9120 Kt 2920  Dt 4010 9120",
////                    "Dt 9020 Kt 2920 Dt 4010 9120", "Dt 9120 Kt 2910 Dt 4010 9020", "Dt 9120 Kt 2820 Dt 4010 9120 "), 2) ,
////        new Test("Bahor korxonasiga tushgan pulda bank o'z foizini ushlab qoldi",List.of("Dt 9430 Kt 5010",
////                    "Dt 5010 Kt 9430","Dt 9430 Kt 5110","Dt 5110 Kt 9430 "),2),
////            new Test("Korxona omboridan tovar o'g'irlandi",List.of("Dt 5110 Kt 2910",
////                    "Dt 9430 Kt 2910","Dt 2910 Kt 9430","Dt 2910 Kt 5110"),1),
////            new Test("Direktordan NPS ushlab qolindi",List.of("Dt 9420 Kt 6521",
////                    "Dt 6710 Kt 9420","Dt 9410 Kt 6710","Dt 9430 Kt 6521"),0),
////            new Test("Moliyaviy natijaga tegishli schotlarni tanlang",List.of("9430 9120 6700  ",
////                    "9000 8700 8330","8700 3010 5110","9100 9200 9430"),3),
////            new Test("Zarar...",List.of("Bu xarajatlarning daromadlardan oshishdir",
////                    "Bu daromadlarning xarajatlardan oshishidir","Bu daromadlarning aktivlardan oshishdir","Bu aktivlarning daromadlardan oshishdir"), 1),
////            new Test("Moliyaviy natija schotlari qanday nomlanasi",List.of("Tranzit",
////                    "Kontr passiv ","Kontr aktiv","Aktiv"),0),
////            new Test("Tannarx..",List.of("Bu mahsulot sotilgan narxi",
////                    "Bu tovarni sotib olish uchun ketgan mablag'","Bu mahsulotni ishlab chiqarish uchun sarflangan xarajatlarning puldagi ifodasidir","Hamma javoblar to'g'ri"),3)
//    ));

    @Override
    public SendMessage start(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText("Salom" + update.getMessage().getFrom().getFirstName() + " "
                + (update.getMessage().getFrom().getLastName() == null ? "" : update.getMessage().getFrom().getLastName()));
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setSelective(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton(ConstanTaUz.UZBEK);
        KeyboardButton button1 = new KeyboardButton(ConstantRu.RUS);
        row.add(button);
        row.add(button1);
        rowList.add(row);
        markup.setKeyboard(rowList);
        message.setReplyMarkup(markup);

        return message;

    } // yaxwi

    @Override
    public SendLocation laction(Update update) {

        SendLocation build = SendLocation.builder().latitude(41.343291D).longitude(69.206728D).chatId(String.valueOf(update.getMessage().getChatId())).build();

        return build;
    }

    @Override
    public SendMessage boglanish(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText("\uD83D\uDCDE Telefon raqami\n" +
                "\uD83C\uDFE2Idora: +998(95) 100-01-71\n" +
                "\uD83E\uDDD4\uD83C\uDFFBKompaniya asoschisi: +998 (95) 100-01-71\n" +
                "\n" +
                "\uD83D\uDCEE Email\n" +
                "ℹ️ consultingfinway@gmail.com\n" +
                "\n" +
                "\uD83C\uDF10 Ijtimoiy tarmoqlardagi sahifalarimiz\n" +
                " | [Tik Tok](https://vt.tiktok.com/ZSdmGFsPX/)\n" +
                " | [Instagram](https://www.instagram.com/invites/contact/?i=q7gbx710bqju&utm_content=o5g3ybo/) " +
                "| [Telegram](https://t.me/BUGALTERIYAKURSLARI)"
        );
        message.setParseMode("Markdown");

        return message;
    }

    @Override
    public SendMessage C1INFORMATION(Update update) {
        Optional<Client> byChatId = clientRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
        byChatId.get().setKursnomi(update.getMessage().getText());
        clientRepository.save(byChatId.get());
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText("C1 haqida information yozish kere");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button = new KeyboardButton(Buttons.ROYHATDAN_OTISH);
        KeyboardButton button1 = new KeyboardButton(Buttons.BACK);

        row.add(button);
        row1.add(button1);
        rowList.add(row);
        rowList.add(row1);
        replyKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(replyKeyboardMarkup);

        return message;
    }

    @Override
    public SendMessage contact(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText("Contact tugmasini boshing");

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        List<KeyboardRow> rowList = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button = new KeyboardButton(Buttons.CONTACT);
        KeyboardButton button1 = new KeyboardButton(Buttons.BACK);

        Client currentClient;
        Optional<Client> byChatId = clientRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
        currentClient = byChatId.get();
        currentClient.setState(State.CLIENT);
        clientRepository.save(currentClient
        );

        button.setRequestContact(true);
        row.add(button);
        row1.add(button1);
        rowList.add(row);
        rowList.add(row1);
        markup.setKeyboard(rowList);
        message.setReplyMarkup(markup);
        return message;

    }

    @Override
    public SendMessage bugalteriyaXizmati(Update update) {
        SendMessage message = new SendMessage();
        message.setText("Shu no`merga aloqaga chiqsangiz malumot berishadi\n" +
                "+998 90 541 92 82");
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        return message;
    }

    @Override
    public SendMessage sendName(Update update) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        rowList.add(row);
        markup.setKeyboard(rowList);
        SendMessage message = new SendMessage();
        message.setText("ism va familyangizni kriting");
        message.setReplyMarkup(markup);
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        Optional<Client> byChatId = clientRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
        byChatId.get().setState(State.NAME);
        clientRepository.save(byChatId.get());
        return message;
    }

    @Override
    public SendMessage glabux(Update update) {
        SendMessage message = new SendMessage();
        Optional<Client> byChatId = clientRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
        byChatId.get().setKursnomi(update.getMessage().getText());
        clientRepository.save(byChatId.get());
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText("Glabux xaqida informtion yozish kere");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button = new KeyboardButton(Buttons.ROYHATDAN_OTISH);
        KeyboardButton button1 = new KeyboardButton(Buttons.BACK);

        row.add(button);
        row1.add(button1);
        rowList.add(row);
        rowList.add(row1);
        replyKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(replyKeyboardMarkup);

        return message;
    }

    @Override
    public SendMessage shogirdlik(Update update) {
        SendMessage message = new SendMessage();
        Optional<Client> byChatId = clientRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
        byChatId.get().setKursnomi(update.getMessage().getText());
        clientRepository.save(byChatId.get());
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText("0 dan balancgacha shogirdlik kursi haqida information haqida yozish kere");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button = new KeyboardButton(Buttons.ROYHATDAN_OTISH);
        KeyboardButton button1 = new KeyboardButton(Buttons.BACK);

        row.add(button);
        row1.add(button1);
        rowList.add(row);
        rowList.add(row1);
        replyKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(replyKeyboardMarkup);

        return message;
    }

    @Override
    public SendMessage soliqkursi(Update update) {
        SendMessage message = new SendMessage();
        Optional<Client> byChatId = clientRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
        byChatId.get().setKursnomi(update.getMessage().getText());
        clientRepository.save(byChatId.get());
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText("Soliq kursi haqida information yozish kere");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button = new KeyboardButton(Buttons.ROYHATDAN_OTISH);
        KeyboardButton button1 = new KeyboardButton(Buttons.BACK);

        row.add(button);
        row1.add(button1);
        rowList.add(row);
        rowList.add(row1);
        replyKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(replyKeyboardMarkup);

        return message;
    }

    @Override
    public SendMessage test(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText("savol");
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(message.getChatId());
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        row.add(InlineKeyboardButton.builder()
                .text("start")
                .callbackData("start")
                .build());
        rows.add(row);
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);
        return message;
    }


    @SneakyThrows
    @Override
    public EditMessageText  parseTest(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        String[] split = callbackQuery.getData().split("#");
//        salom#hayir
//        salom, hayir
        if (split[0].equals("start")) {
            Optional<Client> byChatId = clientRepository.findByChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
            byChatId.get().setTrueCounts(0);
            byChatId.get().setElseCounts(0);
            clientRepository.save(byChatId.get());
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

            List<List<InlineKeyboardButton>> rows = new ArrayList<>();
            for (String answer : tests.get(0).getAnswers()) {
                List<InlineKeyboardButton> row = new ArrayList<>();
                row.add(InlineKeyboardButton.builder()
                        .text(answer)
                        .callbackData("test#0#" + answer)

                        .build());
                rows.add(row);
            }

            markup.setKeyboard(rows);

            return EditMessageText.builder().chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .text(tests.get(0).getQuastion())
                    .replyMarkup(markup)
                    .build();
        } else if (split[0].equals("test")) {
            int i = Integer.parseInt(split[1])+1 ;
            Optional<Client> byChatId = clientRepository.findByChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));

            if (split[2].equals(tests.get(i-1).getAnswers().get(tests.get(i-1).getCorrect()))) {
                byChatId.get().setTrueCounts(byChatId.get().getTrueCounts() + 1);
                System.out.println(trueCount);
            } else {
                byChatId.get().setElseCounts(byChatId.get().getElseCounts() + 1);

            }
            if (i >= tests.size()) {
                if (i == tests.size()) {
                    telegramBot.execute(informationAllThing(update));

                }
                return EditMessageText.builder().
                        messageId(callbackQuery.getMessage().getMessageId())
                        .text(byChatId.get().getTrueCounts() + " ta togri javob" + "\n" + byChatId.get().getElseCounts() + " ta nto`g`ri javob")
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .build();
            }
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

            clientRepository.save(byChatId.get());

            List<List<InlineKeyboardButton>> rows = new ArrayList<>();
            for (String answer : tests.get(i).getAnswers()) {
                List<InlineKeyboardButton> row = new ArrayList<>();
                row.add(InlineKeyboardButton.builder()
                        .text(answer)
                        .callbackData("test#" + (i) + "#" + answer)
                        .build());
                rows.add(row);
            }
            markup.setKeyboard(rows);


            return EditMessageText.builder().chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .text(tests.get(i).getQuastion())
                    .replyMarkup(markup)
                    .build();
        }
        return null;
    }

    public SendMessage locationSms(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText("\uD83D\uDCCDManzil:Beruniy metro\n" +
                "\uD83D\uDD79Mo'ljal:243-maktab oldi,ECOBOZOR ro'parasi");
        return message;
    }

    @Override
    public SendMessage clientInformation(Update update) {

        SendMessage message = new SendMessage();
        message.setText("Tanlang biron bir tugmani");
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
//        Optional<Client> byChatId = clientRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
//        currentClient = byChatId.get();
//
//        if (currentClient.getState()!= null) {
//            if (currentClient.getState().equals(State.CLIENT)) {
//                String text = update.getMessage().getText();
//                System.out.println(text);
//            }
//        }

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setSelective(true);
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton(Buttons.C1);
        KeyboardButton button2 = new KeyboardButton(Buttons.SOLIQ_K);
        KeyboardButton button3 = new KeyboardButton(Buttons.SHOGIRLDLIK);
        KeyboardButton button4 = new KeyboardButton(Buttons.GLABUX);
        KeyboardButton button5 = new KeyboardButton(Buttons.BACK);

        row1.add(button1);
        row1.add(button2);
        row2.add(button3);
        row2.add(button4);
        row3.add(button5);
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        markup.setKeyboard(rowList);
        message.setReplyMarkup(markup);
//        String text = update.getMessage().getText();
//        System.out.println(text);
        return message;
    }

    public SendMessage informationAllThing(Update update) {
        SendMessage message = new SendMessage();

        if (update.hasCallbackQuery()) {
            message.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        } else if (update.hasMessage()) {
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
        }
        message.setText("tanlang");
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        markup.setSelective(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        KeyboardRow row5 = new KeyboardRow();

        KeyboardButton button = new KeyboardButton(Buttons.KURSGA_YOZILISH);
        KeyboardButton button1 = new KeyboardButton(Buttons.BUGALTERIYA_HIZMATI);
        KeyboardButton button2 = new KeyboardButton(Buttons.BOGLANISH);
        KeyboardButton button3 = new KeyboardButton(Buttons.LAKATSIYA);
        KeyboardButton button4 = new KeyboardButton(Buttons.TEST);


        row1.add(button);
        row2.add(button4);
        row3.add(button1);
        row4.add(button3);
        row5.add(button2);
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        rowList.add(row4);
        rowList.add(row5);
        markup.setKeyboard(rowList);
        message.setReplyMarkup(markup);

        return message;
    } // yaxwi


}
