package query;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 下午3:19 18-9-6
 */
@Slf4j
public class SinpleExample {

    public static Client getClient() throws UnknownHostException {
        // on startup

        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        return client;
        // on shutdown
//        client.close();
    }

    public static void main(String[] args) {

        try {
            System.out.println(InetAddress.getByName("localhost"));
            Client client = getClient();
            GetResponse response = client.prepareGet("movies", "movie", "3").get();

            System.out.println(response);
            client.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
