package pe.joedayz.gcpPubSubSenderDemo;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
public class GcpPubSubSenderDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpPubSubSenderDemoApplication.class, args);
	}


	@Bean
	@ServiceActivator(inputChannel = "demoOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubSubTemplate){
		return new PubSubMessageHandler(pubSubTemplate, "testTopic");
	}

	@MessagingGateway(defaultRequestChannel = "demoOutputChannel")
	public interface PubsubOutboundGateway {
		void sendToPubSub(String text);
	}
}
