package my.homework.service;

import org.springframework.context.MessageSource;
import org.springframework.util.Assert;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageService {
    private static final Locale LOCALE = new Locale("ru","RU");
    private MessageSource messageSource;

    public MessageService(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    public String getMessage(String code){
        Assert.notNull(code,"Illegal argument value");
        return messageSource.getMessage(code,null,LOCALE);
    }
}
