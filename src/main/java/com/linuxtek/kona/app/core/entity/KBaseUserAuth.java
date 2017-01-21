package com.linuxtek.kona.app.core.entity;

import java.util.Date;

public class KBaseUserAuth implements KUserAuth {
	private static final long serialVersionUID = 1L;

	private Long id;
    private Long userId;
    private String password;

    private String pin;
    private String duressPin;

    private String question1;
    private String answer1;
    private String duressAnswer1;

    private String question2;
    private String answer2;
    private String duressAnswer2;
    
    private Date createdDate;
    private Date updatedDate;
    

	@Override
    public Long getId() {
        return id;
    }

	@Override
    public void setId(Long id) {
        this.id = id;
    }

	@Override
    public Long getUserId() {
        return userId;
    }

	@Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
	@Override
    public String getPassword() {
        return password;
    }

	@Override
    public void setPassword(String password) {
        this.password = password;
    }
    
	@Override
    public String getPin() {
        return pin;
    }

	@Override
    public void setPin(String pin) {
        this.pin = pin;
    }

	@Override
    public String getDuressPin() {
        return duressPin;
    }

	@Override
    public void setDuressPin(String duressPin) {
        this.duressPin = duressPin;
    }

	@Override
	public String getQuestion1() {
		return question1;
	}

	@Override
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	@Override
	public String getAnswer1() {
		return answer1;
	}

	@Override
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
		
	}

	@Override
	public String getDuressAnswer1() {
		return duressAnswer1;
	}

	@Override
	public void setDuressAnswer1(String duressAnswer1) {
		this.duressAnswer1 = duressAnswer1;
	}

	@Override
	public String getQuestion2() {
		return question2;
	}

	@Override
	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	@Override
	public String getAnswer2() {
		return answer2;
	}

	@Override
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	@Override
	public String getDuressAnswer2() {
		return duressAnswer2;
	}

	@Override
	public void setDuressAnswer2(String duressAnswer2) {
		this.duressAnswer2 = duressAnswer2;
	}

	@Override
	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public Date getUpdatedDate() {
		return updatedDate;
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
