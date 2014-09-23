package org.wso2.carbon.connector.integration.test.profile;

import org.wso2.carbon.connector.integration.test.feedly.FeedlyConnectorIntegrationTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.connector.integration.test.common.ConnectorIntegrationUtil;

import javax.activation.DataHandler;
import java.net.URL;

/**
 * Created by zamly-PC on 9/22/14.
 */
public class ProfileIntegrationTest extends FeedlyConnectorIntegrationTest {


    /**
     * test case for getProfile method.
     */
    @Test(groups = { "wso2.esb" },priority = 1, description = "Feedly {getProfile} integration test.")
    public void testGetProfile() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getProfile.txt";
        String methodName = "feedly_getProfile";

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
     * Negative test case for getProfile method.
     */
    @Test(groups = { "wso2.esb" },priority = 2, description = "Feedly {getProfileNegative} integration test.")
    public void testGetProfileNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getProfile.txt";
        String methodName = "feedly_getProfile";

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
     * test case for updateProfile method.
     */
    @Test(groups = { "wso2.esb" },priority = 3, description = "Feedly {updateProfile} integration test.")
    public void testUpdateProfile() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateProfile.txt";
        String methodName = "feedly_updateProfile";

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
     * Negative test case for updateProfile method.
     */
    @Test(groups = { "wso2.esb" },priority = 4, description = "Feedly {updateProfileNegative} integration test.")
    public void testUpdateProfileNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateProfile.txt";
        String methodName = "feedly_updateProfile";

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
     * Optional test case for updateProfile method.
     */
    @Test(groups = { "wso2.esb" },priority = 4, description = "Feedly {updateProfileOptional} integration test.")
    public void testUpdateProfileOptional() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateProfileOptional.txt";
        String methodName = "feedly_updateProfile";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessTokens"));

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
