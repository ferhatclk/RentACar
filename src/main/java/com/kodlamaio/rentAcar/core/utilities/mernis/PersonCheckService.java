package com.kodlamaio.rentAcar.core.utilities.mernis;

import java.rmi.RemoteException;

import com.kodlamaio.rentAcar.entities.concretes.User;

public interface PersonCheckService {
	boolean checkPerson(User user) throws NumberFormatException, RemoteException;
}
