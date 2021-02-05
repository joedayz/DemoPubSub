package pe.joedayz.gcpPubSubSenderDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubSubMessageController {

    @Autowired
    private GcpPubSubSenderDemoApplication.PubsubOutboundGateway messagingGateway;

    @PostMapping("/publishMessage")
    public ResponseEntity<?> publishMessage(@RequestParam("message") String message){
        messagingGateway.sendToPubSub(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
