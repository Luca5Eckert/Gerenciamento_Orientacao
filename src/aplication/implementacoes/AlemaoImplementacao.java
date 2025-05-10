package aplication.implementacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;
import service.filtros.GerenciadorFiltrosOrientacao;

public class AlemaoImplementacao implements IdiomaImplementacao {

    @Override
    public IdiomaOrientacao obterIdiomaOrientacao() {
        return IdiomaOrientacao.ALEMAO;
    }

    @Override
    public String mostrarMenuInicial(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                          START                              ");
        System.out.println("============================================================");
        System.out.println(" Willkommen im Orientierung-Management-System:              ");
        System.out.println(" 1- Login");
        System.out.println(" 2- Registrieren");
        System.out.println(" 3- Sprache ändern");
        System.out.println(" 4- System verlassen");
        System.out.println("------------------------------------------------------------");

        return input.nextLine();
    }

    @Override
    public UsuarioDto mostrarMenuLogin(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                          LOGIN                             ");
        System.out.println("============================================================");
        System.out.println(" Geben Sie Ihre Anmeldedaten ein:\n");

        System.out.print(" E-Mail:");
        String email = input.nextLine();

        System.out.print(" Passwort:");
        String senha = input.nextLine();

        System.out.println("------------------------------------------------------------");
        return new UsuarioDto(email, null, senha);
    }

    @Override
    public UsuarioDto mostrarMenuCadastro(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                         REGISTRIEREN                       ");
        System.out.println("============================================================");
        System.out.println(" Geben Sie Ihre Daten ein:\n");

        System.out.print(" Name:");
        String nome = input.nextLine();

        System.out.print(" E-Mail:");
        String email = input.nextLine();

        System.out.print(" Passwort:");
        String senha = input.nextLine();

        System.out.println("------------------------------------------------------------");
        return new UsuarioDto(nome, email, senha);
    }

    @Override
    public String mostrarMenuGeral(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                        VERWALTER                           ");
        System.out.println("============================================================");

        System.out.println(" 0- Orientierung erstellen");
        System.out.println(" 1- Orientierungen ansehen");
        System.out.println(" 2- Abmelden");
        System.out.println(" 3- System verlassen");
        System.out.println(" 4- Sprache ändern");

        System.out.println("------------------------------------------------------------");

        return input.nextLine();
    }

