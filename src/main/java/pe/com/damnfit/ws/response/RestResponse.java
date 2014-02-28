package pe.com.damnfit.ws.response;

import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RestResponse {

	@XmlElement(name="success")
	private Boolean success;
	
	@XmlElement(name="data")
    private List<?> data;
	
	@XmlElement(name="message")
    private String message;
    
    public RestResponse() {
        this(true, Collections.EMPTY_LIST);
    } 
     
    public RestResponse(boolean success) {
        this(success, Collections.EMPTY_LIST);
    }
     
    public RestResponse(boolean success, List<?> data) {
        this.success = success;
        this.data = data;
    }
    
    public RestResponse(boolean success,String message) {
        this.success = success;
        this.message = message;
    }
    
    public RestResponse(boolean success, List<?> data,String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public Boolean getSuccess()
    {
        return success;
    }

    public void setSuccess(Boolean success)
    {
        this.success = success;
    }

    public List<?> getData()
    {
        return data;
    }

    public void setData(List<?> data)
    {
        this.data = data;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
