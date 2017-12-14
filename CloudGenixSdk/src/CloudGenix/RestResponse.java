package CloudGenix;

import java.util.HashMap; 

public class RestResponse {
    public HashMap<String, String> headers;
    public String contentType;
    public long contentLength;
    public int statusCode;
    public String responseBody;
}