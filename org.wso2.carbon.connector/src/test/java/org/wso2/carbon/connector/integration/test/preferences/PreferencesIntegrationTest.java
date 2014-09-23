package org.wso2.carbon.connector.integration.test.preferences;

import org.wso2.carbon.connector.integration.test.feedly.FeedlyConnectorIntegrationTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.connector.integration.test.common.ConnectorIntegrationUtil;

import javax.activation.DataHandler;
import java.net.URL;

/**
 * Created by zamly-PC on 9/22/14.
 */
public class PreferencesIntegrationTest extends FeedlyConnectorIntegrationTest {

    /**
     * test case for getPreference method.
     */
    @Test(groups = { "wso2.esb" },priority = 41, description = "Feedly {getPreference} integration test.")
    public void testGetPreference() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getPreference.txt";
        String methodName = "feedly_getPreference";

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
     * Negative test case for getPreference method.
     */
    @Test(groups = { "wso2.esb" },priority = 42, description = "Feedly {getPreferenceNegative} integration test.")
    public void testGetPreferenceNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getPreference.txt";
        String methodName = "feedly_getPreference";

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
     * test case for updatePreference method.
     */
    @Test(groups = { "wso2.esb" },priority = 43, description = "Feedly {updatePreference} integration test.")
    public void testUpdatePreference() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updatePreference.txt";
        String methodName = "feedly_updatePreference";

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
     * Negative test case for updatePreference method.
     */
    @Test(groups = { "wso2.esb" },priority = 44, description = "Feedly {updatePreferenceNegative} integration test.")
    public void testUpdatePreferenceNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updatePreference.txt";
        String methodName = "feedly_updatePreference";

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
