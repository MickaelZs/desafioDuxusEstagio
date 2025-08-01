# Sistema de Escalação de Times

## Como Inicializar o Projeto
Você pode iniciar a aplicação de uma forma:

Via Maven:

mvn spring-boot:run

### Rotas para Testar
Método	Endpoint	Parâmetros	Descrição
GET	/timeDaData	data=YYYY-MM-DD	Retorna o time com integrantes na data especificada
GET	/integranteMaisUsado	dataInicial=YYYY-MM-DD, dataFinal=YYYY-MM-DD Retorna o integrante mais usado no período
GET	/timeMaisComum	dataInicial e dataFinal	Retorna os integrantes do time mais comum no período
GET	/funcaoMaisComum	dataInicial e dataFinal	Retorna a função mais comum no período
GET	/franquiaMaisFamosa	dataInicial e dataFinal	Retorna a franquia mais comum no período
GET	/contagemPorFranquia	dataInicial e dataFinal	Retorna a contagem de aparições por franquia
GET	/contagemPorFuncao	dataInicial e dataFinal	Retorna a contagem de aparições por função
POST	/cadastro/integrante	JSON com franquia, nome, função	Cadastra um novo integrante
POST	/cadastro/time	JSON com data e lista de integrantes	Cadastra um novo time com integrantes

##### Feedback

Este desafio me proporcionou uma excelente oportunidade para aplicar e aprimorar conceitos importantes. Meu foco principal esteve na modelagem e relacionamento de dados, onde defini as entidades Integrante, Time e ComposicaoTime para construir uma base sólida. A maior parte do tempo foi dedicada à lógica de processamento de dados no ApiService, abordando a filtragem por período (inclusive com parâmetros nulos), a contagem e agregação eficiente de ocorrências e, de forma mais interessante, a identificação do "Time Mais Comum" pela sua composição de integrantes. O foco em testes unitários foi primordial; além de garantir a aprovação dos testes existentes, criei novos casos para diversos cenários, validando a robustez das implementações.