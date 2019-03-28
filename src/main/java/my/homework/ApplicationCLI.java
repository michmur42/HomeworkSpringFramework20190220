package my.homework;

import my.homework.domain.Result;
import my.homework.exception.StopException;
import my.homework.service.ReportService;
import my.homework.service.TestingService;
import org.jline.utils.AttributedString;
import org.springframework.core.io.ClassPathResource;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import sun.misc.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@ShellComponent
public class ApplicationCLI implements PromptProvider {

  private final TestingService testingService;
  private final ReportService reportService;
  private Result result;
  private String help;

  public ApplicationCLI(TestingService testingService, ReportService reportService) {
    this.testingService = testingService;
    this.reportService = reportService;
  }

  @ShellMethod("Start test")
  public void start() throws IOException, StopException {
    this.result = testingService.start();
  }

  @ShellMethod("Make report")
  public void report() throws IOException, StopException {
    reportService.build(this.result);
  }

  @Override
  public AttributedString getPrompt() {
    return new AttributedString("Test1.0>");
  }

}