    @Override
    public List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) throws Exception {
        int repeatCount = 1;
        List<OrientacaoDto> list = new ArrayList<>();

        System.out.println("============================================================");
        System.out.println("                       ERSTELLUNG                           ");
        System.out.println("============================================================");

        System.out.println(" S- Beenden ");
        System.out.println(" Möchten Sie nur für Ihre Sprache oder für alle erstellen? ");
        System.out.println(" 1- Nur für Portugiesisch ");
        System.out.println(" 2- Für alle ");
        String opcao = input.nextLine();

        switch (opcao.toUpperCase()) {
        case "1":
        	repeatCount = 1;
            break;
        case "2":
        	repeatCount = IdiomaOrientacao.values().length;
            break;
        case "S":
            throw new Exception();
        }

        System.out.println(" Orientierungstyp:");
        System.out.println(TipoOrientacao.mostrarTodasTipos(this.obterIdiomaOrientacao()));
        TipoOrientacao tipo = TipoOrientacao.pegarOrientacao(input.nextInt());
        input.nextLine();

        for (int i = 0; i < repeatCount; i++) {
            IdiomaOrientacao idioma = IdiomaOrientacao.pegarIdioma(i);
            String languageName = pegarNomeIdioma(idioma);

            System.out.println(" Orientierungstitel in " + languageName + ":");
            String title = input.nextLine();

            System.out.println(" Inhalt in " + languageName + ":");
            String content = input.nextLine();

            System.out.println("------------------------------------------------------------");

            list.add(new OrientacaoDto(title, tipo, content, idioma));
        }

        return list;
    }

    @Override
    public OrientacaoDto mostrarMenuEditarOrientacao(OrientacaoDto dto, Scanner input) {
        boolean editing = true;
        String title = dto.titulo();
        String content = dto.conteudo();
        TipoOrientacao tipo = dto.tipoOrientacao();

        do {
            System.out.println("============================================================");
            System.out.println("                         BEARBEITUNG                        ");
            System.out.println("============================================================");
            System.out.println(" Wählen Sie aus, was Sie bearbeiten möchten:\n");

            System.out.println(" 1- Orientierungstitel");
            System.out.println(" 2- Orientierungstyp");
            System.out.println(" 3- Orientierungstext");
            System.out.println(" 0- Bearbeitung beenden");
            System.out.println("------------------------------------------------------------");

            String inputOption = input.nextLine();

            switch (inputOption) {
                case "1" -> {
                    System.out.println("------------------------------------------------------------");
                    System.out.print(" Neuer Titel: ");
                    title = input.nextLine();
                    System.out.println("------------------------------------------------------------");
                }
                case "2" -> {
                    System.out.println("------------------------------------------------------------");
                    System.out.println(" Neuer Orientierungstyp:");
                    System.out.println(TipoOrientacao.mostrarTodasTipos(obterIdiomaOrientacao()));
                    System.out.print(" Geben Sie die entsprechende Zahl ein: ");
                    try {
                        int option = Integer.parseInt(input.nextLine());
                        tipo = TipoOrientacao.pegarOrientacao(option);
                    } catch (NumberFormatException e) {
                        System.out.println(" Ungültige Eingabe! Verwenden Sie eine Zahl.");
                    }
                    System.out.println("------------------------------------------------------------");
                }
                case "3" -> {
                    System.out.println("------------------------------------------------------------");
                    System.out.print(" Neuer Inhalt: ");
                    content = input.nextLine();
                    System.out.println("------------------------------------------------------------");
                }
                case "0" -> editing = false;
                default -> System.out.println(" Ungültige Option.");
            }

        } while (editing);

        return new OrientacaoDto(title, tipo, content, obterIdiomaOrientacao());
    }

    @Override
    public String mostrarMenuOrientacaoDisponiveis(Scanner input, String formattedList, String searchTerm) {
        System.out.println("============================================================");
        System.out.println("                       ORIENTIERUNGEN                        ");
        System.out.println("============================================================");
        System.out.println(" S - Zurück zum Hauptmenü");
        System.out.println(" F - Filter                                 P - Suche");
        System.out.println(" A - Suche löschen");
        System.out.println(" R - Filter entfernen\n ");
        System.out.println("\n Ergebnis: " + searchTerm);
        System.out.println(formattedList);
        System.out.println("============================================================");

        return input.nextLine();
    }

    @Override
    public String mostrarMenuOpcoesOrientacao(Scanner input) {
        return null;
    }

    @Override
    public String mostrarMenuAcerto(Scanner input, String successMessage) {
        System.out.println("------------------------------------------------------------");
        System.out.println(successMessage);
        System.out.println(" 1 - Weiter");
        System.out.println("------------------------------------------------------------");
        return input.nextLine();
    }

    @Override
    public String mostrarMenuErro(Scanner input, String errorMessage) {
        System.out.println("------------------------------------------------------------");
        System.out.println("                            FEHLER                           ");
        System.out.println(errorMessage);
        System.out.println(" 1 - Weiter");
        System.out.println("------------------------------------------------------------");
        return input.nextLine();
    }

    @Override
    public String pegarMensagemErroLogin() {
        return "Login fehlgeschlagen.";
    }

    @Override
    public String pegarMensagemErroLoginUsuario() {
        return "Benutzer existiert nicht.";
    }

    @Override
    public String pegarMensagemErroLoginSenha() {
        return "Falsches Passwort.";
    }

    @Override
    public String pegarMensagemLoginConcluido() {
        return "Login erfolgreich.";
    }

    @Override
    public String pegarMensagemErroCadastroUsuarioExistente() {
        return "Benutzer bereits registriert.";
    }

    @Override
    public String pegarMensagemErroCadastroSenhaPequena() {
        return "Passwort muss mindestens 8 Zeichen lang sein.";
    }

    @Override
    public String pegarMensagemErroCadastroSenhaSemMaiscula() {
        return "Passwort muss mindestens einen Großbuchstaben enthalten.";
    }

    @Override
    public String pegarMensagemErroCadastroSenhaSemCaracterEspecial() {
        return "Passwort muss mindestens ein Sonderzeichen enthalten.";
    }

    @Override
    public String pegarMensagemCadastroConcluido() {
        return "Registrierung erfolgreich!";
    }

    @Override
    public String mostrarOrientacao(Scanner input, OrientacaoDto dto, String otherLanguages) {
        System.out.println("============================================================");
        System.out.println("                       ORIENTIERUNG                         ");
        System.out.println("============================================================");
        System.out.println(" Titel: " + dto.titulo());
        System.out.println(" Typ: " + dto.tipoOrientacao());
        System.out.println("\n Inhalt:");
        System.out.println(" " + dto.conteudo());
        System.out.println("\n  S - Beenden      E - Bearbeiten      D - Löschen");
        System.out.println(" In anderen Sprachen: ");
        System.out.println(otherLanguages);
        System.out.println("============================================================");

        return input.nextLine();
    }

    @Override
    public String mostrarMenuFiltro(Scanner input, GerenciadorFiltrosOrientacao manager) {
        String choice;

        System.out.println("============================================================");
        System.out.println("                          FILTERS                           ");
        System.out.println("============================================================");
        System.out.println(" 1 - Filter nach Sprache");
        System.out.println(" 2 - Filter nach Orientierungstyp\n");
        System.out.println(" 3 - Aktuelle Filter anzeigen");
        System.out.println(" 4 - Ausgewählte Filter anwenden");
        System.out.println("============================================================");

        choice = input.nextLine();

        switch (choice) {
            case "1" -> {
                System.out.println("\nWählen Sie die Sprachen zum Filtern:");
                System.out.println("P - Portugiesisch");
                System.out.println("I - Englisch");
                System.out.println("E - Spanisch");
                System.out.println("A - Deutsch");
                choice = input.nextLine();
            }
            case "2" -> {
                System.out.println("\nWählen Sie die Orientierungstypen zum Filtern:");
                System.out.println("M - Betriebsanleitung");
                System.out.println("S - Sicherheitsvorschriften");
                System.out.println("R - Wartung und Reparaturen");
                System.out.println("D - Tests und Diagnosen");
                System.out.println("C - Verhaltens- und Betriebsanleitung");
                choice = input.nextLine();
            }
            case "3" -> {
                System.out.println("------------------------------------------------------------");
                System.out.println(manager.formatarFiltrosAtivados());
                System.out.println("------------------------------------------------------------");
                System.out.println(" 1- Zurück");
                String opt = input.nextLine();
                if (opt.equals("2")) {
                    System.out.println("Geben Sie die Filternummer ein: ");
                    return input.nextLine();
                }
            }
        }

        return choice;
    }

    @Override
    public String mostrarMenuPesquisaOrientacao(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                          SUCHEN                            ");
        System.out.println("============================================================");
        System.out.println(" Geben Sie ein Schlüsselwort ein, um nach Orientierung zu suchen:");
        System.out.println("------------------------------------------------------------");

        return input.nextLine();
    }

    @Override
    public String pegarMensangemAdicaoConcluida() {
        return "Hinzufügung abgeschlossen!";
    }

    @Override
    public String pegarMensangemAdicaoFalhada() {
        return "Hinzufügung fehlgeschlagen.";
    }

    @Override
    public String mostrarOrientacao(Scanner input, OrientacaoDto orientacao) {
    	return null;
    }

    @Override
    public String pegarMensagemEdicaoConcluida() {
        return "Bearbeitung abgeschlossen!";
    }

    @Override
    public String pegarNomeIdioma(IdiomaOrientacao idiomaOrientacao) {
        switch(idiomaOrientacao) {
            case PORTUGUES:
                return "Portugiesisch";
            case INGLES:
                return "Englisch";
            case ESPANHOL:
                return "Spanisch";
            case ALEMAO:
                return "Deutsch";
            default:
                return "Unbekannt";
        }
    }

    @Override
    public String pegarFiltroIdioma() {
        return "Filter nach Sprache:";
    }

    @Override
    public String pegarFiltroTipo() {
        return "Filter nach Typ:";
    }

    @Override
    public String mostrarMenuTrocarIdioma(Scanner input, String idiomaFormatados) {
        System.out.println("Wählen Sie die Sprache:");
        System.out.println(idiomaFormatados);
        return input.nextLine();
    }

    @Override
    public String pegarMensagemTrocaDeIdiomaBemSucedida(String idiomaAlterado) {
        return "Sprache erfolgreich geändert zu: " + idiomaAlterado;
    }

}
