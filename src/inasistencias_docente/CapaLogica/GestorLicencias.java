
package CapaLogica;

import CapaExeption.GuardarLicencia;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class GestorLicencias {
    public boolean registrarLicencia(String ciDocente, LocalDate inicio, LocalDate fin) {
        
        Date sqlInicio = Date.valueOf(inicio);
        Date sqlFin = Date.valueOf(fin);
        
        GuardarLicencia dao = new GuardarLicencia();
        
        int idDocente = Integer.parseInt(ciDocente);
        
        return dao.registrarLicencia(idDocente, sqlInicio, sqlFin);
    }
}
