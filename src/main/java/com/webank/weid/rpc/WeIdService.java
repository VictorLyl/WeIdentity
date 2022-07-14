

package com.webank.weid.rpc;

import java.util.List;

import com.webank.weid.protocol.base.WeIdAuthentication;
import com.webank.weid.protocol.base.WeIdDocument;
import com.webank.weid.protocol.base.WeIdPojo;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.base.WeIdPublicKey;
import com.webank.weid.protocol.request.AuthenticationArgs;
import com.webank.weid.protocol.request.CreateWeIdArgs;
import com.webank.weid.protocol.request.PublicKeyArgs;
import com.webank.weid.protocol.request.ServiceArgs;
import com.webank.weid.protocol.response.CreateWeIdDataResult;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.protocol.response.WeIdListResult;


/**
 * Service inf for operations on WeIdentity DID.
 *
 * @author tonychen
 */
public interface WeIdService {

    /**
     * Create a WeIdentity DID without a keypair. SDK will generate a keypair for the caller.
     *
     * @return a data set including a WeIdentity DID and a keypair
     */
    ResponseData<CreateWeIdDataResult> createWeId();

    /**
     * Create a WeIdentity DID from the provided public key.
     *
     * @param createWeIdArgs you need to input a public key
     * @return WeIdentity DID
     */
    ResponseData<String> createWeId(CreateWeIdArgs createWeIdArgs);

    /**
     * Create a WeIdentity DID from the provided public key.
     *
     * @param publicKey the public key to create a weid
     * @param weIdAuthentication your private key
     * @return WeIdentity DID
     */
    ResponseData<String> delegateCreateWeId(
        WeIdPublicKey publicKey,
        WeIdAuthentication weIdAuthentication
    );

    /**
     * Query WeIdentity DID document.
     *
     * @param weId the WeIdentity DID
     * @return WeIdentity document in json type
     */
    ResponseData<String> getWeIdDocumentJson(String weId);

    /**
     * Query WeIdentity DID document.
     *
     * @param weId the WeIdentity DID
     * @return weId document in java object type
     */
    ResponseData<WeIdDocument> getWeIdDocument(String weId);

    /**
     * Add a public key in the WeIdentity DID Document. If this key is already revoked, then it will
     * be un-revoked.
     *
     * @param weId the WeID to add public key to
     * @param publicKeyArgs the public key args
     * @param privateKey the private key to send blockchain transaction
     * @return the public key ID, -1 if any error occurred
     */
    ResponseData<Integer> addPublicKey(String weId, PublicKeyArgs publicKeyArgs,
        WeIdPrivateKey privateKey);

    /**
     * Add a public key in the WeIdentity DID Document by other delegate caller (currently it must
     * be admin / committee). If this key is already revoked, then it will be un-revoked.
     *
     * @param weId the WeID to add public key to
     * @param publicKeyArgs the set public key args
     * @param delegateAuth the delegate's auth
     * @return the public key ID, -1 if any error occurred
     */
    ResponseData<Integer> delegateAddPublicKey(
        String weId,
        PublicKeyArgs publicKeyArgs,
        WeIdPrivateKey delegateAuth
    );

    /**
     * Set service properties.
     *
     * @param weId the WeID to set service to
     * @param serviceArgs your service name and endpoint
     * @param privateKey the private key
     * @return true if the "set" operation succeeds, false otherwise.
     */
    ResponseData<Boolean> setService(String weId, ServiceArgs serviceArgs,
        WeIdPrivateKey privateKey);

    /**
     * Set service properties.
     *
     * @param weId the WeID to set service to
     * @param serviceArgs your service name and endpoint
     * @param delegateAuth the delegate's auth
     * @return true if the "set" operation succeeds, false otherwise.
     */
    ResponseData<Boolean> delegateSetService(
        String weId,
        ServiceArgs serviceArgs,
        WeIdPrivateKey delegateAuth
    );

    /**
     * Set authentications in WeIdentity DID.
     *
     * @param weId the WeID to set auth to
     * @param authenticationArgs A public key is needed
     * @param privateKey the private key
     * @return true if the "set" operation succeeds, false otherwise.
     */
    ResponseData<Boolean> setAuthentication(
        String weId,
        AuthenticationArgs authenticationArgs,
        WeIdPrivateKey privateKey);

    /**
     * Set authentications in WeIdentity DID.
     *
     * @param weId the WeID to set auth to
     * @param authenticationArgs A public key is needed.
     * @param delegateAuth the delegate's auth
     * @return true if the "set" operation succeeds, false otherwise.
     */
    ResponseData<Boolean> delegateSetAuthentication(
        String weId,
        AuthenticationArgs authenticationArgs,
        WeIdPrivateKey delegateAuth
    );

    /**
     * Check if the WeIdentity DID exists on chain.
     *
     * @param weId The WeIdentity DID.
     * @return true if exists, false otherwise.
     */
    ResponseData<Boolean> isWeIdExist(String weId);

    /**
     * Remove a public key enlisted in WeID document together with the its authentication.
     *
     * @param weId the WeID to delete public key from
     * @param publicKeyArgs the public key args
     * @param privateKey the private key to send blockchain transaction
     * @return true if succeeds, false otherwise
     */
    ResponseData<Boolean> revokePublicKeyWithAuthentication(
        String weId,
        PublicKeyArgs publicKeyArgs,
        WeIdPrivateKey privateKey);

    /**
     * Remove an authentication tag in WeID document only - will not affect its public key.
     *
     * @param weId the WeID to remove auth from
     * @param authenticationArgs A public key is needed
     * @param privateKey the private key
     * @return true if succeeds, false otherwise
     */
    ResponseData<Boolean> revokeAuthentication(
        String weId,
        AuthenticationArgs authenticationArgs,
        WeIdPrivateKey privateKey);

    /**
     * query data according to block height, index location and search direction.
     * 
     * @param blockNumber the query blockNumber
     * @param pageSize the page size
     * @param indexInBlock the beginning (including) of the current block
     * @param direction search direction: true means forward search, false means backward search
     * @return return the WeIdPojo List
     */
    ResponseData<List<WeIdPojo>> getWeIdList(
        Integer blockNumber,
        Integer pageSize,
        Integer indexInBlock,
        boolean direction
    );
    
    /**
     * get total weId.
     *
     * @return total weid
     */
    ResponseData<Integer> getWeIdCount();

    /**
     * get WeID list by pubKey list.
     * @param pubKeyList the pubKey list
     * @return return the WeIDListResult
     */
    ResponseData<WeIdListResult> getWeIdListByPubKeyList(List<WeIdPublicKey> pubKeyList);
}
