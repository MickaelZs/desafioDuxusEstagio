package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.TimeDataDto;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.Comparator;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * OBS ao candidato: PREFERENCIALMENTE, NÃO ALTERE AS ASSINATURAS DOS MÉTODOS!
 * Trabalhe com a proposta pura.
 *
 * @author carlosau
 */
@Service
public class ApiService {

    @Autowired
    private TimeRepository timeRepository;


    public List<Time> todosOsTimes(){
        return timeRepository.findAll();
    }

    public TimeDataDto timeDaData(LocalDate data, List<Time> todosOsTimes){
        List<String> integrantes = todosOsTimes.stream()
                .filter(time -> time.getData().isEqual(data))
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicao -> composicao.getIntegrante().getNome())
                .collect(Collectors.toList());

        TimeDataDto timeData = new TimeDataDto();
        timeData.setData(data);
        timeData.setIntegrantes(integrantes);

        return timeData;
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */


   public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
    Map<Integrante, Long> integranteContagem = todosOsTimes.stream()
            .filter(time -> time.getData().isAfter(dataInicial) || time.getData().isEqual(dataInicial))
            .filter(time -> time.getData().isBefore(dataFinal) || time.getData().isEqual(dataFinal))
            .flatMap(time -> time.getComposicaoTime().stream())
            .collect(Collectors.groupingBy(
                    ComposicaoTime::getIntegrante,
                    Collectors.counting()
            ));

    Map.Entry<Integrante, Long> entry = integranteContagem.entrySet().stream()
            .max(Comparator.comparing(Map.Entry::getValue)) 
            .orElse(null);

    return entry != null ? entry.getKey() : null;
}

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */

      public List<String> timeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        Map<List<String>, Long> timesRepetidos = todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) && !time.getData().isAfter(dataFinal))
                .map(time -> time.getComposicaoTime().stream()
                        .map(composicao -> composicao.getIntegrante().getNome())
                        .sorted()
                        .collect(Collectors.toList()))
                .collect(Collectors.groupingBy(
                        jogadorNomes -> jogadorNomes,
                        Collectors.counting()
                ));

        OptionalLong maxRepeticoes = timesRepetidos.values().stream()
                .mapToLong(Long::longValue)
                .max();

        List<List<String>> timesMaisComuns = timesRepetidos.entrySet().stream()
                .filter(entry -> entry.getValue() == maxRepeticoes.orElse(0L))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (!timesMaisComuns.isEmpty()) {
            // Retorna o primeiro time mais comum (pode haver mais de um com o mesmo número de repetições)
            return timesMaisComuns.get(0);
        } else {
            return List.of(); // Retorna uma lista vazia se não houver times no período
        }
    }

    
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
     public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        
        List<Time> timesNoPeriodo = todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) && !time.getData().isAfter(dataFinal))
                .collect(Collectors.toList());

        if (timesNoPeriodo.isEmpty()) {
            return "Nenhuma função encontrada no período especificado";
        }

        Map<String, Long> contagemFuncoes = timesNoPeriodo.stream()
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicao -> composicao.getIntegrante().getFuncao())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Encontra a função mais comum
        Optional<Map.Entry<String, Long>> entryMaisComum = contagemFuncoes.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if (entryMaisComum.isPresent()) {
            return entryMaisComum.get().getKey();
        } else {
            return "Nenhuma função encontrada no período especificado";
        }
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
      
        List<Time> timesNoPeriodo = todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) && !time.getData().isAfter(dataFinal))
                .collect(Collectors.toList());

        if (timesNoPeriodo.isEmpty()) {
            return "Nenhuma franquia encontrada no período especificado";
        }

        
        Map<String, Long> contagemFranquias = timesNoPeriodo.stream()
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(time -> time.getIntegrante().getFranquia())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        
        Optional<Map.Entry<String, Long>> entryMaisComum = contagemFranquias.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if (entryMaisComum.isPresent()) {
            return entryMaisComum.get().getKey();
        } else {
            return "Nenhuma franquia encontrada no período especificado";
        }
    }



    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Filtra os times que estão dentro do período
        List<Time> timesNoPeriodo = todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) && !time.getData().isAfter(dataFinal))
                .collect(Collectors.toList());

        // Contagem de times por franquia
        Map<String, Long> contagemFranquias = timesNoPeriodo.stream()
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicao -> composicao.getIntegrante().getFranquia())
                .distinct() 
                .collect(Collectors.toMap(
                        franquia -> franquia, 
                        franquia -> timesNoPeriodo.stream()
                                .filter(time -> time.getComposicaoTime().stream()
                                        .anyMatch(composicao -> composicao.getIntegrante().getFranquia().equals(franquia)))
                                .count() 
                ));

        return contagemFranquias;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Filtra os times que estão dentro do período
        List<Time> timesNoPeriodo = todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) && !time.getData().isAfter(dataFinal))
                .collect(Collectors.toList());

       
        List<Integrante> integrantesNoPeriodo = timesNoPeriodo.stream()
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(ComposicaoTime::getIntegrante)
                .collect(Collectors.toList());

        
        Map<String, Long> contagemFuncoes = integrantesNoPeriodo.stream()
                .distinct()
                .collect(Collectors.groupingBy(Integrante::getFuncao, Collectors.counting()));

        return contagemFuncoes;
    }

}
