package my.homework.service;

import my.homework.domain.Result;

import java.io.IOException;

public interface ReportService {
  void build(Result result) throws IOException;
}
