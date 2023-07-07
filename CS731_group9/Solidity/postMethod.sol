// SPDX-License-Identifier: MIT
pragma solidity ^0.8.7;

import "@chainlink/contracts/src/v0.8/ChainlinkClient.sol";

contract PostMessageAPI is ChainlinkClient{
    using Chainlink for Chainlink.Request;

    bytes32 private jobId;
    uint256 private fee;
    
    string public baseUrl;
    string public response;
    string public userid;
    string public topicid;
    string public message;



    event InsertMessage(bytes32 indexed requestId, string response);

    constructor() {
        setChainlinkToken(0x779877A7B0D9E8603169DdbD7836e478b4624789);
        setChainlinkOracle(0x6090149792dAAeE9D1D568c9f9a6F6B46AA29eFD);
        jobId = "7d80a6386ef543a3abb52817f6707e3b";
        fee = (1 * LINK_DIVISIBILITY) / 10; // 0,1 * 10**18 (Varies by network and job)
        response ="Inserted Nothing.";
    }

    function setParam(string memory _url,string memory _userid,string memory _topicid,string memory _message) public{
        baseUrl = _url;
        userid = _userid;
        topicid =_topicid;
        message =_message;
    }

    
    function resetContract() public{
        response = "Inserted Nothing.";
    }

    function getResponse() public view returns(string memory){
        return response;
    }

    function insertQueueData() public returns (bytes32 requestId) {
        Chainlink.Request memory req = buildChainlinkRequest(
            jobId,
            address(this),
            this.fulfill.selector
        );
 
        req.add("get",string(abi.encodePacked(baseUrl,"topic_id=",topicid,"&user_id=",userid,"&msg=",message)));
        req.add("path","response");

        return sendChainlinkRequest(req, fee);
    }

    function fulfill(bytes32 _requestId,string memory _reply) public recordChainlinkFulfillment(_requestId) {
        emit InsertMessage(_requestId, _reply);
        response = _reply;
    }

}
