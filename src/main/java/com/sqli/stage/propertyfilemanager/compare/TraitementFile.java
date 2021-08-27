package com.sqli.stage.propertyfilemanager.compare;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.entities.Parametre;
import com.sqli.stage.propertyfilemanager.entities.Propertie;
import com.sqli.stage.propertyfilemanager.entities.Status;
import com.sqli.stage.propertyfilemanager.entities.Value;
import com.sqli.stage.propertyfilemanager.service.ParametreService;
import com.sqli.stage.propertyfilemanager.service.PropertieService;
import com.sqli.stage.propertyfilemanager.service.StatusService;
import com.sqli.stage.propertyfilemanager.service.ValueService;

@Component
public class TraitementFile {

	@org.springframework.beans.factory.annotation.Value("${propertie.commun.name}")
	private String nameFilePropertyCommun;

	@Autowired
	private StatusService statusService;
	@Autowired
	private ValueService valueService;
	@Autowired
	private PropertieService propertieService;

	@Autowired
	private ParametreService parametreService;

	public TraitementFile() {
		super();

	}

	/*
	 * 
	 * method for scanned file property .
	 * 
	 */

	public Map<String, Properties> scannedFile(MultipartFile file) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
		ZipInputStream zis = new ZipInputStream(bis);
		ZipEntry zipEntry = zis.getNextEntry();
		Properties filePropertie;
		Map<String, Properties> listPropertie = new HashMap<String, Properties>();
		while (zipEntry != null) {
			filePropertie = new Properties();
			filePropertie.load(zis);
			listPropertie.put(zipEntry.getName(), filePropertie);
			zipEntry = zis.getNextEntry();
		}
		zis.close();
		return listPropertie;

	}
	/*
	 * arshive file property
	 */

	public void arshiveFileProperty(MultipartFile file) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
		ZipInputStream zis = new ZipInputStream(bis);
		ZipEntry zipEntry = zis.getNextEntry();
		byte[] buffer = new byte[1024];
		while (zipEntry != null) {
			String filePath = System.getProperty("user.dir") + "/uploadFile" + java.io.File.separator
					+ zipEntry.getName();
			if (!zipEntry.isDirectory()) {
				FileOutputStream fos = new FileOutputStream(filePath);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
			} else {
				java.io.File dir = new java.io.File(filePath);
				dir.mkdir();
			}
			zis.closeEntry();
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();

	}
	/*
	 * ajouter les variables de fichier commun dans les fichiers specifique
	 */

	public Map<String, Properties> addProtertieCommunToSpec(Map<String, Properties> listFile) {
		Map<String, Properties> propertyCommun = new HashMap<String, Properties>();
		Map<String, Properties> propertySpec = new HashMap<String, Properties>();
		listFile.forEach((key, value) -> {
			if (key.matches("(.*)"+nameFilePropertyCommun)) {
				
				System.out.println(key +value);
				propertyCommun.put(key, value);
			} else {
				if (key.matches("(.*).properties")) {
				
				propertySpec.put(key, value);
				}
			}
		});
		propertyCommun.forEach((kCommun, vCommun) -> vCommun.forEach((k, v) ->

		propertySpec.forEach((kspec, vSpec) -> {
			if (!vSpec.containsKey(k)) {
				vSpec.put(k, v);

			}
		})));

		return propertySpec;

	}

//
////	
//	  Compare specific files
//	  
//	 

	public void compareFile(Map<String, Properties> listFileProperty, List<Propertie> prop, List<Parametre> param,
			List<Status> stat) {
		String typeStatus;
		for (Map.Entry<String, Properties> file1Spec : listFileProperty.entrySet()) {
			for (Map.Entry<String, Properties> file2Spec : listFileProperty.entrySet()) {
				if (!file1Spec.getKey().equals(file2Spec.getKey())) {

					for (Entry<Object, Object> propertieSpec2 : file2Spec.getValue().entrySet()) {
						if (file1Spec.getValue().containsKey(propertieSpec2.getKey())) {
							if (!file1Spec.getValue().get(propertieSpec2.getKey()).equals(propertieSpec2.getValue())) {
								typeStatus = "differant";

							} else {
								typeStatus = "normal";
							}
						} else {
							typeStatus = "oublie";
						}
						Propertie propertie = propertieService.searchPropertie(prop, file2Spec.getKey());
						Parametre parametre = parametreService.searchParametre(param, propertieSpec2.getKey().toString());
						Status status = statusService.searchStatus(stat, typeStatus);
						Value value = new Value(propertieSpec2.getValue().toString(), parametre, status, propertie);
						valueService.addValue(value);

					}
				}

			}

		}

	}

}