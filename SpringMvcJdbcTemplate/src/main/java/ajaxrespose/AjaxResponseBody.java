package ajaxrespose;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import net.codejava.spring.model.Contactdup;
import net.codejava.spring.model.Views;



public class AjaxResponseBody {

      
    
    public List<Contactdup> getResult() {
		return result;
	}

	public void setResult(List<Contactdup> result) {
		this.result = result;
	}
	@JsonView(Views.Public.class)
    String msg;
    
    @JsonView(Views.Public.class)
    String code;
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@JsonView(Views.Public.class)
    List<Contactdup> result;

    //getters and setters
}