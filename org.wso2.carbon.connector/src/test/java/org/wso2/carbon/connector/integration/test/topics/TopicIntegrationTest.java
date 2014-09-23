package org.wso2.carbon.connector.integration.test.topics;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.connector.integration.test.common.ConnectorIntegrationUtil;
import org.wso2.carbon.connector.integration.test.feedly.FeedlyConnectorIntegrationTest;

import javax.activation.DataHandler;
import java.net.URL;

/**
 * Created by zamly-PC on 9/22/14.
 */
public class TopicIntegrationTest extends FeedlyConnectorIntegrationTest {

    /**
     * test case for getListTopics method.
     */
    @Test(groups = { "wso2.esb" },priority = 62, description = "Feedly {getListTopics} integration test.")
    public void testGetListTopics() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListTopics.txt";
        String methodName = "feedly_getListTopics";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(object.length() > 0);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for getListTopics method.
     */
    @Test(groups = { "wso2.esb" },priority = 63, description = "Feedly {getListTopicsNegative} integration test.")
    public void testGetListTopicsNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListTopics.txt";
        String methodName = "feedly_getListTopics";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessTokenNegative"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for addTopic method.
     */
    @Test(groups = { "wso2.esb" },priority = 64, description = "Feedly {addTopic} integration test.")
    public void testAddTopic() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_addTopic.txt";
        String methodName = "feedly_addTopic";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("topicId"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for addTopic method.
     */
    @Test(groups = { "wso2.esb" },priority = 65, description = "Feedly {addTopicNegative} integration test.")
    public void testAddTopicNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_addTopic.txt";
        String methodName = "feedly_addTopic";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessTokenNegative"),
                feedlyConnectorProperties.getProperty("topicId"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for updateTopic method.
     */
    @Test(groups = { "wso2.esb" },priority = 66, description = "Feedly {updateTopic} integration test.")
    public void testUpdateTopic() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateTopic.txt";
        String methodName = "feedly_updateTopic";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("topicId"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for updateTopic method.
     */
    @Test(groups = { "wso2.esb" },priority = 67, description = "Feedly {updateTopicNegative} integration test.")
    public void testUpdateTopicNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateTopic.txt";
        String methodName = "feedly_updateTopic";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessTokenNegative"),
                feedlyConnectorProperties.getProperty("topicId"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for removeTopic method.
     */
    @Test(groups = { "wso2.esb" },priority = 68, description = "Feedly {removeTopic} integration test.")
    public void testRemoveTopic() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_removeTopic.txt";
        String methodName = "feedly_removeTopic";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("topicId"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for removeTopic method.
     */
    @Test(groups = { "wso2.esb" },priority = 69, description = "Feedly {removeTopicNegative} integration test.")
    public void testRemoveTopicNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_removeTopic.txt";
        String methodName = "feedly_removeTopic";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessTokenNegative"),
                feedlyConnectorProperties.getProperty("topicId"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONArray object = ConnectorIntegrationUtil.sendRequestJSONArray(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

}
