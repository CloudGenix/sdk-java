/*

    CloudGenix Controller SDK
    (c) 2018 CloudGenix, Inc.
    All Rights Reserved

    https://www.cloudgenix.com

    This SDK is released under the MIT license.
    For support, please contact us on:

        NetworkToCode Slack channel #cloudgenix: http://slack.networktocode.com
        Email: developers@cloudgenix.com

 */

package CloudGenix;
 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloudGenixSdk 
{
    // <editor-fold desc="Public Members">
    
    public String email;
    public String password;
    public Boolean debug;
    public String endpoint;
    public String authToken;
    public String tenantId;
    
    // </editor-fold>
    
    // <editor-fold desc="Private Members">
    
    private String samlRequestId;
    private EndpointManager endpoints;
    private HashMap<String, String> authHeaders;
    
    // </editor-fold>
    
    // <editor-fold desc="Constructors and Factories">
    
    public CloudGenixSdk(String email, String password, Boolean debug) throws IOException, Exception
    {
        if (stringNullOrEmpty(email)) throw new NullPointerException("email must not be null");
        if (stringNullOrEmpty(password)) throw new NullPointerException("password must not be null");
        
        this.email = email;
        this.password = password;
        this.debug = debug;
        this.endpoint = "https://api.cloudgenix.com:443";
        this.authToken = "";
        
        this.endpoints = new EndpointManager();
        
        if (!loginInternal()) throw new Exception("Unable to login");
        if (!getProfile()) throw new Exception("Unable to retrieve tenant ID");
        if (!getEndpoints()) throw new Exception("Unable to retrieve endpoints");
    }
    
    public CloudGenixSdk(String email, Boolean debug)
    {
        if (stringNullOrEmpty(email)) throw new NullPointerException("email must not be null");
        
        this.email = email; 
        this.debug = debug;
        this.endpoint = "https://api.cloudgenix.com:443";
        this.authToken = "";
        
        this.endpoints = new EndpointManager(); 
    }
    
    public CloudGenixSdk(String token, Boolean isToken, Boolean debug) throws IOException, Exception
    {
        if (stringNullOrEmpty(token)) throw new NullPointerException("token must not be null");
        
        this.authToken = token;
        this.email = null;
        this.password = null;
        this.debug = debug;
        this.endpoint = "https://api.cloudgenix.com:443";
        
        this.endpoints = new EndpointManager();
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Public Methods">
     
    public Boolean logout() throws IOException
    {
        RestResponse resp = RestRequest.Send(
            this.endpoint + endpoints.getEndpoint("logout"), 
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("logout no response from server");
            return false;
        }
        
        if (resp.statusCode > 299) 
        {
            log("logout failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return false;
        }
         
        return true;
    }
    
    public Boolean loginWithToken() throws IOException, Exception
    {
        this.authHeaders = new HashMap<>();
        this.authHeaders.put("x-auth-token", this.authToken);
        
        if (!getProfile()) throw new Exception("Unable to retrieve tenant ID");
        if (!getEndpoints()) throw new Exception("Unable to retrieve endpoints");
        return true;
    }
    
    public String loginSamlStart() throws IOException
    { 
        HashMap<String, String> loginRequest = new HashMap<String, String>();
        loginRequest.put("email", this.email); 
        
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Referer", this.endpoint + "/v2.0/api/login");

        RestResponse resp = RestRequest.Send(
            this.endpoint + endpoints.getEndpoint("login"), 
            "POST",
            "application/json",
            headers,
            serialize(loginRequest),
            this.debug);
        
        if (resp == null) 
        {
            log("login no response from server");
            throw new IOException("No response from server");
        }
        
        if (resp.statusCode > 299) 
        {
            log("loginSamlStart failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            throw new IOException("Failure status returned from server");
        }
        
        Gson gson = new Gson(); 
        LoginSamlResponse loginResp = gson.fromJson(resp.responseBody, LoginSamlResponse.class);
        this.samlRequestId = loginResp.requestId;
        
        log("loginSamlStart returning URL " + loginResp.urlpath + " request ID " + loginResp.requestId);
        return loginResp.urlpath;
    }
    
    public Boolean loginSamlFinish() throws IOException, Exception
    { 
        HashMap<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", this.email); 
        loginRequest.put("requestId", this.samlRequestId);
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Referer", this.endpoint + "/v2.0/api/login");

        RestResponse resp = RestRequest.Send(
            this.endpoint + endpoints.getEndpoint("login"), 
            "POST",
            "application/json",
            headers,
            serialize(loginRequest),
            this.debug);
        
        if (resp == null) 
        {
            log("login no response from server");
            throw new IOException("No response from server");
        }
        
        if (resp.statusCode > 299) 
        {
            log("loginSamlStart failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            throw new IOException("Failure status returned from server");
        }
        
        Gson gson = new Gson(); 
        LoginResponse loginResp = gson.fromJson(resp.responseBody, LoginResponse.class);
        this.authToken = loginResp.x_auth_token;
        
        this.authHeaders = new HashMap<>();
        this.authHeaders.put("x-auth-token", this.authToken);
        
        if (!getProfile()) throw new Exception("Unable to retrieve tenant ID");
        if (!getEndpoints()) throw new Exception("Unable to retrieve endpoints");
        return true;
    }
    
    public HashMap<String, String> getAllVersions()
    {
        return this.endpoints.getAllVersions();
    }
    
    public HashMap<String, String> getAllEndpoints()
    {
        return this.endpoints.getAllEndpoints();
    }
    
    public List<Context> getContexts() throws IOException
    {
        String url = this.endpoint + this.endpoints.getEndpoint("networkcontexts");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getContexts no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getContexts failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<Context>>(){}.getType();
        ResourceResponse<Context> data = gson.fromJson(resp.responseBody, type);
        
        log("getContexts returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<Site> getSites() throws IOException
    {
        String url = this.endpoint + this.endpoints.getEndpoint("sites");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getSites no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getSites failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<Site>>(){}.getType();
        ResourceResponse<Site> data = gson.fromJson(resp.responseBody, type);
        
        log("getSites returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<Element> getElements() throws IOException
    {
        String url = this.endpoint + this.endpoints.getEndpoint("elements");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getElements no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getElements failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<Element>>(){}.getType();
        ResourceResponse<Element> data = gson.fromJson(resp.responseBody, type);
        
        log("getElements returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<ElementInterface> getElementInterfaces(String siteId, String elementId) throws IOException
    {
        if (stringNullOrEmpty(siteId)) throw new NullPointerException("siteId must not be null");
        if (stringNullOrEmpty(elementId)) throw new NullPointerException("elementId must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("interfaces");
        url = url.replaceFirst("%s", this.tenantId);
        url = url.replaceFirst("%s", siteId);
        url = url.replaceFirst("%s", elementId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getElementInterfaces no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getElementInterfaces failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<ElementInterface>>(){}.getType();
        ResourceResponse<ElementInterface> data = gson.fromJson(resp.responseBody, type);
        
        log("getElementInterfaces returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<ElementInterfaceStatus> getElementInterfaceStatus(String siteId, String elementId, String interfaceId) throws IOException
    {
        if (stringNullOrEmpty(siteId)) throw new NullPointerException("siteId must not be null");
        if (stringNullOrEmpty(elementId)) throw new NullPointerException("elementId must not be null");
        if (stringNullOrEmpty(interfaceId)) throw new NullPointerException("interfaceId must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("interfaces");
        url = url.replaceFirst("%s", this.tenantId);
        url = url.replaceFirst("%s", siteId);
        url = url.replaceFirst("%s", elementId);
        url = url.replaceFirst("%s", interfaceId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getElementInterfaceStatus no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getElementInterfaceStatus failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<ElementInterfaceStatus>>(){}.getType();
        ResourceResponse<ElementInterfaceStatus> data = gson.fromJson(resp.responseBody, type);
        
        log("getElementInterfaceStatus returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<WanNetwork> getWanNetworks() throws IOException
    {
        String url = this.endpoint + this.endpoints.getEndpoint("wannetworks");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getWanNetworks no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getWanNetworks failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<WanNetwork>>(){}.getType();
        ResourceResponse<WanNetwork> data = gson.fromJson(resp.responseBody, type);
        
        log("getWanNetworks returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<LanNetwork> getLanNetworks(String siteId) throws IOException
    {
        if (stringNullOrEmpty(siteId)) throw new NullPointerException("siteId must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("lannetworks");
        url = url.replaceFirst("%s", this.tenantId);
        url = url.replaceFirst("%s", siteId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getLanNetworks no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getLanNetworks failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<LanNetwork>>(){}.getType();
        ResourceResponse<LanNetwork> data = gson.fromJson(resp.responseBody, type);
        
        log("getLanNetworks returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<ApplicationDefinition> getApplicationDefinitions() throws IOException
    {
        String url = this.endpoint + this.endpoints.getEndpoint("appdefs");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getApplicationDefinitions no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getApplicationDefinitions failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<ApplicationDefinition>>(){}.getType();
        ResourceResponse<ApplicationDefinition> data = gson.fromJson(resp.responseBody, type);
        
        log("getApplicationDefinitions returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<PolicySet> getPolicySets() throws IOException
    {
        String url = this.endpoint + this.endpoints.getEndpoint("policysets");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getPolicySets no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getPolicySets failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<PolicySet>>(){}.getType();
        ResourceResponse<PolicySet> data = gson.fromJson(resp.responseBody, type);
        
        log("getPolicySets returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<PolicyRule> getPolicyRules(String policySetId) throws IOException
    {
        if (stringNullOrEmpty(policySetId)) throw new NullPointerException("policySetId must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("policyrules");
        url = url.replaceFirst("%s", this.tenantId);
        url = url.replaceFirst("%s", policySetId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getPolicyRules no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getPolicyRules failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<PolicyRule>>(){}.getType();
        ResourceResponse<PolicyRule> data = gson.fromJson(resp.responseBody, type);
        
        log("getPolicyRules returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<SecurityZone> getSecurityZones() throws IOException
    {
        String url = this.endpoint + this.endpoints.getEndpoint("securityzones");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getSecurityZones no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getSecurityZones failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<SecurityZone>>(){}.getType();
        ResourceResponse<SecurityZone> data = gson.fromJson(resp.responseBody, type);
        
        log("getSecurityZones returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<SiteSecurityZone> getSiteSecurityZones(String siteId) throws IOException
    {
        if (stringNullOrEmpty(siteId)) throw new NullPointerException("siteId must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("sitesecurityzones");
        url = url.replaceFirst("%s", this.tenantId);
        url = url.replaceFirst("%s", siteId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getSiteSecurityZones no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getSiteSecurityZones failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<SiteSecurityZone>>(){}.getType();
        ResourceResponse<SiteSecurityZone> data = gson.fromJson(resp.responseBody, type);
        
        log("getSiteSecurityZones returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<SecurityPolicySet> getSecurityPolicySets() throws IOException
    {
        String url = this.endpoint + this.endpoints.getEndpoint("securitypolicysets");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getSecurityPolicySets no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getSecurityPolicySets failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<SecurityPolicySet>>(){}.getType();
        ResourceResponse<SecurityPolicySet> data = gson.fromJson(resp.responseBody, type);
        
        log("getSecurityPolicySets returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<SecurityPolicyRule> getSecurityPolicyRules(String secPolicySetId) throws IOException
    {
        if (stringNullOrEmpty(secPolicySetId)) throw new NullPointerException("secPolicySetId must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("securitypolicyrules");
        url = url.replaceFirst("%s", this.tenantId);
        url = url.replaceFirst("%s", secPolicySetId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getSecurityPolicyRules no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getSecurityPolicyRules failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<SecurityPolicyRule>>(){}.getType();
        ResourceResponse<SecurityPolicyRule> data = gson.fromJson(resp.responseBody, type);
        
        log("getSecurityPolicyRules returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public List<SiteWanInterface> getSiteWanInterfaces(String siteId) throws IOException
    {
        if (stringNullOrEmpty(siteId)) throw new NullPointerException("siteId must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("waninterfaces");
        url = url.replaceFirst("%s", this.tenantId);
        url = url.replaceFirst("%s", siteId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getSiteWanInterfaces no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getSiteWanInterfaces failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<SiteWanInterface>>(){}.getType();
        ResourceResponse<SiteWanInterface> data = gson.fromJson(resp.responseBody, type);
        
        log("getSiteWanInterfaces returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public Topology getSiteTopology(String siteId) throws IOException
    {
        if (stringNullOrEmpty(siteId)) throw new NullPointerException("siteId must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("topology");
        url = url.replaceFirst("%s", this.tenantId);
        url = url.replaceFirst("%s", siteId);
        
        HashMap<String, Object> body = new HashMap<String, Object>();
        body.put("type", "basenet");
        
        List<String> nodes = new ArrayList<String>();
        nodes.add(siteId);
        body.put("nodes", nodes);
        
        RestResponse resp = RestRequest.Send(
            url,
            "POST",
            "application/json",
            this.authHeaders,
            serialize(body),
            this.debug);
        
        if (resp == null) 
        {
            log("getSiteTopology no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getSiteTopology failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson();  
        Topology data = gson.fromJson(resp.responseBody, Topology.class); 
        return data;
    }
    
    public List<SnmpAgent> getSnmpAgents(String siteId, String elementId) throws IOException
    {
        if (stringNullOrEmpty(siteId)) throw new NullPointerException("siteId must not be null");
        if (stringNullOrEmpty(elementId)) throw new NullPointerException("elementId must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("snmpagents");
        url = url.replaceFirst("%s", this.tenantId);
        url = url.replaceFirst("%s", siteId);
        url = url.replaceFirst("%s", elementId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getSnmpAgents no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getSnmpAgents failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson(); 
        java.lang.reflect.Type type = new TypeToken<ResourceResponse<SnmpAgent>>(){}.getType();
        ResourceResponse<SnmpAgent> data = gson.fromJson(resp.responseBody, type);
        
        log("getSnmpAgents returning " + data.items.size() + " object(s)");
        return data.items;
    }
    
    public MetricsResponse getMetrics(MetricsQuery query) throws IOException
    {
        if (query == null) throw new NullPointerException("query must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("metrics_monitor");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "POST",
            "application/json",
            this.authHeaders,
            serialize(query),
            this.debug);
        
        if (resp == null) 
        {
            log("getMetrics no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getMetrics failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson();  
        MetricsResponse data = gson.fromJson(resp.responseBody, MetricsResponse.class); 
        return data;
    }
    
    public EventResponse getEvents(EventQuery query) throws IOException
    {
        if (query == null) throw new NullPointerException("query must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("query_events");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "POST",
            "application/json",
            this.authHeaders,
            serialize(query),
            this.debug);
        
        if (resp == null) 
        {
            log("getEvents no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getEvents failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson();  
        EventResponse data = gson.fromJson(resp.responseBody, EventResponse.class); 
        return data;
    }
    
    public TopNResponse getTopN(TopNQuery query) throws IOException
    {
        if (query == null) throw new NullPointerException("query must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("topn_monitor");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "POST",
            "application/json",
            this.authHeaders,
            serialize(query),
            this.debug);
        
        if (resp == null) 
        {
            log("getTopN no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getTopN failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson();  
        TopNResponse data = gson.fromJson(resp.responseBody, TopNResponse.class); 
        return data;
    }
    
    public FlowResponse getFlows(FlowQuery query) throws IOException
    {
        if (query == null) throw new NullPointerException("query must not be null");
        
        String url = this.endpoint + this.endpoints.getEndpoint("flows_monitor");
        url = url.replaceFirst("%s", this.tenantId);
        
        RestResponse resp = RestRequest.Send(
            url,
            "POST",
            "application/json",
            this.authHeaders,
            serialize(query),
            this.debug);
        
        if (resp == null) 
        {
            log("getFlows no response from server");
            return null;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getFlows failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return null;
        }
        
        Gson gson = new Gson();  
        FlowResponse data = gson.fromJson(resp.responseBody, FlowResponse.class); 
        return data;
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Private Methods">
    
    private void log(String s)
    {
        if (this.debug) System.out.println(s);
    }
    
    private Boolean stringNullOrEmpty(String s) 
    {
        if (s == null) return true;
        return s.isEmpty();
    }
    
    private String serialize(Object o) 
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(o);    
        return json;
    }
    
    private Boolean loginInternal() throws IOException 
    {
        HashMap<String, String> loginRequest = new HashMap<String, String>();
        loginRequest.put("email", this.email);
        loginRequest.put("password", this.password);
        
        RestResponse resp = RestRequest.Send(
            this.endpoint + endpoints.getEndpoint("login"), 
            "POST",
            "application/json",
            null,
            serialize(loginRequest),
            this.debug);
        
        if (resp == null) 
        {
            log("login no response from server");
            return false;
        }
        
        if (resp.statusCode > 299) 
        {
            log("login failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return false;
        }
        
        Gson gson = new Gson(); 
        LoginResponse loginResp = gson.fromJson(resp.responseBody, LoginResponse.class);
        this.authToken = loginResp.x_auth_token;
        
        this.authHeaders = new HashMap<String, String>();
        this.authHeaders.put("x-auth-token", this.authToken);
        
        return true;
    }
    
    private Boolean getProfile() throws IOException
    {
        RestResponse resp = RestRequest.Send(
            this.endpoint + endpoints.getEndpoint("profile"), 
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getProfile no response from server");
            return false;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getProfile failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return false;
        }
        
        Gson gson = new Gson(); 
        ProfileResponse profileResp = gson.fromJson(resp.responseBody, ProfileResponse.class);
        this.tenantId = profileResp.tenant_id;
        
        return true;
    }
    
    private Boolean getEndpoints() throws IOException
    {
        RestResponse resp = RestRequest.Send(
            this.endpoint + endpoints.getEndpoint("permissions"), 
            "GET",
            "application/json",
            this.authHeaders,
            null,
            this.debug);
        
        if (resp == null) 
        {
            log("getEndpoints no response from server");
            return false;
        }
        
        if (resp.statusCode > 299) 
        {
            log("getEndpoints failure status " + resp.statusCode + " returned from server");
            log(serialize(resp));
            return false;
        }
        
        Gson gson = new Gson(); 
        EndpointResponse endpointResp = gson.fromJson(resp.responseBody, EndpointResponse.class);
        
        if (endpointResp.resource_version_map != null && endpointResp.resource_version_map.size() > 0) 
        {
            for (Map.Entry<String, String> curr : endpointResp.resource_version_map.entrySet()) 
            {
                this.endpoints.addVersion(curr.getKey(), curr.getValue());
            }
        } 

        if (endpointResp.resource_uri_map != null && endpointResp.resource_uri_map.size() > 0) 
        {
            for (Map.Entry<String, String> curr : endpointResp.resource_uri_map.entrySet()) 
            {
                this.endpoints.addEndpoint(curr.getKey(), curr.getValue());
            }
        } 
 
        return true;
    }
    
    // </editor-fold>
}
