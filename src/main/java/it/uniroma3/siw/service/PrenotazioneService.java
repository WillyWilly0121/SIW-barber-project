package it.uniroma3.siw.service;

import it.uniroma3.siw.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PrenotazioneService {
    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    Map<Date,Integer> ottieniNumeroPrenotazioniPerData(Long barberId) {
        HashMap<Date,Integer> result = new HashMap<>();

        for (Object[] tuple: prenotazioneRepository.ottieniNumeroPrenotazioniPerData(barberId)){
            Date data = (Date) tuple[0];
            Integer count= (Integer) tuple[1];

            result.put(data,count);
        }

        return result;
    }
}
