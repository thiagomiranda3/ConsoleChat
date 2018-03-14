package br.com.tommiranda.rabbitmqhello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.Scanner;

public class Produtor {

    private final String QUEUE_HELLO = "hello";

    public void producer() {
        System.out.println("Iniciando PRODUCER!");

        ConnectionFactory factory = new ConnectionFactory();

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            factory.setUri("amqp://tfhdvpwp:_J6BtyjS11BiGOswS_6n8nzavaW9rMHZ@skunk.rmq.cloudamqp.com/tfhdvpwp");
            channel.queueDeclare(QUEUE_HELLO, true, false, true, null);

            while (true) {
                Scanner in = new Scanner(System.in);
                String message = in.nextLine();

                channel.basicPublish("", QUEUE_HELLO, null, message.getBytes());
            }
        } catch (Exception e) {
            System.out.println("EXCESSÃO no Producer");
            System.out.println(e.getMessage());
        }
    }
}
