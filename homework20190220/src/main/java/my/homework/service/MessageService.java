package my.homework.service;

import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Locale;

@Service
public class MessageService {

    private final Locale locale;
    private MessageSource messageSource;

    public MessageService(MessageSource messageSource, Environment environment) {
        this.messageSource = messageSource;
        this.locale = new Locale(environment.getProperty("application.language"), environment.getProperty("application.language").toUpperCase());
    }

    public String getMessage(String code) {
        Assert.notNull(code, "Illegal argument value");
        return messageSource.getMessage(code, null, locale);
    }
}
