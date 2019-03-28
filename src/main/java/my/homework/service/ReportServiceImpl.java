package my.homework.service;

import j2html.attributes.Attr;
import j2html.tags.ContainerTag;
import my.homework.domain.Option;
import my.homework.domain.Result;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;

import static j2html.TagCreator.*;

@Service
public class ReportServiceImpl implements ReportService {
  private final String COLOR_DEFAULT = "black";
  private final String COLOR_SUCCESS = "green";
  private final String COLOR_FAIL = "red";

  private final MessageService messageService;

  public ReportServiceImpl(MessageService messageService) {
    this.messageService = messageService;
  }

  @Override
  public void build(Result result) throws IOException {
    if (result == null){
      System.out.println(messageService.getMessage("report.error.notfound"));
      return;
    }
    String reportContent = makeReport(result);
    File htmlFile = new File("report.html");
    FileWriter writer = new FileWriter(htmlFile);
    writer.write(reportContent);
    writer.flush();
    writer.close();
    System.out.println(MessageFormat.format(messageService.getMessage("report.completed"), htmlFile.getAbsolutePath()));
  }

  private String makeReport(Result result) {
    return html().with(
            body().with(
                    h1(messageService.getMessage("report.title")),
                    h3(MessageFormat.format(messageService.getMessage("report.user"), result.getUser())),
                    hr(),
                    each(result.getAnswers(), answer ->
                            div().with(
                                    p(answer.getQuestion().getText()),
                                    ul().with(each(answer.getQuestion().getOptions(), option ->
                                            optionFormat(option, answer.getChoice())
                                    ))
                            )
                    )
            )
    ).render();
  }

  private ContainerTag optionFormat(Option option, Option choice) {
    String color = COLOR_DEFAULT;
    if (option.getText().equals(choice.getText()) && option.getFlag()) {
      color = COLOR_SUCCESS;
    } else if (option.getText().equals(choice.getText()) && !option.getFlag()) {
      color = COLOR_FAIL;
    }
    return li(option.getText()).attr(Attr.STYLE, "color:" + color);
  }
}
