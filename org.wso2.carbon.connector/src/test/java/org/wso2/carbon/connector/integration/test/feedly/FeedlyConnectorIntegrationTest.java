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


    protected static final String CONNECTOR_NAME = "feedly";

    protected ProxyServiceAdminClient proxyAdmin;

    protected String pathToProxiesDirectory = null;

    protected String pathToRequestsDirectory = null;

    protected Properties feedlyConnectorProperties = null;

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

}
