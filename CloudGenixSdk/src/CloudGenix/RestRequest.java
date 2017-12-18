/*

    CloudGenix Controller SDK
    (c) 2017 CloudGenix, Inc.
    All Rights Reserved

    https://www.cloudgenix.com

    This SDK is released under the MIT license.
    For support, please contact us on:

        NetworkToCode Slack channel #cloudgenix: http://slack.networktocode.com
        Email: developers@cloudgenix.com

 */

package CloudGenix;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.Header;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "unused" })
public class RestRequest
{
    public static RestResponse Send(
        String url, 
        String method,  
        String contentType,
        HashMap<String, String> headers, 
        String body,
        Boolean debug) throws IOException 
    {         
        // check for null values
        if (stringNullOrEmpty(url)) throw new NullPointerException("url must not be null");
        if (stringNullOrEmpty(method)) throw new NullPointerException("method must not be null");
        if (stringNullOrEmpty(contentType)) throw new NullPointerException("contentType must not be null");

        method = method.toUpperCase();

        if (debug) System.out.println("RestRequest " + method + " " + url);
        
        HttpClient client = HttpClientBuilder.create().disableCookieManagement().build();
        HttpResponse response = null;

        switch (method) 
        {
            case "GET":
                HttpGet get = new HttpGet(url);
                get.removeHeaders("User-Agent");
                get.addHeader("User-Agent", "cgnx-sdk-java");

                if (headers != null && headers.size() > 0) 
                {
                    for (Map.Entry<String, String> curr : headers.entrySet()) 
                    {
                        get.addHeader(curr.getKey(), curr.getValue());
                    }
                } 

                get.addHeader("Content-Type", contentType);
                response = client.execute(get);
                break;

            case "HEAD":
                HttpHead head = new HttpHead(url);
                head.removeHeaders("User-Agent");
                head.addHeader("User-Agent", "cgnx-sdk-java");

                if (headers != null && headers.size() > 0) 
                {
                    for (Map.Entry<String, String> curr : headers.entrySet()) 
                    {
                        head.addHeader(curr.getKey(), curr.getValue());
                    }
                } 

                head.addHeader("Content-Type", contentType);
                response = client.execute(head);
                break;

            case "POST":
                HttpPost post = new HttpPost(url);
                post.removeHeaders("User-Agent");
                post.addHeader("User-Agent", "cgnx-sdk-java");

                if (headers != null && headers.size() > 0) 
                {
                    for (Map.Entry<String, String> curr : headers.entrySet()) 
                    {
                        post.addHeader(curr.getKey(), curr.getValue());
                    }
                } 

                post.addHeader("Content-Type", contentType);

                if (body != null)
                {
                    StringEntity input = new StringEntity(body);
                    input.setContentType(contentType);
                    post.setEntity(input);
                }

                response = client.execute(post);
                break;

            case "PUT":
                HttpPut put = new HttpPut(url);
                put.removeHeaders("User-Agent");
                put.addHeader("User-Agent", "cgnx-sdk-java");

                if (headers != null && headers.size() > 0) 
                {
                    for (Map.Entry<String, String> curr : headers.entrySet()) 
                    {
                        put.addHeader(curr.getKey(), curr.getValue());
                    }
                } 

                put.addHeader("Content-Type", contentType);

                if (body != null)
                {
                    StringEntity input = new StringEntity(body);
                    input.setContentType(contentType);
                    put.setEntity(input);
                }

                response = client.execute(put);
                break;

            case "DELETE":
                HttpDelete delete = new HttpDelete(url);
                delete.removeHeaders("User-Agent");
                delete.addHeader("User-Agent", "cgnx-sdk-java");

                if (headers != null && headers.size() > 0) 
                {
                    for (Map.Entry<String, String> curr : headers.entrySet()) 
                    {
                        delete.addHeader(curr.getKey(), curr.getValue());
                    }
                } 

                delete.addHeader("Content-Type", contentType);

                response = client.execute(delete);
                break;

            default:
                throw new IllegalArgumentException("method must be one of get, put, post, delete, head");
        }

        // build response
        RestResponse ret = new RestResponse();
        ret.statusCode = response.getStatusLine().getStatusCode();
        
        Header[] respHeaders = response.getAllHeaders();
        if (respHeaders != null && respHeaders.length > 0)
        {
            ret.headers = new HashMap<String, String>();
            
            for (int i = 0; i < respHeaders.length; i++) 
            {
                ret.headers.put(respHeaders[i].getName(), respHeaders[i].getValue());
            }
        }
        
        ContentType respContentType = ContentType.getOrDefault(response.getEntity());
        ret.contentType = respContentType.getMimeType();

        switch (method)
        {
            case "HEAD":
                // no response body
                ret.contentLength = 0;
                ret.responseBody = null;
                break;

            case "GET":
            case "PUT":
            case "POST":
            case "DELETE":
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer bodySb = new StringBuffer();
                String tempLine = "";

                while ((tempLine = rd.readLine()) != null)
                {
                    bodySb.append(tempLine);
                }

                ret.contentLength = bodySb.length();
                ret.responseBody = bodySb.toString();
                break;

            default:
                throw new IllegalArgumentException("method must be one of get, put, post, delete, head");
        }

        return ret; 
    }
     
    private static Boolean stringNullOrEmpty(String s) {
        if (s == null) return true;
        return s.isEmpty();
    } 
}