# mini-autorizador
Desafio técnico de Spring Boot do programa de Reskiling da Solutis.

## O desafio
A descrição completa do desafio pode ser encontrada no arquivo [DESAFIO.md](https://github.com/anatartari/mini-autorizador/blob/main/DESAFIO.md).

## Desenvolvimento

###TDD
Durante o desenvolvimento do projeto foi utilizado a abordagem do TDD (_Test-Driven Development_) para que o codigo
seja feito de maneira limpa e simples, devido ao incentivo de desenvolvimento de pequenos passos por vez, evitanso assim
codigos desnecessários e pela maior confiabilidade do código por ser testado contantemente durante o desenvolvimento.

### Arquitetura

O projeto foi desenvolvido utilizando a arquitetura padrão Spring MVC, esta arquitetura foi escolida por afinidade e
por proporcionar uma redução da complexibilidade do codigo, facilidade de manutenção, separação muito clara entre as 
camadas de visualização e regras de negócios e outras vantagens que esta arquitetura traz. 
<br/><br/>
Sendo assim temos a divisão do projeto nas seguintes camadas:
 * **Controller**: Responsável por lidar com as requisições dos usuarios;
 * **Service**: Camada onde esta localizada a aplicação das regras de negocios;
 * **Repository**: Isola os objetos ou entidades do domínio do código que acessa o banco de dados.

###Desafios Opcionais
O desafio apresentado contou com 2 desafios opcionais:
<br/><br/>
**Construção da solução sem a utilização dos comandos `if`, `continue` e `break`**:<br/>
Para isso o codigo conta com operadores ternarios e switch case, porém isso acaba gerando criações de variaveis que
não necessariamente serão utilizadas, como por exemplo: <br/><br/>
``` Object cartaoValido = cartaoOptional.isPresent() ? true : handleException.throwExcecaoDeValidacao(ValidacoesEnum.CARTAO_INEXISTENTE);```
<br/><br/>
O que torna esta solução dificil de ser usada em ambientes reais de desenvolvimento, alem disso para a utilização do
switch case gerou uma função com o retorno de um `Object` que nunca é usado já que o switch case é utilizado para o
lançamento de exceções personalizadas para o tratamento correto dos erros gerados.
<br/><br/>
Função que utiliza switch case: 
```
public Object throwExcecaoDeValidacao(ValidacoesEnum erro) {
    switch (erro){
        case CARTAO_EXISTENTE:
            throw new CartaoExistenteException();
        case CARTAO_INEXISTENTE:
            throw new CartaoInexistenteException();
        case SENHA_INVALIDA:
            throw new SenhaIncorretaException();
        case SALDO_INSUFICIENTE:
            throw new SaldoInsuficienteException();
        default:
            return null;
    }
}
```

**Prevenção contra transações concorrentes**:<br/>
Esta prevenção foi feita através do uso das Transações no Spring Framework que possibilita o uso de transação,
garantindo:
* Atomicidade: Todos os comandos presentes podem ser concluídos com sucesso ou sem sucesso. Caso uma das operações do
banco de dados falhe havera um _rollback_ em todas as outras operações ja executadas naquela transação.
* Consistência: Representa a consistência de integridade da base de dados.
* Durabilidade: Uma transação após persistida deve continuar persistida e não pode ser apagada por 
falhas no sistema.
* Isolação: Muitas transação podem ser executadas em simultâneo, a isolação garante que cada transação 
é isolada da outra, prevenindo corrupção de dados.
 