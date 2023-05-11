/*
    Trabalho prático 2
    Crie um pequeno Simulador (em Java ou outra linguagem), capaz de SIMULAR A FILA DE PRONTO (READY QUEUE) de um SO.
    Seu Simulador deve possibilitar no mínimo:
    Escalonar processos com um Algoritmo NÃO-PREEMPTIVO

    Nome: João Ryan dos Santos
    Matricula: 239

    Algoritmo usado foi o Shortest Job First (SJF), ele escolhe o processo com o menor tempo de execução.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Processo {
    private String nome;
    private int tempoExecucao;

    public Processo(String nome, int tempoExecucao) {
        this.nome = nome;
        this.tempoExecucao = tempoExecucao;
    }

    public String getNome() {
        return nome;
    }

    public int getTempoExecucao() {
        return tempoExecucao;
    }
}

public class SimuladorFilaPronto {
    private List<Processo> filaPronto;

    public SimuladorFilaPronto() {
        filaPronto = new ArrayList<>();
    }

    public void adicionarProcesso(String nome, int tempoExecucao) {
        Processo processo = new Processo(nome, tempoExecucao);
        filaPronto.add(processo);
        System.out.println("Processo adicionado à fila de pronto: " + nome);
    }

    public void escalonar() {
        if (filaPronto.isEmpty()) {
            System.out.println("Não há processos na fila de pronto.");
            return;
        }

        int menorTempoExecucao = filaPronto.get(0).getTempoExecucao();
        Processo processoEscalonado = filaPronto.get(0);

        for (Processo processo : filaPronto) {
            if (processo.getTempoExecucao() < menorTempoExecucao) {
                menorTempoExecucao = processo.getTempoExecucao();
                processoEscalonado = processo;
            }
        }

        System.out.println("Processo escalonado: " + processoEscalonado.getNome());
        filaPronto.remove(processoEscalonado);
    }

    public static void main(String[] args) {
        SimuladorFilaPronto simulador = new SimuladorFilaPronto();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Opções:");
            System.out.println("1 - Adicionar processo");
            System.out.println("2 - Escalonar processo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do processo: ");
                    String nome = scanner.next();
                    System.out.print("Digite o tempo de execução do processo: ");
                    int tempoExecucao = scanner.nextInt();
                    simulador.adicionarProcesso(nome, tempoExecucao);
                    break;
                case 2:
                    simulador.escalonar();
                    break;
                case 0:
                    System.out.println("Encerrando o simulador.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }
    }
}
