package my.homework.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class InputServiceImpl implements InputService{

  private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

  @Override
  public String waitInput(String prompt) throws IOException {
    System.out.print(prompt);
    return bufferedReader.readLine();
  }

  @Override
  public String waitInput() throws IOException {
    return bufferedReader.readLine();
  }
}
