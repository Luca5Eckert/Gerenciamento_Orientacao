Este é o projeto final da disciplina de Lógica de Programação, no qual foi desenvolvido um sistema chamado WegOne, 
responsável por gerenciar orientações sobre o uso de equipamentos.

Descrição do Projeto
O objetivo do projeto era implementar um sistema com funcionalidades básicas de CRUD (Criar, Ler, Atualizar e Deletar) para gerenciar orientações. 
Um dos principais requisitos definidos pelo professor foi a disponibilidade do sistema em quatro idiomas:
Português (Brasil)
Inglês
Espanhol
Alemão

O foco principal do projeto está na lógica de programação aplicada no backend,
conforme os conteúdos abordados na disciplina. Por isso, a interface foi inteiramente feita via terminal, 
permitindo concentrar os esforços no raciocínio lógico, estruturação do código e flexibilidade.

```
Gerenciamento_Orientacao
├── .gitignore
├── README.md
├── bin
│   └── .gitignore
├── pom.xml
├── src
│   ├── App.java
│   ├── Dominio
│   │   ├── Filtro.java
│   │   ├── IdiomaOrientacao.java
│   │   ├── NivelAcesso.java
│   │   ├── Orientacao.java
│   │   ├── OrientacaoId.java
│   │   ├── TipoOrientacao.java
│   │   └── Usuario.java
│   ├── aplication
│   │   ├── IdiomaFactory.java
│   │   ├── MenuFactory.java
│   │   ├── MenuGerenciador.java
│   │   ├── MenuHistorico.java
│   │   ├── Sistema.java
│   │   ├── implementacoes
│   │   │   ├── AlemaoImplementacao.java
│   │   │   ├── EspanholImplementacao.java
│   │   │   ├── IdiomaImplementacao.java
│   │   │   ├── InglesImplementacao.java
│   │   │   └── PortuguesImplementacao.java
│   │   └── interfaces
│   │       ├── Executor.java
│   │       ├── Menu.java
│   │       ├── MenuAdicaoOrientacao.java
│   │       ├── MenuAdicionarIdiomaOrientacao.java
│   │       ├── MenuApagarOrientacao.java
│   │       ├── MenuCadastro.java
│   │       ├── MenuCerto.java
│   │       ├── MenuDefinirFiltro.java
│   │       ├── MenuEditarOrientacao.java
│   │       ├── MenuExibirFiltros.java
│   │       ├── MenuExibirOrientacoes.java
│   │       ├── MenuFalha.java
│   │       ├── MenuFiltroGeral.java
│   │       ├── MenuGeral.java
│   │       ├── MenuHistoricoComando.java
│   │       ├── MenuInicial.java
│   │       ├── MenuIniciarSistema.java
│   │       ├── MenuLogin.java
│   │       ├── MenuPesquisaOrientacao.java
│   │       ├── MenuTrocaIdioma.java
│   │       ├── MenuVisualizarOrientacao.java
│   │       ├── TipoMenu.java
│   │       ├── exceptions
│   │       │   ├── OrientacaoNaoDisponivelIdiomaException.java
│   │       │   └── SairMenuException.java
│   │       └── teste
│   │           └── menuTeste01.java
│   ├── dtos
│   │   ├── OrientacaoDto.java
│   │   └── UsuarioDto.java
│   ├── infrastructure
│   │   ├── ConexaoFactory.java
│   │   ├── dao
│   │   │   ├── OrientacaoDAO.java
│   │   │   ├── RegistroComandoDAO.java
│   │   │   ├── RegistroLoginDAO.java
│   │   │   └── UsuarioDAO.java
│   │   ├── security
│   │   │   └── UsuarioSecurity.java
│   │   └── utilitarios
│   │       └── FormatacaoUtil.java
│   ├── repositorio
│   │   ├── OrientacaoRepositorio.java
│   │   ├── UsuarioRepositorio.java
│   │   └── testes
│   │       └── UsuarioRepositorioteste01.java
│   └── service
│       ├── CadastroService.java
│       ├── CriadorSessao.java
│       ├── LoginService.java
│       ├── OrientacaoService.java
│       ├── SessaoUsuario.java
│       ├── UsuarioService.java
│       ├── Validacao.java
│       ├── Validacoes.java
│       ├── ValidarEmail.java
│       ├── ValidarSenha.java
│       ├── ValidarUsuario.java
│       ├── commandos
│       │   ├── Comando.java
│       │   ├── ComandoAdicionarIdiomaOrientacao.java
│       │   ├── ComandoAdicionarOrientacao.java
│       │   ├── ComandoEditarOrientacao.java
│       │   ├── ComandoHistorico.java
│       │   ├── ComandoRemoverOrientacao.java
│       │   ├── ExecutadorComando.java
│       │   ├── RegistroComando.java
│       │   └── TiposComando.java
│       ├── exceptions
│       │   ├── ComandoException.java
│       │   ├── ComandoHistoricoException.java
│       │   ├── FiltroJaAdicionadoException.java
│       │   ├── NivelDeAcessoInsuficienteException.java
│       │   ├── orientacao
│       │   │   ├── OrientacaoException.java
│       │   │   └── TituloNaoDisponivelException.java
│       │   └── usuario
│       │       ├── CadastroException.java
│       │       ├── CadastroSenhaException.java
│       │       ├── CadastroUsuarioJaExistenteException.java
│       │       ├── EmailInvalidoException.java
│       │       ├── LoginException.java
│       │       ├── LoginSenhaException.java
│       │       └── LoginUsuarioException.java
│       ├── filtros
│       │   ├── FiltroFactory.java
│       │   ├── FiltroOrientacao.java
│       │   ├── FiltroOrientacaoIdioma.java
│       │   ├── FiltroOrientacaoTipo.java
│       │   ├── GerenciadorFiltrosOrientacao.java
│       │   └── TipoFiltro.java
│       ├── formatacao
│       │   ├── FormatacaoListaComDivisoria.java
│       │   └── FormatacaoNumerarLista.java
│       ├── pesquisas
│       │   ├── PesquisaFactory.java
│       │   ├── PesquisaOrientacao.java
│       │   ├── PesquisaOrientacaoConteudo.java
│       │   ├── PesquisaOrientacaoTitulo.java
│       │   └── testes
│       │       └── PesquisaOrientacaoTeste01.java
│       └── testes
│           └── ValidarEmailTeste.java
└── target

```
