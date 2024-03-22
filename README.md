# COVID Benchmark API

## Sobre o projeto
O projeto visa permitir a comparação das informações de COVID entre dois Estados brasileiros em uma data especifica. A aplicação, sob demanda, consulta uma API externa para obter os dados dos Estados consultados e realiza a comparação entre eles.

É possível salvar benchmarks realizados, atribuindo o nome e, se necessário, atribuindo uma observação a este benchmark salvo, facilitando consultas futuras.

No momento, a API externa consultada é da [Brasil.io](https://brasil.io/dataset/covid19/caso/), que permite consultar todos os Estados brasileiros entre as datas de 2020-02-25 a 2022-03-27.

## Tecnologias utilizadas
- Java 17
- Spring Boot 3.2.3
- Flyway Migrations
- Banco de dados PostgreSQL

## Endpoints 
### Benchmark
```
GET /api/benchmark/{primeiroEstado}/{segundoEstado}/{data}
```
Realiza a comparação entre dois Estados em uma data especificada.

Exemplo de requisição: **/api/benchmark/GO/ES/2021-01-01**

### Salvar Benchmark
```
POST /api/benchmark/{primeiroEstado}/{segundoEstado}/{data}/salvar
```
**JSON Body**
```
{ "nomeBenchmark": String, "observacao": String }
```
Salva, na base dados, a comparação entre os dois Estados e data especificada. Deve ser informado um nome para este benchmark salvo. Campo 'observacao' é opcional.

Exemplo de requisição: **/api/benchmark/GO/ES/2021-01-01/salvar**

### Listar Benchmarks
```
GET /api/benchmark/listar
```
Lista todos os benchmarks salvos na base de dados.

Exemplo de requisição: **/api/benchmark/listar**

### Listar Benchmark por ID
```
GET /api/benchmark/listar/{id}
```
Lista o benchmark pelo seu ID.

Exemplo de requisição: **/api/benchmark/listar/1**

### Listar Benchmark por nome
```
GET /api/benchmark/listarPorNome
```
**JSON Body**
```
{ "nomeBenchmark": String }
```
Lista o benchmark pelo seu nome. Retornará todos que contém parte do nome informado. Não é case sensitive.

Exemplo de requisição: **/api/benchmark/listarPorNome**

### Editar Benchmark
```
PUT /api/benchmark/editar/{id}
```
**JSON Body**
```
{ "observacao": String }
```
Edita o benchmark. O único atributo que pode ser alterado é a observação.

Exemplo de requisição: **/api/benchmark/editar/1**

### Deletar Benchmark
```
DELETE /api/benchmark/deletar/{id}
```
Deleta o benchmark do ID informado.

Exemplo de requisição: **/api/benchmark/deletar/1**
