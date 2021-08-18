package com.sqli.stage.propertyfilemanager.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.compare.TraitementFile;

@RestController
public class FileController {
	@Autowired
	TraitementFile traitementFile;

	@PostMapping("/uploadFileZip")
	public void  uploadFilezip(@RequestParam("file") MultipartFile file) throws IOException {

		Map<String, List<String>> zipZ = traitementFile.scannedFile(file);

		Map<String, Map<String, String>> Listfinal = traitementFile.splitListe(zipZ);
		Map<String, Map<String, String>> xx= 	traitementFile.addProtertieCommonToSpecifique(Listfinal);
	
		traitementFile.CompareFileProperties(xx);
		

	/*	for (Iterator<Entry<String, Map<String, String>>> iterator = Listfinal.entrySet().iterator(); iterator
				.hasNext();) {
			Entry<String, Map<String, String>> mapentry = iterator.next();
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("name propriete : " + mapentry.getKey());

			Map<String, String> entryValue = (Map<String, String>) mapentry.getValue();
			for (Iterator<Entry<String, String>> iterator2 = entryValue.entrySet().iterator(); iterator2.hasNext();) {
				Entry<String, String> mapelast = iterator2.next();
				System.out.println("key propriete : " + mapelast.getKey());
				System.out.println("value propriete : " + mapelast.getValue());
			}
		}
		
		*/
		
		
		
		
		
		
		
		
		
	}

}
