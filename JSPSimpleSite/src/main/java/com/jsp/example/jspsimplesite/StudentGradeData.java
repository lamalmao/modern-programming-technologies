package com.jsp.example.jspsimplesite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class StudentGradeData {

  public String name;
  public String surname;
  public String patronymic;
  public final String fileName;
  private int grade;
  private String subject;

  public StudentGradeData(String name, String surname, String patronymic, int grade,
      String subject) throws Exception {
    this.name = name.trim();
    this.surname = surname.trim();
    this.patronymic = patronymic.trim();
    this.grade = grade;
    this.subject = subject.trim();

    fileName = genFileName();
    File dataFile = new File(fileName);
    if (!dataFile.createNewFile()) {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.trim().startsWith(this.subject)) {
          throw new Exception(String.format("Балл по предмету '%s' уже проставлен", this.subject));
        }
      }
      reader.close();
    }

    Writer writer = new FileWriter(fileName, true);
    writer.write(String.format("%s %d%n", this.subject, this.grade));
    writer.flush();
    writer.close();
  }

  private String genFileName() {
    return String.format("%s_%s_%s.txt",
        name,
        surname,
        patronymic
    ).replace(" ", "_");
  }

  public ArrayList<String> getFileData() throws IOException {
    ArrayList<String> result = new ArrayList<>();

    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String line;
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
      result.add(line);
    }

    reader.close();
    return result;
  }
}
