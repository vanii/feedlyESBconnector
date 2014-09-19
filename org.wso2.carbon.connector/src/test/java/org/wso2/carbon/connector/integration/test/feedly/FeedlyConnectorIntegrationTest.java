package org.wso2.carbon.connector.integration.test.feedly;

import netscape.javascript.JSObject;
import org.apache.axis2.context.ConfigurationContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.automation.api.clients.proxy.admin.ProxyServiceAdminClient;
import org.wso2.carbon.automation.api.clients.utils.AuthenticateStub;
import org.wso2.carbon.automation.utils.axis2client.ConfigurationContextProvider;
import org.wso2.carbon.connector.integration.test.common.ConnectorIntegrationUtil;
import org.wso2.carbon.esb.ESBIntegrationTest;
import org.wso2.carbon.mediation.library.stub.MediationLibraryAdminServiceStub;
import org.wso2.carbon.mediation.library.stub.upload.MediationLibraryUploaderStub;

import javax.activation.DataHandler;
import java.net.URL;
import java.util.Properties;

/**
 * Created by zamly-PC on 9/5/14.
 */
public class FeedlyConnectorIntegrationTest extends ESBIntegrationTest {


    private static final String CONNECTOR_NAME = "feedly";

    private ProxyServiceAdminClient proxyAdmin;

    private String pathToProxiesDirectory = null;

    private String pathToRequestsDirectory = null;

    private Properties feedlyConnectorProperties = null;

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {

        super.init();

        ConfigurationContextProvider configurationContextProvider = ConfigurationContextProvider.getInstance();
        ConfigurationContext cc = configurationContextProvider.getConfigurationContext();
        MediationLibraryUploaderStub mediationLibUploadStub = new MediationLibraryUploaderStub(cc, esbServer.getBackEndUrl() + "MediationLibraryUploader");
        AuthenticateStub.authenticateStub("admin", "admin", mediationLibUploadStub);

        MediationLibraryAdminServiceStub adminServiceStub = new MediationLibraryAdminServiceStub(cc, esbServer.getBackEndUrl() + "MediationLibraryAdminService");

        AuthenticateStub.authenticateStub("admin", "admin", adminServiceStub);

        String repoLocation = null;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            repoLocation = System.getProperty("connector_repo").replace("/", "\\");
        } else {
            repoLocation = System.getProperty("connector_repo").replace("/", "/");
        }
        proxyAdmin = new ProxyServiceAdminClient(esbServer.getBackEndUrl(), esbServer.getSessionCookie());

        String feedlyConnectorFileName = CONNECTOR_NAME + ".zip";
        ConnectorIntegrationUtil.uploadConnector(repoLocation, mediationLibUploadStub, feedlyConnectorFileName);
        log.info("Sleeping for " + 60000 / 1000 + " seconds while waiting for synapse import");
        Thread.sleep(60000);
        adminServiceStub.updateStatus("{org.wso2.carbon.connector}" + CONNECTOR_NAME, CONNECTOR_NAME,
                "org.wso2.carbon.connector", "enabled");

        feedlyConnectorProperties = ConnectorIntegrationUtil.getConnectorConfigProperties(CONNECTOR_NAME);

        pathToProxiesDirectory = repoLocation + feedlyConnectorProperties.getProperty("proxyDirectoryRelativePath");
        pathToRequestsDirectory = repoLocation + feedlyConnectorProperties.getProperty("requestDirectoryRelativePath");
    }

    @Override
    protected void cleanup() {
        axis2Client.destroy();
    }
