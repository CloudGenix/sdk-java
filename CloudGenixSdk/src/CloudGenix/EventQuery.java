
package CloudGenix;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class EventQuery 
{ 
    @SerializedName("severity")
    public List<String> severity;

    @SerializedName("query")
    public QueryType query;

    @SerializedName("_offset")
    public String offset;

    @SerializedName("summary")
    public Boolean summary;

    @SerializedName("start_time")
    public String startTime;

    @SerializedName("end_time")
    public String endTime; 
 
    public EventQuery()
    {
        query = new QueryType();
        severity = new ArrayList<>();
    }

    public EventQuery(String startTime, String endTime, String offset, String queryType, Boolean summary)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.offset = offset;
        this.summary = summary;
        this.query = new QueryType();
        this.severity = new ArrayList<>();

        if (!stringNullOrEmpty(queryType)) query.type = queryType; 

        if (summary) 
        {
            this.query = null;
        }
    }
 
    public class QueryType 
    {
        @SerializedName("type")
        public String type;

        public QueryType()
        {
            this.type = null;
        } 
    }

    private Boolean stringNullOrEmpty(String s) 
    {
        if (s == null) return true;
        return s.isEmpty();
    } 
}
