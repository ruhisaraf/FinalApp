package com.example.ruhisaraf.finalapp.Models;

public abstract class Searchable {
    User user = new User();

	public User generateUser(){
        return user;
    };
}

abstract class SearchDecorator extends Searchable {
    public abstract User generateUser();
}

/*
class SearchByEmail extends Search{

    String emailID;

	SearchByEmail(Object email)
	{
		this.emailID = (String) email;
	}

	public User generateUser(User user) {
		user.setEmailID(this.emailID);
        return user;
	}

}
class SearchByGender extends Search{

	String gender;

	SearchByGender(Object gender)
	{
		this.gender = (String) gender;
	}

	public User generateUser(User user) {

		return user;
	}

}
class SearchByName extends Search {

	String name;

	SearchByName(Object name)
	{
		this.name = (String) name;
	}

	public User generateUser(User user) {
        user.setName(this.name);
		return user;
	}

}
class SearchByPay extends Search{

	Integer pay;

	SearchByPay(Object pay)
	{
		this.pay = (Integer) pay;
	}

	public User generateUser(User user) {

		return user;
	}

}
class SearchBySubject extends Search{

	String subject;

	SearchBySubject(Object subject)
	{
		this.subject = (String) subject;
	}

	public User generateUser(User user) {

		return user;
	}

}
class SearchByUniversity extends Search{

	protected String university;

	SearchByUniversity(Object university)
	{
		this.university = (String) university;
	}

	public User generateUser(User user) {

		return user;
	}
}
class SearchByUserType extends Search{

    Integer type;

    SearchByUserType(Object type)
    {
        this.type = (Integer) type;
    }

    public User generateUser(User user) {

        return user;
    }

}
*/