//
//    /**
//     * test case for getProfile method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 1, description = "Feedly {getProfile} integration test.")
//    public void testGetProfile() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getProfile.txt";
//        String methodName = "feedly_getProfile";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for getProfile method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 1, description = "Feedly {getProfileNegative} integration test.")
//    public void testGetProfileNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getProfile.txt";
//        String methodName = "feedly_getProfile";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessTokenNegative"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 401);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//
//    /**
//     * test case for updateProfile method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 2, description = "Feedly {updateProfile} integration test.")
//    public void testUpdateProfile() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateProfile.txt";
//        String methodName = "feedly_updateProfile";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for updateProfile method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 2, description = "Feedly {updateProfileNegative} integration test.")
//    public void testUpdateProfileNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateProfile.txt";
//        String methodName = "feedly_updateProfile";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessTokenNegative"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 401);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//
//    /**
//     * test case for getCategories method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 3, description = "Feedly {getCategories} integration test.")
//    public void testGetCategories() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getCategories.txt";
//        String methodName = "feedly_getCategories";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//
//    /**
//     * test case for changeLabelCategory method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 0, description = "Feedly {changeLabelCategory} integration test.")
//    public void testChangeLabelCategory() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_changeLabelCategory.txt";
//        String methodName = "feedly_changeLabelCategory";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName), newJsonString);
//            //System.out.println(object);
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            System.out.println("\n\n\n\n\n-------*********-------");
//            System.out.println("response"+responseHeader+" object: "+object);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for deleteCategory method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 4, description = "Feedly {deleteCategory} integration test.")
//    public void testDeleteCategory() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_deleteCategory.txt";
//        String methodName = "feedly_deleteCategory";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//
//    /**
//     * test case for getUserSubscription method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 5, description = "Feedly {getUserSubscription} integration test.")
//    public void testGetUserSubscription() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getUserSubscription.txt";
//        String methodName = "feedly_getUserSubscription";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for getUserSubscription method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 5, description = "Feedly {getUserSubscriptionNegative} integration test.")
//    public void testGetUserSubscriptionNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getUserSubscription.txt";
//        String methodName = "feedly_getUserSubscription";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessTokenNegative"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 401);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for feedSubscribe method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 6, description = "Feedly {feedSubscribe} integration test.")
//    public void testFeedSubscribe() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_feedSubscribe.txt";
//        String methodName = "feedly_feedSubscribe";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for feedSubscribe method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 6, description = "Feedly {feedSubscribeNegative} integration test.")
//    public void testFeedSubscribeNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_feedSubscribe.txt";
//        String methodName = "feedly_feedSubscribe";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessTokenNegative"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 401);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for feedUnsubscribe method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 7, description = "Feedly {feedUnsubscribe} integration test.")
//    public void testFeedUnsubscribe() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_feedUnsubscribe.txt";
//        String methodName = "feedly_feedUnsubscribe";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for feedUnsubscribe method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 7, description = "Feedly {feedUnsubscribeNegative} integration test.")
//    public void testFeedUnsubscribeNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_feedUnsubscribe.txt";
//        String methodName = "feedly_feedUnsubscribe";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessTokenNegative"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 401);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for updateSubscription method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 8, description = "Feedly {updateSubscription} integration test.")
//    public void testUpdateSubscription() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateSubscription.txt";
//        String methodName = "feedly_updateSubscription";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for searchFeeds method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 9, description = "Feedly {searchFeeds} integration test.")
//    public void testSearchFeeds() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_searchFeeds.txt";
//        String methodName = "feedly_searchFeeds";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("q"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(response.has("results"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for searchFeeds method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 9, description = "Feedly {searchFeedsNegative} integration test.")
//    public void testSearchFeedsNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_searchFeeds.txt";
//        String methodName = "feedly_searchFeeds";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, "");
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(response == 400);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Optional test case for searchFeeds method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 9, description = "Feedly {searchFeedsNegativeOptional} integration test.")
//    public void testSearchFeedsNegativeOptional() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_searchFeedsOptional.txt";
//        String methodName = "feedly_searchFeeds";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("q"),
//                feedlyConnectorProperties.getProperty("locale"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for getListTopics method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 10, description = "Feedly {getListTopics} integration test.")
//    public void testGetListTopics() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListTopics.txt";
//        String methodName = "feedly_getListTopics";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for addTopic method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 11, description = "Feedly {addTopic} integration test.")
//    public void testAddTopic() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_addTopic.txt";
//        String methodName = "feedly_addTopic";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("topicId"),
//                feedlyConnectorProperties.getProperty("interest"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for updateTopic method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 12, description = "Feedly {updateTopic} integration test.")
//    public void testUpdateTopic() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateTopic.txt";
//        String methodName = "feedly_updateTopic";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("topicId"),
//                feedlyConnectorProperties.getProperty("updatedInterest"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for removeTopic method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 13, description = "Feedly {removeTopic} integration test.")
//    public void testRemoveTopic() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_removeTopic.txt";
//        String methodName = "feedly_removeTopic";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("topicId"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//
//    /**
//     * test case for getEntryContent method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 14, description = "Feedly {getEntryContent} integration test.")
//    public void testGetEntryContent() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEntryContent.txt";
//        String methodName = "feedly_getEntryContent";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("entryIdOne"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for getContentForListOfEntries method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 15, description = "Feedly {getContentForListOfEntries} integration test.")
//    public void testGetContentForListOfEntries() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getContentForListOfEntries.txt";
//        String methodName = "feedly_getContentForListOfEntries";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("entryIdOne"),
//                feedlyConnectorProperties.getProperty("entryIdTwo"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for createAndTagEntry method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 16, description = "Feedly {createAndTagEntry} integration test.")
//    public void testCreateAndTagEntry() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_createAndTagEntryOptional.txt";
//        String methodName = "feedly_createAndTagEntry";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(responseHeader == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//
//    /**
//     * test case for getFeedMetadata method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 17, description = "Feedly {getFeedMetadata} integration test.")
//    public void testGetFeedMetadata() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getFeedMetadata.txt";
//        String methodName = "feedly_getFeedMetadata";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("feedId"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("id"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for getMetadataForListOfFeeds method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 18, description = "Feedly {getMetadataForListOfFeeds} integration test.")
//    public void testGetMetadataForListOfFeeds() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getMetadataForListOfFeeds.txt";
//        String methodName = "feedly_getMetadataForListOfFeeds";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("feedId"),
//                feedlyConnectorProperties.getProperty("feedIdTwo"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//
//    /**
//     * test case for getListOfUnreadCount method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 19, description = "Feedly {getListOfUnreadCount} integration test.")
//    public void testGetListOfUnreadCount() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListOfUnreadCount.txt";
//        String methodName = "feedly_getListOfUnreadCount";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("unreadcounts"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Optional test case for getListOfUnreadCount method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 20, description = "Feedly {getListOfUnreadCountOptional} integration test.")
//    public void testGetListOfUnreadCountOptional() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListOfUnreadCountOptional.txt";
//        String methodName = "feedly_getListOfUnreadCount";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for markArticleAsRead method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 21, description = "Feedly {markArticleAsRead} integration test.")
//    public void testMarkArticleAsRead() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_markArticleAsRead.txt";
//        String methodName = "feedly_markArticleAsRead";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("entryIdThree"),
//                feedlyConnectorProperties.getProperty("entryIdFour"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//
//    /**
//     * test case for markArticleAsUnread method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 22, description = "Feedly {markArticleAsUnread} integration test.")
//    public void testMarkArticleAsUnread() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_markArticleAsUnread.txt";
//        String methodName = "feedly_markArticleAsUnread";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("entryIdThree"),
//                feedlyConnectorProperties.getProperty("entryIdFour"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for markFeedAsRead method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 23, description = "Feedly {markFeedAsRead} integration test.")
//    public void testMarkFeedAsRead() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_markFeedAsRead.txt";
//        String methodName = "feedly_markFeedAsRead";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("entryIdFour"),
//                feedlyConnectorProperties.getProperty("feedIdThree"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for markCategoryAsRead method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 24, description = "Feedly {markCategoryAsRead} integration test.")
//    public void testMarkCategoryAsRead() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_markCategoryAsRead.txt";
//        String methodName = "feedly_markCategoryAsRead";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("entryIdFour"),
//                feedlyConnectorProperties.getProperty("categoryId"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for undoMarkAsReadForCategories method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 25, description = "Feedly {undoMarkAsReadForCategories} integration test.")
//    public void testUndoMarkAsReadForCategories() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_undoMarkAsReadForCategories.txt";
//        String methodName = "feedly_undoMarkAsRead";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("categoryIdTwo"),
//                feedlyConnectorProperties.getProperty("categoryIdThree"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for undoMarkAsReadForFeeds method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 26, description = "Feedly {undoMarkAsReadForFeeds} integration test.")
//    public void testUndoMarkAsReadForFeeds() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_undoMarkAsReadForFeeds.txt";
//        String methodName = "feedly_undoMarkAsRead";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("feedIdThree"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for markArticleAsSaved method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 27, description = "Feedly {markArticleAsSaved} integration test.")
//    public void testMarkArticleAsSaved() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_markArticleAsSaved.txt";
//        String methodName = "feedly_markArticleAsSaved";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("entryIdThree"),
//                feedlyConnectorProperties.getProperty("entryIdFour"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for markArticlesAsUnsaved method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 28, description = "Feedly {markArticlesAsUnsaved} integration test.")
//    public void testMarkArticlesAsUnsaved() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_markArticlesAsUnsaved.txt";
//        String methodName = "feedly_markArticlesAsUnsaved";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("entryIdThree"),
//                feedlyConnectorProperties.getProperty("entryIdFour"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for getLatestReadOperations method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 29, description = "Feedly {getLatestReadOperations} integration test.")
//    public void testGetLatestReadOperations() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getLatestReadOperations.txt";
//        String methodName = "feedly_getLatestReadOperations";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("feeds"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Optional test case for getLatestReadOperations method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 30, description = "Feedly {getLatestReadOperationsOptional} integration test.")
//    public void testGetLatestReadOperationsOptional() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getLatestReadOperationsOptional.txt";
//        String methodName = "feedly_getLatestReadOperations";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("feeds"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for getLatestTagEntryIds method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 31, description = "Feedly {getLatestTagEntryIds} integration test.")
//    public void testGetLatestTagEntryIds() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getLatestTagEntryIds.txt";
//        String methodName = "feedly_getLatestTagEntryIds";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("taggedEntries"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Optional test case for getLatestTagEntryIds method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 32, description = "Feedly {getLatestTagEntryIdsOptional} integration test.")
//    public void testGetLatestTagEntryIdsOptional() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getLatestTagEntryIdsOptional.txt";
//        String methodName = "feedly_getLatestTagEntryIds";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("taggedEntries"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//
//    /**
//     * test case for getEngagingContentInFeedStream method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 33, description = "Feedly {getEngagingContentInFeedStream} integration test.")
//    public void testGetEngagingContentInFeedStream() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEngagingContentInFeedStream.txt";
//        String methodName = "feedly_getEngagingContentInFeedStream";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("feedId"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("id"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Optional test case for getEngagingContentInFeedStream method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 33, description = "Feedly {getEngagingContentInFeedStreamOptional} integration test.")
//    public void testGetEngagingContentInFeedStreamOptional() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEngagingContentInFeedStreamOptional.txt";
//        String methodName = "feedly_getEngagingContentInFeedStream";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("feedId"),
//                feedlyConnectorProperties.getProperty("locale"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("id"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for getEngagingContentInCategoryStream method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 34, description = "Feedly {getEngagingContentInCategoryStream} integration test.")
//    public void testGetEngagingContentInCategoryStream() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEngagingContentInCategoryStream.txt";
//        String methodName = "feedly_getEngagingContentInCategoryStream";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("categoryIdFour"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("items"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Optional test case for getEngagingContentInCategoryStream method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 35, description = "Feedly {getEngagingContentInCategoryStreamOptional} integration test.")
//    public void testGetEngagingContentInCategoryStreamOptional() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEngagingContentInCategoryStreamOptional.txt";
//        String methodName = "feedly_getEngagingContentInCategoryStream";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"),
//                feedlyConnectorProperties.getProperty("categoryIdFour"),
//                feedlyConnectorProperties.getProperty("locale"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("items"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//
//    /**
//     * test case for exportOPML method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 36, description = "Feedly {exportOPML} integration test.")
//    public void testExportOPML() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_exportOPML.txt";
//        String methodName = "feedly_exportOPML";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for exportOPML method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 37, description = "Feedly {exportOPMLNegative} integration test.")
//    public void testExportOPMLNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_exportOPML.txt";
//        String methodName = "feedly_exportOPML";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessTokenNegative"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 401);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for getPreference method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 38, description = "Feedly {getPreference} integration test.")
//    public void testGetPreference() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getPreference.txt";
//        String methodName = "feedly_getPreference";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for getPreference method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 39, description = "Feedly {getPreferenceNegative} integration test.")
//    public void testGetPreferenceNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getPreference.txt";
//        String methodName = "feedly_getPreference";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessTokenNegative"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 401);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for updatePreference method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 40, description = "Feedly {updatePreference} integration test.")
//    public void testUpdatePreference() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updatePreference.txt";
//        String methodName = "feedly_updatePreference";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for updatePreference method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 41, description = "Feedly {updatePreferenceNegative} integration test.")
//    public void testUpdatePreferenceNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updatePreference.txt";
//        String methodName = "feedly_updatePreference";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessTokenNegative"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 401);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for getContentOfStream method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 42, description = "Feedly {getContentOfStream} integration test.")
//    public void testGetContentOfStream() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getContentOfStream.txt";
//        String methodName = "feedly_getContentOfStream";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for getContentOfStream method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 43, description = "Feedly {getContentOfStreamNegative} integration test.")
//    public void testGetContentOfStreamNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getContentOfStream.txt";
//        String methodName = "feedly_getContentOfStream";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessTokenNegative"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 401);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Optional test case for getContentOfStream method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 44, description = "Feedly {getContentOfStreamOptional} integration test.")
//    public void testGetContentOfStreamOptional() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getContentOfStreamOptional.txt";
//        String methodName = "feedly_getContentOfStream";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 200);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * test case for getListOfEntriesInStream method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 45, description = "Feedly {getListOfEntriesInStream} integration test.")
//    public void testGetListOfEntriesInStream() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListOfEntriesInStream.txt";
//        String methodName = "feedly_getListOfEntriesInStream";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("ids"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Negative test case for getListOfEntriesInStream method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 46, description = "Feedly {getListOfEntriesInStreamNegative} integration test.")
//    public void testGetListOfEntriesInStreamNegative() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListOfEntriesInStream.txt";
//        String methodName = "feedly_getListOfEntriesInStream";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessTokenNegative"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            int response = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response == 401);
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }
//
//    /**
//     * Optional test case for getListOfEntriesInStream method.
//     */
//    @Test(groups = { "wso2.esb" },priority = 47, description = "Feedly {getListOfEntriesInStreamOptional} integration test.")
//    public void testGetListOfEntriesInStreamOptional() throws Exception {
//
//        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListOfEntriesInStreamOptional.txt";
//        String methodName = "feedly_getListOfEntriesInStream";
//
//        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
//        String newJsonString = String.format(jsonString,
//                feedlyConnectorProperties.getProperty("accessToken"));
//
//        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
//        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));
//
//        try {
//            JSONObject response = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName),newJsonString);
//
//            Assert.assertTrue(response.has("ids"));
//        } finally {
//            proxyAdmin.deleteProxy(methodName);
//        }
//    }

    /**
     * test case for getListOfTags method.
     */
    @Test(groups = { "wso2.esb" },priority = 45, description = "Feedly {getListOfTags} integration test.")
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


}
