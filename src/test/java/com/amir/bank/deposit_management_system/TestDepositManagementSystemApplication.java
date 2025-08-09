package com.amir.bank.deposit_management_system;

import org.springframework.boot.SpringApplication;

public class TestDepositManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(DepositManagementSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
