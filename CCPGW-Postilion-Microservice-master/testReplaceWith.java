/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testReplaceWith {
    
    
    private String getMainURL(String urlIn){
        if (urlIn.indexOf("https://") == 0){
            String newUrl = urlIn.substring("https://".length(),urlIn.length());
            return "https://"+newUrl.substring(0,newUrl.indexOf('/'));
        }
        if (urlIn.indexOf("http://") == 0){
            String newUrl = urlIn.substring("http://".length(),urlIn.length());
            return "http://"+newUrl.substring(0,newUrl.indexOf('/'));
        }
        return urlIn;
    }
    
    private String insertMainUrl(String netForm, String threeDSRequestorURL){
        if (netForm.contains("src='/")){
            String challengeForm = netForm.replaceAll("src='/","src='"+getMainURL(threeDSRequestorURL)+"/");
            return challengeForm;
        }
        else if (netForm.contains("src=\"/")){
           String challengeForm = netForm.replaceAll("src=\"/","src=\""+getMainURL(threeDSRequestorURL)+"/");
           return challengeForm;            
        }
        return netForm;
    }
        
    public static void main(String[] args) {
        
        testReplaceWith test = new testReplaceWith();
        
        String threeDSRequestorURL = "https://geoissuer.cardinalcommerce.com/DeviceFingerprintWeb/V2/Browser/RenderMethodURL?id=5e84b10b260f434e93b8688f";
        
        System.out.println(test.getMainURL(threeDSRequestorURL));
        
        
        String NetForm = "<html>  <head>                  <script type=\"text/javascript\" src=\"https://h.online-metrix.net/fp/tags.js?org_id=01zzvc40&session_id=46b04c65-9d94-4543-8a84-85a0d87cf6d5\"></script>              <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>    <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/json3/3.3.2/json3.min.js'></script>    <script type='text/javascript' src='/DeviceFingerprintWeb/includes/js/6d028a6f5c5c7a8f8dbd924b0fc274afbf37412e.min.js'></script>    <script type='text/javascript' src='/DeviceFingerprintWeb/includes/js/profiler.min.js'></script>  </head>  <body>    <input type=\"hidden\" id=\"base64payload\" name=\"base64payload\" value=\"eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjQ2YjA0YzY1LTlkOTQtNDU0My04YTg0LTg1YTBkODdjZjZkNSJ9\" />    <input type=\"hidden\" id=\"deviceChannel\" name=\"deviceChannel\" value=\"Browser\" />    <input type=\"hidden\" id=\"domain\" name=\"domain\" value=\"ISSUER\" />    <input type=\"hidden\" id=\"environment\" name=\"environment\" value=\"PROD\" />    <input type=\"hidden\" id=\"notificationUrl\" name=\"notificationUrl\" value=\"https://ccpgw.bsppay.com.pg/3dsmethodnotification\" />    <input type=\"hidden\" id=\"orgUnitId\" name=\"orgUnitId\" value=\"5e84b10b260f434e93b8688f\" />    <input type=\"hidden\" id=\"referenceId\" name=\"referenceId\" value=\"46b04c65-9d94-4543-8a84-85a0d87cf6d5\" />    <input type=\"hidden\" id=\"threatMetrixEnabled\" name=\"threatMetrixEnabled\" value=\"true\" />    <input type=\"hidden\" id=\"threatMetrixDelay\" name=\"threatMetrixDelay\" value=\"1500\" />    <input type=\"hidden\" id=\"threatMetrixUrl\" name=\"threatMetrixUrl\" value=\"https://h.online-metrix.net/fp/tags.js?org_id=01zzvc40&session_id=46b04c65-9d94-4543-8a84-85a0d87cf6d5\" />    <input type=\"hidden\" id=\"threeDSServerTransId\" name=\"threeDSServerTransId\" value=\"46b04c65-9d94-4543-8a84-85a0d87cf6d5\" />    <input type=\"hidden\" id=\"tmEventType\" name=\"tmEventType\" value=\"PAYMENT\" />    <input type=\"hidden\" id=\"methodUrlLazyLoadingEnabled\" name=\"methodUrlLazyLoadingEnabled\" value=\"false\" />    <input type=\"hidden\" id=\"callSignEnabled\" name=\"callSignEnabled\" value=\"false\" />    <input type=\"hidden\" id=\"callSignPromiseExpiryTimeMs\" name=\"callSignPromiseExpiryTimeMs\" value=\"2000\" />    <input type=\"hidden\" id=\"callSignTriggerTimeMs\" name=\"callSignTriggerTimeMs\" value=\"1500\" />    <input type=\"hidden\" id=\"callSignOrgUnit\" name=\"callSignOrgUnit\" value=\"org-ms5faqj4-9vrbajm6-ybytw3qmm-3408wf38\" />    <input type=\"hidden\" id=\"fraudNetId\" name=\"fraudNetId\" value=\"null\" />    <input type=\"hidden\" id=\"fraudNetSessionId\" name=\"fraudNetSessionId\" value=\"null\" />    <input type=\"hidden\" id=\"fraudNetEnabled\" name=\"fraudNetEnabled\" value=\"false\" />    <input type=\"hidden\" id=\"performanceTrackingEnabled\" name=\"performanceTrackingEnabled\" value=\"false\" />    <input type=\"hidden\" id=\"performanceDataLogUrl\" name=\"performanceDataLogUrl\" value=\"https://fhk3tys8z6.execute-api.us-east-1.amazonaws.com/prod/methodUrlLogToKinesis\" />        <script type=\"text/javascript\">      $(function() {        try {          var profileConfigurations = {            referenceId: document.getElementById(\"referenceId\").value,            orgUnitId: document.getElementById(\"orgUnitId\").value,            features: {              cardinalDataCollection: true,              geolocation: false,              notificationUrlData: {                base64Payload: document.getElementById(\"base64payload\").value,                notificationUrl: document.getElementById(\"notificationUrl\").value,              },              threatMetrix: {                enabled: JSON.parse(document.getElementById(\"threatMetrixEnabled\").value),                eventType: document.getElementById(\"tmEventType\").value,                delay: document.getElementById(\"threatMetrixDelay\").value,                url: document.getElementById(\"threatMetrixUrl\").value,              },              callSign: {                enabled: JSON.parse(document.getElementById(\"callSignEnabled\").value),                callSignPromiseExpiryTimeMs: document.getElementById(\"callSignPromiseExpiryTimeMs\").value,                callSignTriggerTimeMs: document.getElementById(\"callSignTriggerTimeMs\").value              },              fraudNet: {                enabled: JSON.parse(document.getElementById(\"fraudNetEnabled\").value),                delay: document.getElementById(\"threatMetrixDelay\").value,                fraudNetId: document.getElementById(\"fraudNetId\").value,                fraudNetSessionId: document.getElementById(\"fraudNetSessionId\").value              }            },            deviceChannel: document.getElementById(\"deviceChannel\").value,            domain: document.getElementById(\"domain\").value,            environment: document.getElementById(\"environment\").value,            threeDSServerTransId: document.getElementById(\"threeDSServerTransId\").value,            performanceTrackingEnabled: JSON.parse(document.getElementById(\"performanceTrackingEnabled\").value),            performanceDataLogUrl: document.getElementById(\"performanceDataLogUrl\").value,            methodUrlLazyLoadingEnabled: JSON.parse(document.getElementById(\"methodUrlLazyLoadingEnabled\").value)          };          profiler.start(profileConfigurations);        } catch (error) {          try {            var log = new Logger({              domain: document.getElementById(\"domain\").value,              environment: document.getElementById(\"environment\").value,              sessionId: document.getElementById(\"referenceId\").value,            });            log.error('Failed while attempting to run profiler: ' + error);          } catch (e) { /* eat exception if we can't log */ }        }      });    </script>          </body></html>\n" +
"2022-10-12 22:40:53 INFO  ThreeDSV2ResultsResponse:78 -Success <html>";
        
        
        String challengeForm = test.insertMainUrl(NetForm, threeDSRequestorURL);
        
        System.out.println("Updated form: "+challengeForm);

    }
}

