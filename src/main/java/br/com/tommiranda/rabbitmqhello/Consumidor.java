package br.com.tommiranda.rabbitmqhello;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.Scanner;

public class Consumidor {

    private final String QUEUE_HELLO = "hello";

    public void consumer() {
        System.out.println("Iniciando CONSUMER!");

        ConnectionFactory factory = new ConnectionFactory();

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            factory.setUri("amqp://tfhdvpwp:_J6BtyjS11BiGOswS_6n8nzavaW9rMHZ@skunk.rmq.cloudamqp.com/tfhdvpwp");
            channel.queueDeclare(QUEUE_HELLO, true, false, true, null);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(
                        String consumerTag,
                        Envelope envelope,
                        AMQP.BasicProperties properties,
                        byte[] body
                ) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                }
            };

            channel.basicConsume(QUEUE_HELLO, consumer);

            Scanner in = new Scanner(System.in);
            in.nextLine();
        } catch (Exception e) {
            System.out.println("EXCESSÃO no Consumer");
            System.out.println(e.getMessage());
        }
    }
}
