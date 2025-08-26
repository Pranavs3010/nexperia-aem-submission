package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import javax.servlet.Servlet;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

@Component(service = { Servlet.class })
@SlingServletPaths(value = "/bin/getBlogPosts")
public class BlogPostServlet extends SlingSafeMethodsServlet {

    private static final String API_ENDPOINT = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {
        try {
            URL url = new URL(API_ENDPOINT);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                String jsonResponse = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

                resp.setContentType("application/json");
                resp.getWriter().write(jsonResponse);
            } else {
                resp.sendError(status, "Failed to fetch data from external API.");
            }
        } catch (Exception e) {
            resp.sendError(HttpURLConnection.HTTP_INTERNAL_ERROR, "Server error: " + e.getMessage());
        }
    }
}