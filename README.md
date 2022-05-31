# residencia-20221-api-fornecedor_produto
API REST utilizando Spring Boot e recursos como validação, consumo de api´s externas, entre outros.

## Descrição

Projeto simples de API REST utilizando o framework Spring

## Script do banco de dados 

```bash
create database comercio;

CREATE TABLE public.fornecedor (
  id_fornecedor serial4 NOT NULL,
  cnpj int8 NOT NULL,
  tipo varchar(255) NULL,
  razao_social varchar(255),
  uf varchar(2) NULL,
  telefone varchar(100) NULL,
  email varchar(255) NULL,
  nome_fantasia varchar(255),
  status_situacao varchar(100) NULL,
  bairro varchar(255) NULL,
  logradouro varchar(255) NULL,
  numero int8 NULL,
  complemento varchar(100) NULL,
  cep varchar(10) NULL,
  municipio varchar(255),
  data_abertura timestamp NULL,
  PRIMARY KEY (id_fornecedor)
);

CREATE TABLE public.categoria (
  id_categoria serial4 NOT NULL,
  nome_categoria varchar(255),
  imagem varchar,
  PRIMARY KEY (id_categoria)
);

CREATE TABLE public.produto (
  id_produto serial4 NOT NULL,
  sku varchar(255),
  nome_produto varchar(255),
  id_fornecedor int8 NOT NULL,
  id_categoria int8 NOT NULL,
  PRIMARY KEY (id_produto),
  FOREIGN KEY (id_fornecedor) REFERENCES public.fornecedor(id_fornecedor),
  FOREIGN KEY (id_categoria) REFERENCES public.categoria(id_categoria)
);
```

## Conceitos e recursos utilizados na aplicação

- Tratamento de recursividade infinita;
- Tratamento de exceções (de forma global);
- Validação de dados;
- Documentação com Springdoc;
- Uso de DTO/VO para transição de dados;
- Consumo de API externa;

## Configurando a API para execução

As credenciais para acesso ao banco de dados, o nome do contexto da API, email e senha para envio de email e caminho para salvar dados deverão ser alterados no arquivo application.properties.

## Sobre

- Author - [Alexandre Paixão]
- Author - [Marina Portugal]
- Author - [Pedro Henrique]
- Author - [Ester Baltazar]
- Author - [Sophia Araujo]
- Author - [Kauã Cassiano]

## Licença

GNU GPL
