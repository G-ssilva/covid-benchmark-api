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
**Retorno**
```
{
  "covidUfDtoList": [
    {
      "data": String,
      "uf": String,
      "confirmacoes": long,
      "mortes": long,
      "confirmadosSobreCemMilHabitantes": float,
      "mortesSobreConfirmados": float,
      "possuiDados": boolean
    },
    {
      "data": String,
      "uf": String,
      "confirmacoes": long,
      "mortes": long,
      "confirmadosSobreCemMilHabitantes": float,
      "mortesSobreConfirmados": float,
      "possuiDados": boolean
    }
  ],
  "dadosComparadosDto": {
    "primeiroEstado": String,
    "segundoEstado": String,
    "data": String,
    "diferencaConfirmacoes": long,
    "diferencaMortes": long,
    "diferencaConfirmadosSobreCemMilHabitantes": float,
    "diferencaMortesSobreConfirmados": float
  }
}
```

Realiza a comparação entre dois Estados em uma data especificada.

Exemplo de requisição: **/api/benchmark/GO/MG/2021-01-01**

Exemplo de retorno:
```json
{
  "covidUfDtoList": [
    {
      "data": "2021-01-01",
      "uf": "GO",
      "confirmacoes": 309191,
      "mortes": 6805,
      "confirmadosSobreCemMilHabitantes": 4346.51,
      "mortesSobreConfirmados": 0.02,
      "possuiDados": true
    },
    {
      "data": "2021-01-01",
      "uf": "MG",
      "confirmacoes": 546884,
      "mortes": 12001,
      "confirmadosSobreCemMilHabitantes": 2568.4148,
      "mortesSobreConfirmados": 0.0219,
      "possuiDados": true
    }
  ],
  "dadosComparadosDto": {
    "primeiroEstado": "GO",
    "segundoEstado": "MG",
    "data": "2021-01-01",
    "diferencaConfirmacoes": -237693,
    "diferencaMortes": -5196,
    "diferencaConfirmadosSobreCemMilHabitantes": 1778.0952,
    "diferencaMortesSobreConfirmados": -0.0019
  }
}
```

### Salvar Benchmark
```
POST /api/benchmark/{primeiroEstado}/{segundoEstado}/{data}/salvar
```
**JSON Body**
```
{ 
  "nomeBenchmark": String, 
  "observacao": String 
}
```
**Retorno**
```
{
  "nomeBenchmark": String,
  "observacao": String,
  "id": long,
  "primeiroEstado": String,
  "segundoEstado": String,
  "data": String,
  "dadosComparadosDto": {
    "primeiroEstado": String,
    "segundoEstado": String,
    "data": String,
    "diferencaConfirmacoes": long,
    "diferencaMortes": long,
    "diferencaConfirmadosSobreCemMilHabitantes": float,
    "diferencaMortesSobreConfirmados": float
  }
}
```
Salva, na base dados, a comparação entre os dois Estados e data especificada. Deve ser informado um nome para este benchmark salvo. Campo 'observacao' é opcional.

Exemplo de requisição: **/api/benchmark/GO/MG/2021-01-01/salvar**

Exemplo de body:
```json
{
  "nomeBenchmark": "Benchmark GO-MG",
  "observacao": "Utilizado para documentar"
}
```

Exemplo de retorno:
```json
{
  "nomeBenchmark": "Benchmark GO-MG",
  "observacao": "Utilizado para documentar",
  "id": 1,
  "primeiroEstado": "GO",
  "segundoEstado": "MG",
  "data": "2021-01-01",
  "dadosComparadosDto": {
    "primeiroEstado": "GO",
    "segundoEstado": "MG",
    "data": "2021-01-01",
    "diferencaConfirmacoes": -237693,
    "diferencaMortes": -5196,
    "diferencaConfirmadosSobreCemMilHabitantes": 1778.1,
    "diferencaMortesSobreConfirmados": 0
  }
}
```

### Listar Benchmarks
```
GET /api/benchmark/listar
```
**Retorno**
```
[
  {
    "nomeBenchmark": String,
    "observacao": String,
    "id": long,
    "primeiroEstado": String,
    "segundoEstado": String,
    "data": String,
    "dadosComparadosDto": {
      "primeiroEstado": String,
      "segundoEstado": String,
      "data": String,
      "diferencaConfirmacoes": long,
      "diferencaMortes": long,
      "diferencaConfirmadosSobreCemMilHabitantes": float,
      "diferencaMortesSobreConfirmados": float
    }
  },
  ...
]
```
Lista todos os benchmarks salvos na base de dados.

Exemplo de requisição: **/api/benchmark/listar**

Exemplo de retorno:
```json
[
  {
    "nomeBenchmark": "Benchmark GO-MG",
    "observacao": "Utilizado para documentar",
    "id": 1,
    "primeiroEstado": "GO",
    "segundoEstado": "MG",
    "data": "2021-01-01",
    "dadosComparadosDto": {
      "primeiroEstado": "GO",
      "segundoEstado": "MG",
      "data": "2021-01-01",
      "diferencaConfirmacoes": -237693,
      "diferencaMortes": -5196,
      "diferencaConfirmadosSobreCemMilHabitantes": 1778.1,
      "diferencaMortesSobreConfirmados": 0
    }
  },
  {
    "nomeBenchmark": "Benchmark GO-RJ",
    "observacao": "Utilizado para documentar",
    "id": 2,
    "primeiroEstado": "GO",
    "segundoEstado": "RJ",
    "data": "2021-01-01",
    "dadosComparadosDto": {
      "primeiroEstado": "GO",
      "segundoEstado": "RJ",
      "data": "2021-01-01",
      "diferencaConfirmacoes": -126199,
      "diferencaMortes": -18795,
      "diferencaConfirmadosSobreCemMilHabitantes": 1839.4,
      "diferencaMortesSobreConfirmados": -0.04
    }
  }
]
```

