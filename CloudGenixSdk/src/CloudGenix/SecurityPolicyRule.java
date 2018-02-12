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
import java.util.List;

public class SecurityPolicyRule
{
    @SerializedName("id")
    public String id;

    @SerializedName("_etag")
    public int etag;

    @SerializedName("name")
    public String name;

    @SerializedName("action")
    public String action;

    @SerializedName("application_ids")
    public List<String> applicationIds;

    @SerializedName("disabled_flag")
    public Boolean disabled;

    @SerializedName("destination_filter_ids")
    public List<String> destinationFilterIds;

    @SerializedName("destination_zone_ids")
    public List<String> destinationZoneIds;

    @SerializedName("source_filter_ids")
    public List<String> sourceFilterIds;

    @SerializedName("source_zone_ids")
    public List<String> sourceZoneIds;
}
