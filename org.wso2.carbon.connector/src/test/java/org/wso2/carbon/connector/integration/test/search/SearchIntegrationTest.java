package org.wso2.carbon.connector.integration.test.search;

import org.json.JSONObject;
import org.wso2.carbon.connector.integration.test.feedly.FeedlyConnectorIntegrationTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.connector.integration.test.common.ConnectorIntegrationUtil;

import javax.activation.DataHandler;
import java.net.URL;

/**
 * Created by zamly-PC on 9/22/14.
 */
public class SearchIntegrationTest extends FeedlyConnectorIntegrationTest {


    /**
     * test case for searchFeeds method.
     */
    @Test(groups = { "wso2.esb" },priority = 15, description = "Feedly {searchFeeds} integration test.")
    public void testSearchFeeds() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_searchFeeds.txt";
        String methodName = "feedly_searchFeeds";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("q"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(response.has("results"));
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for searchFeeds method.
     */
    @Test(groups = { "wso2.esb" },priority = 16, description = "Feedly {searchFeedsNegative} integration test.")
    public void testSearchFeedsNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_searchFeeds.txt";
        String methodName = "feedly_searchFeeds";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, "");

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(response == 400);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Optional test case for searchFeeds method.
     */
    @Test(groups = { "wso2.esb" },priority = 17, description = "Feedly {searchFeedsNegativeOptional} integration test.")
    public void testSearchFeedsNegativeOptional() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_searchFeedsOptional.txt";
        String methodName = "feedly_searchFeeds";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("q"),
                feedlyConnectorProperties.getProperty("locale"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(response == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

}
