package com.linuxtek.kona.app.server;

import java.util.HashMap;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import org.apache.log4j.Logger;

import com.linuxtek.kona.app.servlet.KFileManagerServlet;

public class KFileManagerServer {
    private static Logger logger = Logger.getLogger(KFileManagerServer.class);

    Server server = null;
    Context context = null;

    public KFileManagerServer(Integer port, String contextPath, 
            String springContextConfig, String fileManagerRef) {
        server = new Server(port);
        context = new Context(server, contextPath, Context.SESSIONS);

        ServletHolder servlet = new ServletHolder(KFileManagerServlet.class);
        servlet.setInitParameter("fileManagerRef", fileManagerRef);

        //context.addServlet(KFileManagerServlet.class, "/*");
        context.addServlet(servlet, "/*");

        HashMap<String,String> contextParams = new HashMap<String,String>();
        contextParams.put("contextConfigLocation", springContextConfig);
        context.setInitParams(contextParams);
    }

    public void start() throws Exception {
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }


    /**
     * Starts Jetty web application server to handle download requests.
     * <p>
     * Requires 4 params:
     * </p>
     * <ol>
     *  <li>port (e.g. 8080)</li>
     *  <li>context path (e.g. /)</li>
     *  <li>URI of spring context file (e.g. classpath:spring-context.xml)</li>
     *  <li>Spring reference to Class that implements KFileManager interface</li>
     * </ol>
     */
    public static void main(String args[]) {
        if (args.length == 4) {
            System.err.println("\nUsage: KFileManagerServer <port> "
                + "<context path> <springContextConfig> "
                + "<spring fileManagerRef>\n");

            System.exit(1);
        }

        Integer port = Integer.parseInt(args[0]);
        String contextPath = args[1];
        String springContextConfig = args[2];
        String fileManagerRef = args[3];

        KFileManagerServer server = new KFileManagerServer(port, contextPath, 
                springContextConfig, fileManagerRef);

        try { server.start(); } catch (Exception e) { logger.error(e); }
    }
}
