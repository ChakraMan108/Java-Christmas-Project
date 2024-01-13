package com.bank.ui;

class AccountMenu
{
	Menu menu;
	
	public AccountMenu()
	{
		MenuOption[] mo = new MenuOption[]{
		
			new MenuOption("Create Account"){
				void executeOption()
				{
					createAccount();
				}
			},
			
			new MenuOption("Delete Account"){
				void executeOption()
				{
					//do whatever needs to be done
				}
			},
			
		};
		menu = new Menu("Manage Accounts", mo);
	}
	
	private void createAccount()
	{
		//do whatever needs to be done
	}
}
