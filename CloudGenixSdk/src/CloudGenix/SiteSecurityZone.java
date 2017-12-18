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

public class SiteSecurityZone
{
    @SerializedName("id")
    public String id;

    @SerializedName("zone_id")
    public String zoneId;

    @SerializedName("networks")
    public Object networks;
}
