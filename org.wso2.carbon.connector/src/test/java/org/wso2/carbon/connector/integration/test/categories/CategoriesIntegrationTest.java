package org.wso2.carbon.connector.integration.test.categories;

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
public class CategoriesIntegrationTest extends FeedlyConnectorIntegrationTest {

    /**
      * test case for getCategories method.
      */
    @Test(groups = { "wso2.esb" },priority = 5, description = "Feedly {getCategories} integration test.")
    public void testGetCategories() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getCategories.txt";
        String methodName = "feedly_getCategories";

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
     * Negative test case for getCategories method.
     */
    @Test(groups = { "wso2.esb" },priority = 5, description = "Feedly {getCategoriesNegative} integration test.")
    public void testGetCategoriesNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getCategories.txt";
        String methodName = "feedly_getCategories";

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
     * test case for deleteCategory method.
     */
    @Test(groups = { "wso2.esb" },priority = 7, description = "Feedly {deleteCategory} integration test.")
    public void testDeleteCategory() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_deleteCategory.txt";
        String methodName = "feedly_deleteCategory";

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
     * Negative test case for deleteCategory method.
     */
    @Test(groups = { "wso2.esb" },priority = 7, description = "Feedly {deleteCategoryNegative} integration test.")
    public void testDeleteCategoryNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_deleteCategory.txt";
        String methodName = "feedly_deleteCategory";

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
     * test case for changeLabelCategory method.
     */
    @Test(groups = { "wso2.esb" },priority = 6, description = "Feedly {changeLabelCategory} integration test.")
    public void testChangeLabelCategory() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_changeLabelCategory.txt";
        String methodName = "feedly_changeLabelCategory";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                "test",
                "test");

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            //JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName), newJsonString);
            //System.out.println(object);
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);

            System.out.println("\n\n\n\n\n-------*********-------");
            System.out.println("response"+responseHeader);
            Assert.assertTrue(responseHeader == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * Negative test case for changeLabelCategory method.
     */
    @Test(groups = { "wso2.esb" },priority = 6, description = "Feedly {changeLabelCategoryNegative} integration test.")
    public void testChangeLabelCategoryNegative() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_changeLabelCategory.txt";
        String methodName = "feedly_changeLabelCategory";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                "global.must",
                "Changed");

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName), newJsonString);
            //System.out.println(object);
            //int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);

            Assert.assertTrue(object.has("errorMessage"));
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

}
