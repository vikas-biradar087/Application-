package com.example.blog.payload;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	
	private String message;
	private boolean scccess;
	
	private Date timestamp;
    private String messagee;
    private String details;

    public ApiResponse(Date timestamp, String messagee, String details) {
        this.timestamp = timestamp;
        this.messagee = messagee;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessagee() {
        return messagee;
    }

    public String getDetails() {
        return details;
    }

	
	
	public ApiResponse(String message, boolean scccess) {
		super();
		this.message = message;
		this.scccess = scccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getScccess() {
		return scccess;
	}
	public void setScccess(Boolean scccess) {
		this.scccess = scccess;
	}
	 
	
}
