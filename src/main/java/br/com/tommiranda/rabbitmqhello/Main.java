package br.com.tommiranda.rabbitmqhello;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Digite o tipo da fila:");
        Scanner in = new Scanner(System.in);
        String queue = in.nextLine();

        if (queue.equals("producer"))
            new Produtor().producer();
        else if (queue.equals("consumer"))
            new Consumidor().consumer();
        else
            System.out.println("Escolha 'producer' ou 'consumer' como parâmetros apenas.");
    }
}
