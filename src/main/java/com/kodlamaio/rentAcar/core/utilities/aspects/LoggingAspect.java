package com.kodlamaio.rentAcar.core.utilities.aspects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAspect {
	JSONArray jsonArray = new JSONArray();
	
	//advice
//	@Around("execution(* com.kodlamaio.rentAcar.bussines.concretes.BrandManager.getById(int))")
//	public void beforeLog(ProceedingJoinPoint proceedingJoinPoint) {
//		try {
//			System.out.println("before logging");
//			proceedingJoinPoint.proceed();
//			System.out.println("after returning");
//		} catch (Throwable e) {
//			System.out.println("after throwing");
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		System.out.println("after finaly");
//	}
	
	@Before("execution(* com.kodlamaio.rentAcar.bussines.concretes.*.*(..))")
	public void beforeLog(JoinPoint joinPoint) {
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		ObjectMapper mapper = new ObjectMapper();
		Object[] parameters = joinPoint.getArgs();
		File file = new File("C:\\logs\\operations.json");
		
		try (FileWriter fileWriter = new FileWriter(file,true)){
			
//			StringBuilder stringBuilder = new StringBuilder();
//			
//			stringBuilder.append("date : " + LocalDate.now()+"\n");
//			stringBuilder.append("className : " + joinPoint.getTarget().getClass().getSimpleName()+"\n");
//			stringBuilder.append("methodName : " + signature.getMethod().getName()+"\n");
//			
//			if(signature.getMethod().getName() != "getAll") {
//				stringBuilder.append("parameters : " + mapper.writeValueAsString(parameters));
//			}
			
			JsonObject jsonObject = Json.createObjectBuilder()
					.add("date : " , LocalDate.now().toString()+'\n') // String
			        .add("className : " , joinPoint.getTarget().getClass().getSimpleName()+"\n")
			        .add("methodName : " , signature.getMethod().getName()+'\n')
			        .add("parameters : " , mapper.writeValueAsString(parameters)+"\n")
	                .build();
			
			jsonArray.add(jsonObject);
			BufferedWriter bWriter = new BufferedWriter(fileWriter);
			bWriter.write(jsonArray.toString());
			bWriter.newLine();
			bWriter.close();
			
			System.out.println(jsonArray);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
		
		
		
		
//		JsonArray jsonArray1= Json.createArrayBuilder()
//				.add(obj)
////		        .add("date : " + LocalDate.now()) // String
////		        .add("className : " + joinPoint.getTarget().getClass().getSimpleName())
////		        .add("methodName : " + signature.getMethod().getName())
////		        .add("parameters : " + joinPoint.getArgs()[0].toString())  // JsonValue
//		        .build();
		
		
		
		//System.out.println(stringBuilder);
		
//		System.out.println("before brand manager");
//		System.out.println(joinPoint.getArgs()[0]);
//		System.out.println(signature.getParameterNames()[0]);
//		System.out.println(joinPoint.getSignature());
	}
	
//	@After("pointCut()")
//	public void afterLog() {
//		System.out.println("after brand manager");
//	}
//	
//	@Pointcut("execution(* com.kodlamaio.rentAcar.bussines.concretes.BrandManager.getById(int))")
//	public void pointCut() {} /// Domy metodu
}
