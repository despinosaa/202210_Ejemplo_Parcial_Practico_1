package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {
    @Autowired
    MedicoRepository medicoRepository;
    
    /**
    * Obtiene la lista de los registros de Medico.
    *
    * @return Colección de objetos de MedicoEntity.
    */

    @Transactional
    public List<MedicoEntity> getMedicos() {
        log.info("Inicia proceso de consultar todos los medicos");
        return medicoRepository.findAll();
    }

    @Transactional
    public MedicoEntity createMedico(MedicoEntity medico) {
        log.info("Inicia proceso de creación del medico");
        return medicoRepository.save(medico);
    }

    @Transactional
    public MedicoEntity getMedico(Long medicoId) throws EntityNotFoundException {
        log.info("Inicia proceso de consultar el medico con id = {0}", medicoId);
        Optional < MedicoEntity > medicoEntity = medicoRepository.findById(medicoId);
        if (medicoEntity.isEmpty())
            throw new EntityNotFoundException("No se encontro el medico");
        log.info("Termina proceso de consultar el medico con id = {0}", medicoId);
        return medicoEntity.get();
    }
    @Transactional
    public MedicoEntity updateMedico(Long medicoId, MedicoEntity medico) throws EntityNotFoundException {
        log.info("Inicia proceso de actualizar el medico con id = {0}", medicoId);
        Optional < MedicoEntity > medicoEntity = medicoRepository.findById(medicoId);
        if (medicoEntity.isEmpty())
            throw new EntityNotFoundException("No se encontro el medico");
        log.info("Termina proceso de actualizar el medico con id = {0}", medicoId);
        medico.setId(medicoId);
        return medicoRepository.save(medico);
    }
    @Transactional
    public void deleteMedico(Long medicoId) throws EntityNotFoundException {
        log.info("Inicia proceso de borrar el medico con id = {0}", medicoId);
        Optional < MedicoEntity > medicoEntity = medicoRepository.findById(medicoId);
        if (medicoEntity.isEmpty())
            throw new EntityNotFoundException("No se encontro el medico");
            medicoRepository.deleteById(medicoId);
        log.info("Termina proceso de borrar el medico con id = {0}", medicoId);
    }
}
