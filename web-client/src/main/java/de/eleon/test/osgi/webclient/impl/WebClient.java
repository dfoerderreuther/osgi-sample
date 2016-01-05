package de.eleon.test.osgi.webclient.impl;

import de.eleon.test.osgi.dateserver.DateServer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN + "=/",
                HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT + "=(" + HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME + "=org.osgi.service.http)"})
public class WebClient extends HttpServlet {

    private DateServer dateServer;

    @Reference
    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello world!\n");
        resp.getWriter().write("current date: " + dateServer.currentDate() + "\n");
        resp.getWriter().write("server start date: " + dateServer.startDate() + "\n");
        resp.getWriter().write("uptime: " + dateServer.uptime() + "\n");
    }
}
