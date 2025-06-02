package aplication.implementacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.NivelAcesso;
import Dominio.TipoOrientacao;
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;
import infrastructure.utilitarios.FormatacaoUtil;

public class AlemaoImplementacao implements IdiomaImplementacao {

	@Override
	public IdiomaOrientacao obterIdiomaOrientacao() {
		return IdiomaOrientacao.ALEMAO;
	}

	@Override
	public String mostrarMenuInicial(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                       WILLKOMMEN                           ");
		System.out.println("============================================================");
		System.out.println(" Willkommen im Orientierungsmanagementsystem:              ");
		System.out.println(" 1- Anmelden");
		System.out.println(" 2- Registrieren");
		System.out.println(" 3- Sprache ändern");
		System.out.println(" 4- System beenden");
		System.out.println("------------------------------------------------------------");

		return input.nextLine();
	}

	@Override
	public UsuarioDto mostrarMenuLogin(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                       ANMELDUNG                           ");
		System.out.println("============================================================");
		System.out.println(" Geben Sie Ihre Daten ein:\n");

		System.out.print(" E-Mail:");
		String email = input.nextLine();

		System.out.print(" Passwort:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(null, email, senha, NivelAcesso.ALTERADOR);
	}

	@Override
	public UsuarioDto mostrarMenuCadastro(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                     REGISTRIERUNG                         ");
		System.out.println("============================================================");
		System.out.println(" Geben Sie Ihre Daten ein:\n");

		System.out.print(" Name:");
		String nome = input.nextLine();

		System.out.print(" E-Mail:");
		String email = input.nextLine();

		System.out.print(" Passwort:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(nome, email, senha, NivelAcesso.ALTERADOR);
	}

	@Override
	public String mostrarMenuGeral(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                     MANAGEMENT                            ");
		System.out.println("============================================================");

		System.out.println(" 0- Orientierung erstellen");
		System.out.println(" 1- Orientierungen anzeigen");
		System.out.println(" 2- Abmelden");
		System.out.println(" 3- System beenden");
		System.out.println(" 4- Sprache ändern");

		System.out.println("------------------------------------------------------------");

		return input.nextLine();
	}

	@Override
	public List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) throws Exception {
		List<OrientacaoDto> listaOrientacaoDto = new ArrayList<>();

		System.out.println("============================================================");
		System.out.println("                       ERSTELLUNG                           ");
		System.out.println("============================================================");

		System.out.println(" V- Beenden ");
		System.out.println(" Möchten Sie für nur Ihre Sprache oder für alle erstellen? ");
		System.out.println(" 1- Nur für Deutsch ");
		System.out.println(" 2- Für alle Sprachen ");
		String opcao = input.nextLine();

		if (opcao.trim().toUpperCase().equals("V")) {
			throw new SairMenuException();
		}

		System.out.println(" Orientierungstyp:");
		System.out.println(TipoOrientacao.mostrarTodasTipos(this.obterIdiomaOrientacao()));
		TipoOrientacao tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());
		input.nextLine();

		switch (opcao.toUpperCase()) {
		case "1":
			listaOrientacaoDto.add(mostrarMenuAdicionarOrientacao(input, tipoOrientacao, IdiomaOrientacao.ALEMAO));
			break;
		case "2":
			for (IdiomaOrientacao idioma : IdiomaOrientacao.listarIdiomas()) {
				listaOrientacaoDto.add(mostrarMenuAdicionarOrientacao(input, tipoOrientacao, idioma));
			}
			break;
		}

		return listaOrientacaoDto;
	}

	@Override
	public OrientacaoDto mostrarMenuAdicionarOrientacao(Scanner input, TipoOrientacao tipoOrientacao,
			IdiomaOrientacao idiomaOrientacao) {
		System.out.println("------------------------------------------------------------");
		String idiomaNome = pegarNomeIdioma(idiomaOrientacao);

		System.out.println(" Titel der Orientierung in " + idiomaNome + " :");
		String tituloOrientacao = input.nextLine();

		System.out.println(" Inhalt in " + idiomaNome + " :");
		String conteudo = input.nextLine();

		System.out.println("------------------------------------------------------------");

		return new OrientacaoDto(tituloOrientacao, tipoOrientacao, conteudo, idiomaOrientacao);
	}

