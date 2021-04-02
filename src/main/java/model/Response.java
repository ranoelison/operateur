/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ASUS
 */
public class Response {
    private String status;
	private String message;
	private Object data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Response() {}
	
	public Response(String status, String message, Object data) {
            this.setStatus(status);
            this.setMessage(message);
            this.setData(data);
	}
        public Response(String status, String message) {
            this.status = status;
            this.message = message;
        }
}
