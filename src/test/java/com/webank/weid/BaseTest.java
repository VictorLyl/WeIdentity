

package com.webank.weid;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import com.webank.weid.full.TestBaseUtil;
import com.webank.weid.rpc.AuthorityIssuerService;
import com.webank.weid.rpc.CptService;
import com.webank.weid.rpc.CredentialPojoService;
import com.webank.weid.rpc.CredentialService;
import com.webank.weid.rpc.EvidenceService;
import com.webank.weid.rpc.PolicyService;
import com.webank.weid.rpc.WeIdService;
import com.webank.weid.service.BaseService;
import com.webank.weid.service.impl.AuthorityIssuerServiceImpl;
import com.webank.weid.service.impl.CptServiceImpl;
import com.webank.weid.service.impl.CredentialPojoServiceImpl;
import com.webank.weid.service.impl.CredentialServiceImpl;
import com.webank.weid.service.impl.EvidenceServiceImpl;
import com.webank.weid.service.impl.PolicyServiceImpl;
import com.webank.weid.service.impl.WeIdServiceImpl;

/**
 * Test base class.
 *
 * @author v_wbgyang
 */
public abstract class BaseTest extends BaseService {

    protected AuthorityIssuerService authorityIssuerService;
    protected CptService cptService;
    protected WeIdService weIdService;
    protected CredentialService credentialService;
    protected CredentialPojoService credentialPojoService;
    protected EvidenceService evidenceService;
    protected PolicyService policyService;

    /**
     * the private key of sdk is a BigInteger,which needs to be used when registering authority.
     */
    protected String privateKey;
    
    static {
        // mock DB
        MockMysqlDriver.mockMysqlDriver();
        MockIssuerClient.mockMakeCredentialTemplate();
    }

    /**
     * initialization some for test.
     */
    @Before
    public void setUp()  {

        authorityIssuerService = new AuthorityIssuerServiceImpl();
        cptService = new CptServiceImpl();
        weIdService = new WeIdServiceImpl();
        credentialService = new CredentialServiceImpl();
        evidenceService = new EvidenceServiceImpl();
        credentialPojoService = new CredentialPojoServiceImpl();
        policyService = new PolicyServiceImpl();

        privateKey = TestBaseUtil.readPrivateKeyFromFile("ecdsa_key");

        testInit();
    }

    /**
     * tearDown some for test.
     */
    @After
    public void tearDown() {

        authorityIssuerService = null;
        cptService = null;
        weIdService = null;
        credentialService = null;
        evidenceService = null;
        credentialPojoService = null;

        testFinalize();
    }

    public void testInit() {
        Assert.assertTrue(true);
    }

    public void testFinalize() {
        Assert.assertTrue(true);
    }
}