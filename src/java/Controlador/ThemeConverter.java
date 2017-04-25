package Controlador;

import Modelo.Tabconta;
import DAO.ContaDAO;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter("themeConverter")
public class ThemeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        ContaDAO contadao = new ContaDAO();
        
        if (value != null && value.trim().length() > 0) {
            try {
                int id = Integer.valueOf(value);
                Tabconta conta = contadao.filtrarCt(id);
                return conta;
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Tabconta) object).getIdConta()) ;
        } else {
            return null;
        }
    }

}
