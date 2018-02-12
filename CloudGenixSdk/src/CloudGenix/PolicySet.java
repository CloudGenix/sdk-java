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

public class PolicySet 
{
    @SerializedName("id")
    public String id;

    @SerializedName("_etag")
    public int etag;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("default_policy")
    public Boolean defaultPolicy;

    @SerializedName("bandwidth_allocation_schemes")
    public Object bandwidthAllocationSchemes;

    @SerializedName("business_priority_names")
    public Object businessPriorityNames;
}
