package org.wso2.carbon.connector.integration.test.mixes;

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
public class MixesIntegrationTest extends FeedlyConnectorIntegrationTest {


    /**
     * test case for getEngagingContentInCategoryStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 37, description = "Feedly {getEngagingContentInCategoryStream} integration test.")
    public void testGetEngagingContentInCategoryStream() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEngagingContentInCategoryStream.txt";
        String methodName = "feedly_getEngagingContentInCategoryStream";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("categoryIdFour"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response.has("items"));
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for getEngagingContentInCategoryStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 37, description = "Feedly {getEngagingContentInCategoryStreamNegative} integration test.")
    public void testGetEngagingContentInCategoryStreamNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEngagingContentInCategoryStream.txt";
        String methodName = "feedly_getEngagingContentInCategoryStream";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessTokenNegative"),
                feedlyConnectorProperties.getProperty("categoryIdFour"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Optional test case for getEngagingContentInCategoryStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 38, description = "Feedly {getEngagingContentInCategoryStreamOptional} integration test.")
    public void testGetEngagingContentInCategoryStreamOptional() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEngagingContentInCategoryStreamOptional.txt";
        String methodName = "feedly_getEngagingContentInCategoryStream";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("categoryIdFour"),
                feedlyConnectorProperties.getProperty("locale"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response.has("items"));
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Optional test case for getEngagingContentInFeedStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 36, description = "Feedly {getEngagingContentInFeedStreamOptional} integration test.")
    public void testGetEngagingContentInFeedStreamOptional() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEngagingContentInFeedStreamOptional.txt";
        String methodName = "feedly_getEngagingContentInFeedStream";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("feedId"),
                feedlyConnectorProperties.getProperty("locale"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            JSONObject responseO = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);


            System.out.println("\n\n\n\n\n-------*********-------");
            System.out.println("getEngagingContentInFeedStream "+response);
            Assert.assertTrue(responseO.has("id"));
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for getEngagingContentInFeedStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 35, description = "Feedly {getEngagingContentInFeedStream} integration test.")
    public void testGetEngagingContentInFeedStream() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEngagingContentInFeedStream.txt";
        String methodName = "feedly_getEngagingContentInFeedStream";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("feedId"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            JSONObject responseObject = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);


            System.out.println("\n\n\n\n\n-------*********-------");
            System.out.println("getEngagingContentInFeedStream "+response);
            Assert.assertTrue(responseObject.has("id"));
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

}
