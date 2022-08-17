Antes de tudo, alterar o application.properties para que consiga acessar tudo corretamente na linha 4, que seria essa
logo abaixo.
```code
spring.datasource.password= alterar senha!
```

Depois disso, criar banco de dados da seguinte forma e com o seguinte nome
```sql
CREATE DATABASE PROJETO_LP1;

USE PROJETO_LP1;
```

o projeto já tem arquivos .sql que inserem dados no  banco logo que a api é
inicializada, por isso a importancia de tudo isso.

para acessar e testar a api, utilize a seguinte url :
```link
http://localhost:8080/swagger-ui/index.html
```

Observações:

-Crie um banco na parte de gerenciamentoBanco

-Para criar contas ou clientes, utilize a senha do banco antes criado, essa criação (contas/clientes) se faz em gerenciamentoAdm

-A criação de contas e clientes é necessária para testar os métodos do caixa automático, pois ele mexe com saque, depósito e transferência, na parte da transferência é necessário no mínimo 2 contas

