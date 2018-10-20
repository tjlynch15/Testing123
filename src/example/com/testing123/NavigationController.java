package example.com.testing123;

/**
 * Created by terry on 10/17/18
 */

import javax.annotation.PostConstruct;
//import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;


@ManagedBean(name = "navigationController", eager = true)
@RequestScoped

public class NavigationController implements Serializable {

    private String name;
    private String pageId;

    private FacesContext fc = FacesContext.getCurrentInstance();
    private Map<String, String> params = fc.getExternalContext().getRequestParameterMap();


    public String outcome(){

        this.name = getNameParam().toLowerCase();

        return "page1";
    }

    //get value from "f:param"
    public String getNameParam(){

        return params.get("last");
    }


    //get value from "f:param"
    public String getPageIDParam(){

        return params.get("pageID");
    }


    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}