	@Override
	public String mostrarMenuOrientacaoDisponiveis(Scanner input, String listaFormatada, String palavraPesquisada) {
		System.out.println("============================================================");
		System.out.println("                   ORIENTIERUNGEN                           ");
		System.out.println("============================================================");
		System.out.println(" V- Zum Hauptmenü zurückkehren");
		System.out.println(" F- Filter                                   P- Suchen     ");

		System.out.println(" A - Suche löschen                           L - Neu laden ");
		System.out.println(" R- Filter entfernen  \n ");
		System.out.println("\n Ergebnisse: " + palavraPesquisada);

		System.out.println(listaFormatada);

		System.out.println("============================================================");
		String orientecaoSelecionada = input.nextLine();

		return orientecaoSelecionada;
	}

	@Override
	public String mostrarMenuOpcoesOrientacao(Scanner input) {
		return null;
	}

	@Override
	public String mostrarMenuAcerto(Scanner input, String mensagemAcerto) {
		System.out.println("------------------------------------------------------------");
		System.out.println(mensagemAcerto);
		System.out.println(" 1- Weiter ");
		System.out.println("------------------------------------------------------------");
		return input.nextLine();
	}

	@Override
	public String mostrarMenuErro(Scanner input, String mensagemErro) {
		System.out.println("------------------------------------------------------------");
		System.out.println("                          FEHLER                            ");
		System.out.println(mensagemErro);
		System.out.println(" 1- Weiter ");
		System.out.println("------------------------------------------------------------");
		return input.nextLine();
	}

	@Override
	public String pegarMensagemErroLogin() {
		return "Anmeldung fehlgeschlagen";
	}

	@Override
	public String pegarMensagemErroLoginUsuario() {
		return "Benutzer existiert nicht";
	}

	@Override
	public String pegarMensagemErroLoginSenha() {
		return "Falsches Passwort";
	}

	@Override
	public String pegarMensagemLoginConcluido() {
		return "Anmeldung erfolgreich";
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
		return "Registrierung erfolgreich abgeschlossen!";
	}

	@Override
	public String mostrarOrientacao(Scanner input, OrientacaoDto orientacao, String idiomasOrientacoes) {
		System.out.println("============================================================");
		System.out.println("                       ORIENTIERUNG                         ");
		System.out.println("============================================================");
		System.out.println(" Titel: " + FormatacaoUtil.enquadrarTextoNoMenu(orientacao.titulo(), 51, 9));
		System.out.println("\n Typ: " + orientacao.tipoOrientacao().getNomeAlemao());
		System.out.println(" Sprache: " + orientacao.idiomaOrientacao().getNomeEmAlemao());

		System.out.println("\n Inhalt:");
		System.out.println(" " + FormatacaoUtil.enquadrarTextoNoMenu(orientacao.conteudo(), 59, 1));
		System.out.println("\n  S- Beenden         E- Bearbeiten             A-Löschen         ");
		System.out.println("------------------------------------------------------------");
		System.out.println("Verfügbar in anderen Sprachen:\n");
		System.out.println(idiomasOrientacoes);
		System.out.println("============================================================");

		return input.nextLine();
	}

