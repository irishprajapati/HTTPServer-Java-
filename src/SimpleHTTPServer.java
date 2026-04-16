import java.io.*;
import java.net.*;
import java.util.Date;
public class SimpleHTTPServer{
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening on port 8080...");
        while(true){
            try(Socket socket = server.accept()){
                //Read request
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                String line = reader.readLine();
                while(line != null && !line.isEmpty()){
                    System.out.println(line);
                    line = reader.readLine();

                }
                // Send response
                String body = "<h1>Hello from Java HTTP Server</h1><p>" + new Date() + "</p>";
                String response = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: " + body.length() + "\r\n"
                        + "\r\n"
                        + body;
                socket.getOutputStream().write(response.getBytes("UTF-8"));
            }
        }

    }
}
