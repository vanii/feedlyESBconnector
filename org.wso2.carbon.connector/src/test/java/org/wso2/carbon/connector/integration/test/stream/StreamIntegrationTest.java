package org.wso2.carbon.connector.integration.test.stream;

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
public class StreamIntegrationTest extends FeedlyConnectorIntegrationTest {

    /**
     * Negative test case for getContentOfStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 46, description = "Feedly {getContentOfStreamNegative} integration test.")
    public void testGetContentOfStreamNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getContentOfStream.txt";
        String methodName = "feedly_getContentOfStream";

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

    /**
     * test case for getContentOfStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 45, description = "Feedly {getContentOfStream} integration test.")
    public void testGetContentOfStream() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getContentOfStream.txt";
        String methodName = "feedly_getContentOfStream";

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
     * Optional test case for getContentOfStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 47, description = "Feedly {getContentOfStreamOptional} integration test.")
    public void testGetContentOfStreamOptional() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getContentOfStreamOptional.txt";
        String methodName = "feedly_getContentOfStream";

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
     * test case for getListOfEntriesInStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 48, description = "Feedly {getListOfEntriesInStream} integration test.")
    public void testGetListOfEntriesInStream() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListOfEntriesInStream.txt";
        String methodName = "feedly_getListOfEntriesInStream";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response.has("ids"));
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for getListOfEntriesInStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 49, description = "Feedly {getListOfEntriesInStreamNegative} integration test.")
    public void testGetListOfEntriesInStreamNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListOfEntriesInStream.txt";
        String methodName = "feedly_getListOfEntriesInStream";

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

    /**
     * Optional test case for getListOfEntriesInStream method.
     */
    @Test(groups = { "wso2.esb" },priority = 50, description = "Feedly {getListOfEntriesInStreamOptional} integration test.")
    public void testGetListOfEntriesInStreamOptional() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListOfEntriesInStreamOptional.txt";
        String methodName = "feedly_getListOfEntriesInStream";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response.has("ids"));
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

}
