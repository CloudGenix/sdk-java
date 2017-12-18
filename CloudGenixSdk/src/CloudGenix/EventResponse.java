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

public class EventResponse 
{ 
    @SerializedName("alarm")
    public EventsCount alarm;

    @SerializedName("alert")
    public EventsCount alert;

    @SerializedName("included_count")
    public Object includedCount;

    @SerializedName("total_count")
    public Object totalCount;

    @SerializedName("items")
    public List<EventDetails> events;
 
    public EventResponse()
    {
        this.alarm = null;
        this.alert = null;
    }
 
    public class EventsCount
    {
        @SerializedName("critical")
        public int critical;

        @SerializedName("major")
        public int major;

        @SerializedName("minor")
        public int minor;

        public EventsCount()
        {

        } 
    }

    public class EventDetails
    { 
        @SerializedName("id")
        public String id;

        @SerializedName("cleared")
        public Object cleared;

        @SerializedName("code")
        public String code;

        @SerializedName("correlation_id")
        public String correlationId;

        @SerializedName("entity_ref")
        public String entityRef;

        @SerializedName("info")
        public Object info;

        @SerializedName("severity")
        public String severity;

        @SerializedName("site_id")
        public String siteId;

        @SerializedName("time")
        public String timestamp;

        @SerializedName("type")
        public String type;

        public EventDetails()
        {
        } 
    } 
}
