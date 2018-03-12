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

// To run:
// java -classpath "lib/*" -jar SamlTest.jar
// 

package samltest;
 
import CloudGenix.*;
import java.util.Scanner;

public class SamlTest 
{
    public static CloudGenixSdk sdk;
    
    public static void main(String[] args) throws Exception 
    {
        Scanner scanner = new Scanner(System. in); 
            
        try
        {    
            System.out.println("CloudGenix Controller SDK"); 
            System.out.print("Email: ");
            String email = scanner.nextLine();

            sdk = new CloudGenixSdk(email, true);

            String samlUrl = sdk.loginSamlStart();
            System.out.println("Copy and paste the following URL in your browser.");
            System.out.println("Press ENTER when done.");
            System.out.println(samlUrl);
            scanner.nextLine();

            if (sdk.loginSamlFinish())
            {
                System.out.println("Login success.");
            }
            else
            {
                System.out.println("Login failure.");
            }
        }
        finally
        {
            System.out.println("Press ENTER to exit.");
            scanner.nextLine();
        }
    } 
}
