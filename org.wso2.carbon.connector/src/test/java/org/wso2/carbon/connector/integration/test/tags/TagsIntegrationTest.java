package org.wso2.carbon.connector.integration.test.tags;

import org.json.JSONArray;
import org.wso2.carbon.connector.integration.test.feedly.FeedlyConnectorIntegrationTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.connector.integration.test.common.ConnectorIntegrationUtil;

import javax.activation.DataHandler;
import java.net.URL;
/**
 * Created by zamly-PC on 9/22/14.
 */
public class TagsIntegrationTest extends FeedlyConnectorIntegrationTest {

    /**
     * test case for getListOfTags method.
     */
    @Test(groups = { "wso2.esb" },priority = 51, description = "Feedly {getListOfTags} integration test.")
    public void testGetListOfTags() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListOfTags.txt";
        String methodName = "feedly_getListOfTags";

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
     * Negative test case for getListOfTags method.
     */
    @Test(groups = { "wso2.esb" },priority = 52, description = "Feedly {getListOfTagsNegative} integration test.")
    public void testGetListOfTagsNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListOfTags.txt";
        String methodName = "feedly_getListOfTags";

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
     * test case for changeTagLabel method.
     */
    @Test(groups = { "wso2.esb" },priority = 58, description = "Feedly {changeTagLabel} integration test.")
    public void testChangeTagLabel() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_changeTagLabel.txt";
        String methodName = "feedly_changeTagLabel";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("tagId"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for changeTagLabel method.
     */
    @Test(groups = { "wso2.esb" },priority = 59, description = "Feedly {changeTagLabelNegative} integration test.")
    public void testChangeTagLabelNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_changeTagLabel.txt";
        String methodName = "feedly_changeTagLabel";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessTokenNegative"),
                "negative");

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for deleteTags method.
     */
    @Test(groups = { "wso2.esb" },priority = 60, description = "Feedly {deleteTags} integration test.")
    public void testDeleteTags() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_deleteTags.txt";
        String methodName = "feedly_deleteTags";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("tagId"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }


    /**
     * Negative test case for deleteTags method.
     */
    @Test(groups = { "wso2.esb" },priority = 61, description = "Feedly {deleteTagsNegative} integration test.")
    public void testDeleteTagsNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_deleteTags.txt";
        String methodName = "feedly_deleteTags";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessTokenNegative"),
                "Negative");

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for tagMultipleEntries method.
     */
    @Test(groups = { "wso2.esb" },priority = 57, description = "Feedly {tagMultipleEntriesNegative} integration test.")
    public void testTagMultipleEntriesNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_tagMultipleEntries.txt";
        String methodName = "feedly_tagMultipleEntries";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessTokenNegative"),
                feedlyConnectorProperties.getProperty("entryIdOne"),
                feedlyConnectorProperties.getProperty("entryIdTwo"),
                "negative",
                "evitagen");

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }


    /**
     * test case for tagMultipleEntries method.
     */
    @Test(groups = { "wso2.esb" },priority = 56, description = "Feedly {tagMultipleEntries} integration test.")
    public void testTagMultipleEntries() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_tagMultipleEntries.txt";
        String methodName = "feedly_tagMultipleEntries";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("entryIdOne"),
                feedlyConnectorProperties.getProperty("entryIdTwo"),
                feedlyConnectorProperties.getProperty("tagId"),
                feedlyConnectorProperties.getProperty("tagIdTwo"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for tagExistingEntry method.
     */
    @Test(groups = { "wso2.esb" },priority = 54, description = "Feedly {tagExistingEntryNegative} integration test.")
    public void testTagExistingEntryNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_tagExistingEntryNegative.txt";
        String methodName = "feedly_tagExistingEntry";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessTokenNegative"),
                feedlyConnectorProperties.getProperty("entryIdOne"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 401);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for tagExistingEntry method.
     */
    @Test(groups = { "wso2.esb" },priority = 53, description = "Feedly {tagExistingEntry} integration test.")
    public void testTagExistingEntry() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_tagExistingEntry.txt";
        String methodName = "feedly_tagExistingEntry";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("entryIdOne"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName), newJsonString);
            //JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(response == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

}
