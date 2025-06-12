package view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import controller.EventManager;
import model1.Event;
import model1.User;

public class Main {

    private static EventManager eventManager = new EventManager();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        // Cria um usuário fixo para testes, depois você pode implementar cadastro
        currentUser = new User("Maico", "maico@email.com", "CidadeX");

        while (true) {
            System.out.println("\n=== Sistema de Eventos ===");
            System.out.println("1 - Cadastrar novo evento");
            System.out.println("2 - Listar eventos");
            System.out.println("3 - Participar de evento");
            System.out.println("4 - Cancelar participação");
            System.out.println("5 - Ver meus eventos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
            case 1:
                cadastrarEvento();
                break;
            case 2:
                listarEventos();
                break;
            case 3:
                participarEvento();
                break;
            case 4:
                cancelarParticipacao();
                break;
            case 5:
                listarMeusEventos();
                break;
            case 0:
                System.out.println("Saindo...");
                System.exit(0);
            default:
                System.out.println("Opção inválida!");
        }

        }
    }

    // Lista todos os eventos ordenados pela data
    private static void listarEventos() {
        List<Event> eventos = eventManager.getEventsOrderedByDate();
        System.out.println("\nEventos disponíveis:");
        for (int i = 0; i < eventos.size(); i++) {
            Event e = eventos.get(i);
            System.out.printf("%d - %s (%s) - %s\n", i, e.getName(), e.getCategory(), e.getDateTime());
        }
    }

    // Permite que o usuário participe de um evento
    private static void participarEvento() {
        listarEventos();
        System.out.print("Digite o número do evento para participar: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= eventManager.getAllEvents().size()) {
            System.out.println("Evento inválido!");
            return;
        }

        eventManager.participateInEvent(index, currentUser);
        System.out.println("Participação confirmada!");
    }

    // Permite que o usuário cancele a participação em um evento
    private static void cancelarParticipacao() {
        List<Event> meusEventos = eventManager.getEventsUserIsParticipating(currentUser);
        System.out.println("\nEventos que você está participando:");
        for (int i = 0; i < meusEventos.size(); i++) {
            Event e = meusEventos.get(i);
            System.out.printf("%d - %s (%s) - %s\n", i, e.getName(), e.getCategory(), e.getDateTime());
        }

        System.out.print("Digite o número do evento para cancelar participação: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= meusEventos.size()) {
            System.out.println("Evento inválido!");
            return;
        }

        // Encontra o índice do evento no eventoManager para cancelar participação corretamente
        Event eventoParaCancelar = meusEventos.get(index);
        int indiceNoGerenciador = eventManager.getAllEvents().indexOf(eventoParaCancelar);
        if (indiceNoGerenciador >= 0) {
            eventManager.cancelParticipation(indiceNoGerenciador, currentUser);
            System.out.println("Participação cancelada!");
        } else {
            System.out.println("Erro ao cancelar participação. Evento não encontrado.");
        }
    }

    // Lista os eventos nos quais o usuário está participando
    private static void listarMeusEventos() {
        List<Event> meusEventos = eventManager.getEventsUserIsParticipating(currentUser);
        System.out.println("\nSeus eventos:");
        for (Event e : meusEventos) {
            System.out.printf("- %s (%s) - %s\n", e.getName(), e.getCategory(), e.getDateTime());
        }
    }
    private static void cadastrarEvento() {
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Data e hora (formato: yyyy-MM-ddTHH:mm): ");
        String dataHoraStr = scanner.nextLine();
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        try {
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr);
            Event novoEvento = new Event(nome, endereco, categoria, dataHora, descricao);
            eventManager.addEvent(novoEvento);
            System.out.println("Evento cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Formato de data/hora inválido. Tente novamente.");
        }
    }

}
