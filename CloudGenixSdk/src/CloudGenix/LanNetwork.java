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

public class LanNetwork 
{
    @SerializedName("id")
    public String id;
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("scope")
    public String scope;
    
    @SerializedName("ipv4_config")
    public Object ipv4Config;
    
    @SerializedName("security_policy_set")
    public Object securityPolicySet;
        
    @SerializedName("network_context_id")
    public String networkContextId;
}
