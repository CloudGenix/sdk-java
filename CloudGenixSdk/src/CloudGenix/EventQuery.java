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
import java.util.ArrayList;
import java.util.List;

public class EventQuery 
{ 
    @SerializedName("severity")
    public List<String> severity;

    @SerializedName("query")
    public QueryParams query;

    @SerializedName("_offset")
    public String offset;

    @SerializedName("view")
    public ViewParams view;

    @SerializedName("start_time")
    public String startTime;

    @SerializedName("end_time")
    public String endTime; 
 
    public EventQuery()
    {
        query = new QueryParams();
        severity = new ArrayList<>();
    }

    public EventQuery(String startTime, String endTime, String offset, String queryType, Boolean summary)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.offset = offset;
        this.query = new QueryParams();
        
        this.severity = new ArrayList<>();
        this.severity.add("critical");
        this.severity.add("major");
        
        this.view = new ViewParams();
        
        if (!stringNullOrEmpty(queryType)) query.type = queryType; 

        if (summary) 
        {
            this.view.summary = true;
            this.query = null;
        }
    }
 
    public class QueryParams 
    {
        @SerializedName("type")
        public String type;

        public QueryParams()
        {
            this.type = null;
        } 
    }

    public class ViewParams 
    {
        @SerializedName("summary")
        public Boolean summary;

        @SerializedName("individual")
        public String individual;
        
        public ViewParams()
        {
            this.summary = true;
            this.individual = "code";
        } 
    }

    private Boolean stringNullOrEmpty(String s) 
    {
        if (s == null) return true;
        return s.isEmpty();
    } 
}
