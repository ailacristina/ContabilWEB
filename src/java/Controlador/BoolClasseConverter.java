
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("boolClasseConverter")

public class BoolClasseConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        short number = 0;

        try {
            if (value.equals("Yes")) {
                number = 1;
            }
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_FATAL);

            throw new ConverterException(message);
        }
        return number;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        /*if (value == null || ((String) value).isEmpty()) {
            
            return null;
        }
        String tipo = String.valueOf(value);

        if (tipo.equals("true ")) {
            return "Analítica";
        }

        if (tipo.equals("false ")) {
            return "Sintética";
        }*/
        return value.toString();
        
    }
}
