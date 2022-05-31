# residencia-20221-api-fornecedor_produto
API REST utilizando Spring Boot e recursos como validação, consumo de api´s externas, entre outros.

## Descrição

Projeto simples de API REST utilizando o framework Spring

## Script do banco de dados 

```bash
create table categoria(
id_categoria serial not null,
nome_categoria varchar(50),
primary key(id_categoria)
);

create table endereco(
id_endereco int not null,
logradouro varchar,
complemento varchar,
bairro varchar,
localidade varchar,
uf varchar,
ibge varchar,
primary key(id_endereco)
);

create table fornecedor(
cnpj_fornecedor bigint not null,
situacao varchar,
tipo varchar,
nome varchar,
porte varchar,
abertura varchar,
data_situacao varchar,
motivo_situacao varchar,
email varchar,
natureza_juridica varchar,
ultima_atualizacao varchar,
status varchar,
fantasia varchar,
id_endereco int,
primary key(cnpj_fornecedor),
foreign key(id_endereco) references endereco(id_endereco)
);

create table produto(
id_produto serial not null,
sku varchar,
nome_produto varchar,
cnpj_fornecedor bigint,
id_categoria int not null,
primary key(id_produto),
foreign key(cnpj_fornecedor) references fornecedor(cnpj_fornecedor),
foreign key(id_categoria) references  categoria(id_categoria)
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

As credenciais para acesso ao banco de dados e o nome do contexto da API deverão ser alterados no arquivo application.properties

## Sobre

- Author - [Alexandre Paixão]
- Author - [Kauã Cassiano]

## Licença

GNU GPL
