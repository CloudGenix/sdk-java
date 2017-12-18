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

import java.util.HashMap; 

public class RestResponse {
    public HashMap<String, String> headers;
    public String contentType;
    public long contentLength;
    public int statusCode;
    public String responseBody;
}