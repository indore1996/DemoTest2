package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class BankController {

	@Autowired
	private BankRepo bankRepo;
	
	@GetMapping
	public Page<Bank> getAllProduct(@PathVariable int pageNo,@PathVariable int size,@RequestParam(defaultValue="id") String sortBy){
		Pageable p = PageRequest.of(pageNo, size,Sort.by(sortBy));
		return bankRepo.findAll(p);
	}
	
	@GetMapping("/{id}")
	public Bank getproductbyId(@PathVariable Long id) throws Exception {
		return bankRepo.findById(id).orElseThrow(()-> new Exception("mo"+id));
	}
	
	@PutMapping("/{id}")
	public Bank updateproduct(@PathVariable Long id, @RequestBody Bank bank) throws Exception {
		Bank ban = bankRepo.findById(id).orElseThrow(()-> new Exception("mo"+id));
		
		ban.setId(bank.getId());
		return bankRepo.save(ban);
	}
	
	@DeleteMapping("/{id}")
	public Bank createproduct(@RequestBody Bank bank) {
		return bankRepo.save(bank);
	}
	
}
