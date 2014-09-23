package org.wso2.carbon.connector.integration.test.subscription;

import org.wso2.carbon.connector.integration.test.feedly.FeedlyConnectorIntegrationTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.connector.integration.test.common.ConnectorIntegrationUtil;

import javax.activation.DataHandler;
import java.net.URL;

/**
 * Created by zamly-PC on 9/22/14.
 */
public class SubscriptionIntegrationTest extends FeedlyConnectorIntegrationTest {


    /**
     * test case for getUserSubscription method.
     */
    @Test(groups = { "wso2.esb" },priority = 8, description = "Feedly {getUserSubscription} integration test.")
    public void testGetUserSubscription() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getUserSubscription.txt";
        String methodName = "feedly_getUserSubscription";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(responseHeader == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for getUserSubscription method.
     */
    @Test(groups = { "wso2.esb" },priority = 9, description = "Feedly {getUserSubscriptionNegative} integration test.")
    public void testGetUserSubscriptionNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getUserSubscription.txt";
        String methodName = "feedly_getUserSubscription";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessTokenNegative"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(responseHeader == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for feedSubscribe method.
     */
    @Test(groups = { "wso2.esb" },priority = 10, description = "Feedly {feedSubscribe} integration test.")
    public void testFeedSubscribe() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_feedSubscribe.txt";
        String methodName = "feedly_feedSubscribe";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(responseHeader == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for feedSubscribe method.
     */
    @Test(groups = { "wso2.esb" },priority = 11, description = "Feedly {feedSubscribeNegative} integration test.")
    public void testFeedSubscribeNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_feedSubscribe.txt";
        String methodName = "feedly_feedSubscribe";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessTokenNegative"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(responseHeader == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for feedUnsubscribe method.
     */
    @Test(groups = { "wso2.esb" },priority = 12, description = "Feedly {feedUnsubscribe} integration test.")
    public void testFeedUnsubscribe() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_feedUnsubscribe.txt";
        String methodName = "feedly_feedUnsubscribe";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(responseHeader == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for feedUnsubscribe method.
     */
    @Test(groups = { "wso2.esb" },priority = 13, description = "Feedly {feedUnsubscribeNegative} integration test.")
    public void testFeedUnsubscribeNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_feedUnsubscribe.txt";
        String methodName = "feedly_feedUnsubscribe";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessTokenNegative"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(responseHeader == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for updateSubscription method.
     */
    @Test(groups = { "wso2.esb" },priority = 14, description = "Feedly {updateSubscription} integration test.")
    public void testUpdateSubscription() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateSubscription.txt";
        String methodName = "feedly_updateSubscription";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(responseHeader == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }


}
