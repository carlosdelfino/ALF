# An Algorithm for Fault Location in SDH/WDM Networks 

* Aminadabe B. de Sousa,
* José Neuman de Souza, 
* José Everardo Bessa Maia, 
* Carlos Delfino C. Pinheiro

# **OBS: Carlos Delfino**

Não é novidade para ninguem que desde o desenvolvimento da primeira versão do ALF em Java com Swing, onde eu fui responsável pelo código que foi todo escrito com arvores de decisões baseado em uma estrutura fixa de IFs, que eu tenho buscado novas soluções para uso deste algortimo utilizando novas abordagens, ano passado quando fui apresentado ao WEKA pelo professor Carlos Balos Barros, descobri que o Algortimo C45 ou J48 poderiam ser alternativas plausíveis para uma nova abordagem, substituindo inclusive a abordagem proposta por mim também para se usar Redes Neurais, no caso não irei citar aqui a melhor escolha que já tenho estudado como aplicar por questões óbvias a todos.

No nosso meio acadêmico é prática comum um pesquisador debochar do outro quando uma boa proposta é apresentada e que se apresente melhor que a existente, ou que ele defente, ou mesmo que ele não seja capaz de ver com seus limitados binóculos embaçados pela sua arrogancia disfarçada de boa vontade, numa pura tentativa de descobrir quais são nossas ideias, para depois se apoderar delas, dizendo com anturalidade e dissimulação ser o criador.
----

-THE BEGGIN OF ALL

Version 0.1

--THE FIRST CODE

In this work, we show an algorithm to analyze the information of the alarms 
emitted by the components of an optical network, in the presence of a fault, 
aiming to find which network component is causing the failure. The algorithm 
proposed, named Correlated Fault location Algorithm, CFLA, uses the alarmsÕ 
correlation in order to reduce the list of suspected components shown to the 
network operators. The CFLA is sufficiently robust to work with false alarms, 
lost alarms and components that do not emit alarms when fail. The algorithm is 
described and simulated. The results of the simulations are compared to those 
obtained using another algorithm found in the literature. Index TermsÑAlarms, 
Alarms Correlation, failures, Fault Location, and Networks WDM. 
