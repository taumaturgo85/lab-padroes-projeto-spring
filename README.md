
# Lab Padrões de Projeto com Spring Boot

Este projeto demonstra a implementação de padrões de projeto em uma aplicação Spring Boot. Os padrões abordados incluem Singleton, Strategy/Repository e Facade.

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database
- Lombok
- JUnit 5
- Mockito

## Estrutura do Projeto

```
src/main/java/com/taumaturgo/padroesprojetos
├── PadroesProjetosApplication.java
├── config
├── controllers
├── facades
├── models
├── repositories
├── services
└── strategies
```

### Descrição dos Padrões de Projeto

#### 1. Singleton

O padrão Singleton é utilizado para garantir que uma classe tenha apenas uma instância e forneça um ponto global de acesso a ela. Neste projeto, a classe `AppConfig` foi implementada utilizando o padrão Singleton.

**Classe Singleton:**
```java
package com.taumaturgo.padroesprojetos.config;

public class AppConfig {
    private static AppConfig instance;
    private String configValue;

    private AppConfig() {
        this.configValue = "Valor padrão";
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
```

#### 2. Strategy/Repository

O padrão Strategy permite que uma família de algoritmos seja definida e encapsulada de forma que eles sejam intercambiáveis. O padrão Repository abstrai a lógica de acesso a dados. Neste projeto, diferentes estratégias de desconto foram implementadas com o padrão Strategy, e o acesso aos dados de `Produto` foi feito através do padrão Repository.

**Interfaces de Strategy e Implementação:**
```java
package com.taumaturgo.padroesprojetos.strategies;

public interface DescontoStrategy {
    double aplicarDesconto(double valor);
}

@Component
public class DescontoNatalStrategy implements DescontoStrategy {
    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.9; // 10% de desconto
    }
}
```

**Repository:**
```java
package com.taumaturgo.padroesprojetos.repositories;

import com.taumaturgo.padroesprojetos.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
```

#### 3. Facade

O padrão Facade fornece uma interface simplificada para um conjunto de interfaces em um subsistema. Neste projeto, a classe `ProdutoFacade` foi criada para simplificar a interação com o serviço de produtos.

**Classe Facade:**
```java
package com.taumaturgo.padroesprojetos.facades;

import com.taumaturgo.padroesprojetos.models.Produto;
import com.taumaturgo.padroesprojetos.services.ProdutoService;
import com.taumaturgo.padroesprojetos.strategies.DescontoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoFacade {

    @Autowired
    private ProdutoService produtoService;

    public double obterPrecoComDesconto(Long produtoId, DescontoStrategy descontoStrategy) {
        produtoService.setDescontoStrategy(descontoStrategy);
        return produtoService.calcularPrecoComDesconto(produtoId);
    }

    public Produto criarProduto(String nome, double preco) {
        Produto produto = new Produto(null, nome, preco);
        return produtoService.salvarProduto(produto);
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoService.buscarProdutoPorId(id);
    }
}
```

## Testes

Os testes foram implementados utilizando JUnit 5 e Mockito para garantir que cada padrão de projeto e cada classe esteja funcionando corretamente.

### Teste de ProdutoService:
```java
class ProdutoServiceTest {
    // Implementação do teste
}
```

### Teste de ProdutoFacade:
```java
class ProdutoFacadeTest {
    // Implementação do teste
}
```

### Teste de ProdutoController:
```java
class ProdutoControllerTest {
    // Implementação do teste
}
```

## Configuração do `application.properties`

O arquivo `application.properties` foi configurado para utilizar o banco de dados H2 em memória para facilitar o desenvolvimento.

```properties
spring.application.name=lab-padroes-projeto-spring
spring.datasource.url=jdbc:h2:mem:lab-padroes-projeto
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Execução do Projeto

Para executar o projeto, siga os passos abaixo:

1. Clone o repositório.
2. Importe o projeto em sua IDE de preferência (recomendado: IntelliJ IDEA ou Eclipse).
3. Execute a classe `PadroesProjetosApplication`.
4. Acesse o console H2 em `http://localhost:8080/h2-console` para visualizar e manipular os dados.

## Conclusão

Este projeto demonstrou a aplicação prática de três padrões de projeto comuns no desenvolvimento de software, utilizando o framework Spring Boot. As técnicas abordadas são essenciais para a criação de sistemas robustos e de fácil manutenção.
