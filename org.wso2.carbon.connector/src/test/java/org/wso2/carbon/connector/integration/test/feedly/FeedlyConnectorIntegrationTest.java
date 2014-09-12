package org.wso2.carbon.connector.integration.test.feedly;

import netscape.javascript.JSObject;
import org.apache.axis2.context.ConfigurationContext;
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

    /**
     * test case for getProfile method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {getProfile} integration test.")
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
     * test case for updateProfile method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {updateProfile} integration test.")
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
     * test case for getCategories method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {getCategories} integration test.")
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
     * test case for changeLabelCategory method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {changeLabelCategory} integration test.")
    public void testChangeLabelCategory() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_changeLabelCategory.txt";
        String methodName = "feedly_changeLabelCategory";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            JSONObject object = ConnectorIntegrationUtil.sendRequest(getProxyServiceURL(methodName), newJsonString);
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
     * test case for deleteCategory method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {deleteCategory} integration test.")
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
     * test case for getUserSubscription method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {getUserSubscription} integration test.")
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
     * test case for feedSubscribe method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {feedSubscribe} integration test.")
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
     * test case for feedSubscribe method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {feedUnsubscribe} integration test.")
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
     * test case for feedSubscribe method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {updateSubscription} integration test.")
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

    /**
     * test case for feedSubscribe method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {searchFeeds} integration test.")
    public void testSearchFeeds() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_searchFeeds.txt";
        String methodName = "feedly_searchFeeds";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        //String newJsonString = String.format(jsonString, feedlyConnectorProperties.getProperty("accessToken"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),jsonString);
            Assert.assertTrue(responseHeader == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }

    /**
     * test case for getListTopics method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {getListTopics} integration test.")
    public void testGetListTopics() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getListTopics.txt";
        String methodName = "feedly_getListTopics";

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
     * test case for addTopic method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {addTopic} integration test.")
    public void testAddTopic() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_addTopic.txt";
        String methodName = "feedly_addTopic";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("topicId"),
                feedlyConnectorProperties.getProperty("interest"));

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
     * test case for updateTopic method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {updateTopic} integration test.")
    public void testUpdateTopic() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_updateTopic.txt";
        String methodName = "feedly_updateTopic";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("accessToken"),
                feedlyConnectorProperties.getProperty("topicId"),
                feedlyConnectorProperties.getProperty("updatedInterest"));

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
     * test case for removeTopic method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {removeTopic} integration test.")
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
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);
            Assert.assertTrue(responseHeader == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }


    /**
     * test case for getEntryContent method.
     */
    @Test(groups = { "wso2.esb" }, description = "Feedly {getEntryContent} integration test.")
    public void testGetEntryContent() throws Exception {

        String jsonRequestFilePath = pathToRequestsDirectory + "feedly_getEntryContent.txt";
        String methodName = "feedly_getEntryContent";

        final String jsonString = ConnectorIntegrationUtil.getFileContent(jsonRequestFilePath);
        String newJsonString = String.format(jsonString,
                feedlyConnectorProperties.getProperty("entryIdOne"));

        final String proxyFilePath = "file:///" + pathToProxiesDirectory + methodName + ".xml";
        proxyAdmin.addProxyService(new DataHandler(new URL(proxyFilePath)));

        try {
            int responseHeader = ConnectorIntegrationUtil.sendRequestToRetriveHeaders(getProxyServiceURL(methodName),newJsonString);

            System.out.println("\n\n\n\n\n-------*********-------");
            System.out.println("response Entry ID: "+responseHeader);
            Assert.assertTrue(responseHeader == 200);
        } finally {
            proxyAdmin.deleteProxy(methodName);
        }
    }
}