### Listar Benchmark por ID
```
GET /api/benchmark/listar/{id}
```
**Retorno**
```
{
  "nomeBenchmark": String,
  "observacao": String,
  "id": long,
  "primeiroEstado": String,
  "segundoEstado": String,
  "data": String,
  "dadosComparadosDto": {
    "primeiroEstado": String,
    "segundoEstado": String,
    "data": String,
    "diferencaConfirmacoes": long,
    "diferencaMortes": long,
    "diferencaConfirmadosSobreCemMilHabitantes": float,
    "diferencaMortesSobreConfirmados": float
  }
}
```
Lista o benchmark pelo seu ID.

Exemplo de requisição: **/api/benchmark/listar/1**

Exemplo de retorno:
```json
{
  "nomeBenchmark": "Benchmark GO-MG",
  "observacao": "Utilizado para documentar",
  "id": 1,
  "primeiroEstado": "GO",
  "segundoEstado": "MG",
  "data": "2021-01-01",
  "dadosComparadosDto": {
    "primeiroEstado": "GO",
    "segundoEstado": "MG",
    "data": "2021-01-01",
    "diferencaConfirmacoes": -237693,
    "diferencaMortes": -5196,
    "diferencaConfirmadosSobreCemMilHabitantes": 1778.1,
    "diferencaMortesSobreConfirmados": 0
  }
}
```

### Listar Benchmark por nome
```
GET /api/benchmark/listarPorNome
```
**JSON Body**
```
{ 
  "nomeBenchmark": String 
}
```
**Retorno**
```
[
  {
    "nomeBenchmark": String,
    "observacao": String,
    "id": long,
    "primeiroEstado": String,
    "segundoEstado": String,
    "data": String,
    "dadosComparadosDto": {
      "primeiroEstado": String,
      "segundoEstado": String,
      "data": String,
      "diferencaConfirmacoes": long,
      "diferencaMortes": long,
      "diferencaConfirmadosSobreCemMilHabitantes": float,
      "diferencaMortesSobreConfirmados": float
    }
  },
  ...
]
```
Lista o benchmark pelo seu nome. Retornará todos que contém parte do nome informado. Não é case sensitive.

Exemplo de requisição: **/api/benchmark/listarPorNome**

Exemplo de body:
```json
{
  "nomeBenchmark": "benchmark"
}
```
Exemplo de retorno:
```json
[
  {
    "nomeBenchmark": "Benchmark GO-MG",
    "observacao": "Utilizado para documentar",
    "id": 1,
    "primeiroEstado": "GO",
    "segundoEstado": "MG",
    "data": "2021-01-01",
    "dadosComparadosDto": {
      "primeiroEstado": "GO",
      "segundoEstado": "MG",
      "data": "2021-01-01",
      "diferencaConfirmacoes": -237693,
      "diferencaMortes": -5196,
      "diferencaConfirmadosSobreCemMilHabitantes": 1778.1,
      "diferencaMortesSobreConfirmados": 0
    }
  },
  {
    "nomeBenchmark": "Benchmark GO-RJ",
    "observacao": "Utilizado para documentar",
    "id": 2,
    "primeiroEstado": "GO",
    "segundoEstado": "RJ",
    "data": "2021-01-01",
    "dadosComparadosDto": {
      "primeiroEstado": "GO",
      "segundoEstado": "RJ",
      "data": "2021-01-01",
      "diferencaConfirmacoes": -126199,
      "diferencaMortes": -18795,
      "diferencaConfirmadosSobreCemMilHabitantes": 1839.4,
      "diferencaMortesSobreConfirmados": -0.04
    }
  }
]
```

### Editar Benchmark
```
PUT /api/benchmark/editar/{id}
```
**JSON Body**
```
{ 
  "observacao": String 
}
```
Edita o benchmark. O único atributo que pode ser alterado é a observação.

Exemplo de requisição: **/api/benchmark/editar/1**

Exemplo de body:
```json
{
  "observacao": "Observação alterada"
}
```

### Deletar Benchmark
```
DELETE /api/benchmark/deletar/{id}
```
Deleta o benchmark do ID informado.

Exemplo de requisição: **/api/benchmark/deletar/1**

## Melhorias futuras
- Adicionar opção de filtrar a listagem (UF, data, observação, etc)
- Adicionar mais testes unitários, testes de integração, testes E2E e afins
- Adicionar mais exceptions personalizadas, padronizando ao máximo do retorno da API
- Adicionar proteção por token
- Refatorar a aplicação pensando em aplicar melhores práticas, padrões, redução de depedências, redução de responsabilidades e demais melhorias
- Dentre demais ideias que possam surgir para melhorar a aplicação
