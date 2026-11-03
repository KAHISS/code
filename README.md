# HELLBET
<img src="https://github.com/user-attachments/assets/1c10e624-f3db-4aeb-b69c-9cff73f21abc" alt="imagemHellBet" width="100%" />

**Jogo de cartas inspirado em Blackjack, desenvolvido em Java para terminal.**

Dizem que existe um cassino escondido além das sombras da cidade, um lugar sutil e constante onde as luzes nunca se apagam e qualquer pessoa pode tentar a sorte. Entretanto, poucos conhecem o verdadeiro preço de cruzar aquelas portas: ali, moedas comuns não têm valor, e cada partida é uma aposta direta pela própria alma.

No centro do salão, envolto por uma leve névoa e ostentando um sorriso cortante que parece conhecer todos os seus pecados, está o Dealer. Um anfitrião impecável — que parece testemunhar a queda dos homens desde o início dos tempos — que desafia os desesperados a se sentarem à sua mesa. Suas partidas não envolvem apenas sorte, mas também estratégia, inteligência e a coragem de encarar aquele que nunca perde. Cada carta virada na mesa pode definir seu destino.

Os jogadores recebem fichas, utilizadas para invocar barganhas e habilidades especiais capazes de dobrar as regras e mudar o rumo das partidas. Porém, contra um anfitrião que domina todas as trapaças da criação, nenhuma vantagem é garantida e cada decisão pode ser a última. Bem-vindo ao HELLBET.

Descrição: HELLBET é um jogo de cartas para um jogador, no qual você enfrenta o Dealer em uma sequência de rodadas. Além de decidir quando solicitar uma carta ou parar, é possível utilizar habilidades que consomem fichas e alteram o rumo da partida. O objetivo é reduzir a vida do Dealer a zero antes que ele faça o mesmo com você.

## Funcionalidades
Na versão do código disponibilizada neste repositório, estão implementados:

- **Menu inicial:** iniciar uma partida, personalizar o nome do jogador ou sair do jogo.
- **Partidas por rodadas:** jogador e Dealer começam cada rodada com duas cartas.
- **Baralho embaralhado:** baralho com 52 cartas, distribuídas em quatro naipes.
- **Sistema de vida:** jogador e Dealer começam com 3 pontos de vida. Perder uma disputa ou estourar a pontuação pode custar um ponto.
- **Dealer automatizado:** o Dealer compra cartas enquanto sua pontuação está abaixo de 17, então você compra e ele compra automaticamente.
- **Habilidades especiais:** o jogador começa com 3 fichas, que podem ser gastas para ativar habilidades.
- **Interface de terminal:** menus, cartas, pontuação e opções são apresentados no console.

### Habilidades disponíveis
Cada ativação consome uma ficha:

| Habilidade | Efeito |
|---|---|
| Desfazer última jogada | Remove a última carta da mão do jogador. |
| Recuperar vida | Recupera 1 ponto de vida, respeitando o limite inicial de 3. |
| Causar dano ao Dealer | Retira 1 ponto de vida do Dealer. |
| Forçar compra do Dealer | Faz o Dealer comprar uma carta. |

## Como funciona uma partida

1. No menu inicial, selecione **Iniciar jogo**.
2. O jogador e o Dealer recebem duas cartas.
3. Durante o turno, o jogador pode solicitar outra carta, parar ou usar uma habilidade.
4. Quando a pontuação ultrapassa 21, o participante perde 1 ponto de vida.
5. Ao parar, o jogador encerra suas compras e o Dealer segue sua lógica de compra, buscando atingir pelo menos 17 pontos.
6. A rodada é reiniciada, e a partida continua até que a vida de um dos participantes chegue a zero.

Em caso de empate na pontuação, a rodada é reiniciada sem retirar vida dos participantes.

**Objetivo:** vencer o Dealer reduzindo seus pontos de vida a zero.

## Como executar

### Requisitos

- Java 21 instalado.
- JDK 21 instalado.
- Terminal ou uma IDE Java, como IntelliJ IDEA.

### Pelo IntelliJ IDEA
1. Rodar ```git clone git@github.com:vemser/VS18-Jedi-Masters.git``` na pasta desejada.
1. Abra a pasta do projeto no IntelliJ IDEA.
2. Verifique se um JDK está configurado nas definições do projeto.
3. Abra `src/Main.java` e execute o método `main`.

### Pelo terminal
Execute os comandos a partir da pasta raiz do projeto.

**Prompt de Comando (CMD):**
```cmd
chcp 65001
if not exist out mkdir out
dir /s /B src\*.java > sources.txt
javac -encoding UTF-8 -d out @sources.txt
del sources.txt 
java -cp out Main
```

**Windows (PowerShell):**
```powershell
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
New-Item -ItemType Directory -Force out | Out-Null
$arquivos = Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out $arquivos
java "-Dfile.encoding=UTF-8" -cp out Main
```

