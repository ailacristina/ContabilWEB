package Controlador;

import Modelo.Tabconta;
import DAO.ContaDAO;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("boolTipoConverter")
public class BoolTipoConverter implements Converter {

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
    public String getAsString(FacesContext fc, UIComponent uic, Object modelValue) {
        
       /* if (modelValue == null || ((String) modelValue).isEmpty()) {
            return null;
        }
        String tipo = String.valueOf(modelValue);
        
        if (tipo.equals("true ")) {
            return "Analítica";
        }

        if (tipo.equals("false ")) {
            return "Sintética";
        }
        return tipo;*/
       return modelValue.toString();
    }

}