package view;

import java.time.LocalDateTime;
import java.util.Scanner;

import controller.EventManager;
import model1.Event;
import model1.User;

public class Main {

    private static EventManager eventManager = new EventManager();
    private static User currentUser;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Sistema de Eventos!");

        // Cadastrar usuário inicial
        cadastrarUsuario();

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar evento");
            System.out.println("2. Listar eventos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarEvento();
                    break;
                case "2":
                    listarEventos();
                    break;
                case "3":
                    running = false;
                    System.out.println("Saindo... Obrigado por usar o sistema!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarUsuario() {
        System.out.println("Cadastro de usuário:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        currentUser = new User(nome, email, cidade);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void cadastrarEvento() {
        System.out.println("Cadastro de evento:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Categoria (Festa, Show, Esporte): ");
        String categoria = scanner.nextLine();
        System.out.print("Data e hora (formato: yyyy-MM-ddTHH:mm): ");
        String dataHoraStr = scanner.nextLine();

        LocalDateTime dataHora;
        try {
            dataHora = LocalDateTime.parse(dataHoraStr);
        } catch (Exception e) {
            System.out.println("Formato de data/hora inválido.");
            return;
        }

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Event evento = new Event(nome, endereco, categoria, dataHora, descricao);
        eventManager.addEvent(evento);
        System.out.println("Evento cadastrado com sucesso!");
    }

    private static void listarEventos() {
        System.out.println("\nEventos cadastrados:");
        for (Event e : eventManager.getAllEvents()) {
            System.out.println(e);
        }
    }
}
