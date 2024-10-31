package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="user")
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 6231592965701832179L;
	
	protected static final String PK = "user_id";
	
	@Id
	@Column(name="USER_ID", unique=true, nullable=false, length=20)
	private int userId;
	
    @Column(name="USERNAME", unique=true, length=20)
    private String username;
    
    /** The password. */
    @Column(name="PASSWORD", length=68)
    private String password;
    
    /** The entry timestamp. */
    @Column(name="ENTRY_TIMESTAMP")
    private LocalDateTime entryTimestamp;
    
    /** The termination date. */
    @Column(name="TERMINATION_DATE")
    private LocalDate terminationDate;

}
