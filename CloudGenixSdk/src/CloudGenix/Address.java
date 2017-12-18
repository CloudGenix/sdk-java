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

public class Address 
{
    @SerializedName("street")
    public String street1;
    
    @SerializedName("street2")
    public String street2;
    
    @SerializedName("city")
    public String city;
    
    @SerializedName("state")
    public String state;
    
    @SerializedName("post_code")
    public String postalCode;
    
    @SerializedName("country")
    public String country;
}
