package br.com.otto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.otto.entity.Account;
import br.com.otto.entity.AccountDTO;
import br.com.otto.entity.BusinessException;
import br.com.otto.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountService service;

	@GetMapping()
	public List<Account> findAllAccounts() {

		List<Account> accounts = this.service.findAll();


		return accounts;
	}
	
	@PostMapping()
	public ResponseEntity save(@RequestBody AccountDTO dto) {
		
		try {
			return ResponseEntity.ok().body( this.service.create(dto));
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	
	
}
