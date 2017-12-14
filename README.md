
# CloudGenix Controller SDK in Java
Java software development kit and test application for the CloudGenix Controller.

## Help or Feedback
For issues, please contact joel@cloudgenix.com.

## New
- Initial release
- Authentication, profile retrieval, and dynamic URL building (including API version) with override support
- Includes GET APIs for tenant, elements, sites, interfaces, WANs, LANs, application definitions, policy sets, policy rules, security zones, security policy sets, and security policy rules
- Includes POST APIs to retrieve metrics data, top N data, and flow records
- Basic API infrastructure and plumbing

## Outstanding Items
- Several classes contain members with generic types, which will require casting prior to use in consuming code.  This can be fixed with additional details on the object model from engineering
- Parameters in query bodies are currently strings when they should be enumerations
- Failed APIs return null given Java's lack of support for out and ref parameters without serious Java-foo/code gymnastics/ugly methods.  A more appropriate design would mirror those used by the C# SDK

## Quickstart
Refer to the Test project for a full examination of consuming the SDK.  The SDK can be initialized and instantiated rather quickly:
```
import CloudGenix.*;
...
CloudGenixSdk sdk = new CloudGenixSdk("demo@cloudgenix.com", "demo@cloudgenix.com", true); 
System.out.println("Tenant ID  : " + sdk.tenantId);
System.out.println("Auth token : " + sdk.authToken);

// From here, you can use the SDK APIs.  All APIs are of the form:
List<Site> sites = sdk.getSites();
if (sites == null) { // error } else { // handle data }
```
 
## Version History
Notes from previous versions (starting with v1.0.0) will be moved here.

v1.0.0
- Initial release