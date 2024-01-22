# Lombok

## Pré-requisitos

* Pesquisar sobre o conceito de **JavaBeans**
* Conceito de construtores, getters e setters
* Padrão de Builder (criacional)
* Maven
* Adicionar a dependencia do Lombok
* Em caso de usar o Eclipse, precisa realizar um processo de instalação


Minha definição

> Java Beans é uma `orientação` que determina a Convencão, padronização e codificação de classes na linguagem Java.


Trecho do conteúdo na [Wikipedia](https://pt.wikipedia.org/wiki/JavaBeans)

> Para ser considerada como um JavaBean, uma classe precisa seguir algumas convenções de nomenclatura de métodos, construtores e comportamento. Estas convenções permitem a existência de ferramentas que podem utilizar e manipular os JavaBeans.
As convenções definem que a classe:

* implemente a interface java.io.Serializable (que possibilita a persistência e restauração do estado do objeto da classe);
* possua um construtor sem argumentos;
* que as suas propriedades sejam acessíveis através de métodos `get` e `set`, seguindo um padrão de nomenclatura;
* possa conter qualquer método de tratamento de eventos.

#### Links úteis

Builder [refactoring.guru](https://refactoring.guru/pt-br/design-patterns/builder)