package br.com.otto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otto.entity.Account;
import br.com.otto.entity.AccountDTO;
import br.com.otto.entity.BusinessException;
import br.com.otto.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repo;

	public List<Account> findAll() {

		List<Account> accounts = this.repo.findAll();
		try {
			if (accounts.isEmpty()) {
				throw new RuntimeException("Account is empty");
			}
			return accounts;
		} catch (RuntimeException e) {
			e.getMessage();
		}
		return accounts;
	}

	
	public Account create(AccountDTO dto) {
		
		Account ac = new Account();
		ac.setDeposit(dto.getDeposit());
		ac.setWithdraw(dto.getWithdraw());
		
		
		if(ac.getDeposit() < ac.getWithdraw()) {
			throw new BusinessException("Arroz");
		} 
		
		return this.repo.save(ac);
	}
}
