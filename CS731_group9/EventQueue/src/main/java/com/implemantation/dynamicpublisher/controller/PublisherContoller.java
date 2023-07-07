package com.implemantation.dynamicpublisher.controller;

import com.implemantation.dynamicpublisher.Service.PublisherRepo;
import com.implemantation.dynamicpublisher.Service.SignHashRepo;
import com.implemantation.dynamicpublisher.dto.PublisherItem;
import com.implemantation.dynamicpublisher.dto.Reply;
import com.implemantation.dynamicpublisher.dto.RequestMessage;
import com.implemantation.dynamicpublisher.dto.SignHashItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/rest/api")
public class PublisherContoller {

    private final Object $lock = new Object[0];

    @Autowired
    private PublisherRepo repo1;

//    @Autowired
//    private SignHashRepo repo2;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/publish")
    public Reply publishMessage(
            @RequestParam("user_id") String userid,
            @RequestParam("topic_id") String topic,
            @RequestParam("msg") String msg
            ) throws UnsupportedEncodingException {

        System.out.println("Request for Inserting Data in Event Queue");

        byte[] sign;
        CryptoController controller;
        String msgHash;
        PublicKey publicKey;
        synchronized ($lock) {
            controller = new CryptoController();

            String eventQ = userid + "_" + topic;
            String eqHash = controller.getHash(eventQ, "SHA3-256");
            //System.out.println(eventQ+" -> "+eqHash);

            if (eqHash == null) {
                System.out.println("An Error Occurred while computing Hash");
                return new Reply("Error Publishing Message");
            }

            msgHash = controller.getHash(msg, "SHA3-512");

            sign = controller.signAndHash(msgHash);

            if (sign == null) {
                System.out.println("Null Signature Found");
                return new Reply("Error Publishing Message");
            }
            publicKey = controller.getPubKey();

            repo1.save(new PublisherItem(eqHash, msg));
            System.out.println("Successfully added to Database");

            kafkaTemplate.send(eventQ,msg);

//            repo2.save(new SignHashItem(eqHash, sign, publicKey.getEncoded()));
//            System.out.println("Successfully Created Digitally Signed Hash");

        }
        return new Reply("Message Published Successfully.");

    }
    @GetMapping("/view")
    public List<PublisherItem> fetchData(
            @RequestParam("topic_id") String topic,
            @RequestParam("user_id") String user){
        String eventQ = new CryptoController().getHash(user + "_" + topic,"SHA3-256");
        System.out.println("Request for Fetching Data from Event Queue");

        List<PublisherItem> publisherItemList=repo1.findByTopicId(eventQ);

        return publisherItemList;
    }
}
