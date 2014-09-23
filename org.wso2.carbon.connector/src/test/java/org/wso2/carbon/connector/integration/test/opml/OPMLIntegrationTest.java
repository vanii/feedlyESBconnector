package org.wso2.carbon.connector.integration.test.opml;

import org.wso2.carbon.connector.integration.test.feedly.FeedlyConnectorIntegrationTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.connector.integration.test.common.ConnectorIntegrationUtil;

import javax.activation.DataHandler;
import java.net.URL;

/**
 * Created by zamly-PC on 9/22/14.
 */
public class OPMLIntegrationTest extends FeedlyConnectorIntegrationTest {

    /**
     * test case for exportOPML method.
     */
    @Test(groups = { "wso2.esb" },priority = 39, description = "Feedly {exportOPML} integration test.")
    public void testExportOPML() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_exportOPML.txt";
        String methodName = "feedly_exportOPML";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for exportOPML method.
     */
    @Test(groups = { "wso2.esb" },priority = 40, description = "Feedly {exportOPMLNegative} integration test.")
    public void testExportOPMLNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_exportOPML.txt";
        String methodName = "feedly_exportOPML";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessTokenNegative"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

}
