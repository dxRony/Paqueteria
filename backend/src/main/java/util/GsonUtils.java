/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ronyrojas
 */
public class GsonUtils<T> {

    private final Gson gson;

    public GsonUtils() {
        gson = new Gson();
    }

    public void sendAsJson(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("application/json");

        String res = gson.toJson(object);

        var out = response.getWriter();

        out.print(res);
    }

    public T readFromJson(HttpServletRequest request, Class<T> classT) throws IOException {
        var buffer = new StringBuilder();
        var reader = request.getReader();

        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String payload = buffer.toString();

        return gson.fromJson(payload, classT);
    }

    public String ReadString(HttpServletRequest request, Class<T> classT) throws IOException {
        var buffer = new StringBuilder();
        var reader = request.getReader();

        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        return buffer.toString();
    }
}
