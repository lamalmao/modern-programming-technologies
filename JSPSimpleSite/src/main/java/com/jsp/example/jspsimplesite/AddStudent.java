package com.jsp.example.jspsimplesite;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "AddStudent", urlPatterns = "/add-student")
public class AddStudent extends HttpServlet {

//  @Override
//  public void init(ServletConfig config) throws ServletException {
//    super.init();
//  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");

      StudentGradeData student = new StudentGradeData(
          request.getParameter("name"),
          request.getParameter("surname"),
          request.getParameter("patronymic"),
          Integer.parseInt(request.getParameter("grade")),
          request.getParameter("subject")
      );

      request.setAttribute("fileData", String.join("<br>", student.getFileData()));
      request.setAttribute("fileName", student.fileName);
      getServletContext().getRequestDispatcher("/answer.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      response.setStatus(400);
      request.setAttribute("message", e.getMessage());
      getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }

}