	@Override
	public String mostrarMenuPesquisaOrientacao(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                        SUCHE                              ");
		System.out.println("============================================================");
		System.out.println(" S- Beenden ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" Geben Sie Ihre Suche ein: ");
		String pesquisa = input.nextLine();
		System.out.println("============================================================");

		return pesquisa;
	}

	@Override
	public String pegarMensagemEdicaoConcluida() {
		return "Bearbeitung erfolgreich abgeschlossen";
	}

	@Override
	public String pegarMensangemAdicaoConcluida() {
		return "Hinzufügen erfolgreich abgeschlossen";
	}

	@Override
	public String pegarMensangemAdicaoFalhada() {
		return "Fehler beim Hinzufügen der Orientierung";
	}

	@Override
	public String mostrarMenuTrocarIdioma(Scanner input, String idiomaFormatados) {
		System.out.println("============================================================");
		System.out.println("                    SPRACHE ÄNDERN                         ");
		System.out.println("============================================================");
		System.out.println(" S- Beenden\n");
		System.out.println(" Verfügbare Sprachen: ");
		System.out.println(idiomaFormatados);
		System.out.println("============================================================");

		return input.nextLine();
	}

	@Override
	public String pegarMensagemTrocaDeIdiomaBemSucedida(String idiomaAlterado) {
		return "Sprache geändert zu " + idiomaAlterado;
	}

	@Override
	public String pegarMensagemOrientacoesNaoEncontrada() {
		return "Keine Orientierungen gefunden";
	}

	@Override
	public String pegarIdiomaDisponivel() {
		return " Verfügbar: ";
	}

	@Override
	public String pegarIdiomaIndisponivel() {
		return " Nicht verfügbar: ";
	}

	@Override
	public String pegarMensagemErro() {
		return "Etwas ist schief gelaufen, bitte versuchen Sie es erneut";
	}

	@Override
	public String pegarMensagemEdicaoFalha() {
		return "Fehler beim Bearbeiten der Orientierung";
	}

	@Override
	public String pegarMensagemRemoverComSucessoOrientacao() {
		return "Orientierung erfolgreich entfernt";
	}

	@Override
	public String pegarMensagemErroAoRemoverOrientacao() {
		return "Fehler beim Entfernen der Orientierung";
	}

	@Override
	public OrientacaoDto mostrarMenuAdicionarNovoIdiomaOrientacao(Scanner input, IdiomaOrientacao idiomaOrientacao,
			TipoOrientacao tipoOrientacao) throws Exception {
		System.out.println("============================================================");
		System.out.println("                 ORIENTIERUNG NICHT VERFÜGBAR               ");
		System.out.println("============================================================");
		System.out.println(" Die gesuchte Orientierung existiert nicht in dieser Sprache");

		System.out.println("\n H- Hinzufügen in " + idiomaOrientacao.getNomeEmAlemao());
		System.out.println(" V- Zurück");
		System.out.println("============================================================");
		String opcao = input.nextLine();

		return switch (opcao.trim().toUpperCase()) {
		case "V" -> throw new SairMenuException();
		case "H" -> mostrarMenuAdicionarOrientacao(input, tipoOrientacao, idiomaOrientacao);
		default -> null;
		};
	}

	@Override
	public String mostrarMenuConfirmarApagarOrientacao(Scanner input) throws SairMenuException {
		System.out.println("============================================================");
		System.out.println("                     SIND SIE SICHER?                       ");
		System.out.println("============================================================");
		System.out.println(" Möchten Sie diese Orientierung wirklich löschen?");

		System.out.println("\n A- Orientierung löschen ");
		System.out.println(" C- Abbrechen");
		System.out.println("============================================================");
		String opcao = input.nextLine().trim().toUpperCase();

		if (opcao.trim().toUpperCase().equals("C")) {
			throw new SairMenuException();
		}
		return opcao;
	}

	@Override
	public String mostrarMenuFiltro(Scanner input) {
		String opcaoEscolhida;

		System.out.println("============================================================");
		System.out.println("                        FILTER                             ");
		System.out.println("============================================================");
		System.out.println(" V- Zurück ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" 1- Aktive Filter anzeigen");
		System.out.println(" 2- Filter festlegen");
		System.out.println(" 3- Ausgewählte Filter anwenden");
		System.out.println("============================================================");

		opcaoEscolhida = input.nextLine();

		return opcaoEscolhida;
	}

	@Override
	public String mostrarMenuApagarFiltro(Scanner input, String tipoFiltro, String filtrosDisponiveis) {
		System.out.println("============================================================");
		System.out.println("                 FILTER LÖSCHEN " + tipoFiltro.toUpperCase());
		System.out.println("============================================================");
		System.out.println(" V- Zurück ");
		System.out.println(" Wählen Sie den zu löschenden Filter aus");
		System.out.println(filtrosDisponiveis);
		System.out.println("============================================================");

		return input.nextLine();
	}

	@Override
	public String mostrarMenuVisualizarTiposFiltros(Scanner input, String tipoOrientacoesDisponiveis) {
		System.out.println("============================================================");
		System.out.println("                   FILTERTYPEN                              ");
		System.out.println("============================================================");
		System.out.println(" V- Zurück ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" Wählen Sie den gewünschten Filtertyp aus");
		System.out.println(tipoOrientacoesDisponiveis);
		System.out.println("============================================================");
		return input.nextLine();
	}

	@Override
	public String pegarMensagemEntradaInvalida() {
		return "Ungültige Eingabe";
	}

	@Override
	public String mostrarMenuVisualizarFiltros(Scanner input, String filtrosPossiveis, String tipoFiltro) {
		System.out.println("============================================================");
		System.out.println("                  FILTER " + tipoFiltro.toUpperCase());
		System.out.println("============================================================");
		System.out.println(" V- Zurück ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" " + tipoFiltro.toLowerCase() + " Filter");
		System.out.println(filtrosPossiveis);
		System.out.println("============================================================");
		return input.nextLine();
	}

	@Override
	public String mostrarMenuVisualizarFiltrosDisponiveis(Scanner input, String filtroDisponiveis, String tipoFiltro) {
		System.out.println("============================================================");
		System.out.println("                  FILTER " + tipoFiltro.toUpperCase());
		System.out.println("============================================================");
		System.out.println(" V- Zurück ");
		System.out.println(" D- Filter löschen");
		System.out.println("------------------------------------------------------------");
		System.out.println(" " + tipoFiltro.toLowerCase() + " Filter");
		System.out.println(filtroDisponiveis);
		System.out.println("============================================================");
		return input.nextLine();
	}

	@Override
	public String pegarMensagemAdicionadoNovoFiltro(Scanner input) {
		return "Filter hinzugefügt";
	}

	@Override
	public String pegarMensagemFalhaAdicionarFiltro(Scanner input) {
		return "Fehler beim Hinzufügen des Filters";
	}

	@Override
	public String mostrarMenuEditarOrientacao(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                        BEARBEITEN                          ");
		System.out.println("============================================================");
		System.out.println(" Wählen Sie aus, was Sie ändern möchten: \n");

		System.out.println(" 1- Titel der Orientierung");
		System.out.println(" 2- Typ der Orientierung");
		System.out.println(" 3- Inhalt der Orientierung");
		System.out.println(" 4- Sprache ");
		System.out.println(" V- Bearbeitung beenden");
		System.out.println("------------------------------------------------------------");

		return input.nextLine();
	}

	@Override
	public String mostrarMenuMudarTituloOrientacao(Scanner input, String tituloAntigo) {
		System.out.println("============================================================");
		System.out.println("                    TITEL BEARBEITEN                        ");
		System.out.println("============================================================");
		System.out.println(" V- Zurück");
		System.out.println(" Alter Titel: " + tituloAntigo);
		System.out.println("------------------------------------------------------------");
		System.out.print(" Neuer Titel: ");
		return input.nextLine();
	}

	@Override
	public String mostrarMenuMudarConteudoOrientacao(Scanner input, String conteudoAntigo) {
		System.out.println("============================================================");
		System.out.println("                    INHALT BEARBEITEN                       ");
		System.out.println("============================================================");
		System.out.println(" V- Zurück");
		System.out.println(" Alter Inhalt: " + conteudoAntigo);
		System.out.println("------------------------------------------------------------");
		System.out.print(" Neuer Inhalt: ");
		return input.nextLine();
	}

	@Override
	public String mostrarMenuMudarTipoOrientacao(Scanner input, String tipoAntigo, String idiomaFormatados) {
		System.out.println("============================================================");
		System.out.println("                    TYP BEARBEITEN                          ");
		System.out.println("============================================================");
		System.out.println(" V- Zurück");
		System.out.println(" Alter Typ: " + tipoAntigo);
		System.out.println("------------------------------------------------------------");
		System.out.println(" Neuer Typ: ");
		System.out.println(idiomaFormatados);
		return input.nextLine();
	}

	@Override
	public String mostrarMenuMudarIdiomaOrientacao(Scanner input, String idiomaAntigo, String idiomaFormatados) {
		System.out.println("============================================================");
		System.out.println("                    SPRACHE BEARBEITEN                     ");
		System.out.println("============================================================");
		System.out.println(" V- Zurück");
		System.out.println(" Alte Sprache: " + idiomaAntigo);
		System.out.println("------------------------------------------------------------");
		System.out.println(" Neue Sprache: ");
		System.out.println(idiomaFormatados);
		return input.nextLine();
	}

	@Override
	public String mostrarMenuConfirmarEdicao(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                     SIND SIE SICHER?                       ");
		System.out.println("============================================================");
		System.out.println(" Möchten Sie diese Orientierung wirklich bearbeiten?");

		System.out.println("\n A- Orientierung bearbeiten ");
		System.out.println(" C- Abbrechen");
		System.out.println("============================================================");
		String opcao = input.nextLine().trim().toUpperCase();

		return opcao;
	}

	@Override
	public String pegarNomeIdioma(IdiomaOrientacao idiomaOrientacao) {
		return switch (idiomaOrientacao) {
		case PORTUGUES -> "Portugiesisch";
		case INGLES -> "Englisch";
		case ESPANHOL -> "Spanisch";
		case ALEMAO -> "Deutsch";
		default -> "Andere";
		};
	}

	@Override
	public String pegarMensagemIdiomaNaoDisponivel() {
		return "Bearbeitung nicht möglich: Sprache nicht verfügbar";
	}

	@Override
	public String mostrarMenuConfirmarMudancaTipo(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                     SIND SIE SICHER?                       ");
		System.out.println("============================================================");
		System.out.println(" Der Typ wird in allen anderen Orientierungen geändert\n");

		System.out.println(" Möchten Sie den Typ der Orientierung wirklich ändern?");
		System.out.println("\n A- Bestätigen");
		System.out.println(" C- Abbrechen");
		System.out.println("============================================================");
		String opcao = input.nextLine().trim().toUpperCase();

		return opcao;
	}

	@Override
	public void mostrarMenuAlteradoAtributoComSucesso() {
		System.out.println("============================================================");
		System.out.println("                 ERFOLGREICH GEÄNDERT                       ");
		System.out.println("============================================================");
	}

	public String pegarMensagemEmailInvalidoJaUsado() {
	    return " Die eingegebene E-Mail-Adresse wird bereits verwendet";
	}

	@Override
	public String pegarMensagemEmailComSintaxeIncorreta() {
		return " Ungültige E-Mail aufgrund eines falschen Formats \n benutzer@domain";
	}

	@Override
	public String pegarMensagemUsuarioInvalidoLimiteDeCaracters() {
		return " Ungültiger Benutzername\n" + " Überschreitet das Limit von 15 Zeichen";
	}

	@Override
	public String pegarMensagemUsuarioInvalidoEmBranco() {
		return " Ungültiger Benutzername\n" + " Leer oder beginnt mit einem Leerzeichen";
	}

	@Override
	public String pegarMensagemFiltroJaCriado() {
		return " Filter ist bereits vorhanden";
	}
	
	@Override
	public String mostrarMenuOpcaoApagarOrientacao(Scanner input) throws SairMenuException {
	    System.out.println("============================================================");
	    System.out.println("               LÖSCHOPTION AUSWÄHLEN                        ");
	    System.out.println("============================================================");
	    System.out.println("A- Nur auf Portugiesisch löschen");
	    System.out.println("T- In allen Sprachen löschen");
	    System.out.println("C- Abbrechen");
	    String opcao = input.nextLine().trim().toUpperCase();

	    if (opcao.equals("C")) {
	        throw new SairMenuException();
	    }
	    return opcao;
	}

	@Override
	public String pegarMensagemVoltandoMenu() {
		return " Zurückkehren ";
	}

	@Override
	public String pegarMensagemNivelDeAcessoInsuficiente() {
		return " Unzureichende Zugriffsebene";
	}

	@Override
	public String pegarMensagemTituloNaoDisponivel() {
		return " Kann nicht erstellt werden: Titel wird bereits verwendet";
	}


}