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

import com.google.gson.annotations.SerializedName;

public class Client 
{
    @SerializedName("id")
    public String id;

    @SerializedName("_etag")
    public int etag;
    
    @SerializedName("tenant_id")
    public String tenantId;
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("canonical_name")
    public String canonicalName;
}
