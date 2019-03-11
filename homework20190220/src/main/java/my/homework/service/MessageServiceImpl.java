package my.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService{

    private MessageSource messageSource;
    private Locale locale;

    public MessageServiceImpl(@Value("${application.language}") String language, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = new Locale(language);
    }

    public String getMessage(String code) {
       Assert.notNull(code, "Illegal argument value");
       return messageSource.getMessage(code, null, locale);
    }
}
