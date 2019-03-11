package my.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService{

    @Value("${application.language}")
    private String language;
    private MessageSource messageSource;
    private Locale locale;

    public MessageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = new Locale(language);
    }

    public String getMessage(String code) {
       Assert.notNull(code, "Illegal argument value");
       return messageSource.getMessage(code, null, locale);
    }
}
