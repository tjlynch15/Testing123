package example.com.testing123;

/**
 * Created by terry on 10/17/18
 */


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;


@ManagedBean(name = "navigationController", eager = true)
@RequestScoped

public class NavigationController implements Serializable {

    private String name;
    private int getNameNum = 0;

    private FacesContext fc = FacesContext.getCurrentInstance();
    private Map<String, String> params = fc.getExternalContext().getRequestParameterMap();


    public String outcome(){

        this.name = getNameParam().toLowerCase();

        return "page1";
    }

    //get value from "f:param"
    private String getNameParam(){

        return params.get("last");
    }

    public String getName() {

        System.out.println("getName " + getNameNum);
        getNameNum++;
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}





