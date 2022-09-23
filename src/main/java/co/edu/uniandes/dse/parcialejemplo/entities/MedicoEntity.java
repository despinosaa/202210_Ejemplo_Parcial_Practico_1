package co.edu.uniandes.dse.parcialejemplo.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class MedicoEntity  extends BaseEntity {
    //cree la entidad MedicoEntity en la carpeta correspondiente. 
    //Un médico tiene un nombre, un apellido, un registro médico, 
    //una especilidad y un id de tipo Long que representa su llave 
    //primaria.
    private String nombre;
    private String apellido;
    private String registro;
    private String especialidad;
    
}
