# Locadora de filmes

### Projeto FullStack

 O Projeto de Locação de Filmes é uma aplicação web desenvolvida utilizando as tecnologias Java, Spring e Angular. O objetivo do projeto é fornecer uma plataforma para que os usuários possam reservar filmes, sendo possível como administrador gerenciar o
 aplicação fazendo o controle, O Projeto visa oferecer aos usuários uma experiência prática e conveniente para reservar filmes e gerenciar as operações de locação de forma eficiente. Com base nas regras estabelecidas, o sistema garante que os usuários tenham acesso aos filmes disponíveis e possam realizar as operações de locação com facilidade.

### Funcionalidades Principais 🎥

__Reserva de Filmes:__ Os usuários podem realizar a reserva de filmes tanto de forma presencial na loja física quanto online através da plataforma. O sistema permite que os usuários selecionem o filme desejado e realizem a reserva de forma rápida e prática.

__Cancelamento de Reserva:__ Os usuários têm a opção de cancelar suas reservas caso mudem de ideia ou não possam mais retirar o filme. O sistema permite que os usuários cancelem suas reservas de forma simples.

__Status do Estoque:__ O sistema monitora a quantidade de filmes disponíveis no estoque e atualiza automaticamente o status do estoque de acordo com a quantidade de filmes disponíveis. Por exemplo, o status do estoque pode mudar para "Baixo" se a quantidade de filmes estiver abaixo de um certo limite.

__Disponibilidade do Estoque:__ Antes de realizar a reserva, o sistema verifica a disponibilidade do estoque para garantir que há filmes suficientes para atender à solicitação do usuário.

__Atualização do Estoque:__ O estoque pode ser atualizado para refletir alterações na quantidade de filmes disponíveis. No entanto, se o estoque estiver sendo utilizado por um filme, ele não pode ser atualizado, apenas seu valor pode ser trocado através de operações como alugar e devolução.

__Consulta de Multas e Pagamento:__ Os usuários podem consultar eventuais multas pendentes e escolher a forma de pagamento para quitar as multas.

__Controle de Prazo de Locação:__ O sistema controla o prazo de locação dos filmes e adiciona automaticamente multas caso a devolução do filme seja realizada após o prazo estabelecido.

### __Modelo__ __Relacional__ 🧱

![Uml](https://github.com/AugustoMello09/cursoSpringBackend/assets/101072311/c5778c17-05e0-46c0-9d9b-97fc6efc2260)

### Ferramentas e Tecnologias usadas no Back-end 🧱
<div style="display: inline_block"><br>

<img align="center" alt="Augusto-Java" height="70" width="70" src="https://github.com/devicons/devicon/blob/master/icons/java/java-original.svg">
<img align="center" alt="Augusto-SpringBoot" height="70" width="70" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/spring/spring-original-wordmark.svg">
<img align="center" alt="Augusto-POSTGRESQL" height="60" width="60" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/postgresql/postgresql-original-wordmark.svg">
<img align="center" alt="Augusto-Docker" height="70" width="70" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/docker/docker-original.svg">
<img align="center" alt="Augusto-jwt" height="70" width="70" src="https://jwt.io/img/logo.svg">
<img align="center" alt="Augusto-OAuth2" height="50" width="50" src="https://oauth.net/images/oauth-2-sm.png">
<img align="center" alt="Augusto-H2" height="50" width="50" src="https://user-images.githubusercontent.com/101072311/200666111-2e4878bb-7d5c-4103-a159-fd00d0855a5d.png">
<img align="center" alt="Augusto-Java" height="40" width="40" src="https://static-00.iconduck.com/assets.00/swagger-icon-512x512-halz44im.png">

</div>

### Documentação com swegger

![swagger](https://github.com/AugustoMello09/Locadora/assets/101072311/e5578773-feff-4de7-93de-19756ed5ec0c)

__Acesse a documentação do projeto:__

- __Acesse:__ http://localhost:8080/swagger-ui.html para visualizar os endpoints

### Ferramentas e Tecnologias usadas no Fornt-end ⚙️
<div style="display: inline_block"><br>

<img align="center" alt="Augusto-HTML" height="50" width="50" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/html5/html5-plain.svg">
<img align="center" alt="Augusto-CSS" height="50" width="50" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/css3/css3-original.svg">
<img align="center" alt="Augusto-JAVASCRIP" height="50" width="50" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/javascript/javascript-plain.svg">
<img align="center" alt="Augusto-TYPESCRIPT" height="60" width="60" src="https://img.icons8.com/?size=512&id=nCj4PvnCO0tZ&format=png">
<img align="center" alt="Augusto-ANGULAR" height="50" width="50" src="https://raw.githubusercontent.com/get-icon/geticon/fc0f660daee147afb4a56c64e12bde6486b73e39/icons/angular-icon.svg">

</div> 

## Conta do Administrador 

Login:adm@gmail.com Senha:123456

https://github.com/AugustoMello09/Locadora/assets/101072311/f1953d23-086d-4aa2-8e8a-4c10969b1525

## Conta do Usuário

Login:usuario@gmail.com Senha:123456

https://github.com/AugustoMello09/Locadora/assets/101072311/1811dfa0-136c-42b7-bfc4-9415e993b73c

## Execute o projeto 👁‍🗨

### BackEnd

__Pré-requisitos:__ Java 11, Docker (opcional)

__Clone o repositório do projeto__

~~~~~~
git clone https://github.com/AugustoMello09/Locadora.git
~~~~~~

### Configurando o projeto

__Configurando o ambiente:__

- Abra o arquivo /src/main/resources/application.properties.
- Altere as propriedades para o usuário e senha do seu banco de dados, também é possível usar o banco de dados em memória H2:

~~~~~~
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
~~~~~~

__Opção Docker: Executar com Docker:__

- Certifique-se de que você possui o Docker instalado e configurado corretamente em seu ambiente.
- Execute o comando para puxar a imagem da aplicação:

~~~~~~
docker pull augustomello09/locadora:latest
~~~~~~

__Opção Docker: Executar com Docker:__

- Certifique-se de que você fez o pull corretamente.
- Execute o comando para criar o container com a imagem:

~~~~~~
docker run -d -p 8080:8080 --name locadoraback augustomello09/locadora:latest
~~~~~~

### FrontEnd

__Pré-requisitos:__ Angular 

__Opção 1: Executar 

- Certifique-se de ter o Node.js e o Angular CLI instalados em seu ambiente.
- Navegue até a pasta do projeto front-end:

~~~~~~
cd caminhoDoProjeto/locadorafront
~~~~~~

__Instale as dependências do projeto:__

~~~~~~
npm install
~~~~~~

__Inicie a aplicação:__

~~~~~~
ng serve
~~~~~~

### Entre em contato  

Para mais informações sobre o projeto ou para entrar em contato, você pode me encontrar através dos canais abaixo:

<div style="display: flex">

  <a href="https://www.linkedin.com/in/jos%C3%A9-augusto-mello-794a94234" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
 <a href="mailto:joseaugusto.Mello01@gmail.com" target="_blank"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>   

</div>


