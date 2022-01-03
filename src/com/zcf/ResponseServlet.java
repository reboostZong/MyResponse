package com.zcf;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(998);
        resp.addHeader("name","zhangsan");
        resp.addHeader("name", "lisi");

        resp.setHeader("age", "25");
        resp.setHeader("age", "30");

//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=UTF-8");
        resp.setContentType(this.getServletContext().getMimeType("beauty.JPG"));
        resp.setHeader("Content-Disposition", "attachment;filename=abc.jpg");


        String path = this.getServletContext().getRealPath("/WEB-INF/beauty.JPG");
        FileInputStream fis = new FileInputStream(path);
        ServletOutputStream sos = resp.getOutputStream();

        try {
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fis.read(bytes)) != 0) {
                sos.write(bytes, 0, len);
            }
        } finally {
            fis.close();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
