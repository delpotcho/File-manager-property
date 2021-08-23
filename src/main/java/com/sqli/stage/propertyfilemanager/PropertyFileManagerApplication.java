package com.sqli.stage.propertyfilemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sqli.stage.propertyfilemanager.dto.FileRepository;
import com.sqli.stage.propertyfilemanager.dto.ParametreRepository;
import com.sqli.stage.propertyfilemanager.dto.PropertieRepository;
import com.sqli.stage.propertyfilemanager.dto.StatusRepository;
import com.sqli.stage.propertyfilemanager.dto.ValueRepository;

@SpringBootApplication
public class PropertyFileManagerApplication implements CommandLineRunner {
	@Autowired
	FileRepository fileRepository;
	@Autowired
	PropertieRepository propertieRepository;
	@Autowired
	ValueRepository valueRepo;
	@Autowired
	ParametreRepository parametreRepository;
	@Autowired
	StatusRepository statusRepository;

	public static void main(String[] args) {
		SpringApplication.run(PropertyFileManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		  Propertie propertie; Propertie propertie2; Propertie propertie3; Value value;
//		  File file; File file1; Parametre parametre1; Parametre parametre2; Parametre
//		  parametre3; file = new File(1, "disktop", new Date());
//		  fileRepository.save(file); propertie = new Propertie(1, "property1",
//		  "specifique", file); propertieRepository.save(propertie); propertie2 = new
//		  Propertie(2, "property2", "specifique", file);
//		  propertieRepository.save(propertie2);
//		  List<Parametre> listParam = new ArrayList<>();
//		  parametre1 = new Parametre(1, "name"); parametreRepository.save(parametre1);
//		  parametre2 = new Parametre(2, "port"); parametreRepository.save(parametre2);
//		  parametre3 = new Parametre(3, "password");  parametreRepository.save(parametre3);
//		  listParam.add(parametre1);
//		  listParam.add(parametre2);
//		  listParam.add(parametre3);
//		  String name = "port";
//		  listParam.forEach((v)->{
//			if(  v.getParametrekey().equals(name)) {
//				 System.out.println("touve " +v.getClass());
//			}
//			 
//		  });
//		
//		  
//		  Status status = new Status(1,"oublie"); Status status2 = new
//		  Status(2,"diff"); Status status3 = new Status(3,"normal");
//		  
//		  statusRepository.save(status2); statusRepository.save(status);
//		  statusRepository.save(status3);
//		  
//		  value = new Value("root", parametre3, status, propertie2);
//		  valueRepo.save(value);
//		  

	}

}
