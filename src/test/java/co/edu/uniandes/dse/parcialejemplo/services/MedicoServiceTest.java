package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de lógica de medico
 *
 * @author ISIS2603
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoService.class)
public class MedicoServiceTest {
    @Autowired
    private MedicoService medicoService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<MedicoEntity> medicoList = new ArrayList<>();

    /**
    * Configuración inicial de la prueba.
    */
    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    /**
    * Limpia las tablas que están implicadas en la prueba.
    */
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from medicoEntity").executeUpdate();
    }

    /**
    * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
    */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
            entityManager.persist(medicoEntity);
            medicoList.add(medicoEntity);
        }
    }

    /**
     * Prueba para consultar la lista de medico.
     */
    @Test
    void testGetMedicos() {
        List<MedicoEntity> list = medicoService.getMedicos();
        assertEquals(list.size(), medicoList.size());
    }

    /**
	 * Prueba para consultar un Medico.
	 */
	@Test
	void testGetMedico() throws EntityNotFoundException {
		MedicoEntity entity = medicoList.get(0);
		MedicoEntity resultEntity = medicoService.getMedico(entity.getId());
		assertNotNull(resultEntity);
		assertEquals(entity.getId(), resultEntity.getId());
		assertEquals(entity.getNombre(), resultEntity.getNombre());
		assertEquals(entity.getApellido(), resultEntity.getApellido());
		assertEquals(entity.getRegistro(), resultEntity.getRegistro());
		assertEquals(entity.getEspecialidad(), resultEntity.getEspecialidad());
	}
	
    /**
	 * Prueba para actualizar un Medico.
	 */
	@Test
	void testUpdateMedico() throws EntityNotFoundException {
		MedicoEntity entity = medicoList.get(0);
		MedicoEntity pojoEntity = factory.manufacturePojo(MedicoEntity.class);
		pojoEntity.setId(entity.getId());
		medicoService.updateMedico(entity.getId(), pojoEntity);
        MedicoEntity resp = entityManager.find(MedicoEntity.class, entity.getId());
		assertEquals(pojoEntity.getId(), resp.getId());
		assertEquals(pojoEntity.getNombre(), resp.getNombre());
		assertEquals(pojoEntity.getApellido(), resp.getApellido());
		assertEquals(pojoEntity.getRegistro(), resp.getRegistro());
		assertEquals(pojoEntity.getEspecialidad(), resp.getEspecialidad());
	}

    /**
	 * Prueba para eliminar un Medico.
	 */
	@Test
	void testDeleteMedico() throws EntityNotFoundException {
		MedicoEntity entity = medicoList.get(1);
		medicoService.deleteMedico(entity.getId());
		MedicoEntity deleted = entityManager.find(MedicoEntity.class, entity.getId());
		assertNull(deleted);
	}
	
}
