// SPDX-License-Identifier: MIT
pragma solidity ^0.8.7;

import "@chainlink/contracts/src/v0.8/ChainlinkClient.sol";

contract GetMessageAPI is ChainlinkClient{
    using Chainlink for Chainlink.Request;

    
    bytes32 private jobId;
    uint256 private fee;
    
    string public baseUrl;
    string public response;
    string public userid;
    string public topicid;
    string public path;

    event RequestMessage(bytes32 indexed requestId, string reply);

    constructor() {
        setChainlinkToken(0x779877A7B0D9E8603169DdbD7836e478b4624789);
        setChainlinkOracle(0x6090149792dAAeE9D1D568c9f9a6F6B46AA29eFD);
        jobId = "7d80a6386ef543a3abb52817f6707e3b";
        fee = (1 * LINK_DIVISIBILITY) / 10; 
        response ="No Messages Received.";
    }

    function setParam(string memory _url,string memory _userid,string memory _topicid,string memory _path) public{
        baseUrl = _url;
        userid = _userid;
        topicid =_topicid;
        path = _path;
    }

    function resetContract() public{
        response = "No Messages Received.";
    }

    function getResponse() public view returns(string memory){
        return response;
    }

    function requestQueueData() public returns (bytes32 requestId) {
        
        Chainlink.Request memory req = buildChainlinkRequest(
            jobId,
            address(this),
            this.fulfill.selector
        );
        
        req.add("get",string(abi.encodePacked(baseUrl,"topic_id=",topicid,"&user_id=",userid)));
        req.add("path",path); 


        return sendChainlinkRequest(req, fee);
    }

    function fulfill(bytes32 _requestId,string memory _response) public recordChainlinkFulfillment(_requestId) {
        emit RequestMessage(_requestId, _response);
        response = _response;
    }

}