**Linux ARCH**
```bash
Primeiro instalar Java 
sudo pacman -S jdk-openjdk 
ou
sudo pacman -S jre-openjdk

Se já tem git:
git clone https://github.com/vemser/VS18-Jedi-Masters.git

Se não:
Entre no repositório 
https://github.com/vemser/VS18-Jedi-Masters.git
Clique em <code> e baixe como zip.

Verifique a pasta que foi baixado e abra ela no terminal
cd <diretorio-da-raiz-do-jogo>

find src -name "*.java" > sources.txt
javac -d out/production/IdeaProjects @sources.txt
rm sources.txt
java -cp out/production/IdeaProjects Main
```

## Controles

### Menu inicial
| Opção | Ação |
|---|---|
| `1` | Iniciar jogo |
| `2` | Customizar nome do jogador |
| `3` | Sair |

### Durante a partida
| Opção | Ação |
|---|---|
| `1` | Solicitar uma carta |
| `2` | Parar |
| `3` | Usar Habilidade |

### Menu de usar habilidades
| Opção | Ação |
|-------|---|
| `1`   | Desfazer última jogada do Jogador |
| `2`   | Recuperar 1 de Vida do Jogador |
| `3`   | Causar 1 de Dano Direto ao Dealer |
| `4`   | Forçar o Dealer a comprar uma carta |
| `5`   | Sair |

No menu de habilidades, escolha a opção correspondente à habilidade desejada.

## Organização do projeto
O código segue uma separação inspirada no padrão **MVC (Model-View-Controller)**:

### Responsabilidade das camadas

- **Model:** contém as entidades e regras relacionadas ao jogo, como cartas, baralho, jogador, Dealer e participantes.
- **View:** apresenta os menus e informações no terminal e recebe as opções digitadas pelo usuário.
- **Controller:** coordena o fluxo do jogo, as rodadas, as ações dos participantes, as habilidades e a condição de vitória.
- **Main:** inicializa o `JogoController` e inicia o menu principal.

### Integrantes:
- Alessandro Pereira Libertador Filho
- Hades Castilho de Barros
- João Matheus Oneta
- Kaique Silva Sousa
- Luis Henrique Amorim da Silva

### Créditos pelos sons usados no jogo
- gameOver: Sound Effect by <a href="https://pixabay.com/pt/users/engyclick-41075976/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=179862">Pankaj Yadav</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=179862">Pixabay</a>

- embaralhamento1: Sound Effect by <a href="https://pixabay.com/pt/users/freesound_community-46691455/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=104313">freesound_community</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=104313">Pixabay</a>

- embaralhamento2: Sound Effect by <a href="https://pixabay.com/pt/users/freesound_community-46691455/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=94662">freesound_community</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=94662">Pixabay</a>

- pegarCarta1: Sound Effect by <a href="https://pixabay.com/pt/users/oxidvideos-37598254/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=522513">Alex</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=522513">Pixabay</a>

- pegarCarta2: Sound Effect by <a href="https://pixabay.com/pt/users/oxidvideos-37598254/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=522516">Alex</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=522516">Pixabay</a>

- ganhaFicha: Sound Effect by <a href="https://pixabay.com/pt/users/liecio-3298866/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=190037">LIECIO</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=190037">Pixabay</a>

- risadaDealer: Sound Effect by <a href="https://pixabay.com/pt/users/kunal_acharjee-43428913/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=268133">Kunal Acharjee</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=268133">Pixabay</a>

-select: Sound Effect by <a href="https://pixabay.com/pt/users/driken5482-45721595/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=236670">Driken Stan</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=236670">Pixabay</a>

- trocarNome: Sound Effect by <a href="https://pixabay.com/pt/users/freesound_community-46691455/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=89197">freesound_community</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=89197">Pixabay</a>

- usarHabilidade: Sound Effect by <a href="https://pixabay.com/pt/users/freesound_community-46691455/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=41265">freesound_community</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=41265">Pixabay</a>

- ganhar: Sound Effect by <a href="https://pixabay.com/pt/users/dragon-studio-38165424/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=450460">DRAGON-STUDIO</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=450460">Pixabay</a>

- dano1: Sound Effect by <a href="https://pixabay.com/pt/users/u_xjrmmgxfru-47169417/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=266316">u_xjrmmgxfru</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=266316">Pixabay</a>

- dano2: Sound Effect by <a href="https://pixabay.com/pt/users/mrfriends-45509135/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=233473">mrfriends</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=233473">Pixabay</a>

- recuperarVida: Sound Effect by <a href="https://pixabay.com/pt/users/freesound_community-46691455/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=89189">freesound_community</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=89189">Pixabay</a>

- desfazJogada: Sound Effect by <a href="https://pixabay.com/pt/users/freesound_community-46691455/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=7184">freesound_community</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=7184">Pixabay</a>

- risadaFinal: Sound Effect by <a href="https://pixabay.com/pt/users/dragon-studio-38165424/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=423671">DRAGON-STUDIO</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=423671">Pixabay</a>

