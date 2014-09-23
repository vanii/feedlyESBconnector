package org.wso2.carbon.connector.integration.test.feeds;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.connector.integration.test.common.ConnectorIntegrationUtil;
import org.wso2.carbon.connector.integration.test.feedly.FeedlyConnectorIntegrationTest;

import javax.activation.DataHandler;
import java.net.URL;

/**
 * Created by zamly-PC on 9/22/14.
 */
public class FeedsIntegrationTest extends FeedlyConnectorIntegrationTest {

    /**
     * test case for getFeedMetadata method.
     */
    @Test(groups = { "wso2.esb" },priority = 70, description = "Feedly {getFeedMetadata} integration test.")
    public void testGetFeedMetadata() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getFeedMetadata.txt";
        String methodName = "feedly_getFeedMetadata";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("feedId"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            //int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(object.has("id"));
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for getFeedMetadata method.
     */
    @Test(groups = { "wso2.esb" },priority = 71, description = "Feedly {getFeedMetadataNegative} integration test.")
    public void testGetFeedMetadataNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getFeedMetadata.txt";
        String methodName = "feedly_getFeedMetadata";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                ":feedID");

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            //int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(object.length() == 0);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for getMetadataForListOfFeeds method.
     */
    @Test(groups = { "wso2.esb" },priority = 72, description = "Feedly {getMetadataForListOfFeeds} integration test.")
    public void testGetMetadataForListOfFeeds() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getMetadataForListOfFeeds.txt";
        String methodName = "feedly_getMetadataForListOfFeeds";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("feedId"),
                feedlyConnectorProperties.getProperty("feedIdTwo"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            //int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(object.length() > 0);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for getMetadataForListOfFeeds method.
     */
    @Test(groups = { "wso2.esb" },priority = 73, description = "Feedly {getMetadataForListOfFeedsNegative} integration test.")
    public void testGetMetadataForListOfFeedsNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getMetadataForListOfFeeds.txt";
        String methodName = "feedly_getMetadataForListOfFeeds";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                "asdfas",
                "fefad");

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            //int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(object.length() == 0);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

}
