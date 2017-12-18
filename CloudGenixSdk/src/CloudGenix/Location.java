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

public class Location 
{
    @SerializedName("longitude")
    public Double longitude;
    
    @SerializedName("latitude")
    public Double latitude;
    
    @SerializedName("description")
    public String description;
}
