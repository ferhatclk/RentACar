package com.kodlamaio.rentAcar.core.utilities.mernis;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.entities.concretes.IndividualCustomer;
import com.kodlamaio.rentAcar.entities.concretes.User;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;
@Service
public class MernisKpsAdapter implements PersonCheckService{
	
	@Override
	public boolean checkPerson(IndividualCustomer user) throws NumberFormatException, RemoteException {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
		boolean result = kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(user.getNationalIdentity()), user.getFirstName().toUpperCase(), user.getLastName().toUpperCase(), user.getBirthDay());
		return result;
	}

}
