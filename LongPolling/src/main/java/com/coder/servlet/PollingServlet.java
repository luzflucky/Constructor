package com.coder.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lucky on 2018/9/6.
 */
@WebServlet(name = "pollingServlet",urlPatterns = "/pollingServlet")
public class PollingServlet extends HttpServlet{

    private final AtomicLong sequence = new AtomicLong();

    private final AtomicLong value = new AtomicLong();

    private final Random random = new Random();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        long num = sequence.getAndIncrement();
        System.out.println("服务端收到第" + num +"次请求");

        long i = random.nextInt(100);
        System.out.println("等待:"+ i +"s");
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long result = value.getAndIncrement();
        PrintWriter out = resp.getWriter();

        out.write("服务端返回值:" + result);
        out.flush();
    }
}
