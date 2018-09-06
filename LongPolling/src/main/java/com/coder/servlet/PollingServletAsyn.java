package com.coder.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lucky on 2018/9/6.
 */
@WebServlet(name="pollingServletAsyn",urlPatterns = "/pollingServletAsyn" ,asyncSupported = true)
public class PollingServletAsyn extends HttpServlet{

    private final AtomicLong sequence = new AtomicLong();

    private final AtomicLong value = new AtomicLong();

    private final Random random = new Random();

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(30,50, 50000L,
            TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(50));

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long result = sequence.getAndIncrement();
        System.out.println("这是第 :" + result +"sync请求");
        //异步处理
        AsyncContext asyncContext = req.startAsync();
        //异步处理超时时间，这里需要注意，jetty容器默认的这个值设置的是30s，
        //如果超时，异步处理没有完成（通过是否asyncContext.complete()来进行判断），将会重试（会再次调用doGet方法）。
        //这里由于客户端long polling设置的是50s，所以这里如果小于50，会导致重试。
        asyncContext.setTimeout(51000);
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                //如果超时就重试
                AsyncContext asyncContext1 = asyncEvent.getAsyncContext();
                asyncContext1.complete();
            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

            }
        });

        executor.submit(new HandlePollingTask(result,asyncContext));
    }

    class HandlePollingTask implements Runnable{

        private long sequence;

        private AsyncContext asyncContext;

        public HandlePollingTask(long sequence,AsyncContext asyncContext){
            this.sequence = sequence;
            this.asyncContext = asyncContext;
        }

        @Override
        public void run() {
            try {
                PrintWriter out = asyncContext.getResponse().getWriter();
                long i = random.nextInt(100);
                System.out.println(sequence + "等待:"+ i +"s");
                try {
                    Thread.sleep(i*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long result = value.getAndIncrement();

                out.write(Long.toString(result));

            }catch (Exception e){
                System.out.println("handle error");
            }finally {
                asyncContext.complete();
            }
        }
    }
}
