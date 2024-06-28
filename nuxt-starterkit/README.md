# pnpm

https://dev.to/trinitypath/conheca-o-pnpm-o-npm-mais-rapido-e-com-melhor-desempenho-2apd

O que é o PNPM?
PNPM é um gerenciador de pacotes alternativo para Node.js, que significa “Performant NPM”.

O principal objetivo do PNPM é manter todos os pacotes em um armazenamento global (centralizado) e usá-los, se necessário, por outros projetos também, criando links físicos para ele.

Vantagens de usar PNPM sobre NPM
Economiza uma enorme quantidade de espaço em disco.
Leva menos tempo para instalar os pacotes.
Possui suporte embutido para repositórios mono.

Instalação
Primeiramente, instalei o pnpm usando o npm executando o comando:

npm i -g pnpm

Podemos testar se o pnpm foi instalado com sucesso executando:

pnpm -v

# nuxt-starterkit

prompt de comando

pnpm dlx nuxi@latest init <project-name>


selecionar a opção: npm

cd <project-name-folder>

npm install

npm run dev

http://localhost:3000/

DevTools: Shift + Alt + D

Quando remove o arquivo app.vue e cria a pasta pages, o sistema de rotas automatica é habilitado


pnpm dlx nuxi@latest init nuxt-primevue

selecionar o gestor de projetos e dependencias

entrar na pasta criada

code .

abre o vs code direto na pasta

npm run dev

se voce baixar o projeto futuramente você precisa realizar o comento npm install ou pnpm install

prime vue
# Using pnpm
pnpm add primevue@4.0.0-rc.2
pnpm add -D @primevue/nuxt-module@4.0.0-rc.2