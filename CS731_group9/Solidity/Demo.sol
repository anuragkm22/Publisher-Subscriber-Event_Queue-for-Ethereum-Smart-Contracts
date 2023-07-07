// SPDX-License-Identifier: MIT
pragma solidity ^0.8.7;

import "contracts/getMethod.sol";
import "contracts/postMethod.sol";

contract demoContract{

    address public addrGet;
    address public addrPost;
    string public GetResponse = "No Message Received.";
    string public PostResponse = "Inserted Nothing.";
    
    GetMessageAPI gma;
    PostMessageAPI pma;
    

    function setAddrGet(address _addrGet) public{
        addrGet=_addrGet;
        gma = GetMessageAPI(addrGet);
    }

    function setAddrPost(address _addrPost) public{
        addrPost=_addrPost;
        pma = PostMessageAPI(addrPost);
    }

    function getRequest(string memory url,string memory userid,string memory topicid,string memory path) public{
        gma.setParam(url, userid, topicid, path);
        gma.requestQueueData();
    }

    function postRequest(string memory url,string memory userid,string memory topicid,string memory message) public{
        pma.setParam(url, userid, topicid, message);
        pma.insertQueueData();
    }

    function getMessage()public{
        GetResponse = gma.getResponse();
    }

    function postMessage()public{
        PostResponse = pma.getResponse();
    }

    function reset() public {
        gma.resetContract();
        pma.resetContract();
        getMessage();
        postMessage();
    }
}